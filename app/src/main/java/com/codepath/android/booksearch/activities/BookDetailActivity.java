package com.codepath.android.booksearch.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.codepath.android.booksearch.R;

import static com.codepath.android.booksearch.activities.BookListActivity.AUTHOR;
import static com.codepath.android.booksearch.activities.BookListActivity.COVER_URL;
import static com.codepath.android.booksearch.activities.BookListActivity.TITLE;

public class BookDetailActivity extends AppCompatActivity {
    private ImageView ivBookCover;
    private TextView tvTitle;
    private TextView tvAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        Bundle bundle = getIntent().getExtras();
        String title = bundle.getString(TITLE);
        String author = bundle.getString(AUTHOR);
        String coverUrl = bundle.getString(COVER_URL);

        // Fetch views
        ivBookCover = (ImageView) findViewById(R.id.ivBookCover);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvAuthor = (TextView) findViewById(R.id.tvAuthor);

        tvTitle.setText(title);
        tvAuthor.setText(author);

        Glide
                .with(this)
                .load(coverUrl)
                .centerCrop()
                .placeholder(R.drawable.books_background)
                .into(ivBookCover);


        // Extract book object from intent extras

        // Use book object to populate data into views
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_detail, menu);
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
