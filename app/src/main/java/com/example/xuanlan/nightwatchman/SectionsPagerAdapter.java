package com.example.xuanlan.nightwatchman;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private KnowLedgeFragment knowLedgeFragment;
    private ArticleFragment articleFragment;
    private SystemFragment systemFragment;

    private int SECTION_NUMBER = 3;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
        knowLedgeFragment = new KnowLedgeFragment();
        articleFragment = new ArticleFragment();
        systemFragment = new SystemFragment();
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = knowLedgeFragment;
                break;
            case 1:
                fragment = articleFragment;
                break;
            case 2:
                fragment = systemFragment;
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return SECTION_NUMBER;
    }
}
