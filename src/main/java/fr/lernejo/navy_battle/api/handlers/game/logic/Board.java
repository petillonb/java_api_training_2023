package fr.lernejo.navy_battle.api.handlers.game.logic;

public class Board {

    private final Cell[][] board = new Cell[10][10];

    public Board() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = new Cell(i, j, false);
            }
        }

    }

    public static int[] convertCoordinates(String coordinates) throws Exception {
        if (coordinates.length() != 2) {
            throw new Exception("bad coordinates format");
        }
        int first = ((int) coordinates.charAt(0)) - 65;
        int second = ((int) coordinates.charAt(1)) - 48;
        System.out.println(coordinates + " " + first + second);
        return new int[]{first, second};


    }

    public boolean isHit(int x, int y) {
        return board[x][y].isTouched();

    }

    public boolean isOccupied(int x, int y) {
        return board[x][y].isOccupied();

    }

    public String recieveHit(String coordinates) throws Exception {

        int[] cellcoordinates = convertCoordinates(coordinates);

        System.out.println(cellcoordinates[0] + " " + cellcoordinates[1]);
        if (!board[cellcoordinates[0]][cellcoordinates[1]].markedHit()) {
            return "hit";

        } else {
            return "miss";
        }
    }

}
