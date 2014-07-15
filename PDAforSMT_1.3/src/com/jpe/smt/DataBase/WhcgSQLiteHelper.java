package com.jpe.smt.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WhcgSQLiteHelper extends SQLiteOpenHelper {
	private static final String dbname = "whcg_android";
	private static final int dbversion = 1;

	public WhcgSQLiteHelper(Context paramContext,
			SQLiteDatabase.CursorFactory paramCursorFactory) {
		super(paramContext, dbname, null, dbversion);
	}

	public void onCreate(SQLiteDatabase paramSQLiteDatabase) {
		// 用户信息表 包含：
		// 手机号(作为id关联到投诉表)、昵称、用户名、用户email、性别、年龄、身份证号码、工作、教育、地址、反馈途径、用户id、
		// 是否登陆在线 、积分信息
		paramSQLiteDatabase
				.execSQL("CREATE TABLE table_user(phone VARCHAR(20),nick VARCHAR(20) , name VARCHAR(20),email VARCHAR(20), gender VARCHAR(20),age VARCHAR(20),pid VARCHAR(20),job VARCHAR(20),education VARCHAR(20),address VARCHAR(20),feedback VARCHAR(20),userid VARCHAR(20),islogin integer,score integer)");
		// 案件投诉表
		// 包含：投诉编号id、投诉人手机号、投诉类型名称、投诉内容、区域、投诉人经度、投诉人纬度、投诉人地址街道名称、关联的附件信息id、投诉时间、投诉类型（1、问题上报
		// 2、违建上报 3、市容环境 4、园林绿化）、案件处理状态 （1、未受理 2、待处理 3、处理中 4、已处理）、案件处理时间、处理人名称、
		paramSQLiteDatabase
				.execSQL("CREATE TABLE table_topic(id integer primary key autoincrement,phone VARCHAR(20),subject VARCHAR(20),body VARCHAR(20),area VARCHAR(10),lat VARCHAR(20),lng VARCHAR(20),place VARCHAR(30),attachid integer,time VARCHAR(20),mode VARCHAR(20),status integer,ctime VARCHAR(20),cname VARCHAR(20))");
		// 附件信息表 通过attachId 与投诉表进行关联
		paramSQLiteDatabase
				.execSQL("CREATE TABLE table_attach(id integer primary key autoincrement,imageUrl varchar(50),attachId integer)");
		// 案件处理评论表 （为案件处理情况打分用 暂时没用到）
		paramSQLiteDatabase
				.execSQL("CREATE TABLE table_Comment(topicid NVARCHAR(20),body NVARCHAR(20),name NVARCHAR(20),nick NVARCHAR(20),phone NVARCHAR(20),time NVARCHAR(20),level NVARCHAR(20),score1 NVARCHAR(20),score2 NVARCHAR(20),score3 NVARCHAR(20))");
		// 是否是第一次登陆判断
		paramSQLiteDatabase
				.execSQL("CREATE TABLE first_load(isFirstLoad integer)");

	}

	public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1,
			int paramInt2) {
		paramSQLiteDatabase.execSQL("drop table if exists table_user");
		paramSQLiteDatabase.execSQL("drop table if exists table_topic");
		paramSQLiteDatabase.execSQL("drop table if exists table_Comment");
		paramSQLiteDatabase.execSQL("drop table if exists first_load");
		onCreate(paramSQLiteDatabase);
	}
}