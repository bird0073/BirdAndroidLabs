package com.example.drbir.birdandroidlabs;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.os.AsyncTask;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

/**
 * Created by drbir on 3/20/2018.
 */

public class WeatherForecast extends Activity {

    String windSpeed, minTemp, maxTemp, currTemp, iconName;
    Bitmap weatherPic;

    TextView minTempTV;
    TextView maxTempTV;
    TextView currTempTV;
    TextView windSpeedTV;
    ImageView weatherImageIV;
    ProgressBar weatherProgress;


    protected static final String ACTIVITY_NAME = "WeatherForcast";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weatherforecast);
        minTempTV = findViewById(R.id.minTemp);
        maxTempTV = findViewById(R.id.maxTemp);
        currTempTV = findViewById(R.id.currTemp);
        windSpeedTV = findViewById(R.id.windSpeed);
        weatherImageIV = findViewById(R.id.weatherImage);
        weatherProgress = (ProgressBar) findViewById( R.id.weatherProgress);


        weatherProgress.setMax(100);
        Log.i(ACTIVITY_NAME, "onCreate");
        weatherProgress.setVisibility(View.VISIBLE);

        String urlLink = "http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric";
        ForecastQuery fq = new ForecastQuery();
        fq.execute(urlLink);

    }


    public class ForecastQuery extends AsyncTask<String, Integer, String>{

        public String doInBackground(String ... args)
        {
            for(String siteUrl: args)
            {
                try {
                    publishProgress( 0 );

                    URL url = new URL(siteUrl);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream inStream = urlConnection.getInputStream();


                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    factory.setNamespaceAware(false);
                    XmlPullParser xpp = factory.newPullParser();
                    xpp.setInput( inStream  , "UTF-8");

                    int eventType = xpp.getEventType();
                    while(eventType != XmlPullParser.END_DOCUMENT)
                    {
                        switch(eventType)
                        {
                            case XmlPullParser.START_TAG:
                                String tagName = xpp.getName();
                                if(tagName.equals("speed"))
                                {
                                    windSpeed = xpp.getAttributeValue(null, "name");
                                    Log.i("windSpeed:", windSpeed);
                                    //weatherProgress.setProgress(75);
                                            publishProgress( 75 );

                                }
                                if(tagName.equals("temperature"))
                                {
                                    minTemp = xpp.getAttributeValue(null, "min");
                                    Log.i("min:", minTemp);
                                    weatherProgress.setProgress(25);
                                    publishProgress( 25 );
                                    maxTemp = xpp.getAttributeValue(null, "max");
                                    Log.i("max:", maxTemp);
                                    weatherProgress.setProgress(50);
                                    publishProgress( 50 );
                                    currTemp = xpp.getAttributeValue(null, "value");
                                    Log.i("current:", currTemp);

                                    minTempTV.append(minTemp);
                                    maxTempTV.append(maxTemp);
                                    currTempTV.append(currTemp);

                                }
                                if(tagName.equals("weather"))
                                {
                                    iconName = xpp.getAttributeValue(null, "icon");
                                    String ImageURL = "http://openweathermap.org/img/w/" + iconName + ".png";
                                    weatherProgress.setProgress(100);
                                    publishProgress( 100 );


                                    //***
                                    Log.i("FileName: ", iconName + ".png");
                                    if(fileExistance(iconName + ".png")){
                                        Log.i("File Found: ", "Locally");
                                        FileInputStream fis = null;
                                        try {    fis = openFileInput(iconName + ".png");   }
                                        catch (FileNotFoundException e) {    e.printStackTrace();  }
                                        weatherPic = BitmapFactory.decodeStream(fis);

                                    }else {
                                        Log.i("File: ", "Downloaded");
                                        FileOutputStream outputStream = openFileOutput
                                                ( iconName + ".png", Context.MODE_PRIVATE);
                                        weatherPic = HttpUtils.getImage(ImageURL);
                                        if (weatherPic != null) {
                                            weatherPic.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                                        }
                                        outputStream.flush();
                                        outputStream.close();
                                    }
                                    windSpeedTV.append(windSpeed);
                                    weatherImageIV.setImageBitmap(weatherPic);
                                    //***
                                }
                                //Log.i("Found tag:", tagName);
                                break;
                        }
                        xpp.next();
                        eventType = xpp.getEventType();
                    }

                }catch (Exception mfe)
                {
                    Log.e("Exception:", mfe.getMessage());
                }

            }
            Log.i(ACTIVITY_NAME, "Finished Downloading");
            return "Finished downloading";
        }
    }

    public void onProgressUpdate(int value){
        weatherProgress.setVisibility(VISIBLE);
        weatherProgress.setProgress(value);
        Log.i(ACTIVITY_NAME, "onProgressUpdate");
    }

    public void onPostExecute(String result){
//        minTempTV.setText(minTemp);
//        maxTempTV.setText(maxTemp);
//        currTempTV.setText(currTemp);
//        windSpeedTV.setText(windSpeed);
//        weatherImageIV.setImageBitmap(weatherPic);

        Log.i(ACTIVITY_NAME, "onPostExecute");

        weatherProgress.setVisibility(INVISIBLE);
    }
    public boolean fileExistance(String fname){
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();   }


    @Override
    protected void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME, "onStart");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME, "onPause");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME, "onResume");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME, "onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "onDestroy");
    }
}

//**********************************************************
class HttpUtils {
    private static Bitmap getImage(URL url) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                return BitmapFactory.decodeStream(connection.getInputStream());
            } else
                return null;
        } catch (Exception e) {
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    public static Bitmap getImage(String urlString) {
        try {
            URL url = new URL(urlString);
            return getImage(url);
        } catch (MalformedURLException e) {
            return null;
        }
    }

}


//************************************************************