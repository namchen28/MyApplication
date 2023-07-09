package com.example.myapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.myapplication.databinding.ActivityTranslateBinding;
import com.example.myapplication.ui.feature.TranslateAPI;

import org.checkerframework.checker.units.qual.A;

import java.util.List;
import java.util.Locale;
import java.util.UUID;


public class TranslateActivity extends AppCompatActivity {
    ActivityTranslateBinding activityTranslateBinding;
    TextToSpeech text;
    String[] lFrom = {"English","France","Vietnamese"};
    String[] lTo = {"English","France","Vietnamese"};
    String[] languageFrom = {"en","fr","vi"};
    String[] languageTo = {"en","fr","vi"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTranslateBinding  = ActivityTranslateBinding.inflate(getLayoutInflater());
        setContentView(activityTranslateBinding.getRoot());
        initToolbar();
        initLG();
        handlerEvents();
    }

    private void initLG() {
        ArrayAdapter arrayAdapterFrom = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,lFrom);
        activityTranslateBinding.chooseSourceLanguage.setAdapter(arrayAdapterFrom);
        ArrayAdapter arrayAdapterTo = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,lTo);
        activityTranslateBinding.chooseTargetLanguage.setAdapter(arrayAdapterTo);

    }

    private void handlerEvents() {
        activityTranslateBinding.translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = activityTranslateBinding.sourceLanguageET.getText().toString();

                if(text.length()>0){
                    translate(text,languageFrom[activityTranslateBinding.chooseSourceLanguage.getSelectedItemPosition()],
                           languageTo[activityTranslateBinding.chooseTargetLanguage.getSelectedItemPosition()]);
                }

            }
        });
        activityTranslateBinding.copySrcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", activityTranslateBinding.sourceLanguageET.getText().toString());
                clipboard.setPrimaryClip(clip);
            }
        });
        activityTranslateBinding.copyTargetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", activityTranslateBinding.targetLanguageET.getText().toString());
                clipboard.setPrimaryClip(clip);
            }
        });
        activityTranslateBinding.voiceSrcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textSrc = activityTranslateBinding.sourceLanguageET.getText().toString();
                if(textSrc.length()>0){
                    TextToSpeechLanguage("en",textSrc);
                }
            }
        });
        activityTranslateBinding.voiceTargetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textSrc = activityTranslateBinding.targetLanguageET.getText().toString();
                if(textSrc.length()>0){
                    TextToSpeechLanguage("fr",textSrc);
                }
            }
        });
        activityTranslateBinding.recordSrcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displaySpeechRecognizer();
            }
        });
    }

    private void initToolbar() {
        setSupportActionBar(activityTranslateBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Translate");
        activityTranslateBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    void TextToSpeechLanguage(String language,String word){
         text = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    if(language.equalsIgnoreCase("en")){
                        int toSpeak  = text.setLanguage(Locale.ENGLISH);
                        if(toSpeak == TextToSpeech.LANG_MISSING_DATA){

                        }else if(toSpeak == TextToSpeech.LANG_NOT_SUPPORTED){

                        }else{
                            text.setVoice(text.getVoice());
                            String utteranceId = UUID.randomUUID().toString();
                            text.speak(word, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
                        }
                    }else{

                    }

                }

            }
        });

    }

    private void initTrans() {

    }

    private  void translate(String text,String from,String to){
        TranslateAPI translateAPI = new TranslateAPI(
                from,   //Source Language
                to,         //Target Language
                text);           //Query Text

        translateAPI.setTranslateListener(new TranslateAPI.TranslateListener() {
            @Override
            public void onSuccess(String translatedText) {
               activityTranslateBinding.targetLanguageET.setText(translatedText);

            }

            @Override
            public void onFailure(String ErrorText) {
                activityTranslateBinding.targetLanguageET.setText(ErrorText);

            }
        });




    }
    private void displaySpeechRecognizer() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
// This starts the activity and populates the intent with the speech text.
        startActivityForResult(intent, 123);
    }

    // This callback is invoked when the Speech Recognizer returns.
// This is where you process the intent and extract the speech text from the intent.
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == 123 && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            activityTranslateBinding.sourceLanguageET.setText(spokenText);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
