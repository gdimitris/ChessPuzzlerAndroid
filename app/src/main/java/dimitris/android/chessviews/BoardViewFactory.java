package dimitris.android.chessviews;

import android.graphics.Rect;
import android.graphics.Typeface;

import dimitris.android.chessviews.Pieces.BlackPieceFactory;
import dimitris.android.chessviews.Pieces.FenParser;
import dimitris.android.chessviews.Pieces.WhitePieceFactory;


public class BoardViewFactory {
    private static final String INITIAL_FEN_POSITION = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
    private int size = 0;

    public BoardViewFactory(int squareSize) {
        this.size = squareSize;
    }

    public SquareView[][] createEmptyBoard() {
        SquareView[][] board = new SquareView[8][8];

        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 8; col++)
                board[row][col] = getCurrentSquareColor(row, col);

        return board;
    }

    public SquareView[][] createFromFEN(String FEN, Typeface typeface) throws FenParser.BadFenException {
        SquareView[][] board = createEmptyBoard();
        WhitePieceFactory whiteFactory = new WhitePieceFactory(typeface, size);
        BlackPieceFactory blackPieceFactory = new BlackPieceFactory(typeface, size);
        FenParser parser = new FenParser(board , whiteFactory, blackPieceFactory);
        parser.parse(FEN);
        return board;
    }

    public SquareView[][] createInitialBoard(Typeface typeface) throws FenParser.BadFenException{
        return createFromFEN(INITIAL_FEN_POSITION, typeface);
    }

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

    private boolean isWhiteSquare(int row, int col) {
        return Math.abs(row - col) % 2 == 0;
    }
}
