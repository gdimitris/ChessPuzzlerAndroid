package dimitris.android.chessviews;

import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;

import dimitris.android.chessviews.Pieces.BlackPieceFactory;
import dimitris.android.chessviews.Pieces.PieceFactory;
import dimitris.android.chessviews.Pieces.WhitePieceFactory;
import dimitris.chessboardutils.Board;
import dimitris.chessboardutils.Piece;

public class BoardViewFactory {

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

    public SquareView[][] createFromExistingBoard(Board board, Typeface typeface){
        if(board == null){
            return createEmptyBoard();
        }

        WhitePieceFactory whitePieceFactory = new WhitePieceFactory(typeface, size);
        BlackPieceFactory blackPieceFactory = new BlackPieceFactory(typeface, size);
        SquareView[][] boardView = createBoard(board, whitePieceFactory, blackPieceFactory);


        return boardView;
    }

    private SquareView[][] createBoard(Board board, WhitePieceFactory whiteFactory, BlackPieceFactory blackFactory) {
        SquareView[][] boardViews = new SquareView[8][8];
        PieceFactory currentPieceFactory;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece currentPiece = board.getPieceAt(row, col);
                boardViews[row][col] = getCurrentSquareColor(row, col);
                currentPieceFactory = (currentPiece.color == Piece.PieceColor.White) ? whiteFactory : blackFactory;
                Log.e("BoardViewFactory","row: " + row + " col:" +col);
                dimitris.android.chessviews.Pieces.Piece piece = currentPieceFactory.createFromUtilPiece(currentPiece);
                boardViews[row][col].setPiece(piece);
            }
        }
        return boardViews;
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
