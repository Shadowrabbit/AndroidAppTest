package com.example.myapplication.DataCenter;

/**
 * sql语句相关定义
 */
public class SqlDef {
    public static String RescuerTableName = "rescuer"; //创建救援者表
    public static String CreateRescuerTable = "create table " + RescuerTableName + " (sid integer primary key autoincrement, name varchar(20) not null, age integer not null, desc varchar(200))"; //创建救援者表
    public static String QueryAllRescuer = "select * from " + RescuerTableName; //查询所有救援者信息
}
