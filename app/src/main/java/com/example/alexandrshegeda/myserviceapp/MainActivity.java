package com.example.alexandrshegeda.myserviceapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends ActionBarActivity {

    private ProgressDialog pDialog;
    public static final int progress_bar_type = 0;
    private String LOG_TAG = "MainActivity";
    private SimpleDateFormat sdf;
    private Date now;
    private SharedPreferences sharedPref;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        sdf = new SimpleDateFormat("E hh:mm:ss 'at' yyyy.MM.dd");
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        if(sharedPref.contains("last_update"))
        {
            findViewById(R.id.btn_download).setVisibility(View.GONE);
            TextView stamp = (TextView) findViewById(R.id.time_stamp);
            stamp.setText(sharedPref.getString("last_update",sdf.format(new Date())));
            findViewById(R.id.update_layout).setVisibility(View.VISIBLE);
        }else
        {
            findViewById(R.id.update_layout).setVisibility(View.GONE);
            findViewById(R.id.btn_download).setVisibility(View.VISIBLE);
        }
    }

//    @Override
//    protected Dialog onCreateDialog(int id) {
//        switch (id) {
//            case progress_bar_type: // we set this to 0
//                pDialog = new ProgressDialog(this);
//                pDialog.setMessage("Downloading file. Please wait...");
//                pDialog.setIndeterminate(false);
//                pDialog.setMax(100);
//                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//                pDialog.setCancelable(true);
//                pDialog.show();
//                return pDialog;
//            default:
//                return null;
//        }
//    }


    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Bar Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            now = new Date();
//            File f = new File(Environment.getExternalStorageDirectory()
//                    .getAbsolutePath() + File.separator + "new-prof.png");
//            f.delete();
            findViewById(R.id.btn_download).setVisibility(View.GONE);
            findViewById(R.id.update_layout).setVisibility(View.GONE);
            findViewById(R.id.progress).setVisibility(View.VISIBLE);
//            showDialog(progress_bar_type);
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();

                // this will be useful so that you can show a tipical 0-100%
                // progress bar
                int lenghtOfFile = conection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(),
                        8192);

                // Output stream
                OutputStream output = new FileOutputStream(Environment
                        .getExternalStorageDirectory().toString()
                        + "/data/new-prof.png");

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        /**
         * Updating progress bar
         * */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
//            pDialog.setProgress(Integer.parseInt(progress[0]));
            progressBar.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
//            dismissDialog(progress_bar_type);
            findViewById(R.id.progress).setVisibility(View.GONE);
            findViewById(R.id.update_layout).setVisibility(View.VISIBLE);
//            setContentView(R.layout.update_layout);
            TextView stamp = (TextView) findViewById(R.id.time_stamp);
            stamp.setText(sdf.format(now).toString());
            sharedPref.edit().putString("last_update", stamp.getText().toString()).commit();
        }
    }

    public void onClick(View view)
    {
        new DownloadFileFromURL().execute("http://www.tuttoandroid.net/wp-content/uploads/2011/12/new-prof.png");
    }
}
