package br.edu.unibratec.rafaelwms.askandanswer;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class AccountActivity extends ActionBarActivity implements OnClickListener {
	
	User user;
	TextView txtLogin;
	TextView txtPass1;
	TextView txtPass2;
	EditText editLogin;
	EditText editPass1;
	EditText editPass2;
	Button btnRegister;
	CheckBox chkMasterUser;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);
		
		txtLogin = (TextView)findViewById(R.id.textLogin);
		txtPass1 = (TextView)findViewById(R.id.txtPass);
		txtPass2 = (TextView)findViewById(R.id.txtPass2);
		editLogin = (EditText)findViewById(R.id.editCreateLogin);
		editPass1 = (EditText)findViewById(R.id.editCreatePass);
		editPass2 = (EditText)findViewById(R.id.editCreatePass2);
		btnRegister = (Button)findViewById(R.id.btnCreateAccount);
		chkMasterUser = (CheckBox)findViewById(R.id.chkCreateMasterUser);
		btnRegister.setOnClickListener(this);


	}


	@Override
	public void onClick(View v) {
		
		if(v.getId() == R.id.btnCreateAccount){
			try{
				
				if(editLogin.getText().toString().trim().equals("")){
					throw new Exception(getResources().getString(R.string.exceptionLoginEmpty));
				}
				if(editPass1.getText().toString().trim().equals("")){
					throw new Exception(getResources().getString(R.string.exceptionPassEmpty));
				}
				if(!editPass1.getText().toString().trim().equals(editPass2.getText().toString().trim())){
					editPass1.setText("");
					editPass2.setText("");
					throw new Exception(getResources().getString(R.string.exceptionNoMatchPass));
				}
							
			UserDB db = new UserDB(this);
			user = new User(editLogin.getText().toString(), editPass1.getText().toString(), chkMasterUser.isChecked());
			db.insert(user);
			Toast.makeText(this, getResources().getString(R.string.accountCreated), Toast.LENGTH_SHORT).show();
			finish();
			}catch(Exception ex){
				Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
			}
			
			

			
		}
		
	}

}
