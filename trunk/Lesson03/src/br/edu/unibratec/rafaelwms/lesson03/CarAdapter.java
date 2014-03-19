package br.edu.unibratec.rafaelwms.lesson03;

import java.util.List;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CarAdapter extends BaseAdapter{

	private List<Car> cars;
	
	public CarAdapter(List<Car> c){
		
		cars = c;
	}
	
	
	@Override
	public int getCount() {
		return cars.size();
	}

	@Override
	public Object getItem(int position) {
		return cars.get(position);
	}

	@Override
	public long getItemId(int position) {
		return cars.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, 
			ViewGroup parent) {
		ViewHolder holder;
		Car car = cars.get(position);
		int image = 0;
		
		if(convertView == null){
			convertView = LayoutInflater.from(parent.getContext())
					.inflate(R.layout.activity_model_list, null);
			
			holder = new ViewHolder();
			holder.txtModel = (TextView)convertView.findViewById(R.id.modelName);
			holder.txtYaer = (TextView)convertView.findViewById(R.id.year);
			holder.txtFuel = (TextView)convertView.findViewById(R.id.fuel);
			holder.industryImg = (ImageView)convertView.findViewById(R.id.industryPhoto);
			convertView.setTag(holder);
			
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		
		holder.txtFuel.setText(car.getFuel());
		holder.txtModel.setText(car.getModel());
		holder.txtYaer.setText(car.getYear());
		
		if(car.getIndustry().equals("Fiat")){
			image = R.drawable.fiat;
		}else if(car.getIndustry().equals("Chevrolet")){
			image = R.drawable.chevrolet;
		}else if(car.getIndustry().equals("Ford")){
			image = R.drawable.ford;
		}else if(car.getIndustry().equals("Volkswagen")){
			image = R.drawable.volks;
		}
		holder.industryImg.setImageResource(image);
		return convertView;
	}

	
	class ViewHolder{
		
		ImageView industryImg;
		TextView txtModel;
		TextView txtYaer;
		TextView txtFuel;
		
	}
	
	
}
