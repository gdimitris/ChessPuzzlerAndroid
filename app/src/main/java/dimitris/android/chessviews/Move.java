package dimitris.android.chessviews;

import dimitris.android.chessviews.Pieces.Piece;


public class Move {
    private SquareView source;
    private SquareView destination;
    private Piece captured;


    public Move(SquareView source, SquareView destination) {
        this.source = source;
        this.destination = destination;
    }

    public void execute() {
        captured = destination.getPiece();
        destination.setPiece(source.getPiece());
        source.setPiece(null);
    }

    public void undo() {
        source.setPiece(destination.getPiece());
        destination.setPiece(captured);
        captured = null;
    }

    @Override
    public boolean equals(Object o) {
        Move toCheck = (Move) o;

        if (source.equals(toCheck.source) && destination.equals(toCheck.destination))
            return true;
        return false;
    }

//    TODO
//    @Override
//    public String toString() {
//        return currentState.toString();
//    }

    public Piece.PieceColor getMoveColor() {
        return destination.getPiece().getColor();
    }

//    public Piece.PieceColor getCapturedPieceColor() {
//        if (captured != null)
//            return captured.getColor();
//        return Pie
//    }

    public boolean isCapture() {
        return (captured != null);
    }

//    public boolean enemyPieceCanBeCaptured() {
//        return currentState.enemyPieceCanBeCaptured();
//    }

//    private interface MoveState {
//        public boolean isCapture();
//
//        public boolean enemyPieceCanBeCaptured();
//
//        public Piece.PieceColor getCapturedPieceColor();
//
//        public Piece.PieceColor getMoveColor();
//
//        public String toString();
//    }
//
//    private class NotExecutedMoveState implements MoveState {
//
//        @Override
//        public boolean isCapture() {
//            return (destination.getPiece() != null);
//        }
//
//        @Override
//        public boolean enemyPieceCanBeCaptured() {
//            return destination.getPiece().canBeCaptured();
//        }
//
//        @Override
//        public PieceColor getCapturedPieceColor() {
//            return destination.getPiece().getColor();
//        }
//
//        @Override
//        public PieceColor getMoveColor() {
//            return source.getPiece().getColor();
//        }
//
//        @Override
//        public String toString() {
//            return "Not executed move";
//        }
//    }
//
//    private class ExecutedMoveState implements MoveState {
//
//        @Override
//        public boolean isCapture() {
//            return (captured != null) ? true : false;
//        }
//
//        @Override
//        public boolean enemyPieceCanBeCaptured() {
//            //throw new MoveAlreadyExecutedException();
//            Log.e("Executed Move State", "Should not be here");
//            return (captured != null);
//        }
//
//        @Override
//        public PieceColor getCapturedPieceColor() {
//            return captured.getColor();
//        }
//
//        @Override
//        public PieceColor getMoveColor() {
//            return destination.getPiece().getColor();
//        }
//
//        @Override
//        public String toString() {
//            String toReturn = destination.getPiece().toString();
//            toReturn += (captured == null) ? "" : "x";
//            toReturn += destination.toString();
//            return toReturn;
//        }
//    }
}
