package br.edu.unibratec.qualabastecer;

import java.nio.channels.ByteChannel;

import br.edu.unibratec.qualabastecer.R.id;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;;

public class MainActivity extends Activity implements OnClickListener {
	
	
	TextView lblResult;
	TextView txtResult;
	EditText fieldGas;
	EditText fieldEth;
	Button btnAct;
	Button btnAct2;
	boolean gas = false;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        lblResult =(TextView)findViewById(id.textViewLblResult);
        txtResult = (TextView)findViewById(id.textViewResult);
        fieldGas = (EditText)findViewById(id.editTextGas);
        fieldEth = (EditText)findViewById(id.editTextEthanol);
        btnAct = (Button)findViewById(id.buttonAct);
        btnAct2 = (Button)findViewById(id.buttonAct2);
        
        fieldGas.requestFocus();
        btnAct2.setEnabled(false);
        
        btnAct.setOnClickListener(this);
        btnAct2.setOnClickListener(this);
        
        String res = "";
        boolean btn2 = false;
        
        if(savedInstanceState != null){
        res = savedInstanceState.getString("resultado");
        btn2 = savedInstanceState.getBoolean("btn2");
        }
        
        if(btn2 == true){
        	btnAct2.setEnabled(true);
        }
        
        txtResult.setText(res);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
@Override
protected void onSaveInstanceState(Bundle outState) {
	super.onSaveInstanceState(outState);
	outState.putString("resultado", txtResult.getText().toString());
	
	if(btnAct2.isEnabled()== true){
		outState.putBoolean("btn2", true);		
	}else{
		outState.putBoolean("btn2", false);
	}
	
}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.buttonAct){
			
			
			try {
				double gasCost = Double.parseDouble(fieldGas.getText().toString());
				double ethCost = Double.parseDouble(fieldEth.getText().toString());
				
				if(ethCost < (gasCost * 0.7)){
					txtResult.setText(R.string.ethResult);
				}else{
					txtResult.setText(R.string.gasResult);
					gas = true;
				}
				btnAct2.setEnabled(true);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(v.getId() == R.id.buttonAct2){
			Intent inn = new Intent(this, DetailsActivity.class);
			inn.putExtra("lblAct2GasPrice", fieldGas.getText().toString());
			inn.putExtra("lblAct2EthPrice", fieldEth.getText().toString());
			inn.putExtra("text", gas);
			startActivity(inn);
		}
		
		
	}
    
}
