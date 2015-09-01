package dimitris.chessboardutils;

/**
 * Created by dimitris on 9/1/15.
 */
public class BoardFactory {
    public static Board create(String FEN) {
        Board board = new Board();
        int row = 0;
        int col = 0;

        for(int i = 0; i< FEN.length(); i++){
            char current_char = FEN.charAt(i);
            if(Character.isLetter(current_char)){
                Piece piece = Piece.create(current_char);
                board.setPieceAtSquare(piece, row, col);
                col++;
            } else if (current_char == '/') {
                row++;
                col=0;
            } else if (Character.isDigit(current_char)) {
                int value = Character.getNumericValue(current_char);
                col += value;
            }
        }

        return board;
    }
}