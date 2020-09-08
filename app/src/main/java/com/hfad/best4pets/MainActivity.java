package com.hfad.best4pets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        final SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);
        tabLayout.getTabAt(0).setIcon(R.drawable.baseline_home_white_18dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.baseline_menu_white_18dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.baseline_shopping_cart_white_18dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.baseline_person_white_18dp);


    }






    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        FirebaseUser user = mAuth.getCurrentUser();
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public int getCount() {
            return 4;
        }
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new TopFragment();
                case 1:
                    return new KatalogFragment();
                case 2:
                    if(user==null){
                        return new FailAutificationFragment();
                    } else {
                    return new KorzinaFragment();}
                case 3:
                    if(user==null){
                        return new FailAutificationFragment();
                    }
                    else {
                        return new AccFragment();
                    }
            }
            return null;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getText(R.string.home_tab);
                case 1:
                    return getResources().getText(R.string.katalo_tab);
                case 2:
                    return getResources().getText(R.string.korzina_tab);
                case 3:
                    return getResources().getText(R.string.acc_tab);
            }
            return null;
        }
    }
}
