package dimitris.chess.core;

/**
 * Created by dimitris on 12/29/15.
 */
public class BoardCoords {
    public int row;
    public int column;


    public BoardCoords(int row, int col) {
        this.row = row;
        this.column = col;
    }

    @Override
    public boolean equals(Object o) {
        BoardCoords toTest = (BoardCoords) o;
        return (this.row == toTest.row && this.column == toTest.column);
    }

    public static BoardCoords getSquareCoords(String squareName) {
        char squareColumn = squareName.charAt(0);
        int squareRow = Integer.parseInt(String.valueOf(squareName.charAt(1)));

        int row = 8 - squareRow;
        int column = squareColumn - 'a';

        return new BoardCoords(row, column);
    }
}
