package com.codepath.android.booksearch.presenter;

import com.codepath.android.booksearch.view.SignInView;

public class SignInPresenter {
    private SignInView view;

    public SignInPresenter(SignInView view) {
        this.view = view;
    }

    public void signInWith(String loginId, String password) {

        if ("admin".equals(loginId) && "admin".equals(password)) {
            view.hideError();
            view.navigateToBookList();
        }
        view.showError();
    }
}
