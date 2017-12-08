package recipe.tangy.com.tangyrecipe.Utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import recipe.tangy.com.tangyrecipe.R;

/**
 * Created by android on 13/11/17.
 */

public class GetString {
    //this class shows the another way of showing
    public static Bitmap getDrawable(String id, Context context) {
     /*   Bitmap drawable = null;
        switch (id) {
            case "0":
                drawable = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_chinesesamosa);
                break;
            case "1":
                drawable = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_punjabisamosa);
                break;
            case "2":
                drawable = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_pannertikki);
                break;
            case "3":
                drawable = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_maggi);
                break;
        }
        return drawable;*/
        return null;
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
            case "ic_maggi":
                path = ("file:///android_asset/ic_maggi.html");
                break;
        }
        return path;
    }

}