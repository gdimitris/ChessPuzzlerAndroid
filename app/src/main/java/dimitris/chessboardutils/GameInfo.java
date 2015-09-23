package dimitris.chessboardutils;

/**
 * Created by dimitris on 9/24/15.
 */

public class GameInfo {
    public boolean whiteCanCastleKingSide = false;
    public boolean whiteCanCastleQueenSide = false;
    public boolean blackCanCastleKingSide = false;
    public boolean blackCanCastleQueenSide = false;
    public boolean whitePlays = true;
    public String enPassantSquare;
    public int halfMoveClock;
    public int fullMoveClock;

    public GameInfo(String gameInfoFenString) {
        String[] tokens = gameInfoFenString.split(" ");

        determineSideToPlay(tokens[0]);
        determineCastling(tokens[1]);
        determineEnPassantSquare(tokens[2]);
        determineHalfMoveClock(tokens[3]);
        determineFullMoveClock(tokens[4]);
    }

    private void determineSideToPlay(String side) {
        if (side.equals("w")) {
            this.whitePlays = true;
        } else {
            this.whitePlays = false;
        }
    }

    private void determineCastling(String castling) {

        if (castling.equals("-")) {
            return;
        } else if (castling.length() == 4) {
            this.whiteCanCastleKingSide = true;
            this.whiteCanCastleQueenSide = true;
            this.blackCanCastleKingSide = true;
            this.blackCanCastleQueenSide = true;
        } else {
            if (castling.contains("K")) {
                this.whiteCanCastleKingSide = true;
            }

            if (castling.contains("Q")) {
                this.whiteCanCastleQueenSide = true;
            }

            if (castling.contains("k")) {
                this.blackCanCastleKingSide = true;
            }

            if (castling.contains("q")) {
                this.blackCanCastleQueenSide = true;
            }
        }
    }

    //TODO needs fixing (Square or String??)
    private void determineEnPassantSquare(String enPassant) {
        if (enPassant.equals("-")) {
            return;
        }
        this.enPassantSquare = enPassant;
    }

    private void determineHalfMoveClock(String halfMoveClock) {
        this.halfMoveClock = Integer.parseInt(halfMoveClock);
    }

    private void determineFullMoveClock(String fullMoveClock) {
        this.fullMoveClock = Integer.parseInt(fullMoveClock);
    }
}
