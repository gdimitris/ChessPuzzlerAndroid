package dimitris.chessboardutils.tests;

import dimitris.chessboardutils.Board;
import dimitris.chessboardutils.Move;

/**
 * Created by dimitris on 9/3/15.
 */

public class MockBoard extends Board {
    public boolean onGameEndedWasCalled = false;
    public boolean onMoveRejectedWasCalled = false;

    @Override
    public void onGameDidEnd() {
        super.onGameDidEnd();
        onGameEndedWasCalled = true;
    }

    @Override
    public void rejectMove(Move moveRejected) {
        super.rejectMove(moveRejected);
        onMoveRejectedWasCalled = true;
    }
}
