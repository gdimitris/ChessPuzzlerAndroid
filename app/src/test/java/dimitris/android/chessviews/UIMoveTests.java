package dimitris.android.chessviews;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import dimitris.android.chessviews.Pieces.Piece;
import dimitris.android.chessviews.Pieces.WhitePieceFactory;

/**
 * Created by dimitris on 10/02/16.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class UIMoveTests {

    private Square src;
    private Square dest;
    private Piece knight;
    private Piece bishop;
    private WhitePieceFactory whitePieceFactory;
    private UIMove move;

    @Before
    public void setup(){
        Context mockContext = RuntimeEnvironment.application.getApplicationContext();
        Typeface typeface = FontLoader.loadDefaultFont(mockContext);
        whitePieceFactory = new WhitePieceFactory(typeface,1);
        knight = whitePieceFactory.createKnight();
        bishop = whitePieceFactory.createBishop();
        src = new Square(1,2);
        src.setPiece(knight);
        dest = new Square(2,3);
        dest.setPiece(bishop);
        move = new UIMove(src,dest);
    }

    @Test
    public void testDoMove(){
        move.execute();
        Assert.assertTrue(move.isExecuted());
        Assert.assertEquals(knight,move.getMovedPiece());
        Assert.assertEquals(bishop, move.getCapturedPiece());
        Assert.assertTrue(move.isCaptureMove());
    }

    @Test
    public void testUndoMove(){
        move.execute();
        move.undo();
        Assert.assertFalse(move.isExecuted());
        Assert.assertTrue(move.isCaptureMove());
        Assert.assertEquals(knight, move.getMovedPiece());
        Assert.assertEquals(bishop, move.getCapturedPiece());
    }
}
