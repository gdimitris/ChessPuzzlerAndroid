package dimitris.chesspuzzler.app;

import dimitris.android.chessviews.Pieces.Piece;

import static dimitris.android.chessviews.Pieces.Piece.PieceColor.Black;
import static dimitris.android.chessviews.Pieces.Piece.PieceColor.White;

/**
 * Created by dimitris on 10/22/15.
 */
public class MoveValidator implements  MoveObserver {

    private Piece.PieceColor colorToPlay;
    private MoveSubject subject;

    public MoveValidator(MoveSubject subject){
        colorToPlay = White;
        this.subject = subject;
        this.subject.registerMoveObserver(this);
    }

    public boolean isValid(Move move){
        return (move.getMoveColor() == colorToPlay);
    }

    @Override
    public void onMoveDo(Move move) {
        //updatePrivateBoardState();
        colorToPlay = (colorToPlay == White) ? Black : White;
    }

    @Override
    public void onMoveUndo() {

    }

    @Override
    public void onMoveRedo() {

    }
}
