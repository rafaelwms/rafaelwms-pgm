package br.edu.rafaelwms.httpaula;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mListView = (ListView) findViewById(R.id.lista);
		
		ConnectivityManager cm = (ConnectivityManager)
				getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info != null && info.isConnected()){
			new EquipeAsyncTask().execute();
		}else{
			Toast.makeText(this, "Sem conexão.", Toast.LENGTH_SHORT).show();
		}
				

	}

	class Equipe {

		String nome;
		String escudo;
		String site;

		public Equipe(String nome, String escudo, String site) {
			super();
			this.nome = nome;
			this.escudo = escudo;
			this.site = site;
		}

		@Override
		public String toString() {
			return nome;
		}

	}
	
	class EquipeAsyncTask extends AsyncTask<Void, Void, List<Equipe>>{


		@Override
		protected List<Equipe> doInBackground(Void... params) {
			
			try{
				URL url = new URL("https://dl.dropboxusercontent.com/u/6802536/escudos/times.json");
				HttpURLConnection conexao = (HttpURLConnection)url.openConnection();
				conexao.setRequestMethod("GET");
				conexao.setDoInput(true);
				conexao.connect();
				
				int resposta = conexao.getResponseCode();
				
				if(resposta == HttpURLConnection.HTTP_OK){
					String jsonString = bytesToString(conexao.getInputStream());
					
					List<Equipe> equipes = new ArrayList<Equipe>();
					JSONObject jsonEquipes = new JSONObject(jsonString);
					JSONArray jsonLista = jsonEquipes.getJSONArray("times");
					for (int i = 0; i < jsonLista.length(); i++) {
						JSONObject jsonEquipe = jsonLista.getJSONObject(i);
						Equipe equipe = new Equipe(
							jsonEquipe.getString("nome"), 
							//jsonEquipe.getString("equipe"), 
							//jsonEquipe.getString("site"));
							"","");
						equipes.add(equipe);
						
					}
					return equipes;
				}
				
			}catch(Exception x){
				x.printStackTrace();
			}
			
			return null;
		}
		
		private String bytesToString(InputStream is) throws IOException{
			
			byte[] bytes = new byte[1024];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			while(is.read(bytes) != -1){
				baos.write(bytes);
			}
			String s = new String(baos.toByteArray());
			return s;
		}
		
		@Override
		protected void onPostExecute(List<MainActivity.Equipe> result) {
			super.onPostExecute(result);
			if(result != null){
				ArrayAdapter<Equipe> adapter = new ArrayAdapter<MainActivity.Equipe>(
						MainActivity.this, android.R.layout.simple_list_item_1, result);
				mListView.setAdapter(adapter);
			}
		}
		
	}

}
