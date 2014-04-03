package br.edu.unibratec.rafaelwms.estudofragments;

import br.edu.unibratec.rafaelwms.estudofragments.PessoaListFragment.AoClicarNaPessoaListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

public class PessoaActivity extends FragmentActivity implements AoClicarNaPessoaListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa);
    }

	@Override
	public void clicouNaPessoa(Pessoa pessoa) {
		if(isTablet()){
			DetalhePessoaFragment fragment = DetalhePessoaFragment.novaInstancia(pessoa);
			
			FragmentManager fm = getSupportFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			ft.replace(R.id.detalhe, fragment, "tagDetalhe");
			ft.commit();		
			
		}else{
			Intent it = new Intent(this, DetalhePessoaActivity.class);
			it.putExtra("pessoa", pessoa);
			startActivity(it);
			
		}
	}


   private boolean isTablet(){
	   return findViewById(R.id.detalhe) != null;
	   }
}
