package com.iesebre.dam2.paolodavila.downloadimageview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    //Image view control
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mImageView = (ImageView)findViewById(R.id.ImgView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    public void run() {
                        Bitmap b = loadImageFromNetwork("https://www.google.es/search?q=goku&espv=2&biw=1366&bih=652&site=webhp&tbm=isch&tbo=u&source=univ&sa=X&ved=0ahUKEwjZjf2d0-XJAhVItxoKHes0AvMQsAQIHg#imgrc=Egy-CJuATNZ6UM%3A");
                        setImageBitmap(b);
                    }
                }).start();
            }

            //Load image method
            private Bitmap loadImageFromNetwork(String url) {
                Bitmap bitmap = null;
                try {
                    bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
                    //return bitmap;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return bitmap;
            }

            //Set Image to ImageView method
            public void setImageBitmap(Bitmap b) {
                //set control with the image
                final Bitmap img = b;
                mImageView.post(new Runnable() {
                    public void run() {
                        mImageView.setImageBitmap(img);
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
