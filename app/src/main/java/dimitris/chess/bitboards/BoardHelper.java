package dimitris.chess.bitboards;

public class BoardHelper {

    public static final String INITAL_POSITION_FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    public static BoardCoords getSquareCoords(String squareName) {
        char squareColumn = squareName.charAt(0);
        int squareRow = Integer.parseInt(String.valueOf(squareName.charAt(1)));

        int row = 8 - squareRow;
        int column = squareColumn - 'a';

        return new BoardCoords(row, column);
    }

    public static class BoardCoords {
        public int row;
        public int column;


        public BoardCoords(int row, int col) {
            this.row = row;
            this.column = col;
        }

        @Override
        public boolean equals(Object o) {
            BoardCoords toTest = (BoardCoords) o;

            if (this.row == toTest.row && this.column == toTest.column)
                return true;
            return false;

        }
    }
}
