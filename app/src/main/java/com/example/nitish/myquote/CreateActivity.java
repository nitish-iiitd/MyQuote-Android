package com.example.nitish.myquote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        String quotesdisp="";
        List<Quote> quotes = new ArrayList<Quote>();
        DatabaseHandler dh = new DatabaseHandler(this);
        quotes = dh.getAllQuotes();
        for(int i=0;i<quotes.size();i++)
        {
            quotesdisp = quotesdisp+quotes.get(i).get_id()+" : "+quotes.get(i).getText()+"\n";
        }
        TextView quotestv  = (TextView)findViewById(R.id.quotes);
        quotestv.setText(quotesdisp);
    }
}
