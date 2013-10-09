package com.example.test2;


import android.os.AsyncTask;

public class InternetManager2 extends AsyncTask<String, Void, String> {

	@Override
	protected String doInBackground(String... params) {
		if (params.length <= 0)
			return null;
		return DataManager.getData(params[0]);
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);

		// if(result != null)
		// MainActivity.tv.setText(result);
		// else
		// return;
		// if(result.equals(" "))
		// MainActivity.tv.setText("No Response");

	}

}
