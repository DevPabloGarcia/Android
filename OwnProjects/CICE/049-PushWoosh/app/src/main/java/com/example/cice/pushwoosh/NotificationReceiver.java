package com.example.cice.pushwoosh;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;

import com.pushwoosh.PushManager;
import com.pushwoosh.internal.PushManagerImpl;

import org.json.JSONObject;

/**
 * Created by CICE on 5/4/16.
 */
public class NotificationReceiver extends BroadcastReceiver
{
    public void onReceive(Context context, Intent intent)
    {
        if (intent == null)
            return;

        //Let Pushwoosh SDK to pre-handling push (Pushwoosh track stats, opens rich pages, etc.).
        //It will return Bundle with a push notification data
        Bundle pushBundle = PushManagerImpl.preHandlePush(context, intent);
        if(pushBundle == null)
            return;

        //get push bundle as JSON object
        JSONObject dataObject = PushManagerImpl.bundleToJSON(pushBundle);

        //Get default launcher intent for clarity
        Intent launchIntent  = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        launchIntent.addCategory("android.intent.category.LAUNCHER");

        launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

        //Put push notifications payload in Intent
        launchIntent.putExtras(pushBundle);
        launchIntent.putExtra(PushManager.PUSH_RECEIVE_EVENT, dataObject.toString());

        //Start activity!
        context.startActivity(launchIntent);

        //TODO start activity custom con parentStack. Hay que configurar parents en Manifest
        /*
        Intent launchIntent = new Intent(context, DetailActivity.class);
        TaskStackBuilder stackBuilder  = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(DetailActivity.class);
        stackBuilder.addNextIntent(launchIntent).startActivities();
        */


        //Let Pushwoosh SDK post-handle push (track stats, etc.)
        PushManagerImpl.postHandlePush(context, intent);
    }
}
