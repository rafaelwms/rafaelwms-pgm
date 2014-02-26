package br.edu.unibratec.rafaelwms.exercicio02;

import br.edu.unibratec.rafaelwms.exercicio02.R.id;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class ResultsActivity extends Activity {
	
	TextView txtName;
	TextView txtSocialNet;
	TextView txtState;
	TextView txtTrustedInfo;
	
	String name;
	String social;
	String state;
	String trust;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_results);
		
		txtName = (TextView)findViewById(id.txtAct04Name);
		txtSocialNet = (TextView)findViewById(id.txtAct04Social);
		txtState = (TextView)findViewById(id.txtAct04State);
		txtTrustedInfo = (TextView)findViewById(id.txtAct04TrustInfo);
		
		Intent it = getIntent();
		txtName.setText(it.getStringExtra("name"));
		txtSocialNet.setText(it.getStringExtra("socialNetwork"));
		txtState.setText(it.getStringExtra("state"));
		if(it.getBooleanExtra("trustInfo", false)){
			txtTrustedInfo.setText(R.string.trustYes);
		}else{
			txtTrustedInfo.setText(R.string.trutNo);
		}
		
		if(savedInstanceState != null){
			
			name = savedInstanceState.getString("nameSave");
			social = savedInstanceState.getString("socialSave");
			state = savedInstanceState.getString("stateSave");
			trust = savedInstanceState.getString("trustSave");
			
			txtName.setText(name);
			txtSocialNet.setText(social);
			txtState.setText(state);
			txtTrustedInfo.setText(trust);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.results, menu);
		return true;
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		
		outState.putString("nameSave", txtName.getText().toString());
		outState.putString("socialSave", txtSocialNet.getText().toString());
		outState.putString("stateSave", txtState.getText().toString());
		outState.putString("trustSave", txtTrustedInfo.getText().toString());
		
	}

}
