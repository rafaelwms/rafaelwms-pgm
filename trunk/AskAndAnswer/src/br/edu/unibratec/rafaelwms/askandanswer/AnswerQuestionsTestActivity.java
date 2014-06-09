package br.edu.unibratec.rafaelwms.askandanswer;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
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

public class AnswerQuestionsTestActivity extends ActionBarActivity implements OnItemClickListener {
	
	AskAndAnswerDB db;
	TextView txtTest;
	ListView questionsList;
	Question selectedQuestion;
	Test selectedTest;
	List<Question> questionsTest;
	List<Question> answeredQuestions;
	User loggedUser;
	AnswerQuestionsAdapter adapter;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer_questions_test);
		
		db = new AskAndAnswerDB(this);
		txtTest = (TextView)findViewById(R.id.txtTestTitle);
		questionsList = (ListView)findViewById(R.id.listViewQuestionTest);
		questionsList.setOnItemClickListener(this);
		
		
		
		selectedQuestion = new Question();
		selectedTest = (Test) getIntent().getSerializableExtra(AnswerTestsListActivity.TEST_TO_ANSWER);
		txtTest.setText(selectedTest.getText_title());
		questionsTest = new ArrayList<>();
		questionsTest = db.findQuestionsByTest(selectedTest);
		refreshlist();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.answer_questions_test, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void refreshlist(){
		
		adapter = new AnswerQuestionsAdapter(questionsTest);
		questionsList.setAdapter(adapter);
		selectedQuestion = new Question();
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		selectedQuestion = (Question) questionsList.getItemAtPosition(position);
		
		Toast.makeText(this, getResources().getString(R.string.Question)+" # "+ selectedQuestion.getNumber()+getResources().getString(R.string.selected), Toast.LENGTH_SHORT).show();
		
	}

}
