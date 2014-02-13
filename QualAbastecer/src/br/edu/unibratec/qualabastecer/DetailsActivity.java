package br.edu.unibratec.qualabastecer;

import br.edu.unibratec.qualabastecer.R.id;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DetailsActivity extends Activity implements OnClickListener {
	
	TextView gasCost;
	TextView ethCost;
	TextView result;
	Button back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		gasCost = (TextView)findViewById(id.lblAct2GasPrice);
		ethCost = (TextView)findViewById(id.lblAct2EthPrice);
		result = (TextView)findViewById(id.lblAct2Result);
		
		back = (Button)findViewById(id.btnAct2Back);

		Intent it = getIntent();
		gasCost.setText(it.getStringExtra("lblAct2GasPrice"));
		ethCost.setText(it.getStringExtra("lblAct2EthPrice"));
		
		if(it.getBooleanExtra("text", false) == true){
			result.setText(R.string.lblAct2ResultGas);
		}else{
			result.setText(R.string.lblAct2ResultValueEth);
		}
		
		

		back.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0.getId() == R.id.btnAct2Back){
			
			Intent inn = new Intent(this, MainActivity.class);
			startActivity(inn);
			
		}
	}

}
