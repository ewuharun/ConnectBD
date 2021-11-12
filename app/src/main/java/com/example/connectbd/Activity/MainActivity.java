package com.example.connectbd.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.connectbd.Adapter.ViewPagerAdapter;
import com.example.connectbd.Animation.ViewPagerAnimation;
import com.example.connectbd.Fragment.CategoryFragment;
import com.example.connectbd.Fragment.ProductFragment;
import com.example.connectbd.Fragment.HistoryFragment;
import com.example.connectbd.Fragment.OrderFragment;
import com.example.connectbd.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter viewPagerAdapter;
    private TextView tvCartTotalItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initView();
        setupViewPager(viewPager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.shopping: {
                // Do something
                Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }



    private void setupViewPager(ViewPager viewPager) {



        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(CategoryFragment.newInstance(),"Category");
        adapter.addFragment(ProductFragment.newInstance(),"Product");
        adapter.addFragment(OrderFragment.newInstance(),"Order");
        adapter.addFragment(HistoryFragment.newInstance(),"History");



        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true,new ViewPagerAnimation());
        

        tabLayout.setupWithViewPager(viewPager);


        //setIconInTabLayout(viewPager);



    }

    private void setIconInTabLayout(ViewPager viewPager) {
        //You tab icons
        int[] icons = {
                R.drawable.ic_profile,
                R.drawable.ic_post,
                R.drawable.ic_user,
                R.drawable.ic_profile,

        };

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setIcon(icons[i]);
        }
    }

    private void initView() {
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

    }
}