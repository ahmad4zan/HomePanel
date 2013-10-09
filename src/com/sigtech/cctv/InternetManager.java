package com.sigtech.cctv;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class InternetManager extends AsyncTask<String, Void, String>{

	@Override
	protected String doInBackground(String... url) {
		if(url.length <= 0)
			return null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(URI.create(url[0]));
		try {
			HttpResponse res = httpclient.execute(httppost);
			res.getStatusLine();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return DataManager.getData(params[0]);
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		
	}
	
	

}
