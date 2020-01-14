package fr.umontpellier.iut.conquest.strategies;

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
            int value = Integer.MIN_VALUE;
            for(Move move : board.getValidMoves(player)){
                value = Math.max(value, minimax(player,new Board(board.deepCopyField()), move, level-1, false));
            }
            return value;

        }
        else{
            int value = Integer.MAX_VALUE;
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
        int drawValue = Integer.MIN_VALUE;
        int currentDrawValue;
        Move bestMove = null;
        for(Move move : board.getValidMoves(player)){
            currentDrawValue = minimax(player, new Board(board.deepCopyField()), move, level, false);
            if(currentDrawValue> drawValue){
                drawValue = currentDrawValue;
                bestMove = move;
            }
        }
        return bestMove;
    }

}
