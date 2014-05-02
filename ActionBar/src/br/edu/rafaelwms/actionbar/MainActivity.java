package br.edu.rafaelwms.actionbar;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
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
import android.os.Build;

public class MainActivity extends ActionBarActivity implements OnItemClickListener {
	
	DrawerLayout mDrawer;
	ListView mListView;
	ActionBarDrawerToggle mDrawerToggle;
	String mTitulo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mDrawer = (DrawerLayout)findViewById(R.id.drawer_layout);
		mListView = (ListView)findViewById(R.id.left_drawer);
		

	        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer,
	                R.drawable.ic_navigation_drawer, R.string.app_name, R.string.app_name) {

	            /** Called when a drawer has settled in a completely closed state. */
	            public void onDrawerClosed(View view) {
	                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitulo);
            //    invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
	            }

	            /** Called when a drawer has settled in a completely open state. */
	            public void onDrawerOpened(View drawerView) {
	                super.onDrawerOpened(drawerView);
	                getSupportActionBar().setTitle(R.string.app_name);
	         //       invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
	            }
	        };

	        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	        getSupportActionBar().setHomeButtonEnabled(true);
	        
	        
	        // Set the drawer toggle as the DrawerListener
	        mDrawer.setDrawerListener(mDrawerToggle);
		
		String[] mOpcoes = getResources().getStringArray(R.array.opcoes_menu);
		ArrayAdapter<String> adapter = new  ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mOpcoes);
		mListView.setAdapter(adapter);
		
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
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		mTitulo = mListView.getItemAtPosition(position).toString();
		Nivel1Fragment f = Nivel1Fragment.novaInstancia(position);
		
		FragmentManager fm = getSupportFragmentManager();
		fm.beginTransaction()
		.replace(R.id.content_frame, f)
		.commit();
		mDrawer.closeDrawer(mListView);
	}

}
