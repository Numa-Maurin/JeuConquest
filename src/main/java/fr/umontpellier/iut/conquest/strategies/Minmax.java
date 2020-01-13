package fr.umontpellier.iut.conquest.strategies;


import fr.umontpellier.iut.conquest.Board;
import fr.umontpellier.iut.conquest.Move;
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

    public int minmax(Player player, Board board){
        return board.getNbPawns(player) - board.getNbPawns(player.getGame().getOtherPlayer(player));
    }

    /**
     * Retourne un coup valide à partir du niveau d'intelligence.
     */
    public Move getMove(Board board, Player player) {
        Move move;
        int max = 0;
        if (this.level == 1){
            //Détermine le max
            for (Move moveBis : board.getValidMoves(player)) {
                if(max < board.nbPanwsChanged(moveBis)){
                    max = board.nbPanwsChanged(moveBis);
                }

            }
            //Bouge le premier qui correspond au max
            for(Move move1 : board.getValidMoves(player)){
                if(max == board.nbPanwsChanged(move1)){
                    return move1;
                }
            }
        }
        if (this.level == 2){


        }
        if (this.level == 3){

        }
        if (this.level == 4){

        }
        //Move move = new Move(1,2,3,4);

        int min = 0;
        for (Move moveBis : board.getValidMoves(player)) {
            if(min < board.nbPanwsChanged(moveBis)){
                min =  board.nbPanwsChanged(moveBis);
                break;
            }
        }
        return null;

    }

}
