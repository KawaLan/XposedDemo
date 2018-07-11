package com.kawa.xposeddemo.model;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/****
 * <pre>
 *  Project_Name:    XposedDemo
 *  Created:         Kawa on 2018/6/22 16:47.
 *  Desc:            模块（如果要引入此模块的话，就在assets/xposed_init 里面写入此模块的路径就行）
 * </pre>            路径：com.kawa.xposeddemo.model.XposedTest
 ****/
public class XposedTest implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log(">>>>>>>>>>>>>>>>>>>>>>>>");

        XposedBridge.log("输出包名: " + lpparam.packageName);
    }
}
