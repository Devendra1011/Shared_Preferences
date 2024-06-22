package com.example.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    Button saveMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editText = findViewById(R.id.edit_text);
        textView = findViewById(R.id.text_view);
        saveMe = findViewById(R.id.btn_save);

        DisplaySavedText();

        saveMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredText = editText.getText().toString();


                DisplayAndSaveText(enteredText);
            }
        });
    }

    private void DisplaySavedText() {
        // Retreving the value from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        String s1 = sharedPreferences.getString("name","");
        textView.setText(s1);

    }

    private void DisplayAndSaveText(String enteredText) {
        //Display the text
        textView.setText(enteredText);


        // Saving the text into shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

        // Writing the data to shared preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();


        editor.putString("name",enteredText);
        editor.commit();




    }
}