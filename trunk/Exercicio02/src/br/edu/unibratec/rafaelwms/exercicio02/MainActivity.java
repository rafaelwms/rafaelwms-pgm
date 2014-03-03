package br.edu.unibratec.rafaelwms.exercicio02;

import br.edu.unibratec.rafaelwms.exercicio02.R.id;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	public final static int reqSocial = 1;
	public final static int reqState = 2;

	
	public int idRdb = -1;
	
	Button btnSocial;
	Button btnState;
	Button btnNext;
	EditText edtName;
	CheckBox ckbTrueInfo;
	
	String socialNet = "";
	String state = "";
	String name = "";
	Boolean trustInfo;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnSocial = (Button) findViewById(id.btnAct01Social);
		btnSocial.setOnClickListener(this);
		
		btnState = (Button) findViewById(id.btnAct01State);
		btnState.setOnClickListener(this);
		
		btnNext = (Button) findViewById(id.btnAct01Next);
		btnNext.setOnClickListener(this);
		
		edtName = (EditText) findViewById(id.edtName);
		
		
		ckbTrueInfo = (CheckBox)findViewById(id.chkbxAct01True);
		

		if (savedInstanceState != null) {
			
			name = savedInstanceState.getString("nameSave");
			socialNet = savedInstanceState.getString("socialSave");
			state = savedInstanceState.getString("stateSave");
			trustInfo = savedInstanceState.getBoolean("trustInfoSave");
			edtName.setText(name);
			btnSocial.setText(socialNet);
			btnState.setText(state);
			idRdb = savedInstanceState.getInt("idRdb");
		}

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		
		outState.putString("nameSave", edtName.getText().toString());
		outState.putString("socialSave", btnSocial.getText().toString());
		outState.putString("stateSave", btnState.getText().toString());
		outState.putBoolean("trustInfoSave", ckbTrueInfo.isChecked());
		outState.putInt("idRdb", idRdb);
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
			Intent it = new Intent(this, SocialNetActivity.class);
			it.putExtra("idRdb", idRdb);
			startActivityForResult(it, reqSocial);
		}
		
		if(v.getId() == R.id.btnAct01State){
			Intent it = new Intent(this, StateActivity.class);
			startActivityForResult(it, reqState);
		}
		
		if(v.getId() == R.id.btnAct01Next){
			Intent it = new Intent(this, ResultsActivity.class);
			it.putExtra("name", edtName.getText().toString());
			it.putExtra("state", state);
			it.putExtra("socialNetwork", socialNet);
			it.putExtra("trustInfo", ckbTrueInfo.isChecked());
			startActivity(it);
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == reqSocial && resultCode == RESULT_OK) {
			socialNet = data.getStringExtra("socialNetwork");
			btnSocial.setText(socialNet);
			idRdb = data.getIntExtra("idRdb", -1);
		}
		
		if(requestCode == reqState && resultCode == RESULT_OK){
			state = data.getStringExtra("stateChosen");
			btnState.setText(state);			
		}
		
	}

}
