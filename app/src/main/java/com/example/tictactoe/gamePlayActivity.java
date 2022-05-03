package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class gamePlayActivity extends AppCompatActivity implements View.OnClickListener {
    private  TextView playerOneScore,playerTwoScore,PlayerStatus;
    private Button[] buttons=new Button[9];
    private Button resetGame;

//    playerOneSC = Score count of playerOne

    private  int playerOneSC,playerTwoSC,roundCount;
    boolean activePlayer;

    //    p1=0
//    p2=1
//  empty =2
    int[] gameState ={2,2,2,2,2,2,2,2,2,};

    int [][] winningPositions ={
            {0,1,2},{3,4,5},{6,7,8},// rows
            {0,3,6},{1,4,7},{2,5,8},// columns
            {0,4,8},{2,4,6}//cross
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        playerOneScore =(TextView) findViewById(R.id.tvPlayer1sc);
        playerTwoScore =(TextView) findViewById(R.id.tvPlayer2sc);
        PlayerStatus =(TextView) findViewById(R.id.playerStatues);

        resetGame =(Button) findViewById(R.id.ResetBtn);

        for(int i =0;i<buttons.length;i++){
            String buttonId ="boxBtn" + i;
            int resourceID =getResources().getIdentifier(buttonId,"id",getPackageName());
            buttons[i]=(Button) findViewById(resourceID);
            buttons[i].setOnClickListener(this);
        }
        roundCount =0;
        playerOneSC=0;
        playerTwoSC=0;
        activePlayer=true;

    }


    @Override
    public void onClick(View view) {
        if(!((Button)view).getText().toString().equals("")){
            return;
        }
        String buttonID = view.getResources().getResourceEntryName(view.getId());
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length()-1,buttonID.length()));

        if(activePlayer){
            ((Button)view).setText("X");
            ((Button)view).setTextColor(Color.parseColor("#0FB7AB"));
            gameState[gameStatePointer]=0;
        }else{
            ((Button)view).setText("O");
            ((Button)view).setTextColor(Color.parseColor("#DC9E21"));
            gameState[gameStatePointer]=1;
        }
        roundCount++;
        if(checkWinner()){
            if(activePlayer){
                playerOneSC++;
                updatePlayerScore();
                Toast.makeText(this, "Player One is Won", Toast.LENGTH_SHORT).show();
                playAgain();
            }else{
                playerTwoSC++;
                updatePlayerScore();
                Toast.makeText(this, "Player Two is Won", Toast.LENGTH_SHORT).show();
                playAgain();
            }
        }else if(roundCount==9){
            playAgain();
            Toast.makeText(this, "No Winner", Toast.LENGTH_SHORT).show();

        }else{
            activePlayer=!activePlayer;
        }
        if (playerOneSC > playerTwoSC) {
            PlayerStatus.setText("Player One is Winning");
        }else if(playerTwoSC>playerOneSC){
            PlayerStatus.setText("Player Two is Winning");
        }else{
            PlayerStatus.setText(" Draw ");
        }
        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain();
                playerOneSC=0;
                playerTwoSC=0;
                PlayerStatus.setText("");
                updatePlayerScore();
            }
        });
    }
    public  boolean checkWinner(){
        boolean winnerResult = false;

        for(int [] winningPosition : winningPositions){
            if(gameState[winningPosition[0]]== gameState[winningPosition[1]]
                    && gameState[winningPosition[1]]==gameState[winningPosition[2]]
                    && gameState[winningPosition[0]]!=2){
                winnerResult=true;
            }
        }
        return winnerResult;
    }
    public void updatePlayerScore(){
        playerOneScore.setText(Integer.toString(playerOneSC));
        playerTwoScore.setText(Integer.toString(playerTwoSC));
    }
    public void playAgain(){
        roundCount=0;
        activePlayer=true;
        for(int i =0;i<buttons.length;i++){
            gameState[i]=2;
            buttons[i].setText("");
        }
    }

}