package com.example.y.progressview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.y.progressview.view.ProgressBarView;

public class MainActivity extends AppCompatActivity {


    private ProgressHandler progressHandler = new ProgressHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.btn);
        final ProgressBarView progressBarView = (ProgressBarView) findViewById(R.id.view);

        assert button != null;
        assert progressBarView != null;
        progressBarView.setPercent(false);
        progressBarView.setStyle(0);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressHandler.sendEmptyMessageDelayed(ProgressHandler.UPDATE, ProgressHandler.TIME);
            }
        });


        progressHandler.setProgress(new ProgressHandler.Progress() {
            @Override
            public void setSchedule(int schedule) {
                progressBarView.setCurrentProgress(schedule);
            }

            @Override
            public void onSuccess() {
            }
        });


    }
}
