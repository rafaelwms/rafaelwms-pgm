package br.edu.unibratec.rafaelwms.askandanswer;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AnswerTestListAdapter extends BaseAdapter {
	
	private List<Test> tests;
	
	public AnswerTestListAdapter(List<Test> tsts){
		tests = tsts;
	}

	@Override
	public int getCount() {
		return tests.size();
	}

	@Override
	public Object getItem(int position) {
		return tests.get(position);
	}

	@Override
	public long getItemId(int position) {
		return tests.get(position).getId_test();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Test test = tests.get(position);
		
		if(convertView == null){	
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_tests_model, null);			
		}
		
		TextView txtTestName = (TextView)convertView.findViewById(R.id.txtTestName);
		TextView txtTestCategory  = (TextView)convertView.findViewById(R.id.txtTestCateg);
		TextView txtTestNote =  (TextView)convertView.findViewById(R.id.txtModelTestNote);		
		TextView txtTestQuestions =  (TextView)convertView.findViewById(R.id.txtNumberQuestions);
		TextView txtTestAuthor =  (TextView)convertView.findViewById(R.id.txtTestAuthor);
		
		
		txtTestName.setText(test.getText_title().toString());
		txtTestCategory.setText(test.getCategory().toString());
		txtTestNote.setText(test.getTest_value()+"");
		if(test.getQuestions() != null){
			txtTestQuestions.setText(test.getQuestions().size()+"");
		}else{
			txtTestQuestions.setText("0");
		}
		
		txtTestAuthor.setText(test.getUser().getLogin_user().toString());
		
		return convertView;
	}


}
