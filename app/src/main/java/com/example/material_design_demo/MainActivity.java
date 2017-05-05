package com.example.material_design_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
*测试代码同步
*/
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private DrawerLayout drawer_layout;
    private NavigationView nav_view;
    private FloatingActionButton fab;
    private RecyclerView recycler_view;

    private List<Fruit> fruitList = new ArrayList<>();
    private FruitAdapter adapter;
    private Fruit[] fruits = {new Fruit("apple", R.mipmap.img), new Fruit("pear", R.mipmap.ic_launcher)};
    private SwipeRefreshLayout swipe_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        }

        nav_view.setCheckedItem(R.id.nav_call);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                jump2Acivity(item.getItemId());
                drawer_layout.closeDrawers();
                return true;
            }
        });

        initFruits();

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recycler_view.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitList);
        recycler_view.setAdapter(adapter);

        adapter.setOnItemClickListener(new FruitAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Fruit fruit = fruitList.get(position);
                Intent intent = new Intent(MainActivity.this, FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_NAME, fruit.getName());
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.getImageId());
                startActivity(intent);
            }
        });
    }

    private void jump2Acivity(int itemId) {

        Intent intent = new Intent(this, TabLayoutActivity.class);

        switch (itemId){
            case R.id.nav_call:
                intent.putExtra("position", 0);
            break;
            case R.id.nav_friends:
                intent.putExtra("position", 1);
                break;
            case R.id.nav_location:
                intent.putExtra("position", 2);
                break;
            case R.id.nav_mail:
                intent.putExtra("position", 3);
                break;
            case R.id.nav_task:
                intent.putExtra("position", 4);
                break;
        }

        startActivity(intent);
    }

    private void initFruits() {
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        nav_view = (NavigationView) findViewById(R.id.nav_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        swipe_refresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipe_refresh.setColorSchemeResources(R.color.colorPrimary);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refrushFruits();
            }
        });
    }

    private void refrushFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        adapter.notifyDataSetChanged();
                        swipe_refresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer_layout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
//                Toast.makeText(this, "backup", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, BottomNavigationActivity.class));
                break;
            case R.id.delete:
                Toast.makeText(this, "delete", Toast.LENGTH_LONG).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "settings", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Snackbar.make(v, "fab", Snackbar.LENGTH_LONG).show();
                break;
        }
    }
}
