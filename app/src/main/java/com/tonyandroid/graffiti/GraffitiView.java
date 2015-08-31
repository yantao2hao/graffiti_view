package com.tonyandroid.graffiti;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * author：yanyantao
 * Created on 2015/8/31.
 * 描述：
 */
public class GraffitiView extends FrameLayout {

    private Context mContext;
    private GraffitiSurface doodleView;
    private TextView titleView;
    public static final int  TOP_DOODLE=0,TOP_TITLE_VIE = 1;
    private int topView = TOP_TITLE_VIE;

    public int getTopView() {
        return topView;
    }

    public void setTopView(int topView) {
        this.topView = topView;
        if (topView==TOP_DOODLE){
            bringChildToFront(doodleView);
        }
        else bringChildToFront(titleView);
    }

    public GraffitiView(Context context) {
        super(context);
        init(context);
    }

    public GraffitiView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GraffitiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void toggleTop(){
        if (topView==TOP_TITLE_VIE){
            bringChildToFront(doodleView);
            topView = TOP_DOODLE;
        }
        else {
            bringChildToFront(titleView);
            topView = TOP_TITLE_VIE;
        }
    }

    private void init(Context context){
        this.mContext = context;
        setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        inflate(getContext(), R.layout.graffiti_layout, this);
        titleView = (TextView) findViewById(R.id.title_view);
        titleView.setOnTouchListener(new MultiTouchListener());
        doodleView = (GraffitiSurface) findViewById(R.id.doodle_view);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public Bitmap toBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        draw(canvas);
        return bitmap;
    }
    public String saveBitmap(){
        //this.setDrawingCacheEnabled(true);
        Bitmap b = toBitmap();
        Bitmap bitmapdoodle = doodleView.getBitmap();
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        try {
            Canvas cv = new Canvas(bitmap);
            cv.drawBitmap(b, 0, 0, null);
            cv.drawBitmap(bitmapdoodle, 0, 0, null);
            cv.save(Canvas.ALL_SAVE_FLAG);
            cv.restore();
        } catch (Exception e) {
            bitmap = null;
            e.getStackTrace();
        }

        File sdCard = Environment.getExternalStorageDirectory();
        File file = new File(sdCard, "image.png");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 95, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return file.toString();
    }
}
