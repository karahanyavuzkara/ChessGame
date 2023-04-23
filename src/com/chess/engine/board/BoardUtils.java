package com.chess.engine.board;

public class BoardUtils {
    public static final boolean[] FIRST_COLUMN = initColumn(0);
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] SEVENT_COLUMN = initColumn(6) ;
    public static final boolean[] EIGHT_COLUMN = initColumn(7);

    public static final int NUM_TIlES = 64;
    public static final int NUM_TILES_PER_ROW = 8;

    private BoardUtils() {
        throw new RuntimeException("You cannot instantiate me");
    }
    private static boolean[] initColumn(int columnNumber) {
        final boolean[] column = new boolean[NUM_TIlES];

        do {
            column[columnNumber] = true ;
            columnNumber += NUM_TILES_PER_ROW ;
        }
        while (columnNumber < NUM_TIlES );
        return column;
    }
    public static boolean isValidTileCoordinate(final int coordinate) {
        return coordinate >= 0 && coordinate < NUM_TIlES;
    }
}
