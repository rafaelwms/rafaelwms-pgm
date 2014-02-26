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

public class SocialNetActivity extends Activity implements OnClickListener {
	
	
	
	
	RadioGroup rdgSocial;
	
	RadioButton rdbBadoo;
	RadioButton rdbFacebook;
	RadioButton rdbFoursquare;
	RadioButton rdbLinkedin;
	RadioButton rdbTweeter;
	
	Button btnSelect;
	public int rdbId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_social_net);
		
		rdgSocial = (RadioGroup)findViewById(id.rdgAct02Social);
		
		rdbBadoo = (RadioButton)findViewById(id.rdbAct02Badoo);
		rdbFacebook = (RadioButton)findViewById(id.rdbAct02Face);
		rdbFoursquare = (RadioButton)findViewById(id.rdbAct02Foursquare);
		rdbLinkedin = (RadioButton)findViewById(id.rdbAct02Linkdein);
		rdbTweeter = (RadioButton)findViewById(id.rdbAct02Tweeter);
		
		btnSelect = (Button)findViewById(id.btnAct02Select);
		btnSelect.setOnClickListener(this);
		
		Intent it = getIntent();
		rdbId = it.getIntExtra("idRdb", -1);
		
		if (rdbId != -1){
			rdgSocial.check(rdbId);
		}
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.social_net, menu);
		return true;
	}
	
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.btnAct02Select){
		RadioButton rdbChecked;
		int rdbCheckedId = rdgSocial.getCheckedRadioButtonId();
		rdbChecked = (RadioButton)findViewById(rdbCheckedId);
		Intent it = new Intent();
		it.putExtra("socialNetwork", rdbChecked.getText().toString());
		it.putExtra("idRdb", rdbCheckedId);
		setResult(RESULT_OK, it);
		finish();
		}
	}

}
