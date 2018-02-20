package recipe.tangy.com.tangyrecipe.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import recipe.tangy.com.tangyrecipe.R;
import recipe.tangy.com.tangyrecipe.activity.adapter.NavigationAdapter;
import recipe.tangy.com.tangyrecipe.fragment.CategoriesFragment;
import recipe.tangy.com.tangyrecipe.fragment.FeedBackFragment;
import recipe.tangy.com.tangyrecipe.fragment.MyFavoriteFragment;
import recipe.tangy.com.tangyrecipe.fragment.TipsFragment;

public class MainScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    public static DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    ListView lvNav;
    public static NavigationAdapter navAdapter;
    Toolbar toolbar;
    TextView tvTitle;
    AppBarLayout appbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        ButterKnife.bind(this);
        intViews();
        setUpNavigationAndToolbar();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new CategoriesFragment()).commit();

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
        String[] menuItems = {"home", "Tips", "FeedBack", "Share"};
        navAdapter = new NavigationAdapter(this, menuItems);
        lvNav.setAdapter(navAdapter);
        navigationView.setNavigationItemSelectedListener(MainScreen.this);
        lvNav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new CategoriesFragment()).addToBackStack(null).commit();
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new TipsFragment()).addToBackStack(null).commit();
                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new MyFavoriteFragment()).addToBackStack(null).commit();
                        break;
                    case 3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new FeedBackFragment()).addToBackStack(null).commit();
                        break;
                }
                drawer.closeDrawer(GravityCompat.START);


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
