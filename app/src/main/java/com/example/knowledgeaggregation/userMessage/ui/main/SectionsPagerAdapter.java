package com.example.knowledgeaggregation.userMessage.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.knowledgeaggregation.R;
import com.example.knowledgeaggregation.userMessage.ChemailFragment;
import com.example.knowledgeaggregation.userMessage.ChotherFragment;
import com.example.knowledgeaggregation.userMessage.ChpwdFragment;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment fragment=null;
        switch (position){
            case 0:
                fragment=new ChpwdFragment();
                break;
            case 1:
                fragment=new ChemailFragment();
                break;
            case 2:
                fragment=new ChotherFragment();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "修改密码";

            case 1:
                return "修改邮箱";

            case 2:
                return "修改其他";

        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}