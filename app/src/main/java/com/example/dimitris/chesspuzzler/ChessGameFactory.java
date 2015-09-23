package com.example.dimitris.chesspuzzler;

import dimitris.chessboardutils.BoardFactory;
import dimitris.chessboardutils.GameInfo;

/**
 * Created by dimitris on 9/24/15.
 */
public class ChessGameFactory {
    private static final String INITIAL_FEN_POSITION = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    public static ChessGame createInitialPosition() {
        return ChessGameFactory.createFromFEN(INITIAL_FEN_POSITION);
    }

    public static ChessGame createFromFEN(String FEN) {
        String[] tokens = FEN.split(" ", 2);
        String boardRepresentation = tokens[0];
        String gameInfo = tokens[1];

        ChessGame game = new ChessGame();
        game.board = BoardFactory.create(boardRepresentation);
        game.gameInfo = new GameInfo(gameInfo);
        return game;
    }
}