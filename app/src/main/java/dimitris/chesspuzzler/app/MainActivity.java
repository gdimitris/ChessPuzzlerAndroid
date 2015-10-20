package dimitris.chesspuzzler.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dimitris.chesspuzzler.R;


public class MainActivity extends Activity implements View.OnClickListener{

    private EditText sourceEditText;
    private EditText destEditText;
    private Button sendMoveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //printBoard();
        sourceEditText = (EditText) findViewById(R.id.sourceTextField);
        destEditText = (EditText) findViewById(R.id.destTextField);
        sendMoveButton = (Button) findViewById(R.id.sendMoveButton);
        sendMoveButton.setOnClickListener(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.board) {
            Intent my_int = new Intent(this, TestActivity.class);
            startActivity(my_int);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.sendMoveButton) {
            submitMove();

        }
    }

    private void submitMove(){
        String source = sourceEditText.getText().toString();
        String dest =  destEditText.getText().toString();

    }

}
