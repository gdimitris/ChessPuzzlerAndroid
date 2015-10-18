package dimitris.chesspuzzler.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dimitris.chesspuzzler.R;

import dimitris.chessboardutils.Board;
import dimitris.chessboardutils.BoardFactory;
import dimitris.chessboardutils.MoveFactory;
import dimitris.chessboardutils.MovePrinter;


public class MainActivity extends Activity implements View.OnClickListener{

    private EditText sourceEditText;
    private EditText destEditText;
    private Button sendMoveButton;
    private Board board;
    private MoveFactory factory;
    private MovePrinter movePrinter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeBoard();
        //printBoard();
        sourceEditText = (EditText) findViewById(R.id.sourceTextField);
        destEditText = (EditText) findViewById(R.id.destTextField);
        sendMoveButton = (Button) findViewById(R.id.sendMoveButton);
        sendMoveButton.setOnClickListener(this);
    }

    private void printBoard() {
        Log.e("GAME", board.toString());
        Log.e("GAME", movePrinter.printMovesPlayed());
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
            printBoard();
        }
    }

    private void submitMove(){
        String source = sourceEditText.getText().toString();
        String dest =  destEditText.getText().toString();

        board.playMove(factory.createMove(source,dest));
    }

    private void initializeBoard(){
        this.board = BoardFactory.create("r2qkb1r/pp2nppp/3p4/2pNN1B1/2BnP3/3P4/PPP2PPP/R2bK2R");
        this.factory = new MoveFactory(board);
        this.movePrinter = new MovePrinter(board);
    }

}
