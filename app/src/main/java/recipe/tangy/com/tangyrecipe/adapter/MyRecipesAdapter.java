package recipe.tangy.com.tangyrecipe.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import recipe.tangy.com.tangyrecipe.MainActivity;
import recipe.tangy.com.tangyrecipe.R;
import recipe.tangy.com.tangyrecipe.Utilities.DatabaseHelper;
import recipe.tangy.com.tangyrecipe.Utilities.GetString;

/**
 * Created by android on 13/11/17.
 */

public class MyRecipesAdapter extends RecyclerView.Adapter {
    Context ctx;
    DatabaseHelper dbHelper;
    ArrayList<HashMap<String, Object>> alRecipes;


    public MyRecipesAdapter(MainActivity mainActivity, ArrayList<HashMap<String, Object>> alRecipes) {
        this.ctx = mainActivity;
        dbHelper = new DatabaseHelper(ctx);
        this.alRecipes = alRecipes;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder mHolder = (MyViewHolder) holder;
        HashMap<String, Object> hash = alRecipes.get(position);
        String id = (String) hash.get("Id");
        final String description = (String) hash.get("description");
        final String title = (String) hash.get("title");
        mHolder.textView.setText((title));
        mHolder.imageview.setImageBitmap(GetString.getDrawable(id, ctx));
        mHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(ctx, R.style.AppTheme_NoActionBar);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                dialog.setContentView(R.layout.popup);
                final Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                WebView web = (WebView) dialog.findViewById(R.id.webview);
               // web.loadDataWithBaseURL("file:///android_asset/", GetString.getRecContent(description), "text/html", "utf-8", null);
                web.loadUrl(GetString.getRecContent(description));

                ImageButton imbutn = (ImageButton) dialog.findViewById(R.id.ib_close);
                imbutn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return alRecipes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageview;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            imageview = (ImageView) itemView.findViewById(R.id.imageview);

        }
    }


}
