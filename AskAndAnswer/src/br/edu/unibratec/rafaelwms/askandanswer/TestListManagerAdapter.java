package br.edu.unibratec.rafaelwms.askandanswer;
import java.util.List;

import br.edu.unibratec.rafaelwms.askandanswer.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


public class TestListManagerAdapter extends ArrayAdapter<Test>{

	
	
	public TestListManagerAdapter(Context context, List<Test> objects) {
		super(context, 0, objects);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		// Passo 1 - Pegar o objeto 
		Test test = getItem(position);
		
		// Passo 2 - Carregar o arquivo de Layout
		if(convertView == null){
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.test_list, null);
		}
		
		// Passo 3 - Carregar os componentes do arquivo de Layout
		TextView txtTestName = (TextView)convertView.findViewById(R.id.txtTestName);
		TextView txtTestCateg = (TextView)convertView.findViewById(R.id.txtTestCateg);
		txtTestName.setText(test.getText_title()+" "+getContext().getResources().getString(R.string.lblQuestionNumber)+" "+test.getQuestions().size());
		txtTestCateg.setText(test.getCategory()+" "+getContext().getResources().getString(R.string.lblTestNote)+" "+test.getTest_value());
	
		
		return convertView;
	}


}
