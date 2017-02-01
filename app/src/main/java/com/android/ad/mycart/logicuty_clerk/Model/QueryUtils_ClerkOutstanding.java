package com.android.ad.mycart.logicuty_clerk.Model;

import android.text.TextUtils;
import android.util.Log;

import com.android.ad.mycart.logicuty_clerk.Activity.OutstandingActivity_Clerk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajeev on 1/2/2017.
 */

public class QueryUtils_ClerkOutstanding {
    private static final String SAMPLE_JSON_RESPONSE = "http://172.23.134.192/InventoryService/Service.svc/Outstanding";

    private QueryUtils_ClerkOutstanding() {
    }


    public static final String LOG_TAG = OutstandingActivity_Clerk.class.getName();


    private static List<OutstandingClass_Clerk> extractFeatureFromJson(String outstandingJSON) {

        if (TextUtils.isEmpty(outstandingJSON)) {
            return null;
        }

        List<OutstandingClass_Clerk> outstandings = new ArrayList<>();


        try {
            JSONArray baseJsonArray = new JSONArray(outstandingJSON);

            for (int i = 0; i < baseJsonArray.length(); i++) {

                JSONObject currentOutstanding = baseJsonArray.getJSONObject(i);
                String departmentNameView  = currentOutstanding.getString("DeptName");
                String itemNameView = currentOutstanding.getString("ItemName");
                String quantityView = currentOutstanding.getString("Quantity");

                OutstandingClass_Clerk outstanding = new OutstandingClass_Clerk(departmentNameView,itemNameView,quantityView);
                outstandings.add(outstanding);
            }

        } catch (JSONException e) {

            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        return outstandings;
    }


    public static List<OutstandingClass_Clerk> fetchOutstandingData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<OutstandingClass_Clerk> outstandings = extractFeatureFromJson(jsonResponse);


        return outstandings;
    }






    private static URL createUrl(String stringurl){
        URL url = null;
        try{
            url = new URL(stringurl);
        }catch (MalformedURLException e){
            Log.e(LOG_TAG,"problem building the url",e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the Requisition JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {

                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
