package recipe.tangy.com.tangyrecipe;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.HashMap;

import recipe.tangy.com.tangyrecipe.Utilities.DatabaseHelper;
import recipe.tangy.com.tangyrecipe.adapter.MyRecipesAdapter;

public class RecipeList extends AppCompatActivity {
    RecyclerView recyclerView;
    MyRecipesAdapter adapter;
    ArrayList<HashMap<String, Object>> alRecipes;
    DatabaseHelper dbHelper;
    TextView tvTitle;
    String cat_id, cat_title;
    Toolbar toolbar;
    TextView tvNoResult;
    ImageView ivBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alRecipes = new ArrayList<HashMap<String, Object>>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvNoResult = (TextView) findViewById(R.id.tvNoResult);
        ivBack = (ImageView) findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        cat_id = getIntent().getStringExtra("cat_id");
        cat_title = getIntent().getStringExtra("cat_title");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvTitle.setText(cat_title);
        LinearLayoutManager layoutManager = new LinearLayoutManager(RecipeList.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new MyRecipesAdapter(RecipeList.this, alRecipes);
        recyclerView.setAdapter(adapter);

        dbHelper = new DatabaseHelper(this);
        String title, description, Id, Image, CategoryId;
        Cursor c = dbHelper.getRecipieListAccToCategory(cat_id);
        HashMap<String, Object> hash;
        while (c.moveToNext()) {
            title = c.getString(1);
            description = c.getString(2);
            Id = c.getString(0);
            Image = c.getString(3);
            CategoryId = c.getString(4);
            hash = new HashMap<String, Object>();
            hash.put("Id", Id);
            hash.put("title", title);
            hash.put("description", description);
            hash.put("Image", Image);
            hash.put("CategoryId", CategoryId);
            alRecipes.add(hash);

        }
        if (alRecipes.isEmpty()) {
            tvNoResult.setVisibility(View.VISIBLE);
        } else {
            tvNoResult.setVisibility(View.GONE);
        }

    }


}
