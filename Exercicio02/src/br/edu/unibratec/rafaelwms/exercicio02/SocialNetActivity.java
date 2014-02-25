package br.edu.unibratec.rafaelwms.exercicio02;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SocialNetActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_social_net);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.social_net, menu);
		return true;
	}

}
