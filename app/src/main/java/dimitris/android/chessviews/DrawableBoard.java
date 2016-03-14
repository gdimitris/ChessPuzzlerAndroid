package dimitris.android.chessviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import dimitris.android.chessviews.Pieces.BlackPieceFactory;
import dimitris.android.chessviews.Pieces.Piece;
import dimitris.android.chessviews.Pieces.WhitePieceFactory;


public class DrawableBoard extends Drawable{

    private int squareSize;
    private Paint boardPaint;
    private List<Piece> alivePieces;
    private Square[][] board;
    private SquareHighlighter squareHighlighter;
    private List<UIMove> playedMoves;
    private Context context;
    private BoardAnnotator boardAnnotator;

    public DrawableBoard(Context context) {
        super();
        this.context = context;
        squareSize = 80;
        boardPaint = BoardPaintCreator.createPaintWithSquareSize(squareSize);
        squareHighlighter = new SquareHighlighter(getRectForSquareAt(0,0));
        boardAnnotator = new BoardAnnotator(getBounds());
        alivePieces = new ArrayList<>();
        playedMoves = new ArrayList<>();
        initialiseBoard();
    }

    private void initialiseBoard() {
        board = new Square[8][8];

        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                board[i][j] = new Square(i,j);
    }

    public void setPosition(String FEN){
        clearBoard();
        Typeface typeface = FontLoader.loadDefaultFont(context);
        FenParser parser = getFenParser(typeface);
        alivePieces = parser.parse(FEN);
        populateBoardWithAlivePieces();
    }

    private FenParser getFenParser(Typeface typeface) {
        WhitePieceFactory whiteFactory = new WhitePieceFactory(typeface,squareSize);
        BlackPieceFactory blackPieceFactory = new BlackPieceFactory(typeface,squareSize);
        return new FenParser(whiteFactory, blackPieceFactory,squareSize);
    }

    private void populateBoardWithAlivePieces() {
        for(Piece p : alivePieces){
            int row = p.getRow();
            int col = p.getCol();

            board[row][col].setPiece(p);
        }
    }

    public void setSquareSize(int size){
        if(squareSize == size)
            return;
        this.squareSize = size;
        boardAnnotator.setSquareSize(size);
        this.boardPaint = BoardPaintCreator.createPaintWithSquareSize(squareSize);

        for(Piece p : alivePieces)
            p.setSize(size);

        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                board[i][j].setSize(size);
    }

    @Override
    public void draw(Canvas canvas) {
        drawBoard(canvas);
        boardAnnotator.drawAnnotations(canvas);
        if (boardIsEmpty())
            return;

        drawSelectedSquare(canvas);
        drawAlivePieces(canvas);
    }

    public void doMove(UIMove move) {
        move.execute();
        playedMoves.add(move);
        removeFromAlive(move.getCapturedPiece());
    }

    public void undoMove() {
        int lastIndex = playedMoves.size()-1;
        UIMove move = playedMoves.remove(lastIndex);
        move.undo();
        addInAlive(move.getCapturedPiece());
        clearLastSelectedSquare();
    }

    public int getSquareSize(){
        return squareSize;
    }

    public boolean boardIsEmpty() {
        return alivePieces == null || alivePieces.isEmpty();
    }

    private void drawSelectedSquare(Canvas canvas){
        squareHighlighter.draw(canvas);
    }

    private void drawAlivePieces(Canvas canvas) {
        for(Piece p: alivePieces)
            p.draw(canvas);
    }

    private void drawBoard(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.drawRect(bounds.left,bounds.top,bounds.right,bounds.bottom,boardPaint);
    }

    public boolean squareIsEmpty(Square squareToCheck) {
        return squareToCheck.getPiece() == null;
    }

    private void clearBoard(){
        if(alivePieces!= null)
            alivePieces.clear();
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                board[i][j].clear();
        clearLastSelectedSquare();
    }

    private Rect getRectForSquareAt(int row, int col) {
        return new Rect(col * squareSize, row * squareSize, (col + 1) * squareSize, (row + 1) * squareSize);
    }

    private void removeFromAlive(Piece p){
        if(p!=null)
            alivePieces.remove(p);
    }

    private void addInAlive(Piece p){
        if(p!=null)
            alivePieces.add(p);
    }

    public Square getSquareAt(int row, int col){
        return board[row][col];
    }

    public List<UIMove> getPlayedMoves(){
        return playedMoves;
    }

    @Override
    public void setAlpha(int alpha) {}

    @Override
    public void setColorFilter(ColorFilter cf) {}

    @Override
    public int getOpacity() { return 0; }

    public void selectSquare(int row, int col) {
        squareHighlighter.setSquare(board[row][col]);
    }

    public void clearLastSelectedSquare(){
        squareHighlighter.setSquare(null);
    }
}
