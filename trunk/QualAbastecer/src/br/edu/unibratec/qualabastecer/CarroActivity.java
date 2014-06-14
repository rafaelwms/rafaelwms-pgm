package br.edu.unibratec.qualabastecer;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class CarroActivity extends ActionBarActivity implements OnItemClickListener {
	
	QualAbastecerDB db;
	SeekBar seekR, seekG, seekB;
	CheckBox ckbGasolina, ckbEtanol, ckbDiesel;
	Veiculo veiculo;
	List<Veiculo> veiculoss;
	EditText ediNomeCarro;
	ImageView imgVeiculo;
	CarroAdapter adapter;
	ListView listaCarros;
	TextView txtCor;
	int r;
	int g; 
	int b;
	int corAtual;
	int tipoVeiculo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_carro);
		
		db = new QualAbastecerDB(this);
		veiculo = new Veiculo();
		veiculoss = new ArrayList<Veiculo>();
		
		r = 0;
		g = 0;
		b = 0;
		corAtual = 0; 
		tipoVeiculo = 0;
		
		
		txtCor = (TextView)findViewById(R.id.txtColor);
		ediNomeCarro = (EditText)findViewById(R.id.edtCarroNome);
		ckbEtanol = (CheckBox)findViewById(R.id.chkbCarroEth);
		ckbGasolina = (CheckBox)findViewById(R.id.chkbCarroGas);
		seekR = (SeekBar)findViewById(R.id.seekBarR);
		seekG = (SeekBar)findViewById(R.id.seekBarG);
		seekB = (SeekBar)findViewById(R.id.seekBarB);
		listaCarros = (ListView)findViewById(R.id.listViewCarros);
		listaCarros.setOnItemClickListener(this);
		
		seekR.setOnSeekBarChangeListener(seekBarChangeListener);
		seekG.setOnSeekBarChangeListener(seekBarChangeListener);
		seekB.setOnSeekBarChangeListener(seekBarChangeListener);
		imgVeiculo = (ImageView)findViewById(R.id.imgCarro);
		
		atualizarLista();

	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.carro, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.salvar_carro) {
			try{
			if(veiculo.getId() <= 0){
				
				if(db.buscarCarroPorNome(ediNomeCarro.getText().toString()) != null){
					throw new Exception(getResources().getString(R.string.exceptionCombustivelVazio));
				}
				
				int combustivel = 0;
				if(ckbEtanol.isChecked() == false && ckbGasolina.isChecked() == false){
					throw new Exception(getResources().getString(R.string.exceptionCombustivelVazio));
				}
				if(ckbEtanol.isChecked() == false && ckbGasolina.isChecked() == true){
					combustivel = 0;
				}
				if(ckbEtanol.isChecked() == true && ckbGasolina.isChecked() == false){
					combustivel = 1;
				}
				if(ckbEtanol.isChecked() == true && ckbGasolina.isChecked() == true){
					combustivel = 2;
				}
				
				
				
				veiculo = new Veiculo(ediNomeCarro.getText().toString(), tipoVeiculo, veiculo.getCor(), combustivel);
				db.insertCarro(veiculo);
				Toast.makeText(this, getResources().getString(R.string.toastSalvarCarro), Toast.LENGTH_SHORT).show();
				atualizarLista();
				
			}else{
				int combustivel = -1;
				
				if(ckbEtanol.isChecked() == false && ckbGasolina.isChecked() == false){
					throw new Exception(getResources().getString(R.string.exceptionCombustivelVazio));
				}
				if(ckbEtanol.isChecked() == false && ckbGasolina.isChecked() == true){
					combustivel = 0;
				}
				if(ckbEtanol.isChecked() == true && ckbGasolina.isChecked() == false){
					combustivel = 1;
				}
				if(ckbEtanol.isChecked() == true && ckbGasolina.isChecked() == true){
					combustivel = 2;
				}
				
				veiculo.setNome(ediNomeCarro.getText().toString());
				veiculo.setCor(corAtual);
				veiculo.setCombustivel(combustivel);
				db.alterarCarro(veiculo);
				Toast.makeText(this, getResources().getString(R.string.toastAtualizarCarro), Toast.LENGTH_SHORT).show();
				atualizarLista();
				
			}
			}catch(Exception ex){
				Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
			}

			return true;
		}
		
		if(item.getItemId() == R.id.deletar_carro){
			if(veiculo.getId() <= 0){
				
				Toast.makeText(this, getResources().getString(R.string.toastSelecionarCarro), Toast.LENGTH_SHORT).show();
				atualizarLista();
				
			}else{
				
				AlertDialog.Builder builder = new AlertDialog.Builder(this);

				builder.setMessage(R.string.dialogMsgDeleteCar).setTitle(
						R.string.dialogTitleDeleteCar);

				builder.setPositiveButton(R.string.dialogBtnOK,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
							
								db.deleteCarro(veiculo);
								Toast.makeText(getApplication(), getResources().getString(R.string.toastDeletarCarro), Toast.LENGTH_SHORT).show();
								atualizarLista();
							}
						});
				
				builder.setNegativeButton(R.string.dialogBtnCanclear,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
							
								atualizarLista();
							}
						});
						

				AlertDialog dialog = builder.create();

				dialog.show();
				
			}
			
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener()
	{

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
	  boolean fromUser) {
	// TODO Auto-generated method stub
		mudarCordoCarro();
		if(seekBar.getId() == R.id.seekBarR)
		txtCor.setText(getResources().getString(R.string.corRedValue)+" "+progress);
		
		if(seekBar.getId() == R.id.seekBarG)
			txtCor.setText(getResources().getString(R.string.corGreenValue)+" "+progress);
		
		if(seekBar.getId() == R.id.seekBarB)
			txtCor.setText(getResources().getString(R.string.corBlueValue)+" "+progress);
		
	}
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		if(seekBar.getId() == R.id.seekBarR)
			txtCor.setText(getResources().getString(R.string.corRed));
		
		if(seekBar.getId() == R.id.seekBarG)
			txtCor.setText(getResources().getString(R.string.corGreen));
		
		if(seekBar.getId() == R.id.seekBarB)
			txtCor.setText(getResources().getString(R.string.corBlue));
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
	// TODO Auto-generated method stub
		txtCor.setText("");
	}
	};
	
	
	private void atualizarLista(){
		
		veiculoss = new ArrayList<Veiculo>();
		veiculoss = db.listarCarros();
		adapter = new CarroAdapter(veiculoss);
		listaCarros.setAdapter(adapter);
		veiculo = new Veiculo();
		ediNomeCarro.setText("");
		seekR.setProgress(0);
		seekG.setProgress(0);
		seekB.setProgress(0);
		ckbGasolina.setChecked(false);
		ckbEtanol.setChecked(false);
		mudarCordoCarro();
	}
	

	private void mudarCordoCarro(){
		
		r = seekR.getProgress();
		g = seekG.getProgress();
		b = seekB.getProgress();
		
		imgVeiculo.setBackgroundColor(Color.rgb(r, g, b));
			
		
		veiculo.setCor(0xff000000 
				+ r * 0x10000 
				+ g * 0x100
				+ b);
		
		corAtual = 0xff000000 
				+ r * 0x10000 
				+ g * 0x100
				+ b;
		
	}

	


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		veiculo = (Veiculo) listaCarros.getItemAtPosition(position);	
		ediNomeCarro.setText(veiculo.getNome());
		imgVeiculo.setBackgroundColor(veiculo.getCor());	
		corAtual = veiculo.getCor();
		
		switch (veiculo.getCombustivel()) {
		case 0:
			
			ckbGasolina.setChecked(true);
			ckbEtanol.setChecked(false);
			
			break;
			
		case 1:
			
			ckbGasolina.setChecked(false);
			ckbEtanol.setChecked(true);
			
			break;
			
		case 2:
			
			ckbGasolina.setChecked(true);
			ckbEtanol.setChecked(true);
			
			break;

		default:
			break;
		}		
		
	}
		


}

