package br.edu.unibratec.rafaelwms.estudofragmentsactionbar;

import br.edu.unibratec.rafaelwms.estudofragments.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetalhePessoaFragment extends Fragment {
	
	TextView txtNome;
	TextView txtSobreNome;
	
	
	public static DetalhePessoaFragment novaInstancia(Pessoa p){
		Bundle parametros = new Bundle();
		parametros.putSerializable("pessoa", p);
		
		DetalhePessoaFragment fragment = new DetalhePessoaFragment();
		fragment.setArguments(parametros);
		return fragment;
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		
		Pessoa pessoa = (Pessoa)getArguments().getSerializable("pessoa");
		
		View layout = inflater.inflate(R.layout.fragment_detalhe_pessoa, null);
		
		txtNome = (TextView)layout.findViewById(R.id.textNome);
		
		txtSobreNome = (TextView)layout.findViewById(R.id.textSobrenome);
		
		txtNome.setText(pessoa.nome);
		txtSobreNome.setText(pessoa.sobrenome);
		return layout;
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		getActivity().getMenuInflater().inflate(R.menu.detalhe_pessoa, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId() == R.id.acao_salvar){
			Pessoa p = new Pessoa(txtNome.getText().toString(), 
								  txtSobreNome.getText().toString());
			
			
			Activity activity = getActivity();
			if(activity instanceof AoSalvarPessoaListener){
				AoSalvarPessoaListener listener =
						(AoSalvarPessoaListener)activity;
				listener.salvouAPessoa(p);
			}
		}
		return super.onOptionsItemSelected(item);
	}
	
	public interface AoSalvarPessoaListener{
		
		void salvouAPessoa(Pessoa p);
	}

	public void adicionarPessoa(Pessoa p) {
		
		
	}
	
}
