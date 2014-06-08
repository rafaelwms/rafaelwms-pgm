package br.edu.unibratec.rafaelwms.askandanswer;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class ManageQuestionActivity extends ActionBarActivity {
	AskAndAnswerDB db;
	TextView lblTestName;
	TextView txtQuestion;
	EditText editQuest;
	TextView txtNote;
	CheckBox chkAnswer1;
	EditText editAnswer1;
	CheckBox chkAnswer2;
	EditText editAnswer2;
	CheckBox chkAnswer3;
	EditText editAnswer3;
	CheckBox chkAnswer4;
	EditText editAnswer4;
	CheckBox chkAnswer5;
	EditText editAnswer5;
	Test selectedTest;
	List<Question> questions;
	List<Answer> answers;
	Answer ans1;
	Answer ans2;
	Answer ans3;
	Answer ans4;
	Answer ans5;
	Question newQuestion;
	Question selectedQuestion;
	int questionNumber;
	public static final String SAVED_QUESTION = "savedQuest";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage);
		db = new AskAndAnswerDB(this);

		txtQuestion = (TextView) findViewById(R.id.QuestionTxt);
		editQuest = (EditText) findViewById(R.id.editQuest);
		txtNote = (TextView) findViewById(R.id.textQuestionNote);
		chkAnswer1 = (CheckBox) findViewById(R.id.ckbManageAnswer1);
		editAnswer1 = (EditText) findViewById(R.id.editAnswer1);
		chkAnswer2 = (CheckBox) findViewById(R.id.ckbManageAnswer2);
		editAnswer2 = (EditText) findViewById(R.id.editAnswer2);
		chkAnswer3 = (CheckBox) findViewById(R.id.ckbManageAnswer3);
		editAnswer3 = (EditText) findViewById(R.id.editAnswer3);
		chkAnswer4 = (CheckBox) findViewById(R.id.ckbManageAnswer4);
		editAnswer4 = (EditText) findViewById(R.id.editAnswer4);
		chkAnswer5 = (CheckBox) findViewById(R.id.ckbManageAnswer5);
		editAnswer5 = (EditText) findViewById(R.id.editAnswer5);
		
		selectedTest = (Test) getIntent().getSerializableExtra(QuestionListActivity.SELECTED_TEST);
		selectedQuestion = (Question) getIntent().getSerializableExtra(QuestionListActivity.SELECTED_QUESTION);
				
		
		if(selectedQuestion != null && selectedQuestion.getId_question() >=1){
			
			
			editQuest.setText(selectedQuestion.getText().toString());
			
			selectedQuestion.setAnswers(db.findAnswersByQuestion(selectedQuestion));
			ans1 = selectedQuestion.getAnswers().get(0);
			ans2 = selectedQuestion.getAnswers().get(1);
			ans3 = selectedQuestion.getAnswers().get(2);
			ans4 = selectedQuestion.getAnswers().get(3);
			ans5 = selectedQuestion.getAnswers().get(4);
			
			
			editAnswer1.setText(ans1.getText());
			chkAnswer1.setChecked(ans1.isCorrect());
			editAnswer2.setText(ans2.getText());
			chkAnswer2.setChecked(ans2.isCorrect());
			editAnswer3.setText(ans3.getText());
			chkAnswer3.setChecked(ans3.isCorrect());
			editAnswer4.setText(ans4.getText());
			chkAnswer4.setChecked(ans4.isCorrect());
			editAnswer5.setText(ans5.getText());
			chkAnswer5.setChecked(ans5.isCorrect());
		}
		
				
		if (selectedTest.getQuestions() != null) {
			questions = selectedTest.getQuestions();
			questionNumber = 0;
			for(Question qst : questions){	
				if(questionNumber <= qst.getNumber()){
					questionNumber = (qst.getNumber() + 1);
				}
			}
		} else {
			questionNumber = 1;
		}

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.manage, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.save_question_action) {

			try {
				
				if (editQuest.getText().toString().trim().equals("")) {
					throw new Exception(getResources().getString(
							R.string.exceptionQuestionEmpty));
				}
				if (editAnswer1.getText().toString().trim().equals("")
						|| editAnswer2.getText().toString().trim().equals("")
						|| editAnswer3.getText().toString().trim().equals("")
						|| editAnswer4.getText().toString().trim().equals("")
						|| editAnswer5.getText().toString().trim().equals("")) {
					throw new Exception(getResources().getString(
							R.string.exceptionAnswersEmpty));
				}
				if (chkAnswer1.isChecked() == false
						&& chkAnswer2.isChecked() == false
						&& chkAnswer3.isChecked() == false
						&& chkAnswer4.isChecked() == false
						&& chkAnswer5.isChecked() == false) {
					throw new Exception(getResources().getString(
							R.string.exceptionAnswerCorrect));
				}
				if(selectedQuestion == null || selectedQuestion.getId_question() <=0 ){
				newQuestion = new Question(selectedTest, questionNumber,
						editQuest.getText().toString(), null);
				db.insertQuestion(newQuestion);
				newQuestion = db.findQuestionByTestAndNumber(selectedTest,
						questionNumber);

				ans1 = new Answer(1, editAnswer1.getText().toString(), chkAnswer1.isChecked(), 0, newQuestion.getId_question());
				ans2 = new Answer(2, editAnswer2.getText().toString(), chkAnswer2.isChecked(), 0, newQuestion.getId_question());
				ans3 = new Answer(3, editAnswer3.getText().toString(), chkAnswer3.isChecked(), 0, newQuestion.getId_question());
				ans4 = new Answer(4, editAnswer4.getText().toString(), chkAnswer4.isChecked(), 0, newQuestion.getId_question());
				ans5 = new Answer(5, editAnswer5.getText().toString(), chkAnswer5.isChecked(), 0, newQuestion.getId_question());
				
				db.insertAnswer(ans1);
				db.insertAnswer(ans2);
				db.insertAnswer(ans3);
				db.insertAnswer(ans4);
				db.insertAnswer(ans5);
				
				Toast.makeText(this, getResources().getString(R.string.QuestionSave), Toast.LENGTH_SHORT).show();
				
				}else{
					
					selectedQuestion.setText(editQuest.getText().toString());
					
					db.alterQuestion(selectedQuestion);
					
					ans1.setText(editAnswer1.getText().toString());
					ans1.setCorrect(chkAnswer1.isChecked());
					ans2.setText(editAnswer2.getText().toString());
					ans2.setCorrect(chkAnswer2.isChecked());
					ans3.setText(editAnswer3.getText().toString());
					ans3.setCorrect(chkAnswer3.isChecked());
					ans4.setText(editAnswer4.getText().toString());
					ans4.setCorrect(chkAnswer4.isChecked());
					ans5.setText(editAnswer5.getText().toString());
					ans5.setCorrect(chkAnswer5.isChecked());
					
					db.alterAnswer(ans1);
					db.alterAnswer(ans2);
					db.alterAnswer(ans3);
					db.alterAnswer(ans4);
					db.alterAnswer(ans5);
					
					Toast.makeText(this, getResources().getString(R.string.QuestionAltered), Toast.LENGTH_SHORT).show();
				}
				Intent it = new Intent();
				setResult(ManageQuestionActivity.RESULT_OK, it);
				finish();
			} catch (Exception ex) {
				Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
			}

			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed() {
		Intent it = new Intent();
		setResult(ManageQuestionActivity.RESULT_OK, it);
		finish();
		super.onBackPressed();
	}
	
	
}
	
