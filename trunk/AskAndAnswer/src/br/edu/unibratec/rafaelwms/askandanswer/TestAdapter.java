package br.edu.unibratec.rafaelwms.askandanswer;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class TestAdapter extends BaseAdapter {


private List<Test> tests;

public TestAdapter(List<Test> ts){
	
	tests = ts;
}

@Override
public int getCount() {
	// TODO Auto-generated method stub
	return tests.size();
}


@Override
public Object getItem(int position) {
	// TODO Auto-generated method stub
	return tests.get(position);
}


@Override
public long getItemId(int position) {
	// TODO Auto-generated method stub
	return tests.get(position).getId_test();
}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Test test = tests.get(position);
		
		if(convertView == null){	
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_list, null);			
		}
		
		TextView txtTestName = (TextView)convertView.findViewById(R.id.txtTestName);
		TextView txtTestCategory  = (TextView)convertView.findViewById(R.id.txtTestCateg);
		TextView txtTestNote =  (TextView)convertView.findViewById(R.id.txtModelTestNote);		
		TextView txtTestQuestions =  (TextView)convertView.findViewById(R.id.txtNumberQuestions);
		
		
		txtTestName.setText(test.getText_title().toString());
		txtTestCategory.setText(test.getCategory().toString());
		txtTestNote.setText(test.getTest_value()+"");
		if(test.getQuestions() != null){
			txtTestQuestions.setText(test.getQuestions().size()+"");
		}else{
			txtTestQuestions.setText("0");
		}
		
		
		return convertView;
	}


	
	

}
