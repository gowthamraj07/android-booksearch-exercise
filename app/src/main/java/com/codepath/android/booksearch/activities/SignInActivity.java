package com.codepath.android.booksearch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.codepath.android.booksearch.R;
import com.codepath.android.booksearch.presenter.SignInPresenter;
import com.codepath.android.booksearch.view.SignInView;

public class SignInActivity extends AppCompatActivity implements SignInView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Button btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignInPresenter presenter = new SignInPresenter(SignInActivity.this);
                Intent intent = new Intent(SignInActivity.this, BookListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showError() {

    }

    @Override
    public void navigateToBookList() {

    }
}