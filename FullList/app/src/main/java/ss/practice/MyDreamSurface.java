package ss.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by TOSHIBA on 03-09-2017.
 */
public class MyDreamSurface extends SurfaceView implements Runnable{
    SurfaceHolder ourHolder; //Holder tells whether surface is valid or not,if valid then we can paint
    //it also helps to lock the canvas so no other thread can  draw without permission
    Thread t=null;
    boolean isrunning=false;
    public MyDreamSurface(Context context) {
        super(context);
        ourHolder=getHolder();
    }

    public void pause(){
    isrunning=false;
        while(true){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            break;
        }
        t=null;
    }
    public void resume(){
        isrunning=true;
        t=new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        while (isrunning)
        {
                if(!ourHolder.getSurface().isValid()) //if surface is not valid
                    continue;
            Canvas canvas=ourHolder.lockCanvas();//now no other process(activity or thread) can acces this canvas
            canvas.drawRGB(02,07,150);
            ourHolder.unlockCanvasAndPost(canvas);//it unlocks the canvas and posts it
        }
    }
}