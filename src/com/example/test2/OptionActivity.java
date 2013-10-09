
//not used atm

package com.example.test2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class OptionActivity extends Activity implements OnClickListener {

	Button b1, b2, b3, b4;
	Spinner sp1;
	EditText et1, et2, et3;
	String url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		getWindow().getDecorView().setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_LOW_PROFILE);
		
		setContentView(R.layout.option);

		b1 = (Button) findViewById(R.id.bt1);
		b2 = (Button) findViewById(R.id.bt2);
		b3 = (Button) findViewById(R.id.bt3);
		et1 = (EditText) findViewById(R.id.editText1);
		et2 = (EditText) findViewById(R.id.editText2);
		et3 = (EditText) findViewById(R.id.editText3);
		sp1 = (Spinner) findViewById(R.id.spinner1);

		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
		
		
		
		sp1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				

			}
		});

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.bt1){
			url = et1.getText().toString();
			MainActivity.url = url;
			Toast.makeText(OptionActivity.this, url, Toast.LENGTH_SHORT).show();
		}
		if (id == R.id.bt2){
			
		}
		

	}

}
