package com.dhao.mytestdemo.permission;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by DongHao on 2016/9/12.
 * Description:
 */
public class BasePermissionActivity extends AppCompatActivity {
    protected final static int REQUEST_CODE = 1223;
    private PermissionListener mListener;


    public void checkSignlePermission(final String permissionID, PermissionListener listener) {
        mListener = listener;
        //此权限是否已经获取
        if (ContextCompat.checkSelfPermission(this, permissionID) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissionID)) {
                //用户拒绝过这个权限，应该提示用户为什么需要此权限
                new AlertDialog.Builder(this)
                        .setTitle("友好提醒")
                        .setMessage("我们真的需要此权限，没有此权限我们将无法为你工作")
                        .setPositiveButton("好，给你", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                // 用户同意继续申请。
                                ActivityCompat.requestPermissions(BasePermissionActivity.this, new String[]{permissionID}, REQUEST_CODE);
                            }
                        })
                        .setNegativeButton("我拒绝", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                // 用户拒绝申请。
                            }
                        }).show();
            } else {
                //申请权限
                ActivityCompat.requestPermissions(this, new String[]{permissionID}, REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //权限被用户同意
                    mListener.granted(permissions);
//                      Toast.makeText(BasePermissionActivity.this, "用户同意", Toast.LENGTH_SHORT).show();
                } else {
                    //权限被用户拒绝
                    mListener.denied(permissions);
//                      Toast.makeText(BasePermissionActivity.this, "用户拒绝", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
