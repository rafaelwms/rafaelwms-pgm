package br.edu.unibratec.rafaelwms.askandanswer;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Build;

public class ManageQuestionActivity extends ActionBarActivity {
	
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage);

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
		

	}
	
	

	
}
