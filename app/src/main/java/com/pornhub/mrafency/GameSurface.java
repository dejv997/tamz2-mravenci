package com.pornhub.mrafency;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
<<<<<<< HEAD
import android.support.constraint.solver.widgets.ResolutionDimension;
=======
>>>>>>> master
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.pornhub.mrafency.states.GameStateManager;

<<<<<<< HEAD
public class GameSurface extends SurfaceView implements SurfaceHolder.Callback, Updatable {
=======
public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {
>>>>>>> master

    private GameThread gameThread;
    private GameStateManager gsm;

    public GameSurface(Context context)  {
        super(context);

        // Make Game Surface focusable so it can handle events. .
        this.setFocusable(true);

        // Sét callback.
        this.getHolder().addCallback(this);
    }

    public void update()  {
        gsm.update();
    }



    @Override
    public void draw(Canvas canvas)  {
        super.draw(canvas);
        gsm.draw(canvas);


        // draw
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(200, 200, 50, paint);
        canvas.drawText(Integer.toString(canvas.getWidth()) + " " + Integer.toString(canvas.getHeight()), 50, 50, paint);
    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
<<<<<<< HEAD
        gsm = new GameStateManager(this);
=======
        gsm = new GameStateManager();
>>>>>>> master

        this.gameThread = new GameThread(this, holder);
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry) {
            try {
                this.gameThread.setRunning(false);

                // Parent thread must wait until the end of GameThread.
                this.gameThread.join();
            }catch(InterruptedException e)  {
                e.printStackTrace();
            }
            retry = false;
        }
    }

<<<<<<< HEAD
    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {

    }

=======
>>>>>>> master
}
