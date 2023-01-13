package fr.lernejo.navy_battle.api.handlers.game.logic;

public class Game {

    private static Board board;

    public static Board getBoard() {
        return board;
    }

    public static void newGame() {
        System.out.println("New game");
        Game.board = new Board();
    }
}
