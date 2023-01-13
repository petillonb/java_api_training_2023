package fr.lernejo.navy_battle.api.handlers.game.logic;

public class Cell {

    private final int x;
    private final int y;
    private final boolean occupied;
    private final boolean[] touched;

    public Cell(int x, int y, boolean occupied) {
        this.x = x;
        this.y = y;
        this.occupied = occupied;
        this.touched = new boolean[]{false};
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public boolean isTouched() {
        return this.touched[0];
    }

    public boolean markedHit() {
        this.touched[0] = true;
        return this.occupied;
    }
}
