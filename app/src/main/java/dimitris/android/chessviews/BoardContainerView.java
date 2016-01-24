package dimitris.android.chessviews;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

import dimitris.android.app.MainActivity;
import dimitris.chess.core.ChessPuzzle;

public class BoardContainerView extends View implements View.OnTouchListener {

    protected DrawableBoard chessBoard;
    private int squareSize;
    private int padding;

    public BoardContainerView(Context context) {
        super(context);
        setOnTouchListener(this);
        chessBoard = new DrawableBoard(this);
    }

    public BoardContainerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(this);
        chessBoard = new DrawableBoard(this);
    }

    public BoardContainerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOnTouchListener(this);
        chessBoard = new DrawableBoard(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        chessBoard.draw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int totalWidth = getMeasuredWidth();
        int totalHeight = getMeasuredHeight();

        setMeasuredDimension(totalWidth, totalWidth);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        resizeBoard(w,h);
    }

    private void resizeBoard(int width, int height){
        int min = (width < height) ? width : height;
        squareSize = min / 8;
        padding = (min % 8) / 2;

        if(chessBoard.getSquareSize() != squareSize){
            chessBoard.setSquareSize(squareSize);
            chessBoard.setBounds(0,0,8 * squareSize,8 * squareSize);
            invalidate();
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            int xCoord = (int) motionEvent.getX();
            int yCoord = (int) motionEvent.getY();

            int row = (yCoord - padding) / squareSize;
            int col = (xCoord - padding) / squareSize;

            chessBoard.squareClickedAt(row, col);
            invalidate();
        }

        return true;
    }

    public void setCurrentPuzzle(ChessPuzzle puzzle){
        chessBoard.setPosition(puzzle.fen);
        invalidate();
    }

    public void moveDetected(String source, String dest){
        MainActivity parent = (MainActivity) this.getContext();
        parent.onMoveDetected(source, dest);
    }
}
