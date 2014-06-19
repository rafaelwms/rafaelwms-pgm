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
		veiculos = db.listarCarros();

		ArrayAdapter<Veiculo> adapterVeiculos = new ArrayAdapter<Veiculo>(this, android.R.layout.simple_spinner_item, veiculos);
		adapterVeiculos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinVeiculos.setAdapter(adapterVeiculos);
		
		
		postos = new ArrayList<Posto>();
		postos = db.listarPostos();
		

		ArrayAdapter<Posto> adapterPostos = new ArrayAdapter<Posto>(this, android.R.layout.simple_spinner_item, postos);
		adapterPostos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinPostos.setAdapter(adapterPostos);
		
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
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
			
		/*if(parent.){
			
			veiculo = (Veiculo) parent.getSelectedItem();
			edtKm.setText("id = "+veiculo.getId()+ " nome: "+veiculo.getNome());
		}*/
		
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
		}else if(buttonView.getId() == R.id.radioValorPago && rbValoPago.isChecked()){
			rbLitros.setChecked(false);
		}
		
		
	}


}
