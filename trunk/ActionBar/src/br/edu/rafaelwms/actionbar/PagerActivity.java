package br.edu.rafaelwms.actionbar;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.os.Bundle;


public class PagerActivity extends ActionBarActivity {

	ViewPager mPager;
	PagerTabStrip mTabStrip;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabs);
		
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		
		mPager = (ViewPager)findViewById(R.id.pager);
		mPager.setAdapter(new MeuPagerAdapter(getSupportFragmentManager()));


	}


	
	class MeuPagerAdapter extends FragmentPagerAdapter{

		public MeuPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return new ColorFragment();
		}

		@Override
		public int getCount() {
			return 2;
		}
		}
}
