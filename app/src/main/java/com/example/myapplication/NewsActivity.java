package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private EditText mEditText;
    private Button mFetchFeedButton;
    private TextView mFeedTitleTextView;
    private TextView mFeedLinkTextView;
    private TextView mFeedDescriptionTextView;

    //private List<RssFeedModel> mFeedModelList;
    private String mFeedTitle;
    private String mFeedLink;
    private String mFeedDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

//        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        mEditText = (EditText) findViewById(R.id.rssFeedEditText);
//        mFetchFeedButton = (Button) findViewById(R.id.fetchFeedButton);
//        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
//        mFeedTitleTextView = (TextView) findViewById(R.id.feedTitle);
//        mFeedDescriptionTextView = (TextView) findViewById(R.id.feedDescription);
//        mFeedLinkTextView = (TextView) findViewById(R.id.feedLink);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

//        mFetchFeedButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new FetchFeedTask().execute((Void) null);
//            }
//        });
//        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                new FetchFeedTask().execute((Void) null);
//            }
//        });
    }
}
