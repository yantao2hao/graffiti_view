package com.tonyandroid.graffiti;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private GraffitiView graffitiView;
    private ImageView iconGraffiti,iconClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        graffitiView = (GraffitiView) findViewById(R.id.top_panel);
        iconGraffiti = (ImageView) findViewById(R.id.icon_doodle);
        iconClose = (ImageView) findViewById(R.id.icon_close);
        iconClose.setOnClickListener(this);
        iconGraffiti.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {

        int id = v.getId();
        switch (id){
            case R.id.icon_close:
                finish();
                break;
            case R.id.icon_doodle:
                graffitiView.toggleTop();
                if (graffitiView.getTopView()==graffitiView.TOP_DOODLE)
                    v.setBackgroundColor(getResources().getColor(R.color.active));
                else v.setBackgroundColor(getResources().getColor(R.color.none));
                break;
        }
    }
}
