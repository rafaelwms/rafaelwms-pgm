package br.edu.unibratec.rafaelwms.estudofragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

public class PessoaListFragment extends ListFragment {
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		pessoas.add(new Pessoa("Fulano", "de Tal"));
		pessoas.add(new Pessoa("Beltrano", "Silva"));
		pessoas.add(new Pessoa("Cicrano", "Santos"));
		
		
		ArrayAdapter<Pessoa> adapter = new ArrayAdapter<Pessoa>(getActivity(), 
				android.R.layout.simple_list_item_1,
				pessoas);
		setListAdapter(adapter);
		
		
	}

}


