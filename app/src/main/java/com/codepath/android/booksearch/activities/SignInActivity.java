package com.codepath.android.booksearch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.codepath.android.booksearch.R;
import com.codepath.android.booksearch.presenter.SignInPresenter;
import com.codepath.android.booksearch.view.SignInView;

public class SignInActivity extends AppCompatActivity implements SignInView {

    private TextView invalidCredentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        invalidCredentials = findViewById(R.id.tvErrorMessage);
        invalidCredentials.setVisibility(View.GONE);
        final SignInPresenter presenter = new SignInPresenter(SignInActivity.this);
        final EditText userName = findViewById(R.id.et_email);
        final EditText password = findViewById(R.id.et_password);

        userName.setText("admin");
        password.setText("admin");

        Button btnSignIn = findViewById(R.id.btn_login);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.signInWith(userName.getText().toString(), password.getText().toString());
            }
        });
    }

    @Override
    public void showError() {
        invalidCredentials.setVisibility(View.VISIBLE);
    }

    @Override
    public void navigateToBookList() {
        Intent intent = new Intent(SignInActivity.this, BookListActivity.class);
        startActivity(intent);
    }

    @Override
    public void hideError() {
        invalidCredentials.setVisibility(View.GONE);
    }
}