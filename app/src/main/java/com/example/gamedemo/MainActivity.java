package com.example.gamedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
LinearLayout red_gray,blue_gray,yellow_gray,green_gray;
LinearLayout red,blue,yellow,green;
TextView score,tv_red,tv_blue,tv_yellow,tv_green;
private LinearLayout[] layouts = { red_gray, blue_gray, yellow_gray, green_gray};

private LinearLayout[] Parentlayouts = { red, blue, yellow, green};
private TextView[] txv = { tv_red, tv_blue, tv_yellow, tv_green};

int rndIndex=0,score_count=0,click_count,service_time=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score=findViewById(R.id.score);
        red_gray=findViewById(R.id.red_gray);
        blue_gray=findViewById(R.id.blue_gray);
        yellow_gray=findViewById(R.id.yellow_gray);
        green_gray=findViewById(R.id.green_gray);
        red=findViewById(R.id.red);
        blue=findViewById(R.id.blue);
        yellow=findViewById(R.id.yellow);
        green=findViewById(R.id.green);

        tv_red=findViewById(R.id.tv_red);
        tv_blue=findViewById(R.id.tv_blue);
        tv_yellow=findViewById(R.id.tv_yellow);
        tv_green=findViewById(R.id.tv_green);


layouts[0]=red_gray;
layouts[1]=blue_gray;
layouts[2]=yellow_gray;
layouts[3]=green_gray;

        Parentlayouts[0]=red;
        Parentlayouts[1]=blue;
        Parentlayouts[2]=yellow;
        Parentlayouts[3]=green;

        txv[0]=tv_red;
        txv[1]=tv_blue;
        txv[2]=tv_yellow;
        txv[3]=tv_green;

for (int i=0;i<Parentlayouts.length;i++)
{

    Parentlayouts[i].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            GameOverDialog();
        }
    });

}


for (int i=0;i<layouts.length;i++)
{
    layouts[i].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            click_count=1;
            score_count=Integer.parseInt(score.getText().toString());
            score.setText(String.valueOf(score_count+1));

            tv_red.setText("1");
            tv_blue.setText("1");
            tv_yellow.setText("1");
            tv_green.setText("1");
        }
    });

}


        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //your method

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        layouts[rndIndex].setVisibility(View.GONE);
                        Random random = new Random();
                        rndIndex = random.nextInt(layouts.length);
                        layouts[rndIndex].setVisibility(View.VISIBLE);
                        Log.e("index",rndIndex+"");
if (txv[rndIndex].getText().toString().equals("0"))
{
    GameOverDialog();
    tv_red.setText("1");
    tv_blue.setText("1");
    tv_yellow.setText("1");
    tv_green.setText("1");
}else {

    tv_red.setText("0");
    tv_blue.setText("0");
    tv_yellow.setText("0");
    tv_green.setText("0");
}
                        service_time=1000;
                        // Do some stuff
                    }
                });

            }
        }, 0, service_time);//put here time 1000 milliseconds=1 second
//
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                }
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//
//                        // Do some stuff
//                    }
//                });
//            }
//        };
//        thread.start(); //start the thread
//
  }


    public void GameOverDialog() {

        final Dialog dialog = new Dialog(MainActivity.this, R.style.DialogSlideAnim);
        dialog.setContentView(R.layout.game_over);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        Button btn_restart;
        TextView dai_score;
        btn_restart = dialog.findViewById(R.id.btn_restart);
        dai_score = dialog.findViewById(R.id.dai_score);
        dai_score.setText("Game Over your score is :"+score.getText().toString());

        btn_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();

               score.setText("0");
                tv_red.setText("0");
                tv_blue.setText("0");
                tv_yellow.setText("0");
                tv_green.setText("0");
            }

        });

        dialog.show();

    }

}