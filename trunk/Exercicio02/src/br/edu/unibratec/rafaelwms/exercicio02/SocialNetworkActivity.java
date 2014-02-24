package br.edu.unibratec.rafaelwms.exercicio02;

import br.edu.unibratec.rafaelwms.exercicio02.R.id;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SocialNetworkActivity extends Activity implements OnClickListener {
	
	RadioGroup rdgSocial;
	
	RadioButton rdbBadoo;
	RadioButton rdbFacebook;
	RadioButton rdbFoursquare;
	RadioButton rdbLinkedin;
	RadioButton rdbTweeter;
	
	String retorno = "";
	
	Button btnSelect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_social_network);
		
		rdgSocial = (RadioGroup)findViewById(id.rdgAct2Social);
		rdbBadoo = (RadioButton)findViewById(id.rdbAct02Badoo);
		rdbFacebook = (RadioButton)findViewById(id.rdbAct02Facebook);
		rdbFoursquare = (RadioButton)findViewById(id.rdbAct02Foursquare);
		rdbLinkedin = (RadioButton)findViewById(id.rdbAct02Linkedin);
		rdbTweeter = (RadioButton)findViewById(id.rdbAct02Tweeter);
		btnSelect = (Button)findViewById(id.btnAct02Select);
		
		btnSelect.setOnClickListener(this);
		
		
		
		if(savedInstanceState != null){
			
		}
		
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		

	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.social_network, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		
		if(v.getId() == R.id.btnAct02Select){
			
			if(rdbBadoo.isChecked()){
				retorno = "Badoo";
			}
			if(rdbFacebook.isChecked()){
				retorno = "Facebook";
			}
			if(rdbFoursquare.isChecked()){
				retorno = "Foursquare";
			}
			if(rdbLinkedin.isChecked()){
				retorno = "Linkedin";
			}
			if(rdbTweeter.isChecked()){
				retorno = "Tweeter";
			}
			
			Intent it = new Intent();
			it.putExtra("socialNetwork", retorno);
			setResult(RESULT_OK, it);
			finish();
		}
		
	}

}
