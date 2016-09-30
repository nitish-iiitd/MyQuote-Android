package com.example.nitish.myquote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    public void addQuote(View v)
    {
        TextView quotetv  = (TextView)findViewById(R.id.quote);
        Quote q  = new Quote(1,quotetv.toString());
        DatabaseHandler dh = new DatabaseHandler(this);
        dh.addQuote(q);
    }
}
