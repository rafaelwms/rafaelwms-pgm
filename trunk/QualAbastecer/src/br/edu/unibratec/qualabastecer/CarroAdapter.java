package br.edu.unibratec.qualabastecer;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CarroAdapter extends BaseAdapter {

	private List<Veiculo> carros;

	public CarroAdapter(List<Veiculo> cars) {
		carros = cars;
	}

	@Override
	public int getCount() {
		return carros.size();
	}

	@Override
	public Object getItem(int position) {
		return carros.get(position);
	}

	@Override
	public long getItemId(int position) {
		return carros.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Veiculo carro = carros.get(position);

		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_carros_model, null);
		}

		ImageView imgCarro = (ImageView) convertView.findViewById(R.id.imgCarroList);
		TextView txtNomeCarro = (TextView) convertView.findViewById(R.id.txtNomeCarroList);
		TextView txtCombCarro = (TextView) convertView.findViewById(R.id.txtCarroComb);

		imgCarro.setBackgroundColor(carro.getCor());
		txtNomeCarro.setText(carro.getNome().toString());
		if (carro.getCombustivel() == 0) {
			txtCombCarro.setText(parent.getResources().getString(R.string.gasResult));
		} else if (carro.getCombustivel() == 1) {
			txtCombCarro.setText(parent.getResources().getString(R.string.ethResult));
		} else if (carro.getCombustivel() == 2) {
			txtCombCarro.setText("FLEX POWER");
		}
		return convertView;
	}

}
