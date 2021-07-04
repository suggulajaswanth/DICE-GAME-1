package com.example.dicegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.SecureRandom;

public class MainActivity extends AppCompatActivity {

private static final SecureRandom random =new SecureRandom();
//NAMED INTEGER CONSTANTS
private enum Status{
    NOTSTARTEDYET,PROCEED,WON,LOST
};
private static final int TIGER_CLAWS =2;
//'STATIC' -->WE CAN GET THE ACCESS TO THESE CONSTANTS ANYWHERE MY USING CLASS.CONSTANT I.E MAINACTIVITIY.TIGER_CLAWS
private static final int THREE =3;
    private static final int SEVEN =7;
    private static final int ELEVEN =11;
    private static final int PANTHER =12;
    TextView text;
    TextView text3;
    ImageView image;
    Button button;
    String oldTxtCalculationsValue="";
    Boolean firstTime=true;
    Status gamestatus=Status.NOTSTARTEDYET;
    int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(TextView)findViewById(R.id.text);
        image=(ImageView)findViewById(R.id.image);
        button=(Button)findViewById(R.id.button);
        final TextView status=(TextView)findViewById(R.id.status);
        makeBtnRestartInvisible();
        //status.setText("");
        //text.setText("");
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firstTime){
                    status.setText("");
                    text.setText("");
                    firstTime=false;

                }
                if (gamestatus == Status.NOTSTARTEDYET) {
                    int diceSum = letsRollDice();
                    oldTxtCalculationsValue = text.getText().toString();
                    points = 0;
                    switch (diceSum) {
                        case SEVEN:
                        case ELEVEN:
                            gamestatus = Status.WON;
                            status.setText("YOU WIN!");
                            makeImgDiceInvisible();
                            makeBtnRestartvisible();
                            break;
                        case THREE:
                        case 5:
                        case 9:
                            gamestatus = Status.LOST;
                            status.setText("YOU LOST!");
                            makeImgDiceInvisible();
                            makeBtnRestartvisible();
                            break;

                        case TIGER_CLAWS://2
                        case 4:
                        case 6:
                        case 8:
                        case 10:
                        case PANTHER://12
                            gamestatus = Status.PROCEED;
                            points = diceSum;
                            text.setText(oldTxtCalculationsValue + "Your Point is :" + points + "\n");
                            status.setText("Continue the game");
                            oldTxtCalculationsValue = "YOUR POINT IS " + points + "\n";
                            break;


                    }
                    return;

                }
                if (gamestatus == Status.PROCEED) {
                    int dicesum = letsRollDice();
                    if (dicesum == points) {
                        gamestatus = Status.WON;
                        status.setText("YOU WON!");
                        makeImgDiceInvisible();
                        makeBtnRestartvisible();

                    } else if (dicesum == SEVEN || dicesum == ELEVEN) {
                        gamestatus = Status.LOST;
                        status.setText("YOU LOST!");
                        makeImgDiceInvisible();
                        makeBtnRestartvisible();
                    }
                }

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gamestatus =Status.NOTSTARTEDYET;
                status.setText("");
                text.setText("");
                oldTxtCalculationsValue="";
                makeImgDiceVisible();
                makeBtnRestartInvisible();
            }
        });

    }

    private int letsRollDice() {
        int randDie1=1+random.nextInt(6);
        int randDie2=1+random.nextInt(6);
        int sum=randDie1+randDie2;
        text.setText(String.format(oldTxtCalculationsValue
        + "YOU ROLLED %d + %d = %d%n",randDie1,randDie2,sum));
        return sum;
    }

    //USAGE OF ENCAPSULATION
    private void makeImgDiceInvisible(){
        image.setVisibility(View.INVISIBLE);
    }
    private void makeBtnRestartInvisible(){
        button.setVisibility(View.INVISIBLE);

    }
    private void makeImgDiceVisible(){
        image.setVisibility(View.VISIBLE);
    }
    private void makeBtnRestartvisible(){
        button.setVisibility(View.VISIBLE);

    }
}