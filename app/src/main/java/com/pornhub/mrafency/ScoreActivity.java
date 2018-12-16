package com.pornhub.mrafency;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ScoreActivity extends Activity {

    private ListView scoresView;
    private TextView newTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        newTime = findViewById(R.id.newTime);
        long currTime = getIntent().getLongExtra("time", 0);
        if(currTime == 0) {
            newTime.setText("Skóre");
        } else {
            newTime.setText("Nový čas: " + String.format("%d minut, %d sekund",
                    TimeUnit.MILLISECONDS.toMinutes(currTime),
                    TimeUnit.MILLISECONDS.toSeconds(currTime) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(currTime))
            ));
        }

        scoresView = findViewById(R.id.scores);


        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                List<Score> scoreList = DatabaseManager.getDatabase(getApplicationContext()).scoreDao().getAll();
                List<String> scores = new ArrayList<>();
                for(int i = 0; i < scoreList.size(); i++) {
                    Score score = scoreList.get(i);
                    scores.add(i + 1 + ".: " + String.format("%d minut, %d sekund",
                            TimeUnit.MILLISECONDS.toMinutes(score.time),
                            TimeUnit.MILLISECONDS.toSeconds(score.time) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(score.time))
                    ));
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.list_item, scores);
                    scoresView.setAdapter(adapter);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
    }
}
