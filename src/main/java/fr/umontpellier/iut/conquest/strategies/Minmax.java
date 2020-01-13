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

    /**
     * Retourne un coup valide à partir du niveau d'intelligence.
     */
    public Move getMove(Board board, Player player) {
        List l = new ArrayList<>();
        int nbPawns;
        int max;
        if (this.level == 1){
            for (Move move: board.getValidMoves(player)) {
                nbPawns = board.getNbPawns(player);
                l.add(nbPawns);
            }
            for (int i=0; i<l.size(); i++){
                int depart = 0;
                if (depart < l.get(i)){
                    depart = l.get(i);
                    max = i;
                }
            }
            return board.getValidMoves(player).get(max);
        }
        if (this.level == 2){

        }
        if (this.level == 3){

        }
        if (this.level == 4){

        }
        Move move = new Move(1,2,3,4);
        return move;
    }

}
