package com.app.yuejuan;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import android.util.Log;


public class ForgetPasswordActivity extends Activity {
	public TextView titleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	Log.d("YJ", "onCreate func");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        titleView = (TextView)this.findViewById(R.id.head_title);
        titleView.setText("忘记密码");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
  //
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.submit_button:
        	
//            EditText usernameET =(EditText)findViewById(R.id.login_username);
//            String username = usernameET.getText().toString();
//            EditText passwordET =(EditText)findViewById(R.id.login_password);
//            String password = passwordET.getText().toString();
//            
//            Toast.makeText(ForgetActivity.this, "btn1:"+username+password, Toast.LENGTH_SHORT).show();

            break;
              
        case R.id.head_back_button:
        	
        	Intent intent =new Intent(ForgetPasswordActivity.this, LoginActivity.class);
        	startActivity(intent);
        	overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
            break;

        default:
            break;
        }
    }
}
