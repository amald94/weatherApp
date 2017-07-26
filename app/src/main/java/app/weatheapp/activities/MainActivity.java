package app.weatheapp.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import app.weatheapp.R;
import app.weatheapp.adapter.SlidePagerAdapter;
import app.weatheapp.model.weatherModel;


public class MainActivity extends AppCompatActivity {

    private weatherModel wModel;
    ViewPager mPager;
    SlidePagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wModel = new weatherModel();
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new SlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);


    }


}
