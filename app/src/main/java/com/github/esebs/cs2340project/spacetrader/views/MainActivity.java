package com.github.esebs.cs2340project.spacetrader.views;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.github.esebs.cs2340project.spacetrader.R;

/**
 * This class is tasked with displaying all tabs in
 * this tabbed activity
 *
 * @version 1.0
 * @author Elio Gerges
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SectionsPageAdapter sectionsPageAdapter =
                new SectionsPageAdapter(getSupportFragmentManager());
        System.out.println(sectionsPageAdapter);

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.refreshDrawableState();
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new SystemFragment(), "System");
        adapter.addFragment(new BuyFragment(), "Buy");
        adapter.addFragment(new SellFragment(), "Sell");
        adapter.addFragment(new VehicleFragment(), "Vehicle");
        adapter.addFragment(new TravelFragment(), "Travel");
        viewPager.setAdapter(adapter);
    }
}