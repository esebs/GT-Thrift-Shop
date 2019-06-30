package com.github.esebs.cs2340project.spacetrader.views;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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
        viewPager.setAdapter(adapter);

    }
}