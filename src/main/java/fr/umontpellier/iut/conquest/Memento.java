package fr.umontpellier.iut.conquest;

import fr.umontpellier.iut.conquest.strategies.Strategy;

import java.util.Scanner;

public class Memento {
    private Pawn[][] field;

    /**
     * @param field
     */
    public Memento(Pawn[][] field) {
        this.field = new Pawn[field.length][field.length];
        for(int i = 0; i < field.length; i++){
            for(int j = 0; j < field.length; j++){
                if(!(field[i][j] == null)) {
                    this.field[i][j] = new Pawn(field[i][j].getPlayer());
                }
            }
        }
    }

    /**
     * @return the field
     */
    public Pawn[][] getField() {
        return field;
    }

    public String toString() {
        int size = field.length;
        StringBuilder b = new StringBuilder();
        for (int r = -1; r < size; r++) {
            for (int c = -1; c < size; c++) {
                if (r == -1 && c == -1) {
                    b.append("_");
                } else if (r == -1) {
                    b.append("_").append(c);
                } else if (c == -1) {
                    b.append(r).append("|");
                } else if (field[r][c] == null) {
                    b.append("_ ");
                } else if (field[r][c].getPlayer().getColor() == 1) {
                    b.append("X ");
                } else {
                    b.append("O ");
                }
            }
            b.append("\n");
        }
        b.append("---");
        return b.toString();
    }
}
