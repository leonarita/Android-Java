package com.example.app1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NumberGuessGame extends AppCompatActivity {

    Button btnNewGame, btnGuess;
    EditText inputGuess;
    TextView gameMessage;

    boolean gameFinished = false;
    int secretNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_guess_game);

        btnGuess = findViewById(R.id.btnGuess);
        btnNewGame = findViewById(R.id.btnNumberGuessGame);
        inputGuess = findViewById(R.id.inputGuess);
        gameMessage = findViewById(R.id.textGameMessage);

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionNewGame();
            }
        });

        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processGuess();
            }
        });

        newGame();
    }

    private void processGuess () {

        String strGuess = inputGuess.getText().toString();
        inputGuess.setText("");

        if (strGuess.length() == 0)
            return;

        int guess = Integer.parseInt(strGuess);

        if (guess > secretNumber) {
            gameMessage.setText(R.string.message_to_much);
        }
        else if (guess < secretNumber) {
            gameMessage.setText(R.string.message_to_low);
        }
        else {
            gameMessage.setText(R.string.message_win);
            gameFinished = true;
        }
    }

    private void actionNewGame () {

        if (gameFinished) {
            newGame();
        }
/*
        new AlertDialog.Builder(getActivity())
                .setMessage(R.string.confirm_new_game)
                .setCancelable(false)
                .setNegativeButton(R.string.no, null)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PlaceholderFragment.this.newGame();
                    }
                }).show();
 */
    }

    private void newGame () {
        secretNumber = (int) (Math.random() * 100);
        gameMessage.setText(R.string.message_welcome);
        inputGuess.setText("");
        gameFinished = false;
    }
}
