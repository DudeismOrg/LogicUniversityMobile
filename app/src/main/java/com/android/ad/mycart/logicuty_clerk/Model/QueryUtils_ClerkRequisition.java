package com.android.ad.mycart.logicuty_clerk.Model;

import android.text.TextUtils;
import android.util.Log;

import com.android.ad.mycart.JSONParser;
import com.android.ad.mycart.logicuty_clerk.Activity.RequisitionActivity_Clerk;

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

public class QueryUtils_ClerkRequisition {

    private static final String SAMPLE_JSON_RESPONSE ="http://172.23.200.42/LogicUniversityStore/InventoryService/Service.svc/Requisition/";

    private QueryUtils_ClerkRequisition () {
    }


    public static final String LOG_TAG = RequisitionActivity_Clerk.class.getName();


    private static List<RequisitionClass_Clerk> extractFeatureFromJson(String requisitionJSON) {

        if (TextUtils.isEmpty(requisitionJSON)) {
            return null;
        }

        List<RequisitionClass_Clerk> requisitions = new ArrayList<>();


        try {
            JSONArray baseJsonArray = new JSONArray(requisitionJSON);

            for (int i = 0; i < baseJsonArray.length(); i++) {

                JSONObject currentRequisition = baseJsonArray.getJSONObject(i);
                String DeptName  = currentRequisition.getString("DeptName");
                String ItemName = currentRequisition.getString("ItemName");
                String Quantity = currentRequisition.getString("Quantity");

                RequisitionClass_Clerk requisition = new RequisitionClass_Clerk(DeptName,ItemName, Quantity);
                requisitions.add(requisition);
            }

        } catch (JSONException e) {

            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        return requisitions;
    }


    public static List<RequisitionClass_Clerk> fetchRequisitionData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<RequisitionClass_Clerk> requisitions = extractFeatureFromJson(jsonResponse);


        return requisitions;
    }

    public static List<DisbursementDetailsClass_Clerk> getDisbursementDetails(String reqId) {

        if (TextUtils.isEmpty(reqId)) {
            return null;
        }

        List<DisbursementDetailsClass_Clerk> disbursements = new ArrayList<>();


        try {
            JSONArray baseJsonArray = JSONParser.getJSONArrayFromUrl(SAMPLE_JSON_RESPONSE+reqId);
            //JSONArray baseJsonArray = JSONParser.getJSONArrayFromUrl(host+"/1");

            for (int i = 0; i < baseJsonArray.length(); i++) {

                JSONObject currentRequisition = baseJsonArray.getJSONObject(i);
                String DeptName  = currentRequisition.getString("DeptName");
                String ItemName  = currentRequisition.getString("ItemName");
                String Quantity =  currentRequisition.getString("Quantity");

                DisbursementDetailsClass_Clerk disbursement = new DisbursementDetailsClass_Clerk(DeptName,ItemName,Quantity);
                disbursements.add(disbursement);
            }

        } catch (JSONException e) {

            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

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
