package br.edu.unibratec.rafaelwms.estudofragments;

import br.edu.unibratec.rafaelwms.estudofragments.PessoaListFragment.AoClicarNaPessoaListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
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
			
		}else{
			Intent it = new Intent(this, DetalhePessoaActivity.class);
			it.putExtra("pessoa", pessoa);
			startActivity(it);
			
		}
	}


   private boolean isTablet(){return false;}
}
