package com.example.nitish.myquote;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    /*
     * Adds the given quote if non-empty, in the database
     */
    public void addQuote(View v)
    {
        TextView quotetv  = (TextView)findViewById(R.id.quote);
        //System.out.println("Quote : "+quotetv.getText().toString());
        if(!quotetv.getText().toString().equals("")) {
            Quote q = new Quote(1, quotetv.getText().toString());
            DatabaseHandler dh = new DatabaseHandler(this);
            dh.addQuote(q);
            Toast.makeText(this, "Quote added!", Toast.LENGTH_SHORT).show();
            Intent startscreen = new Intent(this, StartActivity.class);
            startActivity(startscreen);
        }
        else
        {
            Toast.makeText(this, "Cannot add an empty quote!", Toast.LENGTH_SHORT).show();
        }
    }
}
