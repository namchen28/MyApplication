package com.example.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.databinding.ActivityDictionaryBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

public class DictionaryActivity extends AppCompatActivity {

    ActivityDictionaryBinding activityDictionaryBinding;
    String api ="https://api.dictionaryapi.dev/api/v2/entries/en/";
     RequestQueue volley;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDictionaryBinding = ActivityDictionaryBinding.inflate(getLayoutInflater());
        setContentView(activityDictionaryBinding.getRoot());
        setSupportActionBar(activityDictionaryBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Dictionary");
        activityDictionaryBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        volley= Volley.newRequestQueue(this);
        activityDictionaryBinding.searchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    String text = activityDictionaryBinding.searchBar.getText().toString();
                    search(text);
                }
                return  true;
            }
        });

    }

    private void search(String text) {
        if(text.length()>0){
            if(!text.contains(" ")){
                StringRequest stringRequest = new StringRequest(Request.Method.GET, api + text.trim(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            String partofSpeech ="";
                            String Definition ="";
                            String Synonyms ="";
                            String antonyms ="";
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            String word = jsonObject.getString("word");
                            String pronounce = jsonObject.getString("phonetic");
                            JSONArray jsonArrayMeanings = new JSONArray(jsonObject.getString("meanings"));

                            if(jsonObject.has("meanings")){
                                for(int j = 0 ;j< jsonArrayMeanings.length();j++){
                                    JSONObject jsonObject1 = jsonArrayMeanings.getJSONObject(j);
                                    partofSpeech+=jsonObject1.getString("partOfSpeech")+",";
                                    if(jsonObject1.has("definitions")){
                                        JSONArray jsonArraydefinitions = new JSONArray(jsonObject1.getString("definitions"));
                                        for(int k =0 ; k<jsonArraydefinitions.length();k++){
                                            Definition+= jsonArraydefinitions.getJSONObject(k).getString("definition")+"\n ";


                                        }

                                        activityDictionaryBinding.txtDefinition.setText(Definition);


                                    }
                                    if(jsonObject1.has("synonyms")){

                                        List<String> list = Collections.singletonList(jsonObject1.getString("synonyms"));

                                        for(String s :list){
                                            Synonyms+=s+",";
                                        }
                                        Log.d("CHECKED",Synonyms);
                                        activityDictionaryBinding.txtsynonyms.setText(Synonyms);


                                        activityDictionaryBinding.txtDefinition.setText(Definition);



                                    }
                                    if(jsonObject1.has("antonyms")){

                                        List<String> list = Collections.singletonList(jsonObject1.getString("antonyms"));

                                        for(String s :list){
                                            antonyms+=s+",";
                                        }
                                        Log.d("CHECKED",antonyms);
                                        activityDictionaryBinding.txtAntonyms.setText(antonyms);




                                    }


                                    activityDictionaryBinding.txtpartOfSpeech.setText(partofSpeech);

                                }
                                //activityDictionaryBinding.txtpartOfSpeech.setText(partOfSpeech);

                            }

                            activityDictionaryBinding.txtWord.setText(word);
                            activityDictionaryBinding.txtPronounce.setText(pronounce);

                            Log.d("CHECKED", jsonObject.get("word")+"");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                volley.add(stringRequest);
            }else{

            }

        }




    }
}