package br.edu.unibratec.rafaelwms.askandanswer;

import br.edu.unibratec.rafaelwms.askandanswer.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class ManageQuestionFragment extends Fragment {

	public static ManageQuestionFragment newInstance() {

		ManageQuestionFragment frag = new ManageQuestionFragment();
		return frag;

	}

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
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View layout = inflater.inflate(R.layout.fragment_manage_question, container,
				false);

		txtQuestion = (TextView) layout.findViewById(R.id.QuestionTxt);
		editQuest = (EditText) layout.findViewById(R.id.editQuest);
		txtNote = (TextView) layout.findViewById(R.id.textQuestionNote);
		chkAnswer1 = (CheckBox) layout.findViewById(R.id.ckbManageAnswer1);
		editAnswer1 = (EditText) layout.findViewById(R.id.editAnswer1);
		chkAnswer2 = (CheckBox) layout.findViewById(R.id.ckbManageAnswer2);
		editAnswer2 = (EditText) layout.findViewById(R.id.editAnswer2);
		chkAnswer3 = (CheckBox) layout.findViewById(R.id.ckbManageAnswer3);
		editAnswer3 = (EditText) layout.findViewById(R.id.editAnswer3);
		chkAnswer4 = (CheckBox) layout.findViewById(R.id.ckbManageAnswer4);
		editAnswer4 = (EditText) layout.findViewById(R.id.editAnswer4);
		chkAnswer5 = (CheckBox) layout.findViewById(R.id.ckbManageAnswer5);
		editAnswer5 = (EditText) layout.findViewById(R.id.editAnswer5);

		return layout;
	}


}
