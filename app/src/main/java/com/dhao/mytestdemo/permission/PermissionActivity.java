package com.dhao.mytestdemo.permission;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dhao.mytestdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PermissionActivity extends BasePermissionActivity {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        ButterKnife.bind(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSignlePermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, new PermissionListener() {
                    @Override
                    public void denied(String[] permission) {
                        Toast.makeText(PermissionActivity.this, "用户拒绝存储权限", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void granted(String[] permission) {
                        Toast.makeText(PermissionActivity.this, "用户同意存储权限", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSignlePermission(Manifest.permission.WRITE_CONTACTS, new PermissionListener() {
                    @Override
                    public void denied(String[] permission) {
                        Toast.makeText(PermissionActivity.this, "用户拒绝联系人权限", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void granted(String[] permission) {
                        Toast.makeText(PermissionActivity.this, "用户同意联系人权限", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
