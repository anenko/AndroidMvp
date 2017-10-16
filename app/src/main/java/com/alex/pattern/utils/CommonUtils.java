package com.alex.pattern.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Alex
 */

public class CommonUtils {

    public static int getColor(Context context, int resourceId) {
        int color;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            color = context.getResources().getColor(resourceId, null);
        } else {
            color = context.getResources().getColor(resourceId);
        }
        return color;
    }

    public static Drawable getDrawable(Context context, int resourceId) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable = context.getResources().getDrawable(resourceId, null);
        } else {
            drawable = context.getResources().getDrawable(resourceId);
        }
        return drawable;
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view == null) view = new View(activity);
        InputMethodManager inputMethodManager = (InputMethodManager) activity .getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
