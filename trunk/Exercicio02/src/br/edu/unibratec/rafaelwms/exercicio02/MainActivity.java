package br.edu.unibratec.rafaelwms.exercicio02;

import br.edu.unibratec.rafaelwms.exercicio02.R.id;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	Button btnSocial;
	Button btnState;
	Button btnNext;
	EditText edtName;
	int reqSocial = 0;
	String socialNet = "";
	String state = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnSocial = (Button) findViewById(id.btnAct01Social);
		btnState = (Button) findViewById(id.btnAct01State);
		btnNext = (Button) findViewById(id.btnAct01Next);
		edtName = (EditText) findViewById(id.edtName);

		btnSocial.setOnClickListener(this);

		if (savedInstanceState != null) {

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
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		if (v.getId() == R.id.btnAct01Social) {
			Intent it = new Intent(this, SocialNetworkActivity.class);
			startActivityForResult(it, reqSocial);
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == reqSocial && resultCode == RESULT_OK) {
			socialNet = data.getStringExtra("socialNetwork");
			btnSocial.setText(socialNet);
		}

	}

}
