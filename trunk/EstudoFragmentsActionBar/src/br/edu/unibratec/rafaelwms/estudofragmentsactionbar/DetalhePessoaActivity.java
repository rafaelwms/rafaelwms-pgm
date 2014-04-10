package br.edu.unibratec.rafaelwms.estudofragmentsactionbar;

import br.edu.unibratec.rafaelwms.estudofragments.R;
import br.edu.unibratec.rafaelwms.estudofragmentsactionbar.DetalhePessoaFragment.AoSalvarPessoaListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

public class DetalhePessoaActivity extends ActionBarActivity implements AoSalvarPessoaListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalhe_pessoa);
		Pessoa pessoa = (Pessoa)getIntent().getSerializableExtra("pessoa");
		
		DetalhePessoaFragment fragment = DetalhePessoaFragment.novaInstancia(pessoa);
		
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.detalhe, fragment, "tagDetalhe");
		ft.commit();
		
	}

	@Override
	public void salvouAPessoa(Pessoa p) {
		Intent it = new Intent();
		it.putExtra("pessoa", p);
		setResult(RESULT_OK, it);
		finish();
	}



}
