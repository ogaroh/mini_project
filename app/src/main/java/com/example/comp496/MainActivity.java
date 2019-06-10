package com.example.comp496;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.comp496.Adapter.Translations_Adapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText word_to_translate;
    Button button_translate;
    Button button_exit;
    RecyclerView recyclerView;
    Translations_Adapter translations_adapter;
    List<String> wordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        word_to_translate = findViewById(R.id.word);
        button_translate = findViewById(R.id.translate);
        button_exit = findViewById(R.id.exit);

        wordList = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        button_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button_translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromEnglish = word_to_translate.getText().toString();
                if (TextUtils.isEmpty(fromEnglish)){
                    Toast.makeText(MainActivity.this,"Please enter a word",Toast.LENGTH_SHORT).show();
                    return;
                }
                List<String> listOfWords = Translate(fromEnglish);
                translations_adapter.addList(listOfWords);
            }
        });
    }
    private List<String> Translate(String word) {
        String line;
        List<String> wordList = new ArrayList<>();
        try {
            InputStream inputStream = getAssets().open("db.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while ((line = bufferedReader.readLine()) != null) {
                List<String> words = Arrays.asList(line.split(","));
                if (words.size() == 2) {
                    if (word.toLowerCase().equals(words.get(1).toLowerCase())) {
                        wordList.add(words.get(0));
                    }
                }
                if (wordList.size() == 10) {
                    break;
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        translations_adapter = new Translations_Adapter(this,wordList);
        recyclerView.setAdapter(translations_adapter);
        return wordList;
    }
}

