package dimitris.chessboardutils;

import java.util.ArrayList;

/**
 * Created by dimitris on 8/30/15.
 */
public class Board implements MoveSubject{

    protected static final String columns = "abcdefgh";
    protected static final String rows = "87654321";
    protected Square[][] board = new Square[8][8];
    protected ArrayList<MoveObserver> moveObservers;

    public Board() {
        initializeBoard();
        moveObservers = new ArrayList<>();
    }

    private void initializeBoard() {
        for (int row = 0; row < 8; row++) {
            for (char col = 'a'; col <= 'h'; col++) {
                int row_num = 8 - row;
                String squareName = String.format("%s%d", col, row_num);
                int col_index = columns.indexOf(col);
                board[row][col_index] = new Square(squareName);
            }
        }
    }

    public void setPieceAtSquare(Piece piece, int row, int col) {
        String square = "" + columns.charAt(col) + rows.charAt(row);
        setPieceAtSquare(piece, square);
    }

    public void setPieceAtSquare(Piece piece, String square) {
        Square squareToEdit = getSquareAt(square);
        squareToEdit.piece = piece;
    }

    public Piece getPieceAt(String square) {
        return getSquareAt(square).piece;
    }

    public Piece getPieceAt(int row, int column){
        String square = "" + columns.charAt(column) + rows.charAt(row);
        return getPieceAt(square);
    }

    public Square getSquareAt(String squareName) {
        int rowIndex = getIndexOfRow(squareName.charAt(1));
        int colIndex = getIndexOfColumn(squareName.charAt(0));

        return board[rowIndex][colIndex];
    }

    private int getIndexOfColumn(char column) {
        return columns.indexOf(column);
    }

    private int getIndexOfRow(char row) {
        return rows.indexOf(row);
    }

    public void playMove(Move moveToPlay) {
        Piece pieceToMove = moveToPlay.sourceSquare.piece;
        setPieceAtSquare(pieceToMove, moveToPlay.destinationSquare.toString());
        setPieceAtSquare(Piece.create('?'), moveToPlay.sourceSquare.toString());
        propagateMoveToObservers(moveToPlay);
    }

    @Override
    public void propagateMoveToObservers(Move playedMove) {
        for (MoveObserver observer : moveObservers)
            observer.onMovePlayed(playedMove);
    }

    @Override
    public void registerObserver(MoveObserver observer) {
        moveObservers.add(observer);
    }

    @Override
    public void removeObserver(MoveObserver observer){
        moveObservers.remove(observer);
    }

    public void onGameDidEnd() {

    }

    public void rejectMove(Move moveRejected) {
        for (MoveObserver observer : moveObservers)
            observer.onMoveRejected(moveRejected);
    }

    @Override
    public String toString() {
        String toReturn = "";
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                toReturn += board[row][col].piece.getFANString();
            }
            toReturn += "\n";
        }

        return toReturn;
    }
}
