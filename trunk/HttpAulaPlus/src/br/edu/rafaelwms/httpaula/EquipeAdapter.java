package br.edu.rafaelwms.httpaula;

import java.util.List;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EquipeAdapter extends ArrayAdapter<Equipe> {

	public EquipeAdapter(Context context,  List<Equipe> objects) {
		super(context, 0, objects);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Passo 1 - 
		Equipe equipe = getItem(position);
		// Passo 2 -
		if(convertView == null){
			convertView = LayoutInflater.from(getContext()).
						inflate(R.layout.item_lista_equipe, null);
		}
		// Passo 3 - 
		ImageView imgEscudo = (ImageView)convertView.findViewById(R.id.imgEscudo);
		TextView txtNome = (TextView)convertView.findViewById(R.id.txtNome);
		TextView txtSite = (TextView)convertView.findViewById(R.id.txtSite);
		
		Picasso.with(getContext()).load(equipe.escudo).into(imgEscudo);
		txtNome.setText(equipe.nome);
		txtSite.setText(equipe.site);
		
		
		//Passo 4 - 
		return  convertView;
	}

}
