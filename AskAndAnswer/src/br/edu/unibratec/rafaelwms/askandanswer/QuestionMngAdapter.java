package br.edu.unibratec.rafaelwms.askandanswer;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class QuestionMngAdapter extends BaseAdapter {
	
	private List<Question> questions;
	
	public QuestionMngAdapter(List<Question> quests){
		questions = quests;
	}

	@Override
	public int getCount() {
		return questions.size();
	}

	@Override
	public Object getItem(int position) {
		return questions.get(position);
	}

	@Override
	public long getItemId(int position) {
		return questions.get(position).getId_question();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Question quest = questions.get(position);
		
		if(convertView == null){
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_mng_list, null);
		}
		
		TextView txtNumQuest = (TextView)convertView.findViewById(R.id.lblQuestionListTestNumber);
		TextView txtNameQuest = (TextView)convertView.findViewById(R.id.txtQuestionListTitle);
		
		txtNumQuest.setText(quest.getNumber()+") ");
		txtNameQuest.setText(quest.getText().toString());
		
		
		
		return convertView;
	}

}
