package br.com.rafaelwms.qualabastecer;

import java.util.ArrayList;
import java.util.Date;
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
import android.widget.AdapterView.OnItemClickListener;
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

public class AbastecimentoActivity extends ActionBarActivity implements
		OnItemSelectedListener, OnCheckedChangeListener, OnItemClickListener {

	QualAbastecerDB db;

	Abastecimento abs;
	List<Abastecimento> abss;
	ListView listAbss;
	AbastecimentoAdapter adapter;

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

	double litrosAbastecido;
	double valorAbastecimento;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_abastecimento);

		db = new QualAbastecerDB(this);

		valorAbastecimento = 0;
		litrosAbastecido = 0;

		spinVeiculos = (Spinner) findViewById(R.id.spinnerVeiculo);
		spinPostos = (Spinner) findViewById(R.id.spinnerPosto);

		listAbss = (ListView) findViewById(R.id.listViewAbastecimentos);
		listAbss.setOnItemClickListener(this);

		rbDiesel = (RadioButton) findViewById(R.id.radioDiesel);
		rbEtanol = (RadioButton) findViewById(R.id.radioEtanol);
		rbGasolina = (RadioButton) findViewById(R.id.radioGasolina);
		rbLitros = (RadioButton) findViewById(R.id.radioLitros);
		rbValoPago = (RadioButton) findViewById(R.id.radioValorPago);

		edtValorPago = (EditText) findViewById(R.id.editValorPago);
		edtLitros = (EditText) findViewById(R.id.editQtdLitros);
		edtKm = (EditText) findViewById(R.id.editKm);

		spinVeiculos.setOnItemSelectedListener(this);
		spinPostos.setOnItemSelectedListener(this);

		rbDiesel.setOnCheckedChangeListener(this);
		rbEtanol.setOnCheckedChangeListener(this);
		rbGasolina.setOnCheckedChangeListener(this);
		rbLitros.setOnCheckedChangeListener(this);
		rbValoPago.setOnCheckedChangeListener(this);

		carregarSpinners();
		atualizarlista();

	}

	private void carregarSpinners() {

		veiculos = new ArrayList<Veiculo>();
		veiculos.add(new Veiculo(-1,
				getResources().getString(R.string.veiculo), -1, -1, -1));
		for (Veiculo ve : db.listarCarros()) {
			veiculos.add(ve);
		}

		ArrayAdapter<Veiculo> adapterVeiculos = new ArrayAdapter<Veiculo>(this,
				android.R.layout.simple_spinner_item, veiculos);
		adapterVeiculos
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinVeiculos.setAdapter(adapterVeiculos);

		postos = new ArrayList<Posto>();
		postos.add(new Posto(-1, getResources().getString(R.string.Posto), 0,
				0, 0, 0));
		for (Posto po : db.listarPostos()) {
			postos.add(po);
		}

		ArrayAdapter<Posto> adapterPostos = new ArrayAdapter<Posto>(this,
				android.R.layout.simple_spinner_item, postos);
		adapterPostos
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinPostos.setAdapter(adapterPostos);
		
		edtValorPago.setText("");
		edtLitros.setText("");
		edtKm.setText("");
		
		rbLitros.setChecked(false);
		rbValoPago.setChecked(false);
		rbDiesel.setChecked(false);
		rbEtanol.setChecked(false);
		rbGasolina.setChecked(false);
		rbDiesel.setEnabled(false);
		rbEtanol.setEnabled(false);
		rbGasolina.setEnabled(false);
		veiculo = new Veiculo();
		posto = new Posto();
		abs = new Abastecimento();
		

	}

	private boolean calcularCampos() {

		
		if (rbValoPago.isChecked()
				&& !edtValorPago.getText().toString().trim().equals("")
				&& posto.getId() > 0) {

			double valorPago = Double.parseDouble(edtValorPago.getText()
					.toString());
			String litros = "";
			int comb = -10;

			if (rbGasolina.isChecked())
				comb = 0;
			if (rbEtanol.isChecked())
				comb = 1;
			if (rbDiesel.isChecked())
				comb = 3;

			switch (comb) {
			case 0:
				litros = String.format("%.2f",
						(valorPago / posto.getLitroGasolina()));

				break;

			case 1:
				litros = String.format("%.2f",
						(valorPago / posto.getLitroEtanol()));

				break;

			case 3:
				litros = String.format("%.2f",
						(valorPago / posto.getLitroDiesel()));
				
				break;

			default:
				break;
			}
			litros = litros.replace(",", ".");
			edtLitros.setEnabled(true);
			edtLitros.setText(litros);
			edtLitros.setEnabled(false);
			litrosAbastecido = Double.parseDouble(litros);
			valorAbastecimento = valorPago;
			return true;
		}

		if (rbLitros.isChecked()
				&& !edtLitros.getText().toString().trim().equals("")
				&& posto.getId() > 0) {

			double litrosUtilizados = Double.parseDouble(edtLitros.getText()
					.toString());
			String valorPago = "";
			int comb = -10;

			if (rbGasolina.isChecked())
				comb = 0;
			if (rbEtanol.isChecked())
				comb = 1;
			if (rbDiesel.isChecked())
				comb = 3;

			switch (comb) {
			case 0:
				valorPago = String.format("%.2f",
						(litrosUtilizados * posto.getLitroGasolina()));

				break;

			case 1:
				valorPago = String.format("%.2f",
						(litrosUtilizados * posto.getLitroEtanol()));

				break;

			case 3:
				valorPago = String.format("%.2f",
						(litrosUtilizados * posto.getLitroDiesel()));

				break;

			default:
				break;
			}
			valorPago = valorPago.replace(",", ".");
			edtValorPago.setEnabled(true);
			edtValorPago.setText(valorPago);
			edtValorPago.setEnabled(false);		
			valorAbastecimento = Double.parseDouble(valorPago);
			litrosAbastecido = litrosUtilizados;
			return true;
		}
		return false;
	}
	
	private void atualizarlista(){
		
		abss = new ArrayList<Abastecimento>();
		abss = db.listarAbastecimentos();
		adapter = new AbastecimentoAdapter(abss);
		listAbss.setAdapter(adapter);	
		carregarSpinners();
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
		if (id == R.id.salvar_abastecimento) {
			
			try{
				if(veiculo.getId() == -1){
					throw new Exception(getResources().getString(R.string.exceptionVeiculoSelecionado));
				}
				if(posto.getId() == -1){
					throw new Exception(getResources().getString(R.string.exceptionPostoSelecionado));
				}
				if(!calcularCampos()){
					throw new Exception(getResources().getString(R.string.exceptionCamposAPreencher));
				}
				if(edtKm.getText().toString().trim().equals("")){
					throw new Exception(getResources().getString(R.string.exceptionCampoKm));
				}
				
				Date data = new Date();
				
				if(veiculo.getCombustivel() == 2){
					
					if(!rbGasolina.isChecked() && !rbEtanol.isChecked()){
						throw new Exception(getResources().getString(R.string.exceptionCampoKm));
					}
					
					
					int comb = 0;
					
					if(rbGasolina.isChecked()){
						comb = 0;
					}
					
					if (rbEtanol.isChecked()){
						comb = 1;
					}
					calcularCampos();
					Abastecimento  abst = new Abastecimento(Util.formatarDataCompletaUS(data), veiculo, posto, comb, valorAbastecimento, litrosAbastecido, Double.parseDouble(edtKm.getText().toString()));
					db.insertAbastecimento(abst);
					Toast.makeText(getApplication(), abst.getData(), Toast.LENGTH_SHORT).show();
					atualizarlista();
				}else{
				
				Abastecimento  abst = new Abastecimento(Util.formatarDataCompletaUS(data), veiculo, posto, veiculo.getCombustivel(), valorAbastecimento, litrosAbastecido, Double.parseDouble(edtKm.getText().toString()));
				calcularCampos();
				db.insertAbastecimento(abst);
				Toast.makeText(getApplication(), abst.getData(), Toast.LENGTH_SHORT).show();
				atualizarlista();
				}
				
				
			}catch(Exception ex){
				Toast.makeText(getApplication(), ex.getMessage(), Toast.LENGTH_SHORT).show();
			}
					
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

		if (parent == spinVeiculos) {
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
				rbGasolina.setClickable(true);
				rbEtanol.setChecked(false);
				rbEtanol.setClickable(true);
				rbDiesel.setChecked(false);
				rbGasolina.setEnabled(true);
				rbEtanol.setEnabled(true);
				rbDiesel.setEnabled(false);
				break;

			default:
				break;
			}

		}
		if (parent == spinPostos)
			posto = (Posto) parent.getSelectedItem();

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub

		if (buttonView.getId() == R.id.radioDiesel && rbDiesel.isChecked()) {
			rbEtanol.setChecked(false);
			rbGasolina.setChecked(false);
		} else if (buttonView.getId() == R.id.radioEtanol
				&& rbEtanol.isChecked()) {
			rbDiesel.setChecked(false);
			rbGasolina.setChecked(false);
		} else if (buttonView.getId() == R.id.radioGasolina
				&& rbGasolina.isChecked()) {
			rbDiesel.setChecked(false);
			rbEtanol.setChecked(false);
		} else if (buttonView.getId() == R.id.radioLitros
				&& rbLitros.isChecked()) {
			rbValoPago.setChecked(false);
			edtValorPago.setText("");
			edtValorPago.setEnabled(false);
			edtLitros.setEnabled(true);
		} else if (buttonView.getId() == R.id.radioValorPago
				&& rbValoPago.isChecked()) {
			rbLitros.setChecked(false);
			edtLitros.setText("");
			edtLitros.setEnabled(false);
			edtValorPago.setEnabled(true);
		}

	}
	
	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		abs = (Abastecimento) listAbss.getItemAtPosition(position);
		
		for(int i = 0; i <= veiculos.size(); i++){
		 if(spinVeiculos.getItemIdAtPosition(i) == abs.getCarro().getId()){
			 spinVeiculos.setSelection(i);
		 }
		}
		
		for(int i = 0; i <= postos.size(); i++){
			 if(spinPostos.getItemIdAtPosition(i) == abs.getPosto().getId()){
				 spinPostos.setSelection(i);
			 }
			}
		edtValorPago.setEnabled(true);
		edtValorPago.setText(String.valueOf(abs.getValorPago()));
		edtLitros.setEnabled(true);
		edtLitros.setText(String.valueOf(abs.getLitros()));
		edtKm.setText(String.valueOf(abs.getKilometragem()));
		edtValorPago.setEnabled(false);
		edtLitros.setEnabled(false);
		rbValoPago.setChecked(true);
		
		if(abs.getCarro().getCombustivel() == 2){
		
			rbGasolina.setEnabled(true);
			rbEtanol.setEnabled(true);
			
		if(abs.getCombustivel() == 0){
		
			rbGasolina.setChecked(true);
		}else if(abs.getCombustivel() == 1){
			rbEtanol.setChecked(true);
		}
		}
		
	}

}
