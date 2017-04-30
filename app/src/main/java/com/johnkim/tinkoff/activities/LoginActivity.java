package com.johnkim.tinkoff.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.johnkim.tinkoff.R;
import com.johnkim.tinkoff.views.ProgressButton;

public class LoginActivity extends AppCompatActivity {

    private EditText login;
    private EditText password;
    private ProgressButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (EditText) findViewById(R.id.edit_text_login);
        password = (EditText) findViewById(R.id.edit_text_password);
        button = (ProgressButton) findViewById(R.id.btn_enter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Login().execute(login.getText().toString(), password.getText().toString());
            }
        });
    }

    private void startNextScreen(String id) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("LOGIN", id);
        startActivity(intent);
    }

    public void showProgress() {
        button.showProgress();
    }

    public void hideProgress() {
        button.hideProgress();
    }

    class Login extends AsyncTask<String, Void, Boolean> {
        String id, password;
        @Override
        protected Boolean doInBackground(String... args) {
            //emulating slow internet (rotate device NOW)
            try {
                Thread.sleep(2000);
            }catch (InterruptedException ex){

            }

            if(args[0] != null || args[1] != null){
                id = args[0];
                password = args[1];
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            hideProgress();

            if (success) {
                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("id", id);
                editor.putString("password", password);
                editor.apply();

                startNextScreen(id);
            }
        }

        @Override
        protected void onPreExecute() {
                showProgress();
        }
    }


}