package com.example.test2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PageFragment extends Fragment implements OnClickListener {
	Activity act;
	static Context c;
	boolean R1, R2, R3, RALL, S1, S2, S3 = false;
	int index, num;
	String url, url1, url2, url3, device;
	String device_name[];
	TextView tv;
	ImageButton b, b2, b3, b4, b5, b6, b7;
	Button bt1, bt2, bt3;
	EditText et1, et2, et3;
	Spinner s1, s2, sp1;
	String pass = "1234";
	ArrayAdapter<String> dataAdapter;

	public static PageFragment newInstance(int index) {
		PageFragment pageFragment = new PageFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("index", index);
		pageFragment.setArguments(bundle);
		return pageFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.myfragment_layout, container,
				false);

		index = getArguments().getInt("index");
		act = getActivity();
		b = (ImageButton) view.findViewById(R.id.button1);
		b2 = (ImageButton) view.findViewById(R.id.button2);
		b3 = (ImageButton) view.findViewById(R.id.button3);
		b4 = (ImageButton) view.findViewById(R.id.button4);
		b5 = (ImageButton) view.findViewById(R.id.button5);
		b6 = (ImageButton) view.findViewById(R.id.button7);
		b7 = (ImageButton) view.findViewById(R.id.imagebutton2);
		s1 = (Spinner) view.findViewById(R.id.spinner1);
		s2 = (Spinner) view.findViewById(R.id.spinner2);
		bt1 = (Button) view.findViewById(R.id.bt1);

		s1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> av, View v, int pos,
					long arg3) {
				url2 = (String) av.getItemAtPosition(pos);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
		s2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> av, View v, int pos,
					long arg3) {
				Resources res = getResources();
				String[] funct = res.getStringArray(R.array.device);
				url3 = funct[pos];

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
		LayoutParams buttonParam = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		buttonParam.setMargins(0, 0, 0, 0);
		// s1.setVisibility(View.GONE);
		// s2.setLayoutParams(buttonParam);
		if (index == 0) {
			b.setImageResource(R.drawable.relay_off_170px);
			b2.setImageResource(R.drawable.relay_off_170px);
			b3.setImageResource(R.drawable.relay_off_170px);
			b4.setImageResource(R.drawable.relay_alloff_170px);
			b.setBackgroundColor(getResources().getColor(R.color.deepskyblue));
			b2.setBackgroundColor(getResources().getColor(R.color.deepskyblue3));
			b3.setBackgroundColor(getResources().getColor(R.color.deepskyblue3));
			b7.setImageResource(R.drawable.sensor_icon);
			Resources res = getResources();
			device_name = res.getStringArray(R.array.function);
		}
		if (index == 1) {
			b.setImageResource(R.drawable.lock_1_170px);
			b2.setImageResource(R.drawable.siren_off_170px);
			b3.setImageResource(R.drawable.light_off_170px);
			b3.setLayoutParams(buttonParam);
			b4.setVisibility(View.GONE);
			b.setBackgroundColor(getResources().getColor(R.color.lblue));
			b2.setBackgroundColor(getResources().getColor(R.color.purple));
			b3.setBackgroundColor(getResources().getColor(R.color.soylentgreen));
			b7.setImageResource(R.drawable.relay_icon);
			RelativeLayout.LayoutParams rparam = new RelativeLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			rparam.addRule(RelativeLayout.RIGHT_OF, 0);
			b7.setLayoutParams(rparam);
			RelativeLayout.LayoutParams rparam2 = new RelativeLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			rparam2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			b6.setLayoutParams(rparam2);
			Resources res = getResources();
			device_name = res.getStringArray(R.array.zone);

		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(act,
				android.R.layout.simple_spinner_item, device_name);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s2.setAdapter(dataAdapter);
		
		b.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
		b5.setOnClickListener(this);
		b6.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View v) {
		final int id = v.getId();

		act = getActivity();
		if (url1 == null || url1 == " ")
			url1 = "192.168.1.29";

		if (id == R.id.button1 && index == 0) {
			if (!R1) {
				b.setImageResource(R.drawable.relay_on_170px);
				url = url1 + url2 + url3 + "_" + "auto=12";
				if (ConnectionChecker.checkConnection(act)) {
					if (!url.contains("http"))
						url = "http://" + url;
					new InternetManager2().execute(url);
				}
			} else {
				b.setImageResource(R.drawable.relay_off_170px);
				url = url1 + url2 + url3 + "_" + "auto=11";
				if (ConnectionChecker.checkConnection(act)) {
					if (!url.contains("http"))
						url = "http://" + url;
					new InternetManager2().execute(url);

				}
			}
			R1 = !R1;
		}
		if (id == R.id.button2 && index == 0) {
			if (!R2) {
				b2.setImageResource(R.drawable.relay_on_170px);
				url = url1 + url2 + url3 + "_" + "auto=14";
				if (ConnectionChecker.checkConnection(act)) {
					if (!url.contains("http"))
						url = "http://" + url;
					new InternetManager2().execute(url);
				}
			} else {
				b2.setImageResource(R.drawable.relay_off_170px);
				url = url1 + url2 + url3 + "_" + "auto=13";
				if (ConnectionChecker.checkConnection(act)) {
					if (!url.contains("http"))
						url = "http://" + url;
					new InternetManager2().execute(url);
				}
			}
			R2 = !R2;
		}
		if (id == R.id.button3 && index == 0) {
			if (!R3) {
				b3.setImageResource(R.drawable.relay_on_170px);
				url = url1 + url2 + url3 + "_" + "auto=16";
				if (ConnectionChecker.checkConnection(act)) {
					if (!url.contains("http"))
						url = "http://" + url;
					new InternetManager2().execute(url);
				}
			} else {
				b3.setImageResource(R.drawable.relay_off_170px);
				url = url1 + url2 + url3 + "_" + "auto=15";
				if (ConnectionChecker.checkConnection(act)) {
					if (!url.contains("http"))
						url = "http://" + url;
					new InternetManager2().execute(url);
				}

			}
			R3 = !R3;
		}
		if (id == R.id.button4 && index == 0) {
			if (!RALL) {
				b.setImageResource(R.drawable.relay_on_170px);
				b2.setImageResource(R.drawable.relay_on_170px);
				b3.setImageResource(R.drawable.relay_on_170px);
				b4.setImageResource(R.drawable.relay_allon_170px);
				url = url1 + url2 + url3 + "_" + "auto=18";
				R1 = true;
				R2 = true;
				R3 = true;
				if (ConnectionChecker.checkConnection(act)) {
					if (!url.contains("http"))
						url = "http://" + url;
					new InternetManager2().execute(url);
				}
			} else {
				b.setImageResource(R.drawable.relay_off_170px);
				b2.setImageResource(R.drawable.relay_off_170px);
				b3.setImageResource(R.drawable.relay_off_170px);
				b4.setImageResource(R.drawable.relay_alloff_170px);
				url = url1 + url2 + url3 + "_" + "auto=17";
				R1 = false;
				R2 = false;
				R3 = false;
				if (ConnectionChecker.checkConnection(act)) {
					if (!url.contains("http"))
						url = "http://" + url;
					new InternetManager2().execute(url);
				}
			}
			RALL = !RALL;
		}

		if (R1 && R2 && R3) {
			b4.setImageResource(R.drawable.relay_allon_170px);
			RALL = true;
		} else if ((!R1 && !R2 && !R3)) {
			b4.setImageResource(R.drawable.relay_alloff_170px);
			RALL = false;
		}

		if (id == R.id.button1 && index == 1) {
			if (!S1) {
				b.setImageResource(R.drawable.lock_2_170px);
				url = url1 + url2 + url3 + "_" + "secure=22";
				if (ConnectionChecker.checkConnection(act)) {
					if (!url.contains("http"))
						url = "http://" + url;
					new InternetManager2().execute(url);
				}
			} else {
				b.setImageResource(R.drawable.lock_1_170px);
				url = url1 + url2 + url3 + "_" + "secure=21";
				if (ConnectionChecker.checkConnection(act)) {
					if (!url.contains("http"))
						url = "http://" + url;
					new InternetManager2().execute(url);
				}
			}
			S1 = !S1;
		}
		if (id == R.id.button2 && index == 1) {
			if (!S2) {
				b2.setImageResource(R.drawable.siren_on_170px);
				url = url1 + url2 + url3 + "_" + "secure=24";
				if (ConnectionChecker.checkConnection(act)) {
					if (!url.contains("http"))
						url = "http://" + url;
					new InternetManager2().execute(url);
				}
			} else {
				b2.setImageResource(R.drawable.siren_off_170px);
				url = url1 + url2 + url3 + "_" + "secure=23";
				if (ConnectionChecker.checkConnection(act)) {
					if (!url.contains("http"))
						url = "http://" + url;
					new InternetManager2().execute(url);
				}
			}
			S2 = !S2;
		}
		if (id == R.id.button3 && index == 1) {
			if (!S3) {
				b3.setImageResource(R.drawable.light_on_170px);
				url = url1 + url2 + url3 + "_" + "secure=26";
				if (ConnectionChecker.checkConnection(act)) {
					if (!url.contains("http"))
						url = "http://" + url;
					new InternetManager2().execute(url);
				}
			} else {
				b3.setImageResource(R.drawable.light_off_170px);
				url = url1 + url2 + url3 + "_" + "secure=25";
				if (ConnectionChecker.checkConnection(act)) {
					if (!url.contains("http"))
						url = "http://" + url;
					new InternetManager2().execute(url);
				}
			}
			S3 = !S3;
		}
		if (id == R.id.button5 && index == 0) {
			url = url1 + url2 + url3 + "_" + "auto=19";
			if (ConnectionChecker.checkConnection(act)) {
				if (!url.contains("http"))
					url = "http://" + url;
				new InternetManager2().execute(url);
			}

		}
		if (id == R.id.button5 && index == 1) {
			url = url1 + url2 + url3 + "_" + "secure=27";
			if (ConnectionChecker.checkConnection(act)) {
				if (!url.contains("http"))
					url = "http://" + url;
				new InternetManager2().execute(url);
			}
			Animation animation = AnimationUtils.loadAnimation(getActivity(),
					R.anim.rotate_around_center);
			b5.startAnimation(animation);

		}
		if (id == R.id.button7) {
			LayoutInflater inflater = getActivity().getLayoutInflater();
			AlertDialog.Builder builder = new AlertDialog.Builder(act);
			View view = inflater.inflate(R.layout.option, null);
			builder.setTitle("Setting");
			builder.setNegativeButton("back",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							PageFragment.this.onDetach();

						}
					});
			tv = (TextView) view.findViewById(R.id.url1);
			bt1 = (Button) view.findViewById(R.id.bt1);
			bt2 = (Button) view.findViewById(R.id.bt2);
			bt3 = (Button) view.findViewById(R.id.bt3);
			et1 = (EditText) view.findViewById(R.id.editText1);
			et2 = (EditText) view.findViewById(R.id.editText2);
			et3 = (EditText) view.findViewById(R.id.editText3);
			sp1 = (Spinner) view.findViewById(R.id.spinner1);
			tv.setText("Current URL : " + url1);
			bt1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					url1 = et1.getText().toString();
					tv.setText("Current URL : " + url1);
				}
			});
			sp1.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> av, View v, int pos,
						long arg3) {
					num = pos;

				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub

				}
			});

			// spinner setting, still buggy
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(act,
					android.R.layout.simple_spinner_item, device_name);

			sp1.setAdapter(dataAdapter);
			bt2.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					device_name[num] = et2.getText().toString();
					ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(
							act, android.R.layout.simple_spinner_item,
							device_name);
					sp1.setAdapter(dataAdapter2);
					s2.setAdapter(dataAdapter2);
					et2.setText("");
				}
			});

			bt3.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast.makeText(act, url, Toast.LENGTH_SHORT).show();

				}
			});

			builder.setView(view).show();

			// startActivity(new Intent(act, OptionActivity.class));
		}

		Toast.makeText(act, url, Toast.LENGTH_SHORT).show();

	}
}