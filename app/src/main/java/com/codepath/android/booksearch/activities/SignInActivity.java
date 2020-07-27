package com.codepath.android.booksearch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.codepath.android.booksearch.R;
import com.codepath.android.booksearch.presenter.SignInPresenter;
import com.codepath.android.booksearch.view.SignInView;

public class SignInActivity extends AppCompatActivity implements SignInView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        final SignInPresenter presenter = new SignInPresenter(SignInActivity.this);
        final EditText userName = findViewById(R.id.tvUserName);
        final EditText password = findViewById(R.id.tvPassword);
        Button btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.signInWith(userName.getText().toString(), password.getText().toString());
            }
        });
    }

    @Override
    public void showError() {

    }

    @Override
    public void navigateToBookList() {
        Intent intent = new Intent(SignInActivity.this, BookListActivity.class);
        startActivity(intent);
    }
}