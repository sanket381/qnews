package com.example.qnews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class detailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = (ImageView) findViewById(R.id.dimage);
        TextView title = (TextView) findViewById(R.id.dtitle);
        TextView author= (TextView) findViewById(R.id.dauthor);

        Bundle bundle = getIntent().getExtras();

        String mTitle= bundle.getString("title");
        String mImage = bundle.getString("img");
        String  mAuthor = bundle.getString("author");

        Glide.with(this).load(mImage).into(imageView);
        title.setText(mTitle);
        author.setText(mAuthor);

    }
}