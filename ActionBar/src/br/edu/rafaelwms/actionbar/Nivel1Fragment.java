package br.edu.rafaelwms.actionbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Nivel1Fragment extends Fragment implements OnClickListener{
	
	public static final int TIPO_PAGER = 0;
	public static final int TIPO_SPINNER = 1;
	public static final int TIPO_TABS = 2;
	private static final String EXTRA_TIPO = "tipo";
	int mTipo;
	
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
		btn.setOnClickListener(this);
		
		mTipo = getArguments().getInt(EXTRA_TIPO);
		String[] opcoes = getResources().getStringArray(R.array.opcoes_menu);
		
		txt.setText(opcoes[mTipo]);
		
		
		return layout;
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (mTipo) {
		case TIPO_TABS: 
			startActivity(new Intent(getActivity(), TabsActivity.class));
			break;

		default:
			break;
		}
	}
	
}
