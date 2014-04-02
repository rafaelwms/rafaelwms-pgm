package br.edu.unibratec.rafaelwms.estudofragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetalhePessoaFragment extends Fragment {
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
		Pessoa pessoa = (Pessoa)getArguments().getSerializable("pessoa");
		
		View layout = inflater.inflate(R.layout.fragment_detalhe_pessoa, null);
		
		TextView txtNome = (TextView)layout.findViewById(R.id.textNome);
		
		TextView txtSobreNome = (TextView)layout.findViewById(R.id.textSobrenome);
		
		txtNome.setText(pessoa.nome);
		txtSobreNome.setText(pessoa.sobrenome);
		return layout;
	}

}
