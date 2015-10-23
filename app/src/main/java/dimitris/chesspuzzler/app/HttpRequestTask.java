package dimitris.chesspuzzler.app;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dimitris on 10/23/15.
 */
public class HttpRequestTask extends AsyncTask<String, String, String> {


    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection connection = null;
        try{
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(connection.getInputStream());
            Log.e("HttpRequestTask", getStringFromInputStream(in));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

        return "This is the result";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.e("HttpRequestTask", "Result of task: "+s);
    }

    // convert InputStream to String
    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
}
