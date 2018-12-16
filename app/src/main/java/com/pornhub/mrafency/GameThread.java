package com.pornhub.mrafency;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

    private boolean running;
    private GameSurface gameSurface;
    private SurfaceHolder surfaceHolder;

    public GameThread(GameSurface gameSurface, SurfaceHolder surfaceHolder)  {
        this.gameSurface = gameSurface;
        this.surfaceHolder = surfaceHolder;
    }

    @Override
    public void run()  {
        long startTime;
        long targetTime = 1000 / 60;

        while(running)  {
            startTime = System.nanoTime();
            Canvas canvas= null;
            try {
                // Get Canvas from Holder and lock it.
                canvas = this.surfaceHolder.lockCanvas();

                // Synchronized
                synchronized (canvas)  {
                    this.gameSurface.update();
                    this.gameSurface.draw(canvas);
                }
            }catch(Exception e)  {
                // Do nothing.
            } finally {
                if(canvas != null)  {
                    // Unlock Canvas.
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            long now = System.nanoTime() ;
            // Interval to redraw game
            // (Change nanoseconds to milliseconds)
            long waitTime = targetTime - (now - startTime) / 1000000;

            try {
                // Sleep.
                sleep(waitTime);
            } catch(Exception e)  {

            }
            System.out.print(".");
        }
    }

    public void setRunning(boolean running)  {
        this.running = running;
    }
}