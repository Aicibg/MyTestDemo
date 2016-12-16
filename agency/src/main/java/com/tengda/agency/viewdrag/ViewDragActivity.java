package com.tengda.agency.viewdrag;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tengda.agency.R;

public class ViewDragActivity extends AppCompatActivity {
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drag);
        mTextView= (TextView) findViewById(R.id.tv_text);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextView.offsetLeftAndRight(70);
                Log.e("MyTest--->","tvLeft="+mTextView.getLeft());
            }
        });
    }
}
