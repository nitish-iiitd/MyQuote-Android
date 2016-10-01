package com.example.nitish.myquote;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateActivity extends AppCompatActivity {

    Bitmap originalBitmap, image;
    ImageView iv_ttx;
    EditText et_sample;

    Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        String quotesdisp = "";
        List<Quote> quotes = new ArrayList<Quote>();
        DatabaseHandler dh = new DatabaseHandler(this);
        quotes = dh.getAllQuotes();
        for (int i = 0; i < quotes.size(); i++) {
            quotesdisp = quotesdisp + quotes.get(i).get_id() + " : " + quotes.get(i).getText() + "\n";
        }
        TextView quotestv = (TextView) findViewById(R.id.quotes);
        quotestv.setText(quotesdisp);
        quotestv.setMovementMethod(new ScrollingMovementMethod());


    }

    public void createQuote(View v) {
        TextView quoteid = (TextView) findViewById(R.id.quoteid);
        int id = Integer.parseInt(quoteid.getText().toString());
        DatabaseHandler dh = new DatabaseHandler(this);
        Quote q = new Quote();
        q = dh.getQuote(id);
//        Intent startscreen = new Intent(this,MainActivity.class);
//        System.out.println("Quote(createquote):"+q.getText());
//        startscreen.putExtra("quote",q.getText());
//        startActivity(startscreen);
    }





}
