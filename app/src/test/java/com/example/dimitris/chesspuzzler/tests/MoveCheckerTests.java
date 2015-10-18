package com.example.dimitris.chesspuzzler.tests;

import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;
import dimitris.chesspuzzler.app.MoveChecker;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import dimitris.chessboardutils.Move;
import dimitris.chessboardutils.MoveFactory;
import dimitris.chessboardutils.tests.MockBoard;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by dimitris on 9/1/15.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class MoveCheckerTests {

    private MoveChecker movechecker;
    private MockBoard mockBoard;
    private ArrayList<Move> moveList;
    private MoveFactory factory;
    private Move move1;
    private Move move2;
    private Move move3;
    private Move move4;

    @Before
    public void setup() {
        mockBoard = new MockBoard();
        movechecker = new MoveChecker(mockBoard);
        factory = new MoveFactory(mockBoard);
        move1 = factory.createMove("e3", "e4");
        move2 = factory.createMove("d5", "d4");
        move3 = factory.createMove("g1", "f3");
        move4 = factory.createMove("g8", "f6");
        moveList = new ArrayList<>();
        moveList.add(move1);
        moveList.add(move2);
        moveList.add(move3);
        moveList.add(move4);
    }

    @Test
    public void testTwoHalfMoveSequence() {
        moveList.clear();
        moveList.add(move1);
        moveList.add(move2);
        movechecker.initializeWithMoveSequence(moveList);
        playMovesAndAssertGame();
    }

    @Test
    public void testFourHalfMoveSequence() {
        movechecker.initializeWithMoveSequence(moveList);
        playMovesAndAssertGame();
    }

    @Test
    public void testRejectsInvalidMove() {
        movechecker.initializeWithMoveSequence(moveList);

        mockBoard.playMove(move1);
        assertGameIsOngoing();

        Move invalidMove = factory.createMove("a1", "a8");
        mockBoard.playMove(invalidMove);
        assertMoveIsRejected();
    }

    private void assertMoveIsRejected() {
        assertFalse(mockBoard.onGameEndedWasCalled);
        assertTrue(mockBoard.onMoveRejectedWasCalled);
    }

    private void assertGameIsOngoing() {
        assertFalse(mockBoard.onGameEndedWasCalled);
        assertFalse(mockBoard.onMoveRejectedWasCalled);
    }

    private void assertGameEnded() {
        assertTrue(mockBoard.onGameEndedWasCalled);
        assertFalse(mockBoard.onMoveRejectedWasCalled);
    }

    private void playMovesAndAssertGame() {
        int size = moveList.size();

        for(int i=0; i<size; i++){
            mockBoard.playMove(moveList.get(i));

            if(i == (size - 1) )
                assertGameEnded();
            else
                assertGameIsOngoing();
        }
    }
}

