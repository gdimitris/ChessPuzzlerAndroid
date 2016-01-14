package dimitris.chess.core;

import static dimitris.chess.core.Piece.PieceColor.*;
import static dimitris.chess.core.Piece.PieceType.*;

public class Bitboard implements Board {

    private UInt64[] squareIsolationMasks;
    private int[][] squareIndexes = {{56,57,58,59,60,61,62,63},
                                     {48,49,50,51,52,53,54,55},
                                     {40,41,42,43,44,45,46,47},
                                     {32,33,34,35,36,37,38,39},
                                     {24,25,26,27,28,29,30,31},
                                     {16,17,18,19,20,21,22,23},
                                     { 8, 9,10,11,12,13,14,15},
                                     { 0, 1, 2, 3, 4, 5, 6, 7}};

    private UInt64[][] pieceBitboards;

    public Bitboard(){
        initializeBitboards();
        initializeSquareIsolationMasks();
    }

    @Override
    public void doMove(Move move) {
        Piece piece = getPieceAtSquare(move.source);
        removePieceFromSquare(move.source);
        setPieceAtSquare(piece, move.destination);
    }

    @Override
    public void undoMove(Move move) {

    }

    @Override
    public Piece getPieceAtSquare(String square) {
        BoardCoords coords = BoardCoords.getSquareCoords(square);
        int index = squareIndexes[coords.row][coords.column];
        UInt64 mask = squareIsolationMasks[index];

        for(int i=0;i<6;i++){
            for(int j=0;j<2;j++){
                if((pieceBitboards[i][j].and(mask)).cardinality() != 0){
                    return PieceFactory.createPiece(Piece.PieceType.values()[i], Piece.PieceColor.values()[j]);
                }
            }
        }
        return PieceFactory.createNullPiece();
    }

    @Override
    public void setPosition(String FEN) {
        setUpFenPosition(FEN);
    }

    private void setUpFenPosition(String Fen) {
        String pieces_fen = Fen.split(" ")[0];
        int current_row = 0, current_col = 0;

        for(int i=0; i<pieces_fen.length();i++){
            char current_piece = pieces_fen.charAt(i);
            if (Character.isAlphabetic(current_piece)){
                Piece.PieceColor color = Character.isUpperCase(current_piece) ? White : Black;
                current_piece = Character.toLowerCase(current_piece);
                int current_index = squareIndexes[current_row][current_col];
                switch (current_piece){
                    case 'k':
                        setPieceAtSquare(King, color, current_index);
                        break;
                    case 'q':
                        setPieceAtSquare(Queen, color, current_index);
                        break;
                    case 'r':
                        setPieceAtSquare(Rook, color, current_index);
                        break;
                    case 'n':
                        setPieceAtSquare(Knight, color, current_index);
                        break;
                    case 'b':
                        setPieceAtSquare(Bishop, color, current_index);
                        break;
                    case 'p':
                        setPieceAtSquare(Pawn, color, current_index);
                        break;
                }
                current_col++;
            }
            else if (Character.isDigit(current_piece)) {
                int offset = Integer.parseInt(String.valueOf(current_piece));
                current_col += offset;
            }else if ("/".equals(String.valueOf(current_piece))){
                current_row ++;
                current_col = 0;
            }
        }
    }

    private void setPieceAtSquare(Piece.PieceType type, Piece.PieceColor color, int index){
        int pieceType = type.ordinal();
        int pieceColor = color.ordinal();

        pieceBitboards[pieceType][pieceColor] = pieceBitboards[pieceType][pieceColor].or(squareIsolationMasks[index]);
    }

    public void removePieceFromSquare(String squareName){
        Piece piece = getPieceAtSquare(squareName);
        BoardCoords coords = BoardCoords.getSquareCoords(squareName);
        int index = squareIndexes[coords.row][coords.column];

        removePieceFromSquare(piece, index);
    }

    public void setPieceAtSquare(Piece piece, String square){
        BoardCoords boardCoords = BoardCoords.getSquareCoords(square);
        int index = squareIndexes[boardCoords.row][boardCoords.column];
        setPieceAtSquare(piece.type, piece.color, index);
    }

    public UInt64 getAllWhitePieces(){
        UInt64 result = new UInt64();
        for(int i=0; i<6; i++)
            result = result.or(pieceBitboards[i][White.ordinal()]);
        return result;
    }

    public UInt64 getAllBlackPieces(){
        UInt64 result = new UInt64();
        for(int i=0; i<6; i++)
            result = result.or(pieceBitboards[i][Black.ordinal()]);

        return result;
    }

    private void initializeBitboards() {
        pieceBitboards = new UInt64[6][2];
        for (int i=0;i<6;i++)
            for (int j=0; j<2;j++)
                pieceBitboards[i][j] = UInt64.create("0");
    }

    private void initializeSquareIsolationMasks(){
        squareIsolationMasks = new UInt64[64];
        for (int index = 0; index < squareIsolationMasks.length; index++)
            squareIsolationMasks[index] = UInt64.create(Long.toBinaryString(1L << index));
    }

    private void removePieceFromSquare(Piece piece, int index){
        int row = piece.type.ordinal();
        int col = piece.color.ordinal();

        pieceBitboards[row][col] = pieceBitboards[row][col].and(squareIsolationMasks[index].flip());
    }

//    public void printAllBitboards(){
//        for(int j=0; j<2;j++)
//            for (int i=0; i<6; i++ ){
//                String color = PieceType.values()[j].toString();
//                String pieceType = PieceType.values()[i].toString();
//                String message = String.format("%s %s bitboard: ",color, pieceType);
//                System.out.println(message);
//                System.out.println(BitBoardPrinter.print(pieceBitboards[i][j]));
//            }
//    }
}
