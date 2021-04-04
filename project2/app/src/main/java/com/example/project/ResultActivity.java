package com.example.project;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;

    public class ResultActivity extends Activity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_result);
            //get rating bar object
            RatingBar bar=(RatingBar)findViewById(R.id.ratingBar1);
            bar.setNumStars(5);
            bar.setStepSize(0.5f);
            //get text view
            TextView t=(TextView)findViewById(R.id.textResult);
            //get score
            Bundle b = getIntent().getExtras();
            int score= b.getInt("score");
            //display score
            bar.setRating(score);
            switch (score)
            {
                case 0: t.setText("การควบคุมอารมณ์และการจัดการอารมณ์อยู่ในระดับ ดีเยี่ยม\n" +
                        "รู้เท่าอารมณ์ของตนเอง เข้าใจสาเหตุ แยกแยะ แก้ไขปัญหาเมื่อเผชิญอุปสรรคและสามารถควบคุมอารมณ์ได้ดีเยี่ยม");
                    break;
                case 1: t.setText("การควบคุมอารมณ์และการจัดการอารมณ์อยู่ในระดับ ดี\n อารมณ์คุณค่อนข้างดี");
                    break;
                case 2: t.setText("การควบคุมอารมณ์และการจัดการอารมณ์อยู่ในระดับ ดี\n  อารมณ์คุณปกติ");
                    break;
                case 3: t.setText("การควบคุมอารมณ์และการจัดการอารมณ์อยู่ในระดับ ปานกลาง\n อารมณ์คุณคปกติ");
                    break;
                case 4:t.setText("การควบคุมอารมณ์และการจัดการอารมณ์อยู่ในระดับ ค่อนข้างแย่\n อารมณ์คุณค่อนข้างแย่ ");
                    break;
                case 5:t.setText(" การควบคุมอารมณ์และการจัดการอารมณ์อยู่ในระดับ แย่\n อารมณ์คุณแย่");
                    break;
            }
        }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.activity_result, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.action_settings) {
                Intent settingsIntent = new Intent(this, QuizActivity.class);
                startActivity(settingsIntent);
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

    }

