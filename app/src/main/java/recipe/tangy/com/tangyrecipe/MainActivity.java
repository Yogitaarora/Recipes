package recipe.tangy.com.tangyrecipe;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import recipe.tangy.com.tangyrecipe.Utilities.DatabaseHelper;
import recipe.tangy.com.tangyrecipe.adapter.MyRecipesAdapter;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyRecipesAdapter adapter;
    ArrayList<HashMap<String, Object>> alRecipes;
    String title, description, Id;
    DatabaseHelper dbHelper;
    byte[] image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alRecipes = new ArrayList<HashMap<String, Object>>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new MyRecipesAdapter(MainActivity.this, alRecipes);
        recyclerView.setAdapter(adapter);
        dbHelper = new DatabaseHelper(this);
        try {
            dbHelper.createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Cursor c = dbHelper.getAllNames();
        HashMap<String, Object> hash;
        while (c.moveToNext()) {
            title = c.getString(1);
            description = c.getString(2);
            Id = c.getString(0);
            hash = new HashMap<String, Object>();
            hash.put("Id", Id);
            hash.put("title", title);
            hash.put("description", description);
            alRecipes.add(hash);
        }

    }

}
