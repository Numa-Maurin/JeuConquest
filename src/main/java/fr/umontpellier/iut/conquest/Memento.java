package fr.umontpellier.iut.conquest;

import fr.umontpellier.iut.conquest.strategies.Strategy;

import java.util.Scanner;

public class Memento {
    /**
     * Scanner permettant de lire les entrées.
     */
    private static Scanner scan;
    /**
     * Le plateau de jeu.
     */
    private Board board;
    /**
     * Les joueurs.
     */
    private Player[] players = new Player[2];

    public Memento(int size, Strategy strategy1, String name1, Strategy strategy2, String name2) {
        board = new Board(size);
        //players[0] = new Player(strategy1, this, name1, 1);
        //players[1] = new Player(strategy2, this, name2, 2);
    }
}
