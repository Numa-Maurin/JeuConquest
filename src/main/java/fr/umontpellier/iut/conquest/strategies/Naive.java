package fr.umontpellier.iut.conquest.strategies;

import fr.umontpellier.iut.conquest.Board;
import fr.umontpellier.iut.conquest.Move;
import fr.umontpellier.iut.conquest.Player;

public class Naive implements Strategy{

    public Naive (){};

    /** doit retourner un coup valide**/

    public Move getMove(Board board, Player player) {
        if (player != null && board != null) {
            if (!board.getValidMoves(player).isEmpty()) {
                return board.getValidMoves(player).get(0);
            }
            else {
                return null;
            }
        }
        else {
            return null;
        }
    }

}
