package com.kawa.xposeddemo.model;

import android.view.View;
import android.widget.EditText;

import java.lang.reflect.Field;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/****
 * <pre>
 *  Project_Name:    XposedDemo
 *  Copyright:       Copyright  2012-2018 G-emall Technology Co.,Ltd
 *  Created:         Kawa on 2018/6/22 16:47.
 *  E-mail:          zhenlin.liang@g-emall.com
 *  Desc:            模块（如果要引入此模块的话，就在assets/xposed_init 里面写入此模块的路径就行）
 * </pre>            路径：com.kawa.xposeddemo.model.XposedTest
 ****/
public class XposedOnclickTest implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log(">>>>>>>>>>>>>>>>>>>>>>>>");
        if (lpparam.packageName.equals("com.gatewang.bs.transaction")) {
            XposedBridge.log("已经进来指定的app进行拦截！");
            Class clazz = lpparam.classLoader.loadClass("com.gatewang.bs.transaction.ui.activity.LoginActivity");
            XposedHelpers.findAndHookMethod(clazz, "onClick", View.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Class clazz = param.thisObject.getClass();
                    // 通过类的字节码得到该类中声明的所有属性，无论私有或公有
                    Field field = clazz.getDeclaredField("accEditText");
                    field.setAccessible(true);
                    EditText username = (EditText) field.get(param.thisObject);
                    // 设置账号
                    XposedBridge.log("name = " + username.getText().toString());
                }
            });
        }
    }
}
