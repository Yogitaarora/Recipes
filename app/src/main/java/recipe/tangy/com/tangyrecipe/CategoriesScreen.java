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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import recipe.tangy.com.tangyrecipe.Utilities.DatabaseHelper;
import recipe.tangy.com.tangyrecipe.adapter.CategoriesAdapter;

public class CategoriesScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseHelper dbHelper;
    ArrayList<HashMap<String, String>> alRecipeCategories;
    List<String> categoryId;
    String cat;
    RecyclerView recyclerView;
    CategoriesAdapter adapter;
    public static DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    ListView lvNav;
    public NavigationAdapter navAdapter;
    Toolbar toolbar;
    TextView tvTitle;
    AppBarLayout appbar;
    NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_screen);
        ButterKnife.bind(this);
        intViews();
        setUpNavigationAndToolbar();
        alRecipeCategories = new ArrayList<HashMap<String, String>>();
        categoryId = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.rvCategories);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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

    private void intViews() {

        lvNav = (ListView) findViewById(R.id.list);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

    }


    void setUpNavigationAndToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvTitle.setText("Tangy");
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                appbar.setX(navigationView.getWidth() * slideOffset);
                RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) appbar.getLayoutParams();
                lp.height = drawer.getHeight() - (int) (drawer.getHeight() * slideOffset * 0.3f);
                int ht = drawer.getHeight() - (int) (drawer.getHeight() * slideOffset * 0.3f);
                lp.topMargin = (int) ((drawer.getHeight() - ht) / 1.5);
                lp.bottomMargin = (drawer.getHeight() - ht) / 4;
                appbar.setLayoutParams(lp);
                appbar.invalidate();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setScrimColor(Color.TRANSPARENT);
        drawer.setDrawerElevation(0f);
        drawer.setDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        String[] menuItems = {"home", "test", "FeedBack", "Share"};
        navAdapter = new NavigationAdapter(this, menuItems);
        lvNav.setAdapter(navAdapter);
        navigationView.setNavigationItemSelectedListener(CategoriesScreen.this);
        lvNav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:

                        break;
                    case 1:

                        break;
                    case 2:
                        Intent intent=new Intent(CategoriesScreen.this,FeedBackActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        break;
                }

            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
