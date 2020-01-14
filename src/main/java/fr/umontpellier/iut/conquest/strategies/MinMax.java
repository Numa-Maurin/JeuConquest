package fr.umontpellier.iut.conquest.strategies;

function minimax(node, depth, maximizingPlayer) is
        if depth = 0 or node is a terminal node then
        return the heuristic value of node
        if maximizingPlayer then
        value := −∞
        for each child of node do
        value := max(value, minimax(child, depth − 1, FALSE))
        return value
        else (* minimizing player *)
        value := +∞
        for each child of node do
        value := min(value, minimax(child, depth − 1, TRUE))
        return value

import fr.umontpellier.iut.conquest.Board;
import fr.umontpellier.iut.conquest.Move;
import fr.umontpellier.iut.conquest.Pawn;
import fr.umontpellier.iut.conquest.Player;
import java.util.ArrayList;
import java.util.List;

/**
 * Modélise la stratégie MinMax.
 */

public class Minmax implements Strategy {
    /**
     * Constructeur.
     */
    int level;

    public Minmax(int level){
        this.level = level;
    }

    public int minimax(Player player, Board board, Move move0, int level, boolean currentPlayer){
        board.movePawn(move0);
        if(level == 1 ||board.getValidMoves(player).isEmpty()||player.getGame().isFinished()){
            return  board.getNbPawns(player)-board.getNbPawns(player.getGame().getOtherPlayer(player));
        }
        if(currentPlayer){
            int value = -10000000;
            for(Move move : board.getValidMoves(player)){
                value = Math.max(value, minimax(player,new Board(board.deepCopyField()), move, level-1, false));
            }
            return value;

        }
        else{
            int value = 10000000;
            for(Move move : board.getValidMoves(player.getGame().getOtherPlayer(player))){
                value = Math.min(value, minimax(player,new Board(board.deepCopyField()), move, level-1, true));
            }
            return value;
        }
    }

    /**
     * Retourne un coup valide à partir du niveau d'intelligence.
     */
    public Move getMove(Board board, Player player) {
        int drawValue = 0;
        int currentDrawValue = 0;
        Move bestMove = board.getValidMoves(player).get(0);
        for(Move move : board.getValidMoves(player)){
            currentDrawValue = minimax(player, new Board(board.deepCopyField()), move, level, true);
            if(currentDrawValue> drawValue){
                drawValue = currentDrawValue;
                bestMove = move;
            }
        }
        return bestMove;
    }

}
