package br.edu.unibratec.rafaelwms.estudofragmentsactionbar;

import br.edu.unibratec.rafaelwms.estudofragments.R;
import br.edu.unibratec.rafaelwms.estudofragmentsactionbar.DetalhePessoaFragment.AoSalvarPessoaListener;
import br.edu.unibratec.rafaelwms.estudofragmentsactionbar.PessoaListFragment.AoClicarNaPessoaListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.widget.ActionBarContainer;
import android.view.Menu;
import android.view.MenuItem;

public class PessoaActivity extends ActionBarActivity implements AoClicarNaPessoaListener, AoSalvarPessoaListener{

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
			startActivityForResult(it, 0);
			
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == 0 && resultCode == RESULT_OK)	{
			Pessoa p = (Pessoa)data.getSerializableExtra("pessoa");
			salvarPessoa(p);

		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.pessoa, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.acao_novo){
			clicouNaPessoa(new Pessoa("", ""));
		}
		return super.onOptionsItemSelected(item);
	}

   private boolean isTablet(){
	   return findViewById(R.id.detalhe) != null;
	   }

@Override
public void salvouAPessoa(Pessoa p) {
	salvarPessoa(p);
	
}

private void salvarPessoa(Pessoa p){
	FragmentManager fm = getSupportFragmentManager();
	PessoaListFragment lista = 
			(PessoaListFragment)fm.findFragmentById(R.id.fragment1);
	lista.adicionarPessoa(p);
	
}

}
