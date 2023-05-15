package com.example.ciclobnb.Fitxers;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class ConexioFitxer {

    public String url="http://files.000webhostapp.com/";

    private class BaixarImatges extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            return "fet";
        }

        @Override
        protected void onPostExecute(String result) {


        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
    private void downloadFile(String fileUrl, String fileName) {
        try {
            URL url = new URL(fileUrl);

            HttpURLConnection cn= (HttpURLConnection) url.openConnection();
            cn.setConnectTimeout(500);
            cn.connect();

            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
            FileOutputStream fileOutput = new FileOutputStream(file);

            InputStream inputStream = cn.getInputStream();

            byte[] buffer = new byte[1024];
            int bufferLength = 0;

            while ((bufferLength = inputStream.read(buffer)) > 0) {
                fileOutput.write(buffer, 0, bufferLength);
            }
            fileOutput.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
