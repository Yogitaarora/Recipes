package recipe.tangy.com.tangyrecipe.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import recipe.tangy.com.tangyrecipe.R;
import recipe.tangy.com.tangyrecipe.Utilities.DatabaseHelper;
import recipe.tangy.com.tangyrecipe.adapter.CategoriesAdapter;

/**
 * Created by android on 13/2/18.
 */

public class CategoriesFragment extends Fragment {
    DatabaseHelper dbHelper;
    ArrayList<HashMap<String, String>> alRecipeCategories;
    List<String> categoryId;
    String cat;
    RecyclerView recyclerView;
    CategoriesAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_categories, container, false);
        alRecipeCategories = new ArrayList<HashMap<String, String>>();
        categoryId = new ArrayList<>();
        recyclerView = (RecyclerView) v.findViewById(R.id.rvCategories);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CategoriesAdapter(getActivity(), alRecipeCategories);
        recyclerView.setAdapter(adapter);
        dbHelper = new DatabaseHelper(getActivity());
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
        return v;
    }

}
