package com.airletnet.mobile.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airletnet.mobile.pulltorefresh.PullToRefreshLayout;
import com.airletnet.mobile.pulltorefresh.PullableRecyclerView;

/**
 * Created by lig on 2018/4/10.
 */

public class PullToRefreshActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulltorefresh);

        PullToRefreshLayout pullRefreshLayout = findViewById(R.id.pullRefreshLayout);
        PullableRecyclerView pullRecyclerView = findViewById(R.id.pullRecyclerView);
        pullRecyclerView.setEmptyView(pullRefreshLayout.getEmptyView());
        pullRefreshLayout.setEmptyInfo("122","1212","sdfsdffdsdfsdfsd");
        pullRecyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new RecyclerView.ViewHolder(new TextView(parent.getContext())) {
                    @Override
                    public String toString() {
                        return super.toString();
                    }
                };
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ( (TextView)holder.itemView).setText(""+position);
            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });
    }

}
