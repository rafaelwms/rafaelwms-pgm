package br.edu.unibratec.rafaelwms.lesson03;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener {
	
	public static final int REQ_NEW_CAR = 1;	
	
	ListView lista;
	ArrayList<Car> cars;
	Button newCar;
	
	String model;
	String industry;
	String year;
	String fuel;
	CarAdapter adapter;	
	
	
	Car car;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lista = (ListView)findViewById(R.id.listView1);
		newCar = (Button)findViewById(R.id.act01_bt_new);
		newCar.setOnClickListener(this);

		cars = new ArrayList<Car>(); 
		adapter = new CarAdapter(cars);
		lista.setAdapter(adapter);
		
		if (savedInstanceState != null){
			
			cars = ((ArrayList<Car>) savedInstanceState.getSerializable("lista"));
			adapter = new CarAdapter(cars);
			lista.setAdapter(adapter);
		}
		
		
		
	}


	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		
		outState.putSerializable("lista", cars);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.act01_bt_new){			
			Intent it = new Intent(this, DataActivity.class);			
			startActivityForResult(it, REQ_NEW_CAR);	
		}
		
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode == REQ_NEW_CAR && resultCode == RESULT_OK){
			
			cars.add((Car)data.getSerializableExtra(DataActivity.NEW_CAR));
			adapter = new CarAdapter(cars);
			lista.setAdapter(adapter);
		}

		
	}
	

}
