package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void multiPlyBtn(View view){
        Button multiPly= findViewById(R.id.multiPlBtn);
        Intent i = new Intent(this, gamePlayActivity.class);
        startActivity(i);
    }
}