package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityPdfreadBinding;
import com.example.myapplication.ui.model.StoriesModel;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class PDFReadActivity extends AppCompatActivity {

    ActivityPdfreadBinding activityPdfreadBinding;
    Intent intent;
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPdfreadBinding = ActivityPdfreadBinding.inflate(getLayoutInflater());
        setContentView(activityPdfreadBinding.getRoot());
        setSupportActionBar(activityPdfreadBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        activityPdfreadBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        StoriesModel storiesModel = (StoriesModel) intent.getSerializableExtra("Stories");
        getSupportActionBar().setTitle(storiesModel.getName());
        new RetrievePDFfromUrl(activityPdfreadBinding.idPDFView).execute(storiesModel.getUrl());

    }

}
class RetrievePDFfromUrl extends AsyncTask<String, Void, InputStream> {
    PDFView pdfView;
    public  RetrievePDFfromUrl(PDFView pdfView){
        this.pdfView =pdfView;
    }

    @Override
    protected InputStream doInBackground(String... strings) {
        // we are using inputstream
        // for getting out PDF.
        InputStream inputStream = null;
        try {
            URL url = new URL(strings[0]);
            // below is the step where we are
            // creating our connection.
            HttpURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == 200) {
                // response is success.
                // we are getting input stream from url
                // and storing it in our variable.
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
            }

        } catch (IOException e) {
            // this is the method
            // to handle errors.
            e.printStackTrace();
            return null;
        }
        return inputStream;
    }

    @Override
    protected void onPostExecute(InputStream inputStream) {
        // after the execution of our async
        // task we are loading our pdf in our pdf view.
        pdfView.fromStream(inputStream).load();
    }
}
