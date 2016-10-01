package com.example.nitish.myquote;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    SharedPreferences sharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        sharedpref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        int numquotes = sharedpref.getInt("numquotes",0);
        TextView numview = (TextView)findViewById(R.id.num);
        numview.setText("MyQuotes:"+numquotes);

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
