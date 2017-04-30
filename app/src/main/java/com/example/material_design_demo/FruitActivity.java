package com.example.material_design_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class FruitActivity extends AppCompatActivity {

    private ImageView mFruitImageView;
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private AppBarLayout mAppBar;
    private TextView mFruitContentText;
    private CoordinatorLayout mActivityFruit;

    public static final String FRUIT_NAME  = "fruit_name";
    public static final String FRUIT_IMAGE_ID  = "fruit_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        Intent intent = getIntent();
        String fruitName = intent.getStringExtra(FRUIT_NAME);
        int fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0);
        initView();
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mCollapsingToolbar.setTitle(fruitName);
        Glide.with(this).load(fruitImageId).into(mFruitImageView);
        String fruitContent = generateFruitContent(fruitName);
        mFruitContentText.setText(fruitContent);
    }

    private String generateFruitContent(String fruitName) {
        StringBuilder fruitContent = new StringBuilder();
        for (int i=0; i<500; i++){
            fruitContent.append(fruitName);
        }
        return fruitContent.toString();
    }

    private void initView() {
        mFruitImageView = (ImageView) findViewById(R.id.fruit_image_view);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mAppBar = (AppBarLayout) findViewById(R.id.appBar);
        mFruitContentText = (TextView) findViewById(R.id.fruit_content_text);
        mActivityFruit = (CoordinatorLayout) findViewById(R.id.activity_fruit);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
