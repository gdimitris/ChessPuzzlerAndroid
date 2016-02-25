package dimitris.chess.core;

/**
 * Created by enviouscreep on 1/16/16.
 */
interface GameEventsDispatcher {
    void registerGameEventsListener(PuzzleGameEventsListener toRegister);
    void removeGameEventsListener(PuzzleGameEventsListener toRemove);
}
