package dimitris.android.app;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import dimitris.chess.core.ChessPuzzle;

/**
 * Created by dimitris on 10/24/15.
 */
public class JSONPuzzleParser {

    public static ChessPuzzle parse(InputStream stream){
        ChessPuzzle puzzle = null;
        try{
            JsonReader reader = new JsonReader(new InputStreamReader(stream,"UTF-8"));
            puzzle = readPuzzle(reader);
        } catch (IOException e){
            e.printStackTrace();
        }

        return puzzle;
    }

    private static ChessPuzzle readPuzzle(JsonReader reader) throws IOException{

        ChessPuzzle puzzle = new ChessPuzzle();

        reader.beginObject();
        while (reader.hasNext()){
            String key = reader.nextName();

            if(key.equals("id"))
                puzzle.id = reader.nextString();
            else if (key.equals("fen"))
                puzzle.fen = reader.nextString();
            else if (key.equals("description"))
                puzzle.description = reader.nextString();
            else if (key.equals("solution"))
                puzzle.solution = reader.nextString();
            else
                reader.skipValue();
        }
        reader.endObject();

        return puzzle;
    }
}
