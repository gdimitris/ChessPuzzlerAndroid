package com.example.dimitris.chesspuzzler;

import java.util.ArrayList;

import dimitris.chessboardutils.Board;
import dimitris.chessboardutils.Move;
import dimitris.chessboardutils.MoveObserver;

/**
 * Created by dimitris on 9/3/15.
 */
public class MoveChecker implements MoveObserver {
    private int currentMoveIndex;
    private ArrayList<Move> solution;
    private ArrayList<Move> playedMoves;
    private Board boardInstance;

    public MoveChecker(Board board) {
        playedMoves = new ArrayList<>();
        boardInstance = board;
        boardInstance.registerMoveObserver(this);
    }

    public void initializeWithMoveSequence(ArrayList<Move> sequence) {
        this.solution = sequence;
        this.playedMoves.clear();
        currentMoveIndex = 0;
    }

    @Override
    public void onMovePlayed(Move movePlayed) {

        if (solution.get(currentMoveIndex).equals(movePlayed)) {
            playedMoves.add(movePlayed);
            currentMoveIndex++;
            checkGameStatus();
        } else {
            boardInstance.rejectMove(movePlayed);
        }

    }

    @Override
    public void onMoveRejected(Move moveRejected) {

    }

    private void checkGameStatus() {
        if (gameHasEnded()) {
            boardInstance.onGameDidEnd();
        }
    }

    private boolean gameHasEnded() {
        return playedMoves.equals(solution);
    }
}