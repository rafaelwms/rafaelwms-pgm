package br.edu.rafaelwms.actionbar;

import android.support.v7.app.ActionBar.OnNavigationListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.os.Build;

public class SpinnerActivity extends ActionBarActivity implements OnNavigationListener{

	
	private String[] opcoes = new String[]{"Opção 1", "Opção 2", "Opção 3"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spinner);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
		this,android.R.layout.simple_spinner_item, opcoes);
		
		adapter.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);
		
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		getSupportActionBar().setListNavigationCallbacks(adapter, this);

	}

	@Override
	public boolean onNavigationItemSelected(int position, long id) {
		// TODO Auto-generated method stub
		ColorFragment fragment = new ColorFragment();
		
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.container, fragment).commit();
		
		return true;
	}

}
