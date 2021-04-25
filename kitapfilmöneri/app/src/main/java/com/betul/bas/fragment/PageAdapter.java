package com.betul.bas.fragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.betul.bas.FFilmler;
import com.betul.bas.FHepsi;
import com.betul.bas.FKitaplar;
import com.betul.bas.FOyunlar;
import com.betul.bas.R;

public class PageAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] title = new int[]{R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3,R.string.tab_text_4};
    private final Context context;

    public PageAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new FHepsi();
                break;
            case 1:
                fragment = new FFilmler();
                break;
            case 2:
                fragment = new FKitaplar();
                break;
            case 3:
                fragment = new FOyunlar();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(title[position]);
    }

    @Override
    public int getCount() {
        return 4;
    }
}