package br.edu.unibratec.rafaelwms.estudofragments;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

public class DetalhePessoaActivity extends FragmentActivity {

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



}
