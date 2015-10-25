package dimitris.android.chessviews.Pieces;

import java.util.StringTokenizer;

import dimitris.android.chessviews.SquareView;

public class FenParser {

    private SquareView[][] board;
    private PieceFactory blackFactory;
    private PieceFactory whiteFactory;
    private int currentRow = 0;
    private int currentColumn = 0;

    public FenParser(SquareView[][] board, WhitePieceFactory whiteFactory, BlackPieceFactory blackFactory) {
        this.board = board;
        this.whiteFactory = whiteFactory;
        this.blackFactory = blackFactory;
    }

    public void parse(String fen) throws BadFenException {
        StringTokenizer tokenizer = new StringTokenizer(fen);
        String pieces = (String) tokenizer.nextElement();
        for (int currentChar = 0; currentChar < pieces.length(); currentChar++) {
            char current = pieces.charAt(currentChar);

            if (Character.isLetter(current)) {
                handleCharacter(current);
            } else if (Character.isDigit(current)) {
                handleDigit(Integer.parseInt(String.valueOf(current)));
            } else if ("/".equals(String.valueOf(current))) {
                handleSlash();
            } else throw new BadFenException("Bad character '" + current + "' at FEN String");
        }
    }

    private void handleSlash() {
        currentRow++;
        currentColumn = 0;
    }

    private void handleDigit(int columnOffset) {
        currentColumn += columnOffset;
    }

    private void handleCharacter(char current) {
        PieceFactory factory = (Character.isUpperCase(current)) ? whiteFactory : blackFactory;
        Piece piece = factory.createPiece(current);
        board[currentRow][currentColumn].setPiece(piece);
        currentColumn++;
    }


    public class BadFenException extends Exception {
        public BadFenException(String description) {
            super(description);
        }
    }
}
