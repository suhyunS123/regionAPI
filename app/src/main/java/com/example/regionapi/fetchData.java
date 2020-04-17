package com.example.regionapi;

import android.os.AsyncTask;
import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class fetchData {
        String data="";
    String dataParsed="";
    String singleParsed="";
    BufferedReader br = null;
    String appName="P.T";
        try {
        String urlstr = "http://api.visitkorea.or.kr/"
                + "openapi/services/rest/EngService/areaCode"
                + "&ServiceKey=bywYnmxO22GvVcjVqX51LynXlyzrFTtMzTRM88W6rKGfO8VQtw6HycfhvB9ci6s9nH1fPDCOWQnabI9Htz7WDg%3D%3D"
                +"&numOfRows=10&pageNo=1"
                +"&MobileOs=AND&MobileApp=appName";
        URL url = null;
        try {
            url = new URL(urlstr);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        HttpURLConnection urlconnection = null;
        try {
            urlconnection = (HttpURLConnection) url.openConnection();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            urlconnection.setRequestMethod("GET");
        } catch (ProtocolException ex) {
            ex.printStackTrace();
        }
//        br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
//        String result = "";
//        String line;
//        while (true) {
//            try {
//                if (!((line = br.readLine()) != null)) break;
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//            result = result + line + "\n";
//        }
//
//        JSONArray JA= null;
//        try {
//            JA = new JSONArray(data);
//        } catch (JSONException ex) {
//            ex.printStackTrace();
//        }
//        for(int i=0; i<JA.length();i++)
//        {
//            JSONObject JO= null;
//            try {
//                JO = (JSONObject) JA.get(i);
//            } catch (JSONException ex) {
//                ex.printStackTrace();
//            }
//            try {
//                singleParsed="Name:"+JO.get("name")="\n";
//            } catch (JSONException ex) {
//                ex.printStackTrace();
//            }
//
//            dataParsed=dataParsed+singleParsed;
//
//        }
//        System.out.println(result);
//    } catch (Exception e) {
//        System.out.println(e.getMessage());}
    }

@Override
protected void onPostExecute(Void aVoid){
    super.onPostExecute(aVoid);

    MainActivity.data.setText(this.dataParsed);
}
