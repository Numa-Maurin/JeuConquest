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

    public int minimax(Player player, Board board){
        return maxTurn(board, player) - minTurn(board, player);
    }

    public int maxTurn(Board board, Player player){
        int max = 0;
        //Détermine le max
        for (Move moveBis : board.getValidMoves(player)) {
            if(max < board.nbPanwsChanged(moveBis)){
                max = board.nbPanwsChanged(moveBis);
            }

        }
        return max;

    }

    public int minTurn(Board board, Player player){
        int min = 0;
        for (Move moveBis : board.getValidMoves(player)) {
            if(min < board.nbPanwsChanged(moveBis)){
                min = board.nbPanwsChanged(moveBis);
            }
        }
        return min;
    }

    /**
     * Retourne un coup valide à partir du niveau d'intelligence.
     */
    public Move getMove(Board board, Player player) {
        int bestChoice;
        if (this.level == 1){
            return getBestMove(minimax(player, board), board, player);
        }
        if (this.level == 2){


        }
        if (this.level == 3){

        }
        if (this.level == 4){

        }
        //Move move = new Move(1,2,3,4);
        return null;
    }

    public Move getBestMove(int numberBestChoice, Board board, Player player){
        //Bouge le premier qui correspond au meilleur choix
        for(Move move1 : board.getValidMoves(player)){
            if(numberBestChoice == board.nbPanwsChanged(move1)){
                return move1;
            }
        }
        return null;
    }

}
