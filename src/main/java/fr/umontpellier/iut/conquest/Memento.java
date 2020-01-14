package fr.umontpellier.iut.conquest;

import fr.umontpellier.iut.conquest.strategies.Strategy;

import java.util.Scanner;

public class Memento {
    private Pawn[][] field;

    /**
     * @param field
     */
    public Memento(Pawn[][] field) {
        this.field = field;
    }

    /**
     * @return the field
     */
    public Pawn[][] getField() {
        return field;
    }
}
