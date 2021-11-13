package com.example.connectbd.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.connectbd.Listener.BadgeCounterListener;
import com.example.connectbd.R;
import com.example.connectbd.SharedPreference.PrefUtil;
import com.example.connectbd.ViewModel.ProductViewModel;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter viewPagerAdapter;
    private TextView tvCartTotalItem,badgeCounter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setToolbar();
        setupViewPager(viewPager);







    }

    private void initListener() {

    }

    private void setToolbar() {
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
        ProductViewModel productViewModel = new ProductViewModel(getApplication());

        badgeCounter.setText(String.valueOf(new ProductViewModel(getApplication()).totalProductInCart()));

        productViewModel.setIncrementBadgeListener(new BadgeCounterListener() {
            @Override
            public void incrementCounter(int count) {
                badgeCounter.setText(String.valueOf(count));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.shopping:
                // Do something
                Intent intent = new Intent(MainActivity.this,CartActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

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

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("onre","onrestar");
        setToolbar();
    }

    private void initView() {
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        toolbar = findViewById(R.id.toolbar);
        badgeCounter =findViewById(R.id.badge_counter);



    }
}