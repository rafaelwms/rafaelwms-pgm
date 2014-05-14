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

public class EquipeHttp {
	
	public static HttpURLConnection abrirConexao() throws IOException{
		
		URL url = new URL("https://dl.dropboxusercontent.com/u/6802536/escudos/times.json");
		HttpURLConnection conexao = (HttpURLConnection)url.openConnection();
		conexao.setRequestMethod("GET");
		conexao.setDoInput(true);
		conexao.connect();
		return conexao;
		
	}
	
	public static List<Equipe> carregarEquipes(InputStream is)throws Exception{
		List<Equipe> equipes = new ArrayList<Equipe>();
		
		String jsonString = bytesToString(is);	
		JSONObject jsonEquipes = new JSONObject(jsonString);
		JSONArray jsonLista = jsonEquipes.getJSONArray("times");
		for (int i = 0; i < jsonLista.length(); i++) {
			JSONObject jsonEquipe = jsonLista.getJSONObject(i);
			Equipe equipe = new Equipe(
				jsonEquipe.getString("nome"), 
				jsonEquipe.getString("escudo"), 
				jsonEquipe.getString("site"));

			equipes.add(equipe);
			}
		return equipes;
	}
	
	
	public static String bytesToString(InputStream is) throws IOException{
		
		byte[] bytes = new byte[1024];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int bytesLidos;
		while((bytesLidos = is.read(bytes)) != -1){
			baos.write(bytes, 0, bytesLidos);
		}
		String s = new String(baos.toByteArray());
		return s;
	}

}
