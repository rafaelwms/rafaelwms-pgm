package br.edu.unibratec.rafaelwms.askandanswer;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class AnswerQuestionsAdapter extends BaseAdapter {
	
	private List<Question> questions;
	
	public AnswerQuestionsAdapter(List<Question> quests){
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
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.answer_test_model, null);
		}
		
		TextView txtQuestion = (TextView)convertView.findViewById(R.id.txtQuestionText);
		CheckBox chkAns01 = (CheckBox)convertView.findViewById(R.id.chkAns01);
		CheckBox chkAns02 = (CheckBox)convertView.findViewById(R.id.chkAns02);
		CheckBox chkAns03 = (CheckBox)convertView.findViewById(R.id.chkAns03);
		CheckBox chkAns04 = (CheckBox)convertView.findViewById(R.id.chkAns04);
		CheckBox chkAns05 = (CheckBox)convertView.findViewById(R.id.chkAns05);
		
		txtQuestion.setText(quest.getText().toString());
		chkAns01.setText(quest.getAnswers().get(0).getNumber()+") "+quest.getAnswers().get(0).getText().toString());
		chkAns02.setText(quest.getAnswers().get(1).getNumber()+") "+quest.getAnswers().get(1).getText().toString());
		chkAns03.setText(quest.getAnswers().get(2).getNumber()+") "+quest.getAnswers().get(2).getText().toString());
		chkAns04.setText(quest.getAnswers().get(3).getNumber()+") "+quest.getAnswers().get(3).getText().toString());
		chkAns05.setText(quest.getAnswers().get(4).getNumber()+") "+quest.getAnswers().get(4).getText().toString());
		
		return convertView;
	}

}
