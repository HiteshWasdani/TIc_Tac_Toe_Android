package com.example.hitesh.tictac;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
  private int state = 1;                       // state 1 for red && state 0 for yellow
  private int id[] = {2,2,2,2,2,2,2,2,2};  // 1 for red && 0 for yellow and 2 for empty
  private int winPos[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
  private boolean working = true;            // true for game is not won and false if game has won
  private TextView winText;
  private MediaPlayer mediaPlayer;

    public void dropIn(View v)
    {
        ImageView img = (ImageView) v;

        int tag = Integer.parseInt(img.getTag().toString());

        if(id[tag] != 2)   return;

        id[tag] = state;

        if(state == 1 && working)
        {
            state = 0;
            img.setTranslationY(-1500);
            img.setImageResource(R.drawable.red);
            winText.setText("Yellow Player Turn"); winText.setTextColor(Color.YELLOW);
            mediaPlayer.start();

        }

        else if(state == 0 && working )
        {
            state = 1;
            img.setTranslationY(-1500);
            img.setImageResource(R.drawable.yellow);
            winText.setText("Red Player Turn"); winText.setTextColor(Color.RED);
            mediaPlayer.start();

        }

        img.animate().translationYBy(1500).setDuration(200);


        // Check Winning condition

       for (int[] a : winPos)
        {
            if (id[a[0]] == id[a[1]] && id[a[1]] == id[a[2]] && id[a[0]] != 2)
            {

             working = false;

             if(state == 1)  { winText.setText("Yellow has Won !!"); winText.setTextColor(Color.GREEN); }
             else            { winText.setText("Red has Won !!"); winText.setTextColor(Color.GREEN); }

            }
        }

    }

    public void Replay(View v)
    {
       working = true;

        GridLayout gridLayout = (GridLayout) findViewById(R.id.GridL);

        for(int i=0; i<gridLayout.getChildCount(); i++)
        {
            ImageView img = (ImageView) gridLayout.getChildAt(i);
            img.setImageDrawable(null);
            id[i] = 2;
        }

        if(state == 0) {  winText.setText("Red Player Turn"); winText.setTextColor(Color.RED); }
        if(state == 1) { winText.setText("Yellow Player Turn"); winText.setTextColor(Color.YELLOW);  }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        winText = (TextView)findViewById(R.id.WinText);

        winText.setText("Red Turn"); winText.setTextColor(Color.RED);
        mediaPlayer = MediaPlayer.create(this,R.raw.sound);

    }
}
