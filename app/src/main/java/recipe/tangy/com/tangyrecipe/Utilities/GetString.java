package recipe.tangy.com.tangyrecipe.Utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.webkit.WebView;

import recipe.tangy.com.tangyrecipe.R;

/**
 * Created by android on 13/11/17.
 */

public class GetString {

    public static Bitmap getDrawable(String id, Context context) {
        Bitmap drawable = null;
        switch (id) {
            case "0":
                drawable = BitmapFactory.decodeResource(context.getResources(), R.drawable.chinese_samosa);
                break;
            case "1":
                drawable = BitmapFactory.decodeResource(context.getResources(), R.drawable.punjabi_samosa);
                break;
            case "2":
                drawable = BitmapFactory.decodeResource(context.getResources(), R.drawable.panner_tikki);
                break;
            case "3":
                drawable = BitmapFactory.decodeResource(context.getResources(), R.drawable.maggi);
                break;
        }
        return drawable;
    }

    public static String getRecContent(String recName) {
        String path = "";
        switch (recName) {
            case "chinesesamosa":
                path = ("file:///android_asset/chinesesamosa.html");
                break;
            case "punjabisamosa":
                path = ("file:///android_asset/punjabisamosa.html");
                break;
            case "pannertikki":
                path = ("file:///android_asset/pannertikki.html");
                break;
            case "maggi":
                path = ("file:///android_asset/maggi.html");
                break;
        }
        return path;
    }

}