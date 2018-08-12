package com.dev.i.a.v.tablayout;

import android.content.Context;

/**
 * Created by V on 19/1/16.
 */
public class Helper {

    public static int dpToPx(Context context,int dp){
        return Math.round(dp * context.getResources().getDisplayMetrics().density);

    }
}
