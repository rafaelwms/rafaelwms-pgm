package br.edu.unibratec.rafaelwms.lesson03;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DataActivity extends Activity implements OnClickListener {
	
	public static final String NEW_CAR = "newCar";
	
	TextView lblTitle;
	TextView lblModel;
	TextView lblYear;
	TextView lblInd;
	EditText edtModel;
	EditText edtYear;
	Spinner spInd;
	CheckBox ckEth;
	CheckBox ckGas;
	Button btSave;
	
	String model;
	String industry;
	String year;
	String fuel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_data);
		
		lblTitle = (TextView)findViewById(R.id.dataAct_title);
		lblModel = (TextView)findViewById(R.id.dataAct_lbl_model);
		lblYear = (TextView)findViewById(R.id.dataAct_lbl_year);
		lblInd = (TextView)findViewById(R.id.dataAct_lbl_Ind);
		edtModel = (EditText)findViewById(R.id.dataAct_edt_model);
		edtYear = (EditText)findViewById(R.id.dataAct_edt_year);
		spInd = (Spinner)findViewById(R.id.dataAct_sp_Ind);
		ckEth = (CheckBox)findViewById(R.id.dataAct_ck_Eth);
		ckGas = (CheckBox)findViewById(R.id.dataAct_ck_gas);
		btSave = (Button)findViewById(R.id.dataAct_bt_confirm);
		btSave.setOnClickListener(this);
		
		String[] manufacturerList = new String[]{ "Chevrolet", "Fiat" ,"Ford", "Volkswagen"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, manufacturerList);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spInd.setAdapter(adapter);
		if(savedInstanceState != null){
			
			
			
		}
		
		
		
		
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		
		model = edtModel.getText().toString();
		year = edtYear.getText().toString();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.data, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.dataAct_bt_confirm){
			
			if(edtModel.getText().toString().trim().equals("")){
				edtModel.setError("");
			}
			else if(edtYear.getText().toString().trim().equals("")){
				edtModel.setError("");
			}
			else if(ckEth.isChecked() == false && ckGas.isChecked()== false){
				Toast t = new Toast(this);
				t.setText("");
				t.show();
			}else{
			
				fuel = "";
				if(ckEth.isChecked() && ckGas.isChecked()){
					fuel = "FLEX";
				} else if(ckEth.isChecked() && ckEth.isChecked() == false){
					fuel = "GASOLINE";
				} else if(ckEth.isChecked() == false && ckEth.isChecked()){
					fuel = "ETHANOL";
				}

			Car newCar = new Car(spInd.getSelectedItem().toString(), edtModel.getText().toString(), fuel, edtYear.getText().toString());
			Intent it = new Intent();
			it.putExtra(NEW_CAR, newCar);
			setResult(DataActivity.RESULT_OK, it);
			finish();
			}
		}
		
	}
	

	
	

}
