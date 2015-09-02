package com.example.dimitris.chesspuzzler.tests;

import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;
import com.example.dimitris.chesspuzzler.MoveChecker;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import dimitris.chessboardutils.Board;
import dimitris.chessboardutils.Move;

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
    private Move move1;
    private Move move2;
    private Move move3;
    private Move move4;

    @Before
    public void setup() {
        mockBoard = new MockBoard();
        movechecker = new MoveChecker(mockBoard);
        move1 = new Move("e3", "e4");
        move2 = new Move("d5", "d4");
        move3 = new Move("g1", "f3");
        move4 = new Move("g8", "f6");
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

        mockBoard.playMove(move1);
        assertGameIsOngoing();

        mockBoard.playMove(move2);
        assertGameEnded();
    }

    private void assertGameEnded() {
        assertTrue(mockBoard.onGameEndedWasCalled);
        assertFalse(mockBoard.onMoveRejectedWasCalled);
    }


    @Test
    public void testFourHalfMoveSequence() {
        movechecker.initializeWithMoveSequence(moveList);

        mockBoard.playMove(move1);
        assertGameIsOngoing();

        mockBoard.playMove(move2);
        assertGameIsOngoing();

        mockBoard.playMove(move3);
        assertGameIsOngoing();

        mockBoard.playMove(move4);
        assertGameEnded();
    }


    @Test
    public void testRejectsInvalidMove() {
        movechecker.initializeWithMoveSequence(moveList);

        mockBoard.playMove(move1);
        assertGameIsOngoing();

        Move invalidMove = new Move("a1", "a8");
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



    private class MockBoard extends Board {
        public boolean onGameEndedWasCalled = false;
        public boolean onMoveRejectedWasCalled = false;

        @Override
        public void onGameDidEnd() {
            super.onGameDidEnd();
            onGameEndedWasCalled = true;
        }

        @Override
        public void moveRejected(Move moveRejected) {
            super.moveRejected(moveRejected);
            onMoveRejectedWasCalled = true;
        }
    }

}

