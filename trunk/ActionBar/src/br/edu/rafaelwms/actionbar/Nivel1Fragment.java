package br.edu.rafaelwms.actionbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Nivel1Fragment extends Fragment{
	
	public static final int TIPO_PAGER = 0;
	public static final int TIPO_SPINNER = 1;
	public static final int TIPO_TABS = 2;
	private static final String EXTRA_TIPO = "tipo";
	
	public static Nivel1Fragment novaInstancia(int tipo){
		Nivel1Fragment f = new Nivel1Fragment();
		
		Bundle parametros = new Bundle();
		parametros.putInt(EXTRA_TIPO, tipo);
		f.setArguments(parametros);
		return f;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment_nivel1, container, false);
		
		TextView txt = (TextView)layout.findViewById(R.id.textView1);
		Button btn = (Button)layout.findViewById(R.id.button1);
		
		int tipo = getArguments().getInt(EXTRA_TIPO);
		String[] opcoes = getResources().getStringArray(R.array.opcoes_menu);
		
		txt.setText(opcoes[tipo]);
		
		
		return layout;
	}
	
}
