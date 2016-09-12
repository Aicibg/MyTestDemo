package com.dhao.mytestdemo.permission;

/**
 * Created by DongHao on 2016/9/12.
 * Description:
 */
public interface PermissionListener {
     void denied(String[] permission);

     void granted(String[] permission);
}
