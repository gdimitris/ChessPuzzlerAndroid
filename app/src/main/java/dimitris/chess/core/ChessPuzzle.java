package dimitris.chess.core;

/**
 * Created by dimitris on 10/24/15.
 */
public class ChessPuzzle {

    private final String id;
    private final String fen;
    private final String solution;
    private final String description;
    private final String reviewId;
    private final float easinessFactor;
    private final int reviewInterval;
    private final String nextReview;
    private final String lastReviewed;


    private ChessPuzzle(ChessPuzzleBuilder builder){
        this.id = builder.id;
        this.fen = builder.fen;
        this.solution = builder.solution;
        this.description = builder.description;
        this.reviewId = builder.reviewId;
        this.easinessFactor = builder.easinessFactor;
        this.reviewInterval = builder.reviewInterval;
        this.nextReview = builder.nextReview;
        this.lastReviewed = builder.lastReviewed;
    }

    public boolean isWhitePuzzle(){
        String[] tokens = fen.split(" ");
        return tokens[1].equals("w");
    }

    public String getId(){
        return this.id;
    }

    public String getFen(){
        return this.fen;
    }

    public String getSolution(){
        return this.solution;
    }

    public String getReviewId(){
        return this.reviewId;
    }

    public String getDescription(){
        return this.description;
    }

    public float getEasinessFactor(){
        return this.easinessFactor;
    }

    public int getReviewInterval(){
        return this.reviewInterval;
    }

    public String getNextReview(){
        return this.nextReview;
    }

    public String getLastReviewed(){
        return this.lastReviewed;
    }


    public static class ChessPuzzleBuilder{
        private String description;
        private final String fen;
        private final String solution;
        private final String id;
        private String reviewId;
        private float easinessFactor;
        private int reviewInterval;
        private String nextReview;
        private String lastReviewed;

        public ChessPuzzleBuilder(String id, String fen, String solution){
            this.id = id;
            this.fen = fen;
            this.solution = solution;
        }

        public ChessPuzzleBuilder setDescription(String description){
            this.description = description;
            return this;
        }

        public ChessPuzzleBuilder setReviewId(String reviewId){
            this.reviewId = reviewId;
            return this;
        }

        public ChessPuzzleBuilder setEasinessFactor(float easinessFactor){
            this.easinessFactor = easinessFactor;
            return this;
        }

        public ChessPuzzleBuilder setReviewInterval(int reviewInterval){
            this.reviewInterval = reviewInterval;
            return this;
        }

        public ChessPuzzleBuilder setNextReview(String nextReview){
            this.nextReview = nextReview;
            return this;
        }

        public ChessPuzzleBuilder setLastReviewed(String lastReviewed){
            this.lastReviewed = lastReviewed;
            return this;
        }

        public ChessPuzzle build(){
            return new ChessPuzzle(this);
        }
    }
}
