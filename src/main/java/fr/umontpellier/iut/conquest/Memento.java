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

    public Memento(Board board) {

    }
}
