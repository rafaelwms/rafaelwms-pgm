package br.edu.unibratec.rafaelwms.estudofragmentsactionbar;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PessoaListFragment extends ListFragment {
	
	List<Pessoa> pessoas;
	ArrayAdapter<Pessoa> adapter;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		pessoas = new ArrayList<Pessoa>();
		pessoas.add(new Pessoa("Fulano", "de Tal"));
		pessoas.add(new Pessoa("Beltrano", "Silva"));
		pessoas.add(new Pessoa("Cicrano", "Santos"));
		
		
		adapter = new ArrayAdapter<Pessoa>(getActivity(), 
				android.R.layout.simple_list_item_1,
				pessoas);
		setListAdapter(adapter);
		
		
	}

	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Activity activity = getActivity();
		if (activity instanceof AoClicarNaPessoaListener){
			Pessoa pessoa = (Pessoa)l.getItemAtPosition(position);
			
			//assim:
			AoClicarNaPessoaListener listener = (AoClicarNaPessoaListener)activity;
			listener.clicouNaPessoa(pessoa);
			
			// ou assim: ((AoClicarNaPessoaListener)activity).clicouNaPessoa(pessoa);
		}
	}
	
	
	public interface AoClicarNaPessoaListener{
		
		void clicouNaPessoa(Pessoa pessoa);
		
	}


	public void adicionarPessoa(Pessoa p) {
		pessoas.add(p);
		adapter.notifyDataSetChanged();	
	}
	
}


