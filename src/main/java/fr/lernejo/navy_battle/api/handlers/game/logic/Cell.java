package fr.lernejo.navy_battle.api.handlers.game.logic;

public class Cell {

    private final int x;
    private final int y;
    private final boolean occupied;
    private boolean touched;

    public Cell(int x, int y, boolean occupied) {
        this.x = x;
        this.y = y;
        this.occupied = occupied;
        this.touched = false;
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
        return touched;
    }

    public boolean markedHit() {
        this.touched = true;
        return this.occupied;
    }
}
