package com.example.material_design_demo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private LinearLayout mActivityTabLayout;

    private List<Fragment> fragments;
    private TabAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        int position = getIntent().getIntExtra("position", 0);

        initView();
        initFragments();
        initViewPager();
        initTabLayout();

        mViewPager.setCurrentItem(position);
        mViewPager.setOffscreenPageLimit(3);
    }

    private void initViewPager() {
        mAdapter = new TabAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        Fragment callFragment = new CallFragment();
        Fragment friendFragment = new FriendFragment();
        Fragment locationFragment = new LocationFragment();
        Fragment mailFragment = new MailFragment();
        Fragment taskFragment = new TaskFragment();

        Bundle bundle = new Bundle();
        bundle.putString("TAG","call");
        callFragment.setArguments(bundle);

        Bundle bundle2 = new Bundle();
        bundle2.putString("TAG","friend");
        friendFragment.setArguments(bundle2);

        Bundle bundle3 = new Bundle();
        bundle3.putString("TAG","location");
        locationFragment.setArguments(bundle3);

        Bundle bundle4 = new Bundle();
        bundle4.putString("TAG","mail");
        mailFragment.setArguments(bundle4);

        Bundle bundle5 = new Bundle();
        bundle5.putString("TAG","task");
        taskFragment.setArguments(bundle5);

        fragments.add(callFragment);
        fragments.add(friendFragment);
        fragments.add(locationFragment);
        fragments.add(mailFragment);
        fragments.add(taskFragment);
    }

    private void initTabLayout() {
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mActivityTabLayout = (LinearLayout) findViewById(R.id.activity_tab_layout);
    }

    public class TabAdapter extends FragmentPagerAdapter{

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragments.get(position).getArguments().getString("TAG");
        }
    }
}
