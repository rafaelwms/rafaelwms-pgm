package br.edu.unibratec.rafaelwms.askandanswer;

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

public class TutorialListFragment extends ListFragment {
	
	TutorialAsyncTask mTask;
	List<Tutorial> mTutorials;
	ArrayAdapter<Tutorial> mAdapter;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		ConnectivityManager cm = (ConnectivityManager)
				getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo info = cm.getActiveNetworkInfo();
				if (info != null && info.isConnected()){
					if (mTask == null){
						mTask =	new TutorialAsyncTask();
						mTask.execute();
					}
				}else{
					Toast.makeText(getActivity(), "No connection", Toast.LENGTH_SHORT).show();
				}
		
		
		super.onActivityCreated(savedInstanceState);
	}
	
	
	public class TutorialAsyncTask extends AsyncTask<Void, Void, List<Tutorial>> {

		@Override
		protected List<Tutorial> doInBackground(Void... params) {
			
			try{
					HttpURLConnection conexao = TutorialHttp.abrirConexao();
					
					int resposta = conexao.getResponseCode();
					
					if(resposta == HttpURLConnection.HTTP_OK){
						mTutorials = TutorialHttp.carregarTutorials(conexao.getInputStream());
						return mTutorials;
					}
					
				}catch(Exception x){
					x.printStackTrace();
				}			
				return null;
		}
		
		@Override
		protected void onPostExecute(List<Tutorial> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result != null){
				mAdapter = new TutorialAdapter(getActivity(), mTutorials);
				setListAdapter(mAdapter);
			}
		}
	}

}
