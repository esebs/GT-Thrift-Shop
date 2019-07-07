package com.github.esebs.cs2340project.spacetrader.views;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.github.esebs.cs2340project.spacetrader.R;

public class MainActivity extends AppCompatActivity {

    private SectionsPageAdapeter sectionsPageAdapeter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sectionsPageAdapeter = new SectionsPageAdapeter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.refreshDrawableState();
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapeter adapter = new SectionsPageAdapeter(getSupportFragmentManager());
        adapter.addFragment(new SystemFragment(), "System");
        adapter.addFragment(new BuyFragment(), "Buy");
        adapter.addFragment(new SellFragment(), "Sell");
        adapter.addFragment(new VehicleFragment(), "Vehicle");
        viewPager.setAdapter(adapter);

    }
}