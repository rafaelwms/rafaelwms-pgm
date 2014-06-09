package br.edu.unibratec.rafaelwms.askandanswer;

import java.util.ArrayList;
import java.util.List;

import br.edu.unibratec.rafaelwms.askandanswer.R.string;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class AnswerTestsListActivity extends ActionBarActivity implements OnItemClickListener {
	
	AskAndAnswerDB db;
	ListView testList;
	List<Test> tests;
	AnswerTestListAdapter adapter;
	Test selectedTest;
	public static final int CALL_QUESTIONS_TEST = 1212;
	public static final String TEST_TO_ANSWER = "answerThisOne";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer_tests_list);
		
		db = new AskAndAnswerDB(this);
		tests = new ArrayList<>();
		testList = (ListView)findViewById(R.id.listViewAnswerTestsList);
		testList.setOnItemClickListener(this);
		selectedTest = new Test();
		refreshlist();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.answer_tests_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.answer_test) {
			
			if (selectedTest == null || selectedTest.getId_test() <= 0) {
				Toast.makeText(
						this,
						getResources().getString(
								R.string.exceptionMustSelectTest),
						Toast.LENGTH_SHORT).show();
			} else {

				AlertDialog.Builder builder = new AlertDialog.Builder(this);

				builder.setMessage(selectedTest.getText_title()).setTitle(
						R.string.dialogTestSelected);

				builder.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
							
							nextActivity();
								
							}

						});
				builder.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								refreshlist();
							}
						});

				AlertDialog dialog = builder.create();

				dialog.show();
			}		
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		selectedTest = (Test) testList.getItemAtPosition(position);
		selectedTest = db.findTestById(selectedTest.getId_test());
		Toast.makeText(this, getResources().getString(string.test)+" " +selectedTest.getText_title()+" " + getResources().getString(string.selected), Toast.LENGTH_SHORT).show();
		
	}
	
	private void refreshlist(){
		tests = new ArrayList<>();
		tests = db.allTests();
		adapter = new AnswerTestListAdapter(tests);
		testList.setAdapter(adapter);
		selectedTest = new Test();
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
	}
	
	private void nextActivity(){
		Intent it = new Intent(this, AnswerQuestionsTestActivity.class);
		it.putExtra(TEST_TO_ANSWER, selectedTest);
		startActivityForResult(it, CALL_QUESTIONS_TEST);
	}
}
