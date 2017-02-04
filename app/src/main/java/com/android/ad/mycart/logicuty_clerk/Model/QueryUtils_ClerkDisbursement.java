package com.android.ad.mycart.logicuty_clerk.Model;

import android.text.TextUtils;
import android.util.Log;

import com.android.ad.mycart.logicuty_clerk.Activity.DisbursementActivity_Clerk;

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

;

/**
 * Created by rajeev on 1/2/2017.
 */

public class QueryUtils_ClerkDisbursement {

    private static final String SAMPLE_JSON_RESPONSE = "http://www.json-generator.com/api/json/get/bThTKumnsi?indent=2";
   // final static String host = "http://172.23.134.192/LogicUniversityStore/InventoryService/Service.svc/Disbursement";
    private QueryUtils_ClerkDisbursement() {
    }


   public static final String LOG_TAG = DisbursementActivity_Clerk.class.getName();


    private static List<DisbursementClass_Clerk> extractFeatureFromJson(String disbursementJSON) {

        if (TextUtils.isEmpty(disbursementJSON)) {
            return null;
        }

        List<DisbursementClass_Clerk> disbursements = new ArrayList<>();


        try {
            JSONArray baseJsonArray = new JSONArray(disbursementJSON);
            //JSONArray baseJsonArray = JSONParser.getJSONArrayFromUrl(host+"/1");

            for (int i = 0; i < baseJsonArray.length(); i++) {

                JSONObject currentRequisition = baseJsonArray.getJSONObject(i);
                String disbursementCode  = currentRequisition.getString("DisbursementNum");
                String requisitionId = currentRequisition.getString("ReqNum");
                String raisedate = currentRequisition.getString("RequestedDate");

                DisbursementClass_Clerk disbursement = new DisbursementClass_Clerk(disbursementCode,requisitionId, raisedate);
                disbursements.add(disbursement);
            }

        } catch (JSONException e) {

            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        return disbursements;
    }


    public static List<DisbursementClass_Clerk> fetchRequisitionData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<DisbursementClass_Clerk> disbursements = extractFeatureFromJson(jsonResponse);


        return disbursements;
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
