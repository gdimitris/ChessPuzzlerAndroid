package dimitris.chesspuzzler.app;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import dimitris.android.chessviews.DrawableBoard;

/**
 * Created by dimitris on 10/23/15.
 */
public class HttpRequestTask extends AsyncTask<String, Void, InputStream> {

    private DrawableBoard board;

    public HttpRequestTask(DrawableBoard board){
        this.board = board;
    }

    @Override
    protected InputStream doInBackground(String... params) {
        HttpURLConnection connection = null;
        InputStream stream = null;
        try{
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            stream = new BufferedInputStream(connection.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

        return stream;
    }

    @Override
    protected void onPostExecute(InputStream s) {
        super.onPostExecute(s);
        ChessPuzzle puzzle = PuzzleParser.parse(s);
        board.setPosition(puzzle.fen);
    }
}
