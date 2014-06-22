package br.com.rafaelwms.qualabastecer;

import java.util.ArrayList;
import java.util.List;

import br.edu.unibratec.qualabastecer.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.os.Build;

public class AbastecimentoActivity extends ActionBarActivity implements OnItemSelectedListener, OnCheckedChangeListener {
	
	
	QualAbastecerDB db;
	
	Abastecimento abs;
	List<Abastecimento> abss;
	ListView listAbss;
	
	Veiculo veiculo;
	List<Veiculo> veiculos;
	Spinner spinVeiculos;
	EditText edtKm;
	
	Posto posto;
	List<Posto> postos;
	Spinner spinPostos;
	
	RadioGroup rGroupComb;
	RadioButton rbGasolina;
	RadioButton rbEtanol;
	RadioButton rbDiesel;
	
	RadioGroup rGroupPedido;
	RadioButton rbValoPago;
	RadioButton rbLitros;
	EditText edtValorPago;
	EditText edtLitros;
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_abastecimento);
		
		db = new QualAbastecerDB(this);
		
		spinVeiculos = (Spinner)findViewById(R.id.spinnerVeiculo);
		spinPostos = (Spinner)findViewById(R.id.spinnerPosto);
		
		listAbss = (ListView)findViewById(R.id.listViewAbastecimentos);
		
		rbDiesel = (RadioButton)findViewById(R.id.radioDiesel);
		rbEtanol = (RadioButton)findViewById(R.id.radioEtanol);
		rbGasolina  = (RadioButton)findViewById(R.id.radioGasolina);
		rbLitros = (RadioButton)findViewById(R.id.radioLitros);
		rbValoPago = (RadioButton)findViewById(R.id.radioValorPago);
		
		edtValorPago = (EditText)findViewById(R.id.editValorPago);
		edtLitros = (EditText)findViewById(R.id.editQtdLitros);
		edtKm = (EditText)findViewById(R.id.editKm);
		
		spinVeiculos.setOnItemSelectedListener(this);
		spinPostos.setOnItemSelectedListener(this);
		
		rbDiesel.setOnCheckedChangeListener(this);
		rbEtanol.setOnCheckedChangeListener(this);
		rbGasolina.setOnCheckedChangeListener(this);
		rbLitros.setOnCheckedChangeListener(this);
		rbValoPago.setOnCheckedChangeListener(this);
	
		
		carregarSpinners();
		
		
	
	}
	
	private void carregarSpinners(){
		

		veiculos = new ArrayList<Veiculo>();
		veiculos.add(new Veiculo(-1, getResources().getString(R.string.veiculo), -1, -1, 1));
		for(Veiculo ve : db.listarCarros()){
			veiculos.add(ve);
		}

		ArrayAdapter<Veiculo> adapterVeiculos = new ArrayAdapter<Veiculo>(this, android.R.layout.simple_spinner_item, veiculos);
		adapterVeiculos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinVeiculos.setAdapter(adapterVeiculos);
		
		
		postos = new ArrayList<Posto>();
		postos.add(new Posto(-1, getResources().getString(R.string.Posto), 0,0,0,0));
		for(Posto po : db.listarPostos()){
			postos.add(po);
		}

		ArrayAdapter<Posto> adapterPostos = new ArrayAdapter<Posto>(this, android.R.layout.simple_spinner_item, postos);
		adapterPostos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinPostos.setAdapter(adapterPostos);

		
		
	}
	
	private void calcularCampos(){
		if(rbValoPago.isChecked() && !edtValorPago.getText().toString().trim().equals("")){
			
			double valorPago = Double.parseDouble(edtValorPago.getText().toString());
			
			
			
			if(rbGasolina.isChecked() && posto.getId() > 0){				
				String litros = String.format("%.2f",valorPago / posto.getLitroGasolina());
				edtLitros.setEnabled(true);
				edtLitros.setText(litros);
				edtLitros.setEnabled(false);
			} else if(rbEtanol.isChecked() && posto.getId() > 0){				
				String litros = String.valueOf(valorPago / posto.getLitroEtanol());			
				edtLitros.setText(litros);
			}else if(rbDiesel.isChecked() && posto.getId() > 0){				
				String litros = String.valueOf(valorPago / posto.getLitroDiesel());			
				edtLitros.setText(litros);
			}
			
			
			
		}
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.abastecimento, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			calcularCampos();
			carregarSpinners();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
		if(parent == spinVeiculos){
		veiculo = (Veiculo) parent.getSelectedItem();
		switch (veiculo.getCombustivel()) {
		case 0:
			rbGasolina.setChecked(true);
			rbGasolina.setEnabled(true);
			rbGasolina.setClickable(false);
			rbEtanol.setEnabled(false);
			rbDiesel.setEnabled(false);		
			break;
		case 1:
			rbEtanol.setChecked(true);
			rbEtanol.setClickable(false);
			rbGasolina.setEnabled(false);
			rbEtanol.setEnabled(true);
			rbDiesel.setEnabled(false);		
			break;
		case 3:
			rbDiesel.setChecked(true);
			rbDiesel.setClickable(false);
			rbGasolina.setEnabled(false);
			rbEtanol.setEnabled(false);
			rbDiesel.setEnabled(true);		
			break;
		case 2:
			rbGasolina.setChecked(false);
			rbEtanol.setChecked(false);
			rbDiesel.setChecked(false);
			rbGasolina.setEnabled(true);
			rbEtanol.setEnabled(true);
			rbDiesel.setEnabled(false);		
			break;

		default:
			break;
		}
		
		}
		if(parent == spinPostos)
			posto = (Posto) parent.getSelectedItem();
			
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		
		if(buttonView.getId() == R.id.radioDiesel && rbDiesel.isChecked()){
			rbEtanol.setChecked(false);
			rbGasolina.setChecked(false);
		} else if(buttonView.getId() == R.id.radioEtanol && rbEtanol.isChecked()){
			rbDiesel.setChecked(false);
			rbGasolina.setChecked(false);
		}else if(buttonView.getId() == R.id.radioGasolina && rbGasolina.isChecked()){
			rbDiesel.setChecked(false);
			rbEtanol.setChecked(false);
		}else if(buttonView.getId() == R.id.radioLitros && rbLitros.isChecked()){
			rbValoPago.setChecked(false);
			edtValorPago.setText("");
			edtValorPago.setEnabled(false);			
			edtLitros.setEnabled(true);
		}else if(buttonView.getId() == R.id.radioValorPago && rbValoPago.isChecked()){
			rbLitros.setChecked(false);
			edtLitros.setText("");
			edtLitros.setEnabled(false);
			edtValorPago.setEnabled(true);
		}
		
		
	}


}
