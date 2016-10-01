package com.example.nitish.myquote;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import static android.R.attr.bitmap;

public class MainActivity extends AppCompatActivity {

    Bitmap originalBitmap, image;
    ImageView iv_ttx;
    String user_text;
    Paint paint;
    SharedPreferences sharedpref;
    //Set your own color, size etc.
    TextPaint textPaint = new TextPaint();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        //image view
        iv_ttx = (ImageView) findViewById(R.id.iv_ttx);
        //to get screen width and hight
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        //dimentions x,y of device to create a scaled bitmap having similar dimentions to screen size
        int height1 = displaymetrics.heightPixels;
        int width1 = displaymetrics.widthPixels;
        //paint object to define paint properties
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.MAGENTA);
        paint.setTextSize(100);

        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.MAGENTA);
        textPaint.setTextSize(100);
        //loading bitmap from drawable
        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.back3);
        //scaling of bitmap to square size
        originalBitmap = Bitmap.createScaledBitmap(originalBitmap, width1, width1, false);
        //creating another copy of bitmap to be used for editing
        image = originalBitmap.copy(Bitmap.Config.RGB_565, true);


        Button btn_save_img = (Button) findViewById(R.id.btn_save_image);

        btn_save_img.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //function save image is called with bitmap image as parameter
                saveImage(image);

            }
        });

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                user_text = null;
            } else {
                user_text = extras.getString("quote");
            }
        } else {
            user_text = (String) savedInstanceState.getSerializable("quote");
        }

        //function called to perform drawing
        createImage(100, 100, user_text);


    }

    void saveImage(Bitmap img) {
        String RootDir = Environment.getExternalStorageDirectory()
                + File.separator + "myquotes";
        File myDir = new File(RootDir);
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-" + n + ".jpg";
        File file = new File(myDir, fname);
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);

            img.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            Toast.makeText(MainActivity.this, "Image saved to 'myquotes' folder", Toast.LENGTH_LONG).show();
            int prevnum = sharedpref.getInt("numquotes", 0);
            SharedPreferences.Editor editor = sharedpref.edit();
            editor.putInt("numquotes", prevnum + 1);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public Bitmap createImage(float scr_x, float scr_y, String user_text) {
        //canvas object with bitmap image as constructor
        System.out.println("Usertext:" + user_text);
        Canvas canvas = new Canvas(image);
        //int viewTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        //removing title bar height
        //scr_y = scr_y - viewTop;
        //function to draw text on image. you can try more drawing functions like oval,point,rect,etc...

        // draw text to the Canvas center
        Rect bounds = new Rect();
        createImage(user_text,canvas,bounds);
//        paint.getTextBounds(user_text, 0, user_text.length(), bounds);
//        int x = (image.getWidth() - bounds.width())/2;
//        int y = (image.getHeight() + bounds.height())/2;
//
//        canvas.drawText(user_text, x, y, paint);
//        iv_ttx.setImageBitmap(image);
            return image;


    }

    public Bitmap finalImage(EditText text, Bitmap background) {
        Bitmap bmp = Bitmap.createBitmap(text.getDrawingCache());
        Bitmap combined = combineImages(background, bmp);
        return combined;
    }

    public Bitmap combineImages(Bitmap background, Bitmap foreground) {

        int width = 0, height = 0;
        Bitmap cs;

        width = getWindowManager().getDefaultDisplay().getWidth();
        height = getWindowManager().getDefaultDisplay().getHeight();

        cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas comboImage = new Canvas(cs);
        background = Bitmap.createScaledBitmap(background, width, height, true);
        comboImage.drawBitmap(background, 0, 0, null);
        comboImage.drawBitmap(foreground, 10, 10, null);

        return cs;
    }

    public Bitmap createImage(String quote,Canvas canvas,Rect bounds)
    {


        StaticLayout sl = new StaticLayout(user_text, textPaint, bounds.width(),
                Layout.Alignment.ALIGN_CENTER, 1, 1, true);

        canvas.save();

        //calculate X and Y coordinates - In this case we want to draw the text in the
        //center of canvas so we calculate
        //text height and number of lines to move Y coordinate to center.
        float textHeight = getTextHeight(user_text, textPaint);
        int numberOfTextLines = sl.getLineCount();
        float textYCoordinate = bounds.exactCenterY() -
                ((numberOfTextLines * textHeight) / 2);

        //text will be drawn from left
        float textXCoordinate = bounds.left;

        canvas.translate(textXCoordinate, textYCoordinate);

        //draws static layout on canvas
        sl.draw(canvas);
        canvas.restore();
        return image;
    }

//    @Override
//    public void onSizeChanged (Canvas canvas, Rect bounds) {
//        super.onLayout(canvas, bounds);
//
//        //String textOnCanvas = "I registered koc.sexy so that my package name can start with sexy.koc";
//
//
//        StaticLayout sl = new StaticLayout(user_text, textPaint, bounds.width(),
//                Layout.Alignment.ALIGN_CENTER, 1, 1, true);
//
//        canvas.save();
//
//        //calculate X and Y coordinates - In this case we want to draw the text in the
//        //center of canvas so we calculate
//        //text height and number of lines to move Y coordinate to center.
//        float textHeight = getTextHeight(user_text, textPaint);
//        int numberOfTextLines = sl.getLineCount();
//        float textYCoordinate = bounds.exactCenterY() -
//                ((numberOfTextLines * textHeight) / 2);
//
//        //text will be drawn from left
//        float textXCoordinate = bounds.left;
//
//        canvas.translate(textXCoordinate, textYCoordinate);
//
//        //draws static layout on canvas
//        sl.draw(canvas);
//        canvas.restore();
//
//    }
    /**
     * @return text height
     */
    private float getTextHeight(String text, Paint paint) {

        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect.height();
    }
}
