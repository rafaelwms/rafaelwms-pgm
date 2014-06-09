package br.edu.unibratec.rafaelwms.askandanswer;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

public class ManageTestActivity extends ActionBarActivity implements
		OnItemClickListener {

	User loggedUser;
	EditText edtTestName;
	EditText edtTestCategory;
	EditText edtTestNote;
	Test selectedMngTest;
	List<Test> tests;
	AskAndAnswerDB db;
	ListView testList;
	TestAdapter adapter;
	public static final String SELECTED_TEST = "testSelected";
	public static final int REQUEST_NEW_QUESTION = 132;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_management);

		db = new AskAndAnswerDB(this);

		Intent it = getIntent();

		loggedUser = (User) it.getSerializableExtra(MainActivity.SESSION);

		selectedMngTest = null;
		edtTestName = (EditText) findViewById(R.id.editTestName);
		edtTestCategory = (EditText) findViewById(R.id.editNewTestCategory);
		edtTestNote = (EditText) findViewById(R.id.editNewTestNote);
		testList = (ListView) findViewById(R.id.litsViewTests);
		testList.setOnItemClickListener(this);

		tests = new ArrayList<>();

		tests = db.findTestByUser(loggedUser);

		if (tests.size() > 0) {
			refreshTestList();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.management, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@SuppressLint("NewApi")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == R.id.save_test_action) {
			try {

				if (selectedMngTest == null || selectedMngTest.getId_test() <= 0) {

					Test test = new Test(MainActivity.loggedUser, edtTestName
							.getText().toString(), edtTestCategory.getText()
							.toString(), null, Double.parseDouble(edtTestNote
							.getText().toString()));

					db.insertTest(test);
					refreshTestList();
					clearEditTexts();
					return true;
				} else {

					Test test = new Test(
							selectedMngTest.getId_test(),
							MainActivity.loggedUser,
							edtTestName.getText().toString(),
							edtTestCategory.getText().toString(),
							selectedMngTest.getQuestions(),
							Double.parseDouble(edtTestNote.getText().toString()));

					db.alterTest(test);
					refreshTestList();
					clearEditTexts();
					return true;
				}

			} catch (Exception ex) {
				return true;
			}
		}
		if (item.getItemId() == R.id.add_question_action) {
			if (selectedMngTest == null || selectedMngTest.getId_test() <= 0) {
				Toast.makeText(
						this,
						getResources().getString(
								R.string.exceptionMustSelectTest),
						Toast.LENGTH_SHORT).show();
			} else {
				Intent it = new Intent(this, QuestionListActivity.class);
				it.putExtra(SELECTED_TEST, selectedMngTest);
				startActivityForResult(it, REQUEST_NEW_QUESTION);
				return true;
			}
		}

		if (item.getItemId() == R.id.del_test_action) {

			if (selectedMngTest == null || selectedMngTest.getId_test() <= 0) {
				Toast.makeText(
						this,
						getResources().getString(
								R.string.exceptionMustSelectTest),
						Toast.LENGTH_SHORT).show();
			} else {

				AlertDialog.Builder builder = new AlertDialog.Builder(this);

				builder.setMessage(R.string.dialogMsgDelTest).setTitle(
						R.string.menuDelTest);

				builder.setPositiveButton(R.string.ok,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								db.deleteTest(selectedMngTest);
								selectedMngTest = new Test();
								refreshTestList();
								clearEditTexts();
							}
						});
				builder.setNegativeButton(R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								clearEditTexts();
							}
						});

				AlertDialog dialog = builder.create();

				dialog.show();
			}
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private void refreshTestList() {
		tests = new ArrayList<Test>();
		tests = db.findTestByUser(loggedUser);
		adapter = new TestAdapter(tests);
		testList.setAdapter(adapter);
	}

	private void clearEditTexts() {
		edtTestName.setText("");
		edtTestCategory.setText("");
		edtTestNote.setText("");
		selectedMngTest = new Test();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		selectedMngTest = (Test) testList.getItemAtPosition(position);
		selectedMngTest.setQuestions(db.findQuestionsByTest(selectedMngTest));
		edtTestName.setText(selectedMngTest.getText_title());
		edtTestCategory.setText(selectedMngTest.getCategory());
		edtTestNote.setText(selectedMngTest.getTest_value() + "");

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_NEW_QUESTION && resultCode == RESULT_OK) {
			refreshTestList();
			clearEditTexts();

		}

	}

}
