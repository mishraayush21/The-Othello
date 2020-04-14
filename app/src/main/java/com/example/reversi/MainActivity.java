package com.example.reversi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {


    private Button[][] buttons = new Button[8][8];
    private boolean player1Turn = true;

    private int roundCount;
    private int decision = 0;
    private  boolean x = true;
    private int p1=0,p2=0;
    private int player1Points;
    private int player2Points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    private String player1peg = "o";
    private String player2peg = "⚫";

    private TextView chance;

    private void setupUIViews()
    {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String buttonID = "button" + String.valueOf(i) + String.valueOf(j);
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] =(Button) findViewById(resID);
                buttons[i][j].setTextSize(24);
            }
        }

        textViewPlayer1 = findViewById(R.id.tv2);
        textViewPlayer2 = findViewById(R.id.tv3);

        chance = findViewById(R.id.tv4);

        buttons[3][3].setText("⚫");
        buttons[3][4].setText("o");
        buttons[4][4].setText("⚫");
        buttons[4][3].setText("o");

        chance.setTextSize(26);
        chance.setText("Chance : "+ player1peg);
        show_valid_chances(player1peg);
    }








        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUIViews();
            for (int i = 0; i < 8; i++) {


                for (int j = 0; j < 8; j++) {
                    final int ii = i;
                    final  int jj =j;

                    buttons[i][j].setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {




                            if (((Button) v).getText().toString().equals(player1peg) || ((Button) v).getText().toString().equals(player2peg) || ((Button) v).getText().toString().equals("")) {
                                return;
                            }


                            if (player1Turn) {
                                if(x == true)
                                {
                                    ((Button) v).setText(player1peg);
                                    turner(player1peg,ii,jj);
                                }
                                else
                                    player1Turn = !player1Turn;

                            }
                            else {
                                if(x == true)
                                {
                                    ((Button) v).setText(player2peg);
                                    turner(player2peg,ii,jj);
                                }
                                else
                                    player1Turn = !player1Turn;
                            }

                            roundCount++;
                                if (player1Turn)
                                {
                                  //  player1Wins();
                                    chance.setText("Chance : "+ player2peg);
                                    x = show_valid_chances(player2peg);


                                }
                                else {
                                  //  player2Wins();
                                    chance.setText("Chance : "+ player1peg);

                                    x = show_valid_chances(player1peg);

                                }


                                player1Turn = !player1Turn;


                        }
                    });



                }
            }

    }

    private boolean show_valid_chances(String currpeg)
    {

        for(int i = 0; i< 8; i++)
            for(int j = 0;j< 8; j++)
                if(buttons[i][j].getText().toString().equals("."))
                    buttons[i][j].setText("");


        String[][] field = new String[8][8];
        for(int i = 0; i< 8; i++)
        {
            for(int j= 0; j< 8;j++)
            {
                field[i][j] = "";
            }
        }

        p1=0;p2=0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                field[i][j] = buttons[i][j].getText().toString();
                if(field[i][j].equals(player1peg))
                    p1++;
                if(field[i][j].equals(player2peg))
                    p2++;
            }
        }

        textViewPlayer1.setTextSize(20);
        textViewPlayer2.setTextSize(20);
        textViewPlayer1.setText("Player "+player1peg+" : "+p1);
        textViewPlayer2.setText("Player "+player2peg+" : "+p2);



        for(int i = 0; i< 8; i++)
        {
            for(int j = 0; j< 8; j++)
            {
                if(field[i][j].equals(currpeg))
                {
                  insert_chances(field,i,j,currpeg);

                }
            }
        }

        String[][] field_temp = new String[8][8];
        int dotcount = 0;
        int blankcount = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                field_temp[i][j] = buttons[i][j].getText().toString();
                if(field_temp[i][j].equals("."))
                    dotcount++;
                if(field_temp[i][j].equals(""))
                    blankcount++;


            }
        }
        String notcurrpeg;
        if(currpeg.equals(player1peg))
            notcurrpeg = player2peg;
        else
            notcurrpeg = player1peg;

        //if(dotcount == 0){
          //  Toast.makeText(this, "Dradddddddddddddd!", Toast.LENGTH_SHORT).show();


            decision ++;



            if(decision == 2 || blankcount == 0)
            {

                declare_result();
                reset();


                return(false);
        }
        else
        {
            decision = 0;

            return (true);
        }

    }

    private void insert_chances(String field[][],int a,int b,String currpeg)
    {


        int i,j,t;
        //left
        if(b!=0)
        {t=0;
        if(!(field[a][b-1].equals(currpeg) ) && !(field[a][b-1].equals("")) )
        {
            for(j = b-1; j>=0 ; j-- )
            {  // System.out.print(j);
                if(field[a][j].equals(currpeg))
                {
                    t=1;
                    break;
                }

            }

            for(j = b-1 ; j>=0 && t == 0; j--)
            {
                if(field[a][j].equals("")){
                    buttons[a][j].setText(".");
                    break;
                }
            }

        }


        }//<- <- <-

        //right
        if(b!=7)
        {t=0;
            if(!(field[a][b+1].equals(currpeg) ) && !(field[a][b+1].equals("")) )
            {
                for(j = b+1; j<8 ; j++ )
                {  // System.out.print(j);
                    if(field[a][j].equals(currpeg))
                    {
                        t=1;
                        break;
                    }

                }

                for(j = b+1 ; j<8 && t == 0; j++)
                {
                    if(field[a][j].equals("")){
                        buttons[a][j].setText(".");
                        break;
                    }
                }

            }


        }//-> -> ->


        //up

        if(a!=0)
        {t=0;
            if(!(field[a-1][b].equals(currpeg) ) && !(field[a-1][b].equals("")) )
            {
                for(i = a-1; i>=0 ; i-- )
                {  // System.out.print(j);
                    if(field[i][b].equals(currpeg))
                    {
                        t=1;
                        break;
                    }

                }

                for(i = a-1 ; i>=0 && t == 0; i--)
                {
                    if(field[i][b].equals("")){
                        buttons[i][b].setText(".");
                        break;
                    }
                }

            }


        }
        //down
        if(a!=7)
        {t=0;
            if(!(field[a+1][b].equals(currpeg) ) && !(field[a+1][b].equals("")) )
            {
                for(i = a+1; i<8 ; i++ )
                {  // System.out.print(j);
                    if(field[i][b].equals(currpeg))
                    {
                        t=1;
                        break;
                    }

                }

                for(i = a+1 ; i<8 && t == 0; i++)
                {
                    if(field[i][b].equals("")){
                        buttons[i][b].setText(".");
                        break;
                    }
                }

            }


        }

        //left-up
        if(b!=0 && a != 0)
        {t=0;
            if(!(field[a-1][b-1].equals(currpeg) ) && !(field[a-1][b-1].equals("")) )
            {
                for(j = b-1,i=a-1; j>=0 && i >=0 ; i--,j-- )
                {  // System.out.print(j);
                    if(field[i][j].equals(currpeg))
                    {
                        t=1;
                        break;
                    }

                }

                for(j = b-1,i=a-1 ; j>=0&& i >=0 && t == 0;  i--,j--)
                {
                    if(field[i][j].equals("")){
                        buttons[i][j].setText(".");
                        break;
                    }
                }

            }


        }

        //left-down
        if(b!=7 && a != 7)
        {t=0;
            if(!(field[a+1][b+1].equals(currpeg) ) && !(field[a+1][b+1].equals("")) )
            {
                for(j = b+1,i=a+1; j<8 && i <8 ; i++,j++ )
                {  // System.out.print(j);
                    if(field[i][j].equals(currpeg))
                    {
                        t=1;
                        break;
                    }

                }

                for(j = b+1,i=a+1 ; j<8&& i <8 && t == 0;  i++,j++)
                {
                    if(field[i][j].equals("")){
                        buttons[i][j].setText(".");
                        break;
                    }
                }

            }


        }


        //right-up
        if(b!=7 && a != 0)
        {t=0;
            if(!(field[a-1][b+1].equals(currpeg) ) && !(field[a-1][b+1].equals("")) )
            {
                for(j = b+1,i=a-1; j<8 && i >=0 ; i--,j++ )
                {  // System.out.print(j);
                    if(field[i][j].equals(currpeg))
                    {
                        t=1;
                        break;
                    }

                }

                for(j = b+1,i=a-1 ; j<8&& i >=0 && t == 0;  i--,j++)
                {
                    if(field[i][j].equals("")){
                        buttons[i][j].setText(".");
                        break;
                    }
                }

            }


        }

    //right-down
        if(b!=0 && a != 7)
        {t=0;
            if(!(field[a+1][b-1].equals(currpeg) ) && !(field[a+1][b-1].equals("")) )
            {
                for(j = b-1,i=a+1; j>=0 && i <8 ; i++,j-- )
                {  // System.out.print(j);
                    if(field[i][j].equals(currpeg))
                    {
                        t=1;
                        break;
                    }

                }

                for(j = b-1,i=a+1; j>=0 && i <8 && t == 0 ; i++,j-- )
                {
                    if(field[i][j].equals("")){
                        buttons[i][j].setText(".");
                        break;
                    }
                }

            }


        }





    }


    private void turner(String currpeg,int a, int b)
    {
        int i,j,k,k1,k2;
        //right
        k=b+1;
        for(j = b+1; j< 8; j++)
        {
            if(buttons[a][j].getText().toString().equals(currpeg))
            { k = j;break;}
        }
        for(j = b+1; j< k; j++)
        {   if(buttons[a][j].getText().toString().equals("")== false && buttons[a][j].getText().toString().equals(".")== false)
            buttons[a][j].setText(currpeg);
        }
        //left
        k = b-1;
        for(j = b-1; j> -1; j--)
        {
            if(buttons[a][j].getText().toString().equals(currpeg))
            {
                k=j;
                break;
            }

        }
        for(j = b-1; j> k; j--)
           if(buttons[a][j].getText().toString().equals("")== false && buttons[a][j].getText().toString().equals(".")== false)
            buttons[a][j].setText(currpeg);


        //up
        k = a-1;
        for(i = a-1; i> -1; i--)
        {
            if(buttons[i][b].getText().toString().equals(currpeg))
            {
                k = i;
                break;
            }
        }
        for(i = a-1; i> k; i--)
            if(buttons[i][b].getText().toString().equals("")== false && buttons[i][b].getText().toString().equals(".")== false)
            buttons[i][b].setText(currpeg);
        //down
        k =a+1;
        for(i = a+1; i< 8; i++)
        {
            if(buttons[i][b].getText().toString().equals(currpeg))
            {
                k = i;
                break;
            }
        }
        for(i = a+1; i< k; i++)
            if(buttons[i][b].getText().toString().equals("")== false && buttons[i][b].getText().toString().equals(".")== false)
            buttons[i][b].setText(currpeg);

        //left-up
        k1 = a-1;
        k2 = b-1;
        for(j = b-1,i= a-1; j> -1 && i > -1; i--,j--)
        {
            if(buttons[i][j].getText().toString().equals(currpeg))
            {
                k1=i;k2=j;
                break;
            }

        }
        for(j = b-1,i= a-1; j> k2 && i > k1; j--,i--)
            if(buttons[i][j].getText().toString().equals("")== false && buttons[i][j].getText().toString().equals(".")== false)
                buttons[i][j].setText(currpeg);

        //right-down
        k1 = a+1;
        k2 = b+1;
        for(j = b+1,i= a+1; j<8 && i <8; i++,j++)
        {
            if(buttons[i][j].getText().toString().equals(currpeg))
            {
                k1=i;k2=j;
                break;
            }

        }
        for(j = b+1,i= a+1; j< k2 && i < k1; j++,i++)
            if(buttons[i][j].getText().toString().equals("")== false && buttons[i][j].getText().toString().equals(".")== false)
                buttons[i][j].setText(currpeg);

            //left-down
        k1 = a+1;
        k2 = b-1;
        for(j = b-1,i= a+1; j>-1 && i <8; i++,j--)
        {
            if(buttons[i][j].getText().toString().equals(currpeg))
            {
                k1=i;k2=j;
                break;
            }

        }
        for(j = b-1,i= a+1; j> k2 && i < k1; j--,i++)
            if(buttons[i][j].getText().toString().equals("")== false && buttons[i][j].getText().toString().equals(".")== false)
                buttons[i][j].setText(currpeg);



        //right-up
        k1 = a-1;
        k2 = b+1;
        for(j = b+1,i= a-1; j<8 && i >-1; i--,j++)
        {
            if(buttons[i][j].getText().toString().equals(currpeg))
            {
                k1=i;k2=j;
                break;
            }

        }
        for(j = b+1,i= a-1; j< k2 && i > k1; j++,i--)
            if(buttons[i][j].getText().toString().equals("")== false && buttons[i][j].getText().toString().equals(".")== false)
                buttons[i][j].setText(currpeg);


    }



    private boolean checkForWin()
    {
        String[][] field = new String[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        return true;
    }


private void declare_result()
{
    if(p1 > p2)
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
    else if(p2 > p1)
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
    else
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
}

    private void reset() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
    }


}
