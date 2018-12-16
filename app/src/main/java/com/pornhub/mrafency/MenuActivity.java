package com.pornhub.mrafency;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void startGameWithAI(View view) {
        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
        startActivity(intent);
    }


    public void openSettings(View view) {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }

    public void showScore(View view) {
        Intent intent = new Intent(getApplicationContext(), ScoreActivity.class);
        startActivity(intent);
    }
}
