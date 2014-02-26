package br.edu.unibratec.rafaelwms.exercicio02;

import br.edu.unibratec.rafaelwms.exercicio02.R.id;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class StateActivity extends Activity implements OnItemClickListener {
	
	
	ListView lstvStates;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_state);
		
		lstvStates = (ListView)findViewById(id.lvAct03);
		lstvStates.setOnItemClickListener(this);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.state, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> listView, View v, int index, long id) {
		// TODO Auto-generated method stub
		String selectedState = listView.getItemAtPosition(index).toString();
		Intent it = getIntent();
		it.putExtra("stateChosen", selectedState);
		setResult(RESULT_OK, it);
		finish();
		
	}

}
