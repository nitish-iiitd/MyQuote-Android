package com.example.nitish.myquote;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void goToCreateMyQuote(View v)
    {
        Intent createscreen = new Intent(this,CreateActivity.class);
        startActivity(createscreen);
    }

    public void goToAddQuote(View v)
    {
        Intent addscreen = new Intent(this,AddActivity.class);
        startActivity(addscreen);
    }

}
