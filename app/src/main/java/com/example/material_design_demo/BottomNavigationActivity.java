package com.example.material_design_demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class BottomNavigationActivity extends AppCompatActivity {

    private TextView nav_bottom_text;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        initView();
        nav_bottom_text.setText("Text1");
    }

    private void initView() {
        nav_bottom_text = (TextView) findViewById(R.id.nav_bottom_text);
        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
//        bottomNavigation.setLayoutMode(ViewGroup.LAYOUT_MODE_OPTICAL_BOUNDS);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                nav_bottom_text.setText(item.getTitle());
                return true;
            }
        });

    }
}
