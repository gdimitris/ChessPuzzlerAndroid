package com.example.dimitris.chesspuzzler.tests;

import android.os.Build;

import com.example.dimitris.chesspuzzler.BuildConfig;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import dimitris.chessboardutils.Board;
import dimitris.chessboardutils.Move;

/**
 * Created by dimitris on 9/1/15.
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class MoveCheckerTests {

    private MoveChecker movechecker;
    private Board mockBoard;

    @Before
    public void setup(){
        movechecker = new MoveChecker();
        mockBoard = new Board();
    }



    private class MoveChecker {

        private ArrayList<Move> solution;
        private ArrayList<Move> playedMoves;

        public MoveChecker(){
            playedMoves = new ArrayList<>();
        }

        public void initializeWithMoveSequence(ArrayList<Move> sequence){
            this.solution = sequence;
            this.playedMoves.clear();
        }
    }

}

