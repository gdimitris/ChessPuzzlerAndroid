package dimitris.android.chessviews;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.List;

import dimitris.android.chessviews.Pieces.BlackPieceFactory;
import dimitris.android.chessviews.Pieces.FenParser;
import dimitris.android.chessviews.Pieces.Piece;
import dimitris.android.chessviews.Pieces.WhitePieceFactory;


public class DrawableBoard extends Drawable{

    private BoardContainerView parentView;
    private int squareSize;
    private Paint boardPaint;
    private List<Piece> alivePieces;
    private Rect lastSelectedSquare;
    private SquareHighlighter squareHighlighter;

    public DrawableBoard(BoardContainerView parentView) {
        super();

        this.parentView = parentView;
        squareSize = 1;
        boardPaint = createChessBoardPaint();
        lastSelectedSquare = null;
        squareHighlighter = new SquareHighlighter(getRectForSquareAt(0,0));
    }

    public void setPosition(String FEN){
        try {
            clearBoard();
            Typeface typeface = FontLoader.loadDefaultFont(parentView.getContext());
            WhitePieceFactory whiteFactory = new WhitePieceFactory(typeface, squareSize);
            BlackPieceFactory blackPieceFactory = new BlackPieceFactory(typeface, squareSize);
            FenParser parser = new FenParser(whiteFactory, blackPieceFactory,squareSize);
            alivePieces = parser.parse(FEN);
        } catch (FenParser.BadFenException e) {
            e.printStackTrace();
        }
    }

    public void setSquareSize(int size){
        if(squareSize == size)
            return;

        this.squareSize = size;
        this.boardPaint = createChessBoardPaint();

        for(Piece p : alivePieces)
            p.setSize(size);
    }

    @Override
    public void draw(Canvas canvas) {
        if (noPiecesExist())
            return;

        drawBoard(canvas);
        //drawSelectedSquare(canvas);
        drawAlivePieces(canvas);
    }

    public void squareClickedAt(int row, int col) {
        Rect clicked = getRectForSquareAt(row,col);

        if (lastSelectedSquare == null) {
            selectSquareIfNotEmpty(clicked);
        } else if (clicked.equals(lastSelectedSquare)) {
            clearSelection();
        } else {
            //dispatchNewMoveIfPassesFilters(row, col);
            clearSelection();
        }
    }

    public int getSquareSize(){
        return squareSize;
    }

    private boolean noPiecesExist() {
        return alivePieces == null || alivePieces.isEmpty();
    }

    private void drawSelectedSquare(Canvas canvas){
        if (lastSelectedSquare != null){
            squareHighlighter.draw(canvas);
        }
    }

    private void drawAlivePieces(Canvas canvas) {
        for(Piece p: alivePieces)
            p.draw(canvas);
    }

    private void drawBoard(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.drawRect(bounds.left,bounds.top,bounds.right,bounds.bottom,boardPaint);
        Log.e("drawBoard", "Drawing Board with left:"+bounds.left+" top:"+bounds.top+" right:"+bounds.right+" bottom:"+bounds.bottom);
    }

//    private void dispatchNewMoveIfPassesFilters(int row, int col) {
//        String source = lastSelectedSquareView.getName();
//        String dest = squareViews[row][col].getName();
//        parentView.moveDetected(source,dest);
//        //doMove(toMake);
//    }
//
    private void selectSquareIfNotEmpty(Rect squareToSelect) {
        if (!squareIsEmpty(squareToSelect)) {
            lastSelectedSquare = squareToSelect;
            squareHighlighter.setSquare(lastSelectedSquare);
        }
    }

    private void clearSelection(){
        lastSelectedSquare = null;
    }

    private boolean squareIsEmpty(Rect squareToCheck) {
        for(Piece p: alivePieces){
            if(p.getPositionRect().contains(squareToCheck))
                return false;
        }
        return true;
    }

    private void clearBoard(){
        if(alivePieces!= null)
            alivePieces.clear();
        clearSelection();
    }

    private Paint createChessBoardPaint(){
        int darkSquareColor = Color.argb(255 , 160, 82, 45);
        int lightSquareColor = Color.argb(255 ,255, 222, 173);
        Bitmap bitmap = Bitmap.createBitmap(squareSize * 2, squareSize * 2, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        paintLightSquaresInCanvas(canvas, getFillPaintWithColor(lightSquareColor));
        paintDarkSquaresInCanvas(canvas, getFillPaintWithColor(darkSquareColor));

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setShader(new BitmapShader(bitmap, BitmapShader.TileMode.REPEAT, BitmapShader.TileMode.REPEAT));
        return paint;
    }

    private void paintLightSquaresInCanvas(Canvas canvas, Paint fillColor) {
        Rect rect = new Rect(0, 0, squareSize, squareSize);
        canvas.drawRect(rect, fillColor);
        rect.offset(squareSize, squareSize);
        canvas.drawRect(rect, fillColor);
    }

    private void paintDarkSquaresInCanvas(Canvas canvas, Paint fillColor){
        Rect rect = new Rect(0,0,squareSize,squareSize);
        rect.offset(squareSize,0);
        canvas.drawRect(rect, fillColor);
        rect.offset(-squareSize,squareSize);
        canvas.drawRect(rect, fillColor);
    }

    private Paint getFillPaintWithColor(int lightSquareColor) {
        Paint fillColor = new Paint(Paint.ANTI_ALIAS_FLAG);
        fillColor.setStyle(Paint.Style.FILL);
        fillColor.setColor(lightSquareColor);
        return fillColor;
    }

    private String getSquareName(int currentRow, int currentCol) {
        String rows = "12345678";
        String columns = "hgfedcba";
        return "" + columns.charAt(7 - currentCol) + rows.charAt(7 - currentRow);
    }

    private Rect getRectForSquareAt(int row, int col) {
        return new Rect(col * squareSize, row * squareSize, (col + 1) * squareSize, (row + 1) * squareSize);
    }

    @Override
    public void setAlpha(int alpha) {}

    @Override
    public void setColorFilter(ColorFilter cf) {}

    @Override
    public int getOpacity() { return 0; }

}
