package br.edu.rafaelwms.actionbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ColorFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		TextView txt = new TextView(getActivity());
		
		int color = Color.rgb(
				(int)(Math.random() * 255), 
				(int)(Math.random() * 255), 
				(int)(Math.random() * 255));
		
		txt.setBackgroundColor(color);
		txt.setText(Integer.toHexString(color));
		txt.setGravity(Gravity.CENTER);
		
		
		return txt;
	}
	
}
