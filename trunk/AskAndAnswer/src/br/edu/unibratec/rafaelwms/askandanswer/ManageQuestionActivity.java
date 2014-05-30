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
	Question oldQuestion;
	int questionNumber;

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
		
		selectedTest = (Test) getIntent().getSerializableExtra(ManagementActivity.SELECTED_TEST);
		
		if (selectedTest.getQuestions() != null) {
			questions = selectedTest.getQuestions();
			questionNumber = (questions.size() + 1);
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

				newQuestion = new Question(selectedTest, questionNumber,
						editQuest.getText().toString(), null);
				db.insertQuestion(newQuestion);
				newQuestion = db.findQuestionByTestAndNumber(selectedTest,
						questionNumber);

				ans1 = new Answer(newQuestion, 1, editAnswer1.getText()
						.toString(), chkAnswer1.isChecked(), 0);
				ans2 = new Answer(newQuestion, 2, editAnswer2.getText()
						.toString(), chkAnswer2.isChecked(), 0);
				ans3 = new Answer(newQuestion, 3, editAnswer3.getText()
						.toString(), chkAnswer3.isChecked(), 0);
				ans4 = new Answer(newQuestion, 4, editAnswer4.getText()
						.toString(), chkAnswer4.isChecked(), 0);
				ans5 = new Answer(newQuestion, 5, editAnswer5.getText()
						.toString(), chkAnswer5.isChecked(), 0);

				db.insertAnswer(ans1);
				db.insertAnswer(ans2);
				db.insertAnswer(ans3);
				db.insertAnswer(ans4);
				db.insertAnswer(ans5);

				Intent it = new Intent();
				setResult(ManageQuestionActivity.RESULT_OK, it);
				finish();
			} catch (Exception ex) {
				Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT)
						.show();
			}

			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
	
