package br.edu.unibratec.rafaelwms.askandanswer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class TutorialHttp {
	
public static HttpURLConnection abrirConexao() throws IOException{
		
		URL url = new URL("https://dl.dropboxusercontent.com/1/view/85nmogj0gy88eka/5%C2%BA%20per%C3%ADodo/PGM/tutorial.json");
		HttpURLConnection conexao = (HttpURLConnection)url.openConnection();
		conexao.setRequestMethod("GET");
		conexao.setDoInput(true);
		conexao.connect();
		return conexao;
		
	}

public static List<Tutorial> carregarTutorials(InputStream is)throws Exception{
	List<Tutorial> tutorials = new ArrayList<Tutorial>();
	
	String jsonString = bytesToString(is);	
	JSONObject jsonTutorials = new JSONObject(jsonString);
	JSONArray jsonLista = jsonTutorials.getJSONArray("tutorialpics");
	for (int i = 0; i < jsonLista.length(); i++) {
		JSONObject jsonTutorial = jsonLista.getJSONObject(i);
		Tutorial tutorial = new Tutorial(
			jsonTutorial.getString("name"), 
			jsonTutorial.getString("image"), 
			jsonTutorial.getString("desc"));

		tutorials.add(tutorial);
		}
	return tutorials;
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
