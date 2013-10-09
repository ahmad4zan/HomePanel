package com.example.test2;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.sigtech.cctv.ActivitySwipeDetector;
import com.sigtech.cctv.InternetManager;
import com.sigtech.cctv.MjpegInputStream;
import com.sigtech.cctv.MjpegView;
import com.sigtech.cctv.SwipeInterface;

public class MainActivity extends FragmentActivity implements SwipeInterface,
		OnLongClickListener {
	static int numberOfPages = 2;
	ViewPager myViewPager;
	MyFragmentPagerAdapter myFragmentPagerAdapter;
	static String url;

	static final String TAG = "MjpegActivity";
	boolean isPause = false;
	MjpegView mv;
	static TextView tv;
	String url2;
	int i = 0;

	String URL_CCTV = "http://192.168.1.142:5052/videostream.cgi?user=admin&pwd=admin&resolution=8&rate=0";

	@SuppressLint("InlinedApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		getWindow().getDecorView().setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_LOW_PROFILE);

		setContentView(R.layout.activity_main);

		mv = (MjpegView) findViewById(R.id.mv_layout);
		myViewPager = (ViewPager) findViewById(R.id.view_pager);
		tv = (TextView) findViewById(R.id.textView1);
		ActivitySwipeDetector swipe = new ActivitySwipeDetector(this);

		mv.setOnLongClickListener(this);
		mv.setOnTouchListener(swipe);

		myFragmentPagerAdapter = new MyFragmentPagerAdapter(
				getSupportFragmentManager());
		myViewPager.setAdapter(myFragmentPagerAdapter);

		Toast.makeText(MainActivity.this, URL_CCTV, Toast.LENGTH_LONG).show();

		new DoRead().execute(URL_CCTV);

	}

	// Adapters
	private static class MyFragmentPagerAdapter extends FragmentPagerAdapter {
		public MyFragmentPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int index) {
			return PageFragment.newInstance(index);
		}

		@Override
		public int getCount() {
			return numberOfPages;
		}
	}

	public void onPause() {
		super.onPause();
		//mv.stopPlayback();
	}

	public void onResume() {
		super.onResume();
		//mv.startPlayback();
	}

	public class DoRead extends AsyncTask<String, Void, MjpegInputStream> {
		protected MjpegInputStream doInBackground(String... url) {
			// TODO: if camera has authentication deal with it and don't just
			// not work
			HttpResponse res = null;
			DefaultHttpClient httpclient = new DefaultHttpClient();
			Log.d(TAG, "1. Sending http request");
			try {
				res = httpclient.execute(new HttpGet(URI.create(url[0])));
				Log.d(TAG, "2. Request finished, status = "
						+ res.getStatusLine().getStatusCode());
				if (res.getStatusLine().getStatusCode() == 401) {
					// You must turn off camera User Access Control before this
					// will work
					return null;
				}
				return new MjpegInputStream(res.getEntity().getContent());
			} catch (ClientProtocolException e) {
				e.printStackTrace();
				Log.d(TAG, "Request failed-ClientProtocolException", e);
				// Error connecting to camera
			} catch (IOException e) {
				e.printStackTrace();
				Log.d(TAG, "Request failed-IOException", e);
				// Error connecting to camera
			}

			return null;
		}

		protected void onPostExecute(MjpegInputStream result) {
			mv.setSource(result);
			// mv.setDisplayMode(MjpegView.SIZE_STANDARD);
			// mv.setDisplayMode(MjpegView.SIZE_BEST_FIT);
			mv.setDisplayMode(MjpegView.SIZE_FULLSCREEN);
			mv.showFps(true);
		}
	}

	@Override
	public void bottom2top(View v) {
		url2 = "http://192.168.1.142:5052/decoder_control.cgi?command=2&onestep=1&degree=20&user=admin&pwd=admin";
		new InternetManager().execute(url2);
		tv.setText("Down");
	}

	@Override
	public void left2right(View v) {
		url2 = "http://192.168.1.142:5052/decoder_control.cgi?command=6&onestep=1&degree=20&user=admin&pwd=admin";
		new InternetManager().execute(url2);
		tv.setText("Left");
	}

	@Override
	public void right2left(View v) {
		url2 = "http://192.168.1.142:5052/decoder_control.cgi?command=4&onestep=1&degree=20&user=admin&pwd=admin";
		new InternetManager().execute(url2);
		tv.setText("Right");
	}

	@Override
	public void top2bottom(View v) {

		url2 = "http://192.168.1.142:5052/decoder_control.cgi?command=0&onestep=1&degree=20&user=admin&pwd=admin";
		new InternetManager().execute(url2);
		tv.setText("Up");
	}

	@Override
	public boolean onLongClick(View v) {
		tv.setText("Swipe the camera screen to move the camera");
		return true;
	}
}
