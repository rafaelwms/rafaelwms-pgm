package br.edu.unibratec.rafaelwms.askandanswer;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class ManagementActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_management);


		}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.management, menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.save_test_action) {
			Intent it = new Intent(this, AccountActivity.class);
			startActivity(it);
			return true;
		}
		if(item.getItemId() == R.id.logout_action){
			MainActivity.loggedUser = null;
			Intent it = new Intent(this, LoginActivity.class);
			startActivity(it);
			finish();	
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	}





