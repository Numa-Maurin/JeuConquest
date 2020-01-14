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

    public int minimax(Player player, Board board, int level, Move move0){
        board.movePawn(move0);
        if(level == 0 || player.getGame().isFinished()){
            return  board.getNbPawns(player)-board.getNbPawns(player.getGame().getOtherPlayer(player));
        }
        if(level % 2 != 0){
            int value = -100000;
            for(Move move : board.getValidMoves(player)){
                value = Math.max(value, minimax(player,board, level-1, move));
            }
            return value;

        }
        else{
            int value = 100000;
            for(Move move : board.getValidMoves(player.getGame().getOtherPlayer(player))){
                value = Math.min(value, minimax(player,board, level-1, move));
            }
            return value;
        }
    }

    /**
     * Retourne un coup valide à partir du niveau d'intelligence.
     */
    public Move getMove(Board board, Player player) {
        for(Move move : board.getValidMoves(player)){
            if(minimax(player, new Board(board.deepCopyField()), level, move) == (board.nbPanwsChanged(move, player)+board.getNbPawns(player))){
                return move;
            }
        }
        return null;
    }

}
