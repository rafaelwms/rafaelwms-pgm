package br.edu.unibratec.rafaelwms.askandanswer;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity implements OnClickListener{

	Button btnLogin;
	TextView txtLogin;
	EditText editLogin;
	TextView txtPass;
	EditText editPass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		txtLogin = (TextView)findViewById(R.id.textLogin);
		txtPass = (TextView)findViewById(R.id.txtPass);
		editLogin = (EditText)findViewById(R.id.editLogin);
		editPass = (EditText)findViewById(R.id.editPass);
		btnLogin = (Button)findViewById(R.id.btnLoginnn);
		btnLogin.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.new_account) {
			Intent it = new Intent(this, AccountActivity.class);
			startActivity(it);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btnLoginnn) {
			
			try{
			if(editLogin.getText().toString().trim().equals("")){
				throw new Exception(getResources().getString(R.string.exceptionLoginEmpty));
			}
			if(editPass.getText().toString().trim().equals("")){
				throw new Exception(getResources().getString(R.string.exceptionPassEmpty));
			}

			AskAndAnswerDB db = new AskAndAnswerDB(this);

			User logged = db.login(editLogin.getText().toString(), editPass
				.getText().toString());

			if (logged != null) {
				Intent it = new Intent(this, MainActivity.class);
				it.putExtra(MainActivity.SESSION, logged);
				startActivity(it);
				finish();
				
			} else {
				Toast.makeText(this, getResources().getString(R.string.userNotFound), Toast.LENGTH_SHORT)
						.show();
			}
			}catch(Exception ex){
				Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT)
				.show();
			}
		 
		}

	}



}
