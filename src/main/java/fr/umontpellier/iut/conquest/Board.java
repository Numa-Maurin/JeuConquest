package fr.umontpellier.iut.conquest;

import java.util.ArrayList;
import java.util.List;

/**
 * Modélise un plateau.
 */
public class Board {
    /**
     * Tableau des pions.
     */
    private Pawn[][] field;

    /**
     * Constructeur.
     *
     * @param size : la taille du plateau.
     */
    public Board(int size) {
        field = new Pawn[size][size];
    }

    /**
     * Constructeur pour Test.
     *
     * @param field : plateau prédéfini.
     */
    public Board(Pawn[][] field) {
        this.field = field;
    }

    /**
     * Les méthodes suivantes sont utilisées pour les tests automatiques. Il ne faut pas les utiliser.
     */
    public Pawn[][] getField() {
        return field;
    }

    /**
     * Retourne la taille du plateau.
     */
    public int getSize() {
        return field.length;
    }

    /**
     * Affiche le plateau.
     */
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

    /**
     * Initialise le plateau avec les pions de départ.
     * Rappel :
     * - player1 commence le jeu avec un pion en haut à gauche (0,0) et un pion en bas à droite.
     * - player2 commence le jeu avec un pion en haut à droite et un pion en bas à gauche.
     */
    public void initField(Player player1, Player player2) {
        field[0][0] = new Pawn(player1);
        field[field.length-1][field.length-1] = new Pawn(player1);
        field[0][field.length-1] = new Pawn(player2);
        field[field.length-1][0] = new Pawn(player2);
    }

    /**
     * Vérifie si un coup est valide.
     * Rappel :
     * - Les coordonnées du coup doivent être dans le plateau.
     * - Le pion bougé doit appartenir au joueur.
     * - La case d'arrivée doit être libre.
     * - La distance entre la case d'arrivée est au plus 2.
     */
    public boolean isValid(Move move, Player player) {
        if (move != null) {
            return coordonnesInField(move) && pawnAndPlayers(player, move.getRow1(), move.getColumn1()) && caseFree(move) && distanceLessThan2(move);
        }
        return false;
    }

    public boolean coordonnesInField (Move move){
        return move.getColumn2() <= field.length - 1 && move.getColumn1() <= field.length - 1 && move.getColumn2() >= 0 && move.getColumn1() >= 0 && move.getRow2() <= field.length - 1 && move.getRow1() <= field.length - 1 && move.getRow2() >= 0 && move.getRow1() >= 0;
    }

    public boolean pawnAndPlayers(Player player, int i, int j ){
        if(field[i][j] != null){
            return player.getColor() == field[i][j].getPlayer().getColor();
        }
        return false;
    }

    public boolean caseFree(Move move){
        return caseIsEmpty(move.getRow2(), move.getColumn2());
    }

    public boolean distanceLessThan2(Move move){
        if (nbMovingMin(move ) <= 2 ){
            return true;
        }
        return false;
    }

    /**
     * Déplace un pion.
     *
     * @param move : un coup valide.
     *             Rappel :
     *             - Si le pion se déplace à distance 1 alors il se duplique pour remplir la case d'arrivée et la case de départ.
     *             - Si le pion se déplace à distance 2 alors il ne se duplique pas : la case de départ est maintenant vide et la case d'arrivée remplie.
     *             - Dans tous les cas, une fois que le pion est déplacé, tous les pions se trouvant dans les cases adjacentes à sa case d'arrivée prennent sa couleur.
     */
    public void movePawn(Move move) {
            Player player = field[move.getRow1()][move.getColumn1()].getPlayer();
            Pawn pawn = new Pawn(player);
            field[move.getRow2()][move.getColumn2()] = pawn;
            if(nbMovingMin(move) == 2){
                field[move.getRow1()][move.getColumn1()]=null;
            }
            for (int i = -1; i<=1;i++) {
                for(int j = -1; j<=1;j++) {
                    Move move1 = new Move(move.getRow2(), move.getColumn2(), move.getRow2()+i, move.getColumn2() +j);
                    if (coordonnesInField(move1) && !caseIsEmpty(move1.getRow2(), move1.getColumn2())) {
                        field[move.getRow2()+i][move.getColumn2()+j] = pawn;
                    }
                }
            }

    }

    /**
     * Retourne la liste de tous les coups valides de player.
     * S'il n'y a pas de coup valide, retourne une liste vide.
     */
    public List<Move> getValidMoves(Player player) {
        List l = new ArrayList<Move>();
        for (int i = 0; i < field.length; i++){
            for(int j = 0; j < field.length; j++){
                if(!caseIsEmpty(i, j) && player.equals(field[i][j].getPlayer())){
                    for (int i2 = 0; i2 < field.length; i2++) {
                        for (int j2 = 0; j2 < field.length; j2++) {
                            Move m = new Move(i, j, i2, j2);
                            if(isValid(m, player)){
                                l.add(m);
                            }
                        }
                    }
                }
            }
        }
        return l;
    }

    /**
     * Retourne le nombre de pions d'un joueur.
     */
    public int getNbPawns(Player player) {
        int nbPawn = 0;
        for (int i = 0; i < field.length; i++){
            for (int j = 0; j < field.length; j++){
                if(!caseIsEmpty(i, j) && player.equals(field[i][j].getPlayer())){
                    nbPawn ++;
                }
            }
        }
        return nbPawn;
    }


    /** Vérifie si la case est vide **/
    public boolean caseIsEmpty(int row, int column){
        if(field[row][column] == null){
            return true;
        }
        return false;
    }

    /**Retourne le nombre de coups minimum**/

    public int nbMovingMin(Move move){
        int nbRow = Math.abs(move.getRow2() - move.getRow1());
        int nbCol =  Math.abs(move.getColumn2() - move.getColumn1());
        if(nbRow == nbCol){
            return nbRow;
        }
        else if(nbRow == 0 || nbCol == 0){
            return nbRow + nbCol;
        }
        else{
            return nbRow + nbCol - 1;
        }
    }

    public Memento saveToMemento() {
        return new Memento(this.deepCopyField());
    }

    public void undoFromMemento(Memento memento){
        this.field = memento.getField();
    }

    public Pawn[][] deepCopyField(){
        Pawn[][] copieField = new Pawn[field.length][field.length];
        for(int i = 0; i < field.length; i++){
            for(int j = 0; j < field.length; j++){
                if(!(field[i][j] == null)) {
                    copieField[i][j] = new Pawn(field[i][j].getPlayer());
                }
            }
        }
        return copieField;

    }
}
