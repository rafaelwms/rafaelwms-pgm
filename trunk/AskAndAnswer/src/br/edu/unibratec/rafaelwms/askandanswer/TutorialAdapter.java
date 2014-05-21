package br.edu.unibratec.rafaelwms.askandanswer;

import java.util.List;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TutorialAdapter extends ArrayAdapter<Tutorial> {

	public TutorialAdapter(Context context, List<Tutorial> objects) {
		super(context, 0, objects);
		// TODO Auto-generated constructor stub
	}
	
	
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		// Passo 1 - Pegar o objeto 
		Tutorial tutorial = getItem(position);
		
		// Passo 2 - Carregar o arquivo de Layout
		if(convertView == null){
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.tutorial_list, null);
		}
		
		// Passo 3 - Carregar os componentes do arquivo de Layout
		ImageView imgTutorial = (ImageView)convertView.findViewById(R.id.imgTutorial);
		TextView txtTutorialName = (TextView)convertView.findViewById(R.id.txtTutorialName);
		TextView txtTutorialDesc = (TextView)convertView.findViewById(R.id.txtTutorialDesc);
		
		Picasso.with(getContext()).load(tutorial.getImage()).into(imgTutorial);
		txtTutorialName.setText(tutorial.getName());
		txtTutorialDesc.setText(tutorial.getDesc());
	
		
		return convertView;
	}
	
	

}
