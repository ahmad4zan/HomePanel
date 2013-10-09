/****
 * This is the Data Manager. It is where the most static data are located.
 * */

package com.example.test2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class DataManager {
	public static final String URL_UPLOAD = "http://malaysianvote.com/cservice/upload/";

	public static String getData(String url) {
		String line = null;
		StringBuilder sb = new StringBuilder();

		try {
			URL urlCOnn = new URL(url);
			InputStream is = urlCOnn.openConnection().getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);

			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String postData(String url, List<NameValuePair> nameValuePairs) {
		String resp = null;
		StringBuilder sb = new StringBuilder();

		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		if (url == null)
			url = "http://asfwan.dlinkddns.com/test/";
		HttpPost httppost = new HttpPost(url);

		try {
			// Add your data - example
			// nameValuePairs = new ArrayList<NameValuePair>(2);
			// nameValuePairs.add(new BasicNameValuePair("test", "testdata"));

			// setting namevaluepairs
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			while ((resp = reader.readLine()) != null) {
				sb.append(resp);
				Log.d("resp", resp);
			}

			resp = sb.toString();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resp;
	}

	public static void showToast(Context ctxt, String txt) {
		Toast.makeText(ctxt, txt, Toast.LENGTH_SHORT).show();
	}

	public static void showLongToast(Context ctxt, String txt) {
		Toast.makeText(ctxt, txt, Toast.LENGTH_LONG).show();
	}

	public static String putData(String url, List<NameValuePair> nameValuePairs) {
		String resp = null;
		StringBuilder sb = new StringBuilder();

		// Create a new HttpClient and Post Header
		HttpClient httpclient = new DefaultHttpClient();
		if (url == null)
			url = "http://asfwan.dlinkddns.com/test/";
		HttpPut httpput = new HttpPut(url);

		try {
			// setting namevaluepairs
			httpput.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httpput);

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));

			while ((resp = reader.readLine()) != null) {
				sb.append(resp);
				Log.d("resp", resp);
			}

			resp = sb.toString();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return resp;
	}

	public static String deleteData(String url1) {
		String line = "";
		StringBuilder sb = new StringBuilder();
		try {
			URL url = new URL(url1);
			HttpURLConnection httpCon = (HttpURLConnection) url
					.openConnection();
			httpCon.setRequestMethod("DELETE");
			InputStream is = httpCon.getInputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);

			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

			return sb.toString();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
