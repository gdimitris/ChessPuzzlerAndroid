package dimitris.android.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dimitris.chess.core.ChessPuzzle;
import dimitris.chess.core.PuzzleProvider;
import dimitris.chess.core.PuzzleReceiver;

/**
 * Created by dimitris on 1/15/16.
 */
public class TemporaryPuzzleProvider implements PuzzleProvider {
    private List<ChessPuzzle> puzzleList;
    private Random random;
    private PuzzleReceiver puzzleReceiver;

    public TemporaryPuzzleProvider(){
        this.puzzleList = new ArrayList<>();
        initializePuzzles();
        this.random = new Random();
        random.setSeed(System.currentTimeMillis());
    }

    @Override
    public void setPuzzleReceiver(PuzzleReceiver receiver) {
        this.puzzleReceiver = receiver;
    }

    @Override
    public void requestNextPuzzle() {
        int index = random.nextInt(puzzleList.size());
        puzzleReceiver.onPuzzleReady(puzzleList.get(index));
    }

    private void initializePuzzles(){
        ChessPuzzle puzzle1 = new ChessPuzzle("4kb1r/p2n1ppp/4q3/4p1B1/4P3/1Q6/PPP2PPP/2KR4 w k - 1 0", "1. Qb8+ Nxb8 2. Rd8# ");
        ChessPuzzle puzzle2 = new ChessPuzzle("r1b2k1r/ppp1bppp/8/1B1Q4/5q2/2P5/PPP2PPP/R3R1K1 w - - 1 0", "1. Qd8+ Bxd8 2. Re8# ");
        ChessPuzzle puzzle3 = new ChessPuzzle("5rkr/pp2Rp2/1b1p1Pb1/3P2Q1/2n3P1/2p5/P4P2/4R1K1 w - - 1 0", "1. Qxg6+ fxg6 2. Rg7# ");
        ChessPuzzle puzzle4 = new ChessPuzzle("1r1kr3/Nbppn1pp/1b6/8/6Q1/3B1P2/Pq3P1P/3RR1K1 w - - 1 0","1. Qxd7+ Kxd7 2. Bb5# ");
        ChessPuzzle puzzle5 = new ChessPuzzle("5rk1/1p1q2bp/p2pN1p1/2pP2Bn/2P3P1/1P6/P4QKP/5R2 w - - 1 0","1. Qxf8+ Bxf8 2. Rxf8# ");
        ChessPuzzle puzzle6 = new ChessPuzzle("r1nk3r/2b2ppp/p3b3/3NN3/Q2P3q/B2B4/P4PPP/4R1K1 w - - 1 0","1. Qd7+ Bxd7 2. Nxf7# ");
        ChessPuzzle puzzle7 = new ChessPuzzle("6k1/pp4p1/2p5/2bp4/8/P5Pb/1P3rrP/2BRRN1K b - - 0 1", "1... Rg1+ 2. Kxg1 Rxf1#");

        puzzleList.add(puzzle1);
        puzzleList.add(puzzle2);
        puzzleList.add(puzzle3);
        puzzleList.add(puzzle4);
        puzzleList.add(puzzle5);
        puzzleList.add(puzzle6);
        puzzleList.add(puzzle7);
    }
}
