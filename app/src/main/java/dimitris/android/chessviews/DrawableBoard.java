package dimitris.android.chessviews;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import dimitris.android.chessviews.Pieces.BlackPieceFactory;
import dimitris.android.chessviews.Pieces.FenParser;
import dimitris.android.chessviews.Pieces.WhitePieceFactory;


public class DrawableBoard extends Drawable{

    private SquareView lastSelectedSquareView;
    private SquareView[][] squareViews;
    private BoardContainerView parentView;
    private int squareSize=1;
    private Paint boardPaint;

    public DrawableBoard(BoardContainerView parentView) {
        super();
        this.parentView = parentView;
        this.boardPaint = createCheckerBoard(60);
    }

    @Override
    public void draw(Canvas canvas) {
        if (squareViews == null)
            return;

        Rect bounds = getBounds();
        canvas.drawRect(bounds.left,bounds.top,bounds.right,bounds.bottom,boardPaint);
        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 8; col++)
                squareViews[row][col].draw(canvas);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter cf) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }

    protected void squareSelectedAt(int row, int col) {
        if (lastSelectedSquareView == null) {
            selectSquareIfNotEmpty(row, col);
        } else if (lastSelectedSquareView == squareViews[row][col]) {
            clearSelection();
        } else {
            dispatchNewMoveIfPassesFilters(row, col);
            clearSelection();
        }
    }

    private void dispatchNewMoveIfPassesFilters(int row, int col) {
        String source = lastSelectedSquareView.getName();
        String dest = squareViews[row][col].getName();
        parentView.moveDetected(source,dest);
        //doMove(toMake);
    }

    private void selectSquareIfNotEmpty(int row, int col) {
        if (!squareIsEmpty(row, col)) {
            lastSelectedSquareView = squareViews[row][col];
            lastSelectedSquareView.setSelected(true);
        }
    }

    private boolean squareIsEmpty(int row, int col) {
        return squareViews[row][col].isEmpty();
    }

    private void clearSelection() {
        lastSelectedSquareView.setSelected(false);
        lastSelectedSquareView = null;
    }

    public void createInitialBoard() {
        try {
            Typeface font = FontLoader.loadDefaultFont(parentView.getContext());
            squareViews = new DrawableBoardFactory(squareSize).createInitialBoard(font);
        } catch (FenParser.BadFenException e) {
            e.printStackTrace();
        }
    }

    public void createEmptyBoard(){
        squareViews = new DrawableBoardFactory(squareSize).createEmptyBoard();
    }

    public void setSquareSize(int size){
        this.squareSize = size;
        this.boardPaint = createCheckerBoard(size);
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                squareViews[i][j].resize(size,i,j);
    }

    public void setPosition(String FEN){
        try {
            clearBoard();
            Typeface typeface = FontLoader.loadDefaultFont(parentView.getContext());
            WhitePieceFactory whiteFactory = new WhitePieceFactory(typeface, squareSize);
            BlackPieceFactory blackPieceFactory = new BlackPieceFactory(typeface, squareSize);
            FenParser parser = new FenParser(squareViews , whiteFactory, blackPieceFactory);
            parser.parse(FEN);
        } catch (FenParser.BadFenException e) {
            e.printStackTrace();
        }
    }

    private void clearBoard(){
        for (int row = 0; row < 8; row++)
            for (int col = 0; col < 8; col++)
                squareViews[row][col].setPiece(null);
    }

    private Paint createCheckerBoard(int pixelSize){
        Bitmap bitmap = Bitmap.createBitmap(pixelSize * 2, pixelSize * 2, Bitmap.Config.ARGB_8888);

        Paint fill = new Paint(Paint.ANTI_ALIAS_FLAG);
        fill.setStyle(Paint.Style.FILL);
        fill.setColor(0x22000000);

        Canvas canvas = new Canvas(bitmap);
        Rect rect = new Rect(0, 0, pixelSize, pixelSize);
        canvas.drawRect(rect, fill);
        rect.offset(pixelSize, pixelSize);
        canvas.drawRect(rect, fill);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setShader(new BitmapShader(bitmap, BitmapShader.TileMode.REPEAT, BitmapShader.TileMode.REPEAT));
        return paint;
    }

}
