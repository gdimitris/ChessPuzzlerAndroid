package dimitris.android.chessviews;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BoardContainerView extends View implements View.OnTouchListener {

    protected DrawableBoard chessBoard;
    private int squareSize;
    private int padding;

    public BoardContainerView(Context context) {
        super(context);
        setOnTouchListener(this);
        chessBoard = new DrawableBoard(context);
    }

    public BoardContainerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
        chessBoard = new DrawableBoard(context);
    }

    public BoardContainerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOnTouchListener(this);
        chessBoard = new DrawableBoard(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (chessBoard != null)
            chessBoard.draw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int totalWidth = getMeasuredWidth();
        int totalHeight = getMeasuredHeight();

        setMeasuredDimension(totalWidth, totalWidth);
        initBoard(totalWidth, totalHeight);
    }

    private void initBoard(int width, int height) {
        int min = (width < height) ? width : height;
        squareSize = min / 8;
        padding = (min % 8) / 2;

        chessBoard.setSquareSize(squareSize);
        chessBoard.createInitialBoard();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            int xCoord = (int) motionEvent.getX();
            int yCoord = (int) motionEvent.getY();

            int row = (yCoord - padding) / squareSize;
            int col = (xCoord - padding) / squareSize;

            chessBoard.squareSelectedAt(row, col);
        }

        invalidate();
        return true;
    }

    public void setBoardPosition(String Fen){
        this.chessBoard.setPosition(Fen);
    }
}