package dimitris.android.app;

import java.util.List;
import java.util.Queue;

import dimitris.chess.core.ChessPuzzle;
import dimitris.chess.core.PuzzleProvider;
import dimitris.chess.core.PuzzleReceiver;

/**
 * Created by dimitris on 4/3/16.
 */
public class ListPuzzleProvider implements PuzzleProvider {

    private PuzzleReceiver receiver;
    private List<ChessPuzzle> puzzleList;

    public ListPuzzleProvider(List puzzleList){
        this.puzzleList = puzzleList;
    }

    @Override
    public void setPuzzleReceiver(PuzzleReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void requestNextPuzzle() {
        receiver.onPuzzleReady(puzzleList.remove(puzzleList.size()-1));
    }
}
