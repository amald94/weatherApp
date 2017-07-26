package app.weatheapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import app.weatheapp.fragments.currentTempFragments;
import app.weatheapp.fragments.fragmentW;
import app.weatheapp.fragments.weekTempFragment;

/**
 * Created by devkkda on 7/20/2017.
 */

public class SlidePagerAdapter extends FragmentPagerAdapter {
    public SlidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0)
            return new fragmentW();
        else
            return new weekTempFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }
}

