package recipe.tangy.com.tangyrecipe.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import recipe.tangy.com.tangyrecipe.CategoriesScreen;
import recipe.tangy.com.tangyrecipe.MainActivity;
import recipe.tangy.com.tangyrecipe.R;
import recipe.tangy.com.tangyrecipe.Utilities.DatabaseHelper;

/**
 * Created by android on 8/12/17.
 */

public class CategoriesAdapter extends RecyclerView.Adapter {
    Context ctx;
    DatabaseHelper dbHelper;
    ArrayList<HashMap<String, String>> alRecipeCategories;


    public CategoriesAdapter(CategoriesScreen mainActivity, ArrayList<HashMap<String, String>> alRecipeCategories) {
        this.ctx = mainActivity;
        dbHelper = new DatabaseHelper(ctx);
        this.alRecipeCategories = alRecipeCategories;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder mHolder = (MyViewHolder) holder;
        HashMap<String, String> hash = alRecipeCategories.get(position);
        final String id = (String) hash.get("cat_id");
        String title = (String) hash.get("cat_title");
        String cat_image = (String) hash.get("cat_image");
        mHolder.tvCatName.setText(title);
        InputStream ims;
        Drawable d;
        try {
            // get input stream
            ims = ctx.getAssets().open("image/" + cat_image);

            // load image as Drawable
            d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            mHolder.llCatBg.setBackgroundDrawable(d);
            ims.close();
        } catch (IOException ex) {
            return;
        }


        mHolder.tvCatName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, MainActivity.class);
                intent.putExtra("cat_id", id);
                ctx.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return alRecipeCategories.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvCatName;
        ImageView ivCatBg;
        LinearLayout llCatBg;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvCatName = (TextView) itemView.findViewById(R.id.tvCatName);
            llCatBg = (LinearLayout) itemView.findViewById(R.id.llCatBg);
            ivCatBg = (ImageView) itemView.findViewById(R.id.ivCatBg);

        }
    }
}
