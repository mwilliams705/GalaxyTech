package co.michaeland.galaxytech.base_java_classes;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import co.michaeland.galaxytech.activities.MainActivity;
import co.michaeland.galaxytech.R;

/**
 * Created by micha on 10/31/2017.
 */

public class GalaxyTechNotification {

    Context context;


    public GalaxyTechNotification(Context mContext){
        context = mContext;
    }

    public void Welcome(){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"welcomeID");
        builder.setSmallIcon(R.drawable.ic_meteor_notification);
        builder.setContentTitle("Welcome");
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setContentText("Let us show you some quick tips for getting around the app");
        builder.setColor(Color.MAGENTA);
        builder.setLights(Color.MAGENTA,1,2);
        Notification notification = builder.build();
        NotificationManagerCompat.from(context).notify(0,notification);

        NotificationIntentBuilder(builder, context, MainActivity.class);

    }

    public void NotificationIntentBuilder(NotificationCompat.Builder builder, Context context, Class activity){
        Intent intent = new Intent(context, activity);
        PendingIntent resultIntent= PendingIntent.getActivity(
                context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultIntent);

    }
}
