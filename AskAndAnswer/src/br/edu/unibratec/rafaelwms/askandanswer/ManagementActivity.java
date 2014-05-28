package br.edu.unibratec.rafaelwms.askandanswer;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.os.Build;

public class ManagementActivity extends ActionBarActivity implements OnItemClickListener{

	User loggedUser;
	EditText edtTestName;
	EditText edtTestCategory;
	EditText edtTestNote;
	Test testSelected;
	List<Test> tests;
	TestDB db;
	ListView testList;
	TestAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_management);
		
		db = new TestDB(this);
		
		Intent it = getIntent();
		
		loggedUser = (User) it.getSerializableExtra(MainActivity.SESSION);
		
		
		testSelected = null;
		edtTestName = (EditText) findViewById(R.id.editTestName);
		edtTestCategory = (EditText) findViewById(R.id.editNewTestCategory);
		edtTestNote = (EditText) findViewById(R.id.editNewTestNote);
		testList = (ListView) findViewById(R.id.litsViewTests);
		testList.setOnItemClickListener(this);
		
		tests = new ArrayList<>();
		
		tests = db.findTestByUser(loggedUser);;
		
		if(tests.size() > 0){
		refreshTestList();		
		}
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

			Test test = new Test(MainActivity.loggedUser, edtTestName.getText()
					.toString(), edtTestCategory.getText().toString(), null,
					Double.parseDouble(edtTestNote.getText().toString()));
			
			db.insert(test);
			refreshTestList();
			clearEditTexts();
			

			return true;
		}
		if (item.getItemId() == R.id.logout_action) {
			MainActivity.loggedUser = null;
			Intent it = new Intent(this, LoginActivity.class);
			startActivity(it);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void refreshTestList(){
		tests = new ArrayList<Test>();
		tests = db.findTestByUser(loggedUser);
		adapter = new TestAdapter(tests);
		testList.setAdapter(adapter);
	}
	
	private void clearEditTexts(){
		edtTestName.setText("");
		edtTestCategory.setText("");
		edtTestNote.setText("");
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		
		
	}

}
