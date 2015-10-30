package dimitris.dimitris.chess.bitboards;

import dimitris.dimitris.chess.bitboards.BoardHelper.BoardCoords;

public class Bitboard {

    private long[] squareIsolationMasks;
    private int[][] squareIndexes = {{56,57,58,59,60,61,62,63},
                                     {48,49,50,51,52,53,54,55},
                                     {40,41,42,43,44,45,46,47},
                                     {32,33,34,35,36,37,38,39},
                                     {24,25,26,27,28,29,30,31},
                                     {16,17,18,19,20,21,22,23},
                                     { 8, 9,10,11,12,13,14,15},
                                     { 0, 1, 2, 3, 4, 5, 6, 7}};

    private long[][] pieceBitboards;

    public enum PieceType{
        King,
        Queen,
        Rook,
        Knight,
        Bishop,
        Pawn
    }

    public enum PieceColor{
        White,
        Black
    }


    public Bitboard(){
        pieceBitboards = new long[6][2];
        squareIsolationMasks = new long[64];
        initializeSquareIsolationMasks();
    }

    private void initializeSquareIsolationMasks(){
        for (int index = 0; index < squareIsolationMasks.length; index++)
            squareIsolationMasks[index] = 1L << index;
    }

    public void setFenPosition(String FEN){

        String pieces_fen = FEN.split(" ")[0];
        int current_row = 0, current_col = 0;

        for(int i=0; i<pieces_fen.length();i++){
            char current_piece = pieces_fen.charAt(i);
            if (Character.isAlphabetic(current_piece)){
                PieceColor color = Character.isUpperCase(current_piece) ? PieceColor.White : PieceColor.Black;
                current_piece = Character.toLowerCase(current_piece);
                int current_index = squareIndexes[current_row][current_col];
                switch (current_piece){
                    case 'k':
                        setPieceAtSquare(PieceType.King, color, current_index);
                        return;
                    case 'q':
                        setPieceAtSquare(PieceType.Queen, color, current_index);
                        return;
                    case 'r':
                        setPieceAtSquare(PieceType.Rook, color, current_index);
                        return;
                    case 'n':
                        setPieceAtSquare(PieceType.Knight, color, current_index);
                        return;
                    case 'b':
                        setPieceAtSquare(PieceType.Bishop, color, current_index);
                        return;
                    case 'p':
                        setPieceAtSquare(PieceType.Pawn, color, current_index);
                        return;
                }}
            else if (Character.isDigit(current_piece))
                current_col += Integer.parseInt(String.valueOf(current_piece));
            else if ("/".equals(String.valueOf(current_piece))){
                current_row ++;
                current_col = 0;
            }

        }

    }

    public PieceType getPieceType(String square){
        BoardCoords coords = BoardHelper.getSquareCoords(square);
        int index = squareIndexes[coords.row][coords.column];
        long mask = squareIsolationMasks[index];

        for(int i=0;i<6;i++){
            for(int j=0;j<2;j++){
                if((pieceBitboards[i][j] & mask) != 0){
                    return PieceType.values()[i];
                }
            }
        }
        return PieceType.Knight;
    }

    public PieceColor getPieceColor(String square){
        BoardCoords coords = BoardHelper.getSquareCoords(square);
        int index = squareIndexes[coords.row][coords.column];
        long mask = squareIsolationMasks[index];

        for(int i=0;i<6;i++){
            for(int j=0;j<2;j++){
                if((pieceBitboards[i][j] & mask) != 0){
                    return PieceColor.values()[j];
                }
            }
        }
        return PieceColor.White;
    }


    public void setPieceAtSquare(PieceType type, PieceColor color,int index){
        int row = type.ordinal();
        int col = color.ordinal();

        pieceBitboards[row][col] = pieceBitboards[row][col] | squareIsolationMasks[index];
    }

    public void removePieceFromSquare(PieceType type, PieceColor color, int index){
        int row = type.ordinal();
        int col = color.ordinal();

        pieceBitboards[row][col] = pieceBitboards[row][col] &  ~squareIsolationMasks[index];
    }

    public void setPieceAtSquare(PieceType type, PieceColor color, String square){
        BoardCoords boardCoords = BoardHelper.getSquareCoords(square);
        int index = squareIndexes[boardCoords.row][boardCoords.column];
        setPieceAtSquare(type, color, index);
    }

    public void printAllBitboards(){
        for(int j=0; j<2;j++)
            for (int i=0; i<6; i++ ){
                String color = PieceColor.values()[j].toString();
                String pieceType = PieceType.values()[i].toString();
                String message = String.format("%s %s bitboard: ",color, pieceType);
                System.out.println(message);
                System.out.println(BitBoardPrinter.print(pieceBitboards[i][j]));
            }

    }

}
