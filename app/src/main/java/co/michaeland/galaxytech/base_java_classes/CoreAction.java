package co.michaeland.galaxytech.base_java_classes;

import android.content.Context;
import android.os.Vibrator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import co.michaeland.galaxytech.R;

import static android.content.Context.VIBRATOR_SERVICE;



public class CoreAction {

    Context context;

    public CoreAction(Context mContext){
        context = mContext;
    }

    public Animation slideIN(){
        final Animation slide_up = AnimationUtils.loadAnimation(context,
                R.anim.slide_up);
        return slide_up;
    }
    public Animation slideOUT(){
        final Animation slide_down = AnimationUtils.loadAnimation(context,
                R.anim.slide_down);
        return slide_down;
    }
    public Animation disappear(){
        final Animation disappear = AnimationUtils.loadAnimation(context,
                R.anim.disappear);
        disappear.setDuration(1000);
        disappear.start();
        return disappear;
    }
    public Animation appear(){
        final Animation appear = AnimationUtils.loadAnimation(context,
                R.anim.appear);
        appear.setDuration(500);
        appear.start();
        return appear;
    }
    public void hapticFeedback(){
        final Vibrator myVib = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
        myVib.vibrate(5);
    }


}
