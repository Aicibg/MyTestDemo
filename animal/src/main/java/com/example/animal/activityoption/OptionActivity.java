package com.example.animal.activityoption;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.animal.R;

public class OptionActivity extends AppCompatActivity {
    private Button btStart;
    private ImageView ivStart,ivStart2;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        btStart = (Button) findViewById(R.id.btn_start);
        ivStart= (ImageView) findViewById(R.id.iv_start);
        ivStart2= (ImageView) findViewById(R.id.iv_start2);
        mTextView= (TextView) findViewById(R.id.tv_start2);
        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityOptionsCompat compat = ActivityOptionsCompat.makeCustomAnimation(OptionActivity.this, R.anim.translate_in
                        , R.anim.translate_none);
                ActivityCompat.startActivity(OptionActivity.this, new Intent(OptionActivity.this, Option2Activity.class), compat.toBundle());
            }
        });

        ivStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              launch2(view);
            }
        });

        ivStart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launch4(view);
            }
        });
    }

    public void launch(View view){
        ActivityOptionsCompat compat=ActivityOptionsCompat.makeScaleUpAnimation(view,
                view.getMeasuredWidth()/2,view.getMeasuredHeight()/2,0,0);
        ActivityCompat.startActivity(OptionActivity.this,new Intent(OptionActivity.this,Option2Activity.class),
                compat.toBundle());
    }

    public void launch3(View view){
        ActivityOptionsCompat compat=ActivityOptionsCompat.makeSceneTransitionAnimation(this,
               view,getString(R.string.image));
        ActivityCompat.startActivity(OptionActivity.this,new Intent(OptionActivity.this,Option2Activity.class),
                compat.toBundle());
    }

    public void launch4(View view){
        Pair<View, String> imagePair= Pair.create((View)ivStart2,getString(R.string.image));
        Pair<View,String> textPair= Pair.create((View)mTextView,getString(R.string.name));
        ActivityOptionsCompat compat=ActivityOptionsCompat.makeSceneTransitionAnimation(this,imagePair,textPair);
        ActivityCompat.startActivity(OptionActivity.this,new Intent(OptionActivity.this,Option2Activity.class),
                compat.toBundle());
    }

    public void launch2(View view){
        Bitmap bitmap= BitmapFactory.decodeResource(this.getResources(),R.mipmap.image);
        ActivityOptionsCompat compat=ActivityOptionsCompat.makeThumbnailScaleUpAnimation(view,bitmap,
                view.getMeasuredWidth()/2,view.getMeasuredHeight()/2);
        ActivityCompat.startActivity(OptionActivity.this,new Intent(OptionActivity.this,Option2Activity.class),
                compat.toBundle());
        bitmap.recycle();
    }
}
