package com.thotael.webinar1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstActivity extends AppCompatActivity {

    public static final String TEXT_PARAM = "enteredText";
    public static final String PREFERENCES_NAME = "my_config";
    public static final String PREF_TEXT = "text";

    private SharedPreferences preferences;

    private EditText editText;
    private Button button;
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        initControls();
        setupListeners();
    }

    private void initControls() {
        preferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);

        button = (Button) findViewById(R.id.send_button);
        exitButton = (Button) findViewById(R.id.exit_button);
        editText = (EditText) findViewById(R.id.edit_field);

        if (preferences.contains(PREF_TEXT)) {
            editText.setText(preferences.getString(PREF_TEXT, ""));
        }
    }

    private void setupListeners() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSecondWindow();
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void openSecondWindow() {
        Log.d("FirstActivity", "OnClick go on...");
        String retrievedText = editText.getText().toString();

        preferences.edit().putString(PREF_TEXT, retrievedText).commit();

        Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
        intent.putExtra(TEXT_PARAM, retrievedText);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
