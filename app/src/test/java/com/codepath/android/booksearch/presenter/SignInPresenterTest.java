package com.codepath.android.booksearch.presenter;


import com.codepath.android.booksearch.view.SignInView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SignInPresenterTest {

    private SignInPresenter presenter;

    @Mock
    SignInView view;

    @Before
    public void setUp() {
        presenter = new SignInPresenter(view);
    }

    @Test
    public void shouldShowErrorMessageWhenCredentialsAreInvalid() {
        presenter.signInWith("an invalid login id", "a password");

        Mockito.verify(view).showError();
    }

}