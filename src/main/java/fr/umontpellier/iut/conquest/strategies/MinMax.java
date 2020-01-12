package fr.umontpellier.iut.conquest.strategies;

import fr.umontpellier.iut.conquest.Board;
import fr.umontpellier.iut.conquest.Move;
import fr.umontpellier.iut.conquest.Player;

import java.util.Scanner;

/**
 * Modélise la stratégie MinMax.
 */

    public class MinMax implements Strategy {
        /**
         * Scanner de l'entrée.
         */
        private Scanner scan;

        /**
         * Constructeur.
         */
        public MinMax(){};

        /**
         * Retourne un coup valide à partir de l'entrée utilisateur.
         */
        public Move getMove(Board board, Player player) {
            Move move = new Move(1,2,3,4);
            return move;
        }

    }
