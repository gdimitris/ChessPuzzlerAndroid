package dimitris.android.chessviews;

import android.graphics.Rect;

public class BoardViewFactory {

    private int size = 0;

    public BoardViewFactory(int squareSize) {
        this.size = squareSize;
    }

    public SquareView[][] createBoard() {
        SquareView[][] board = new SquareView[8][8];

        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 8; col++)
                board[row][col] = getCurrentSquareColor(row, col);

        return board;
    }

//    public Square[][] createFromFENString(String FEN, Typeface typeface) throws FenParser.BadFenException {
//        Square[][] board = createBoard();
//        WhitePieceFactory whiteFactory = new WhitePieceFactory(typeface, size);
//        BlackPieceFactory blackPieceFactory = new BlackPieceFactory(typeface, size);
//        FenParser parser = new FenParser(board, whiteFactory, blackPieceFactory);
//        parser.parse(FEN);
//        return board;
//    }

    private SquareView getCurrentSquareColor(int row, int col) {
        Rect rect = getCurrentRect(row, col);
        String name = getCurrentSquareName(row, col);
        return (isWhiteSquare(row, col)) ? new LightSquareView(name, rect) : new DarkSquareView(name, rect);
    }

    private String getCurrentSquareName(int currentRow, int currentCol) {
        String rows = "12345678";
        String columns = "hgfedcba";
        return "" + columns.charAt(7 - currentCol) + rows.charAt(7 - currentRow);
    }

    private Rect getCurrentRect(int row, int col) {
        return new Rect(col * size, row * size, (col + 1) * size, (row + 1) * size);
    }

    public boolean isWhiteSquare(int row, int col) {
        return Math.abs(row - col) % 2 == 0;
    }
}
