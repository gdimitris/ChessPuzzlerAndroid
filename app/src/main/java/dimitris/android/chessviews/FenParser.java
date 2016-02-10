package dimitris.android.chessviews;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import dimitris.android.chessviews.Pieces.AbstractPieceFactory;
import dimitris.android.chessviews.Pieces.BlackPieceFactory;
import dimitris.android.chessviews.Pieces.Piece;
import dimitris.android.chessviews.Pieces.WhitePieceFactory;

public class FenParser {

    private AbstractPieceFactory blackFactory;
    private AbstractPieceFactory whiteFactory;
    private int currentRow = 0;
    private int currentColumn = 0;
    private int size;
    private List<Piece> pieces;

    public FenParser(WhitePieceFactory whiteFactory, BlackPieceFactory blackFactory, int size) {
        this.whiteFactory = whiteFactory;
        this.blackFactory = blackFactory;
        this.size = size;
        pieces = new ArrayList<>();
    }

    public List<Piece> parse(String fen) throws BadFenException {
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
        return this.pieces;
    }

    private void handleSlash() {
        currentRow++;
        currentColumn = 0;
    }

    private void handleDigit(int columnOffset) {
        currentColumn += columnOffset;
    }

    private void handleCharacter(char current) {
        AbstractPieceFactory factory = (Character.isUpperCase(current)) ? whiteFactory : blackFactory;
        Piece piece = factory.createPiece(current);
        piece.setPositionCoords(currentRow,currentColumn);
        piece.setSize(size);
        pieces.add(piece);
        currentColumn++;
    }


    public class BadFenException extends Exception {
        public BadFenException(String description) {
            super(description);
        }
    }
}
