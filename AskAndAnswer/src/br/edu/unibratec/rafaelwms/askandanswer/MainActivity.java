package br.edu.unibratec.rafaelwms.askandanswer;

import br.edu.unibratec.rafaelwms.askandanswer.R;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity implements
		OnItemClickListener {

	
	public static final int TUTORIAL_OPTION = 0;
	public static final int ANSWER_OPTION = 1;
	public static final int MANAGE_OPTION = 2;
	

	public static User loggedUser; 
	
	public static final String SESSION = "loggedUser";
	DrawerLayout mDrawer;
	ListView mListView;
	ActionBarDrawerToggle mDrawerToggle;
	String mTitle;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	//	mTitle = getResources().getString(R.string.app_name);
		mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		mListView = (ListView) findViewById(R.id.left_drawer);
		
		loggedUser = (User) getIntent().getSerializableExtra(SESSION);
		
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer,
				R.drawable.ic_navigation_drawer, R.string.app_name,
				R.string.app_name) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				getSupportActionBar().setTitle(R.string.app_name);
				supportInvalidateOptionsMenu(); // creates call to
												// onPrepareOptionsMenu()
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				getSupportActionBar().setTitle(mTitle);
				supportInvalidateOptionsMenu(); // creates call to
												// onPrepareOptionsMenu()
			}
		};

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		// Set the drawer toggle as the DrawerListener
		
		if(loggedUser.isMaster_user()){
			mDrawer.setDrawerListener(mDrawerToggle);
		String[] mOpcoes = getResources()
				.getStringArray(R.array.drawer_options);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mOpcoes);
		mListView.setAdapter(adapter);
		}else{
			mDrawer.setDrawerListener(mDrawerToggle);
			String[] mOpcoes = getResources()
					.getStringArray(R.array.drawer_options2);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, mOpcoes);
			mListView.setAdapter(adapter);
		}
	 
		mListView.setOnItemClickListener(this);

	}
	
	
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
          return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		mTitle = mListView.getItemAtPosition(position).toString();
	//	Fragment f;
	//	FragmentManager fm = getSupportFragmentManager();
		Intent it;
		switch (position) {
		case MANAGE_OPTION:
		//	f = new ManageQuestionFragment();
		//	fm.beginTransaction().replace(R.id.content_frame, f).commit();
			it = new Intent(this, ManagementActivity.class);
			startActivity(it);
			break;

		default:
			break;
		}
		
		mDrawer.closeDrawer(mListView);
	}

}
