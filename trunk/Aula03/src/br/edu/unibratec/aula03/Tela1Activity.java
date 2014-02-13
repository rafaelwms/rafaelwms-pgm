package br.edu.unibratec.aula03;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Tela1Activity extends Activity implements OnClickListener {
	TextView txtResultado;
	EditText edtTexto;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela1);
        
        txtResultado = (TextView)findViewById(R.id.txtResultado);
        edtTexto = (EditText)findViewById(R.id.editTexto);
        
        //Opção 1
        Button btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(this);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tela1, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.button1){
			txtResultado.setText(edtTexto.getText().toString());
		}
	}
    
}
