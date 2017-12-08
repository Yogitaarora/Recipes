package recipe.tangy.com.tangyrecipe;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import recipe.tangy.com.tangyrecipe.Utilities.DatabaseHelper;
import recipe.tangy.com.tangyrecipe.adapter.CategoriesAdapter;

public class CategoriesScreen extends AppCompatActivity {

    DatabaseHelper dbHelper;
    ArrayList<HashMap<String, String>> alRecipeCategories;
    List<String> categoryId;
    String cat;
    RecyclerView recyclerView;
    CategoriesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_screen);
        ButterKnife.bind(this);
        alRecipeCategories = new ArrayList<HashMap<String, String>>();
        categoryId = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.rvCategories);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        adapter = new CategoriesAdapter(this, alRecipeCategories);
        recyclerView.setAdapter(adapter);
        dbHelper = new DatabaseHelper(this);
        try {
            dbHelper.createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String cat_title, cat_id, cat_image;
        Cursor c = dbHelper.getAllCategoryNames();
        HashMap<String, String> hash;
        while (c.moveToNext()) {
            cat_title = c.getString(1);
            cat_id = c.getString(0);
            cat_image = c.getString(2);
            hash = new HashMap<String, String>();
            hash.put("cat_id", cat_id);
            hash.put("cat_title", cat_title);
            hash.put("cat_image", cat_image);
            categoryId.add(cat_id);
            alRecipeCategories.add(hash);
            cat = cat_id;


        }
    }



}
