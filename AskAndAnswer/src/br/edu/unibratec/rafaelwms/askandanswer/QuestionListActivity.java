package br.edu.unibratec.rafaelwms.askandanswer;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.ls.LSInput;

import br.edu.unibratec.rafaelwms.askandanswer.R.string;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class QuestionListActivity extends ActionBarActivity implements
		OnItemClickListener, OnClickListener {

	public static final int NEW_QUESTION = 10;
	public static final int EDIT_QUESTION = 11;
	public static final String SELECTED_TEST = "selectedTest";
	public static final String SELECTED_QUESTION = "selectedQuestion";
	
	AskAndAnswerDB db;
	TextView txtTestName;
	ListView listQuests;
	Button btnAddQuest;
	List<Question> questions;
	QuestionMngAdapter adapter;
	Test selectedTest;
	Question selectedQuestion;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question_list);
		
		db = new AskAndAnswerDB(this);
		txtTestName = (TextView) findViewById(R.id.txtTestName2);
		listQuests = (ListView) findViewById(R.id.listViewQuestions);
		listQuests.setOnItemClickListener(this);
		btnAddQuest = (Button) findViewById(R.id.btnAddQuestion);
		btnAddQuest.setOnClickListener(this);

		Intent it = getIntent();
		selectedTest = (Test) it.getSerializableExtra(ManageTestActivity.SELECTED_TEST);
		txtTestName.setText(selectedTest.getText_title());
		questions = new ArrayList<>();
		selectedQuestion = new Question();
			
		refreshList();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.question_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.edit_question_action) {
			
			try{
				
				if(selectedQuestion == null || selectedQuestion.getId_question() <= 0){
					throw new Exception(getResources().getString(string.exceptionMustSelectQuest));
				}		
				
				Intent it = new Intent(this, ManageQuestionActivity.class);
				it.putExtra(SELECTED_TEST, selectedTest);
				it.putExtra(SELECTED_QUESTION, selectedQuestion);
				startActivityForResult(it, EDIT_QUESTION);	
				return true;
			}catch(Exception ex){
				Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT)
				.show();
				return true;
			}			

		}
		
		if(id == R.id.del_question_action){
			try{
				if(selectedQuestion == null || selectedQuestion.getId_question() <= 0){
					throw new Exception(getResources().getString(string.exceptionMustSelectQuest));
				}
				db.deleteQuestion(selectedQuestion);
				refreshList();
				
			}catch(Exception ex){
				Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT)
				.show();
			}			
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		selectedQuestion = (Question) listQuests.getItemAtPosition(position);
		Toast.makeText(this, getResources().getString(string.Question)+" " +(position + 1)+") " + getResources().getString(string.selected), Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View v) {	
		if(v.getId() == R.id.btnAddQuestion){			
			selectedQuestion = new Question();
			Intent it = new Intent(this, ManageQuestionActivity.class);
			it.putExtra(SELECTED_TEST, selectedTest);
			it.putExtra(SELECTED_QUESTION, selectedQuestion);
			startActivityForResult(it, NEW_QUESTION);			
		}
	}
	
	@Override
	protected void onActivityResult(int requestcode, int resultcode, Intent data) {
		super.onActivityResult(requestcode, resultcode, data);
		if(requestcode == NEW_QUESTION && resultcode == RESULT_OK){
			selectedTest.setQuestions(db.findQuestionsByTest(selectedTest));
			refreshList();
		}
		
	}

	private void refreshList() {
		questions = new ArrayList<>();
		if (selectedTest.getQuestions() != null && selectedTest.getQuestions().size() > 0) {
			questions = db.findQuestionsByTest(selectedTest);
		}
		adapter = new QuestionMngAdapter(questions);
		listQuests.setAdapter(adapter);
	}
	
	@Override
	public void onBackPressed() {
		Intent it = new Intent();
		setResult(QuestionListActivity.RESULT_OK, it);
		finish();
		super.onBackPressed();
	}

}
