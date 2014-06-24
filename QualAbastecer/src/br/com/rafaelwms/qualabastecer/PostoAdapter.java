package br.com.rafaelwms.qualabastecer;

import java.util.List;

import br.com.rafaelwms.qualabastecer.R;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PostoAdapter extends BaseAdapter {
	
	private List<Posto> postos;
	
	public PostoAdapter(List<Posto> pts){
		postos = pts;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return postos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return postos.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return postos.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Posto posto = postos.get(position);
		
		if(convertView == null){
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_posto, null);
		}
		
		ImageView imgPosto = (ImageView)convertView.findViewById(R.id.imgPostoList);
		TextView txtPostoNome = (TextView)convertView.findViewById(R.id.txtPostoNomeList);
		TextView txtPostoPrecos = (TextView)convertView.findViewById(R.id.txtPostoPrecosList);
		TextView txtPostoRating = (TextView)convertView.findViewById(R.id.txtPostoRatingList);
		
		imgPosto.setBackgroundColor(Color.rgb(0, 0, 0));
		
		if(posto.getAtendimento() == 5){
			imgPosto.setImageResource(R.drawable.ic_fav_posto);
		}
				
		txtPostoNome.setText(posto.getNome().toString());
		txtPostoPrecos.setText(parent.getResources().getString(R.string.lblPostoListPrecoGasolina)+ " "+ posto.getLitroGasolina()
				+"\n"+parent.getResources().getString(R.string.lblPostoListPrecoEtanol)+ " "+ posto.getLitroEtanol()
				+"\n"+parent.getResources().getString(R.string.lblPostoListPrecoDiesel)+ " "+ posto.getLitroDiesel());
		txtPostoRating.setText(parent.getResources().getString(R.string.lblPostoListRating)+" "+posto.getAtendimento());
		
		
		
		return convertView;
	}

}
