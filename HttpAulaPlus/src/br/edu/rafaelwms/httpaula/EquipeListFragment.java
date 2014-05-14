package br.edu.rafaelwms.httpaula;

import java.net.HttpURLConnection;
import java.util.List;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class EquipeListFragment extends ListFragment {
	
	EquipeAsyncTask mTask;
	List<Equipe> mEquipes;
	ArrayAdapter<Equipe> mAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
		
		ConnectivityManager cm = (ConnectivityManager)
		getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info != null && info.isConnected()){
			if (mTask == null){
				mTask =	new EquipeAsyncTask();
				mTask.execute();
			}
		}else{
			Toast.makeText(getActivity(), "Sem conexão.", Toast.LENGTH_SHORT).show();
		}
		
		super.onActivityCreated(savedInstanceState);
	}

	
	class EquipeAsyncTask extends AsyncTask<Void, Void, List<Equipe>>{


		@Override
		protected List<Equipe> doInBackground(Void... params) {
			
			try{
			//Thread.sleep(3000); Só a nível de testes.
				HttpURLConnection conexao = EquipeHttp.abrirConexao();
				
				int resposta = conexao.getResponseCode();
				
				if(resposta == HttpURLConnection.HTTP_OK){
					List<Equipe> equipes = EquipeHttp.carregarEquipes(conexao.getInputStream());
					return equipes;
				}
				
			}catch(Exception x){
				x.printStackTrace();
			}			
			return null;
		}
		
		
		
		@Override
		protected void onPostExecute(List<Equipe> result) {
			super.onPostExecute(result);
			if(result != null){
				mAdapter = new ArrayAdapter<Equipe>(
						getActivity(), android.R.layout.simple_list_item_1, result);
				mAdapter = new EquipeAdapter(getActivity(), mEquipes);
				setListAdapter(mAdapter);
			}
		}
		
	}

	
}
