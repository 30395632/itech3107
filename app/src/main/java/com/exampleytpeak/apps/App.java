package com.exampleytpeak.apps;

import android.app.Application;
import android.content.Context;

import org.xutils.DbManager;
import org.xutils.x;

import java.util.Date;


/**

 */
public class App extends Application {
    // 全局上下文
    private static Context context;
    public static DbManager dbManager;//数据库存储


    static DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbVersion(1)
            .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                @Override
                public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                }
            });

    public App() {

    }

    /**
     * 获取全局上下文
     */
    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        context = this.getApplicationContext();
        super.onCreate();
        x.Ext.init(this);
        //x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
        if (dbManager == null) {
            dbManager = x.getDb(daoConfig);
        }
        Date date = new Date();
        //file = new File("/storage/emulated/0/bean"+"log.log");
    }
}
