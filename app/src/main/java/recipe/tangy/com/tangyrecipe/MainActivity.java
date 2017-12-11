package recipe.tangy.com.tangyrecipe;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import recipe.tangy.com.tangyrecipe.Utilities.DatabaseHelper;
import recipe.tangy.com.tangyrecipe.adapter.MyRecipesAdapter;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyRecipesAdapter adapter;
    ArrayList<HashMap<String, Object>> alRecipes;
    DatabaseHelper dbHelper;
    TextView tvTitle;
    String cat_id;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alRecipes = new ArrayList<HashMap<String, Object>>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvTitle.setText("Items");
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new MyRecipesAdapter(MainActivity.this, alRecipes);
        recyclerView.setAdapter(adapter);

        dbHelper = new DatabaseHelper(this);
        cat_id = getIntent().getStringExtra("cat_id");
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

    }


}
