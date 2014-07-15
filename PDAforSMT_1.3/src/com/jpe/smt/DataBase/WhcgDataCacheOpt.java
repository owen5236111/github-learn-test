package com.jpe.smt.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jpe.smt.pojo.Attach;
import com.jpe.smt.pojo.Comment;
import com.jpe.smt.pojo.Topic;
import com.jpe.smt.pojo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author oywf 提供对数据库的增删改查
 */
public class WhcgDataCacheOpt {
	private WhcgSQLiteHelper helper;
	// 用户是否登陆的标志位 0为未登陆1为登陆
	private int userLoginTAG = 0;

	public WhcgDataCacheOpt(Context paramContext) {
		this.helper = new WhcgSQLiteHelper(paramContext, null);
	}

	/**
	 * 第一次登陆的时候往判断是否第一次加载的表里面写值 1
	 */
	public void insert_first_load() {
		SQLiteDatabase localSQLiteDatabase = this.helper.getWritableDatabase();
		localSQLiteDatabase.execSQL("delete from first_load");
		Object[] arrayOfObject = new Object[1];
		arrayOfObject[0] = Integer.valueOf(1);
		localSQLiteDatabase.execSQL(
				"insert into first_load(isFirstLoad) values(?)", arrayOfObject);
		localSQLiteDatabase.close();
	}

	/**
	 * 判断是否是第一次加载该应用
	 * 
	 * @return 是第一次加载则 返回0 不是就返回 1
	 */
	public int get_first_load() {
		SQLiteDatabase localSQLiteDatabase = this.helper.getWritableDatabase();
		Cursor localCursor = null;
		localCursor = localSQLiteDatabase.rawQuery("select * from first_load",
				null);
		boolean bool = localCursor.moveToNext();
		int i = 0;
		if (bool) {
			int j = localCursor.getInt(0);
			i = j;
		}
		localSQLiteDatabase.close();
		localCursor.close();
		return i;
	}

	/**
	 * 得到所有的评论表的信息
	 * 
	 * @return List<Comment>
	 */
	public List<Comment> get_table_Comment() {
		ArrayList<Comment> localArrayList = new ArrayList<Comment>();
		SQLiteDatabase localSQLiteDatabase = this.helper.getWritableDatabase();
		Cursor localCursor = localSQLiteDatabase.rawQuery(
				"select * from table_Comment", null);
		if (localCursor.moveToNext()) {
			Comment localComment = new Comment();
			localComment.topicid = localCursor.getString(0);
			localComment.body = localCursor.getString(0);
			localComment.name = localCursor.getString(0);
			localComment.nick = localCursor.getString(0);
			localComment.phone = localCursor.getString(0);
			localComment.time = localCursor.getString(0);
			localComment.level = localCursor.getString(0);
			localComment.score1 = localCursor.getString(0);
			localComment.score2 = localCursor.getString(0);
			localComment.score3 = localCursor.getString(0);
			localArrayList.add(localComment);
		}

		localCursor.close();
		localSQLiteDatabase.close();
		return localArrayList;

	}

	/**
	 * 得到所有我的上报的信息
	 * 
	 * @return List<Topic>
	 */
	public List<Topic> get_table_topic() {
		ArrayList<Topic> localArrayList = new ArrayList<Topic>();
		SQLiteDatabase localSQLiteDatabase = this.helper.getWritableDatabase();
		Cursor localCursor = localSQLiteDatabase.rawQuery(
				"select * from table_topic", null);
		if (localCursor.moveToNext()) {
			Topic localTopic = new Topic();
			localTopic.id = localCursor
					.getInt(localCursor.getColumnIndex("id"));
			localTopic.phone = localCursor.getString(localCursor
					.getColumnIndex("phone"));
			localTopic.subject = localCursor.getString(localCursor
					.getColumnIndex("subject"));
			localTopic.body = localCursor.getString(localCursor
					.getColumnIndex("body"));
			localTopic.lat = localCursor.getString(localCursor
					.getColumnIndex("lat"));
			localTopic.lng = localCursor.getString(localCursor
					.getColumnIndex("lng"));
			localTopic.place = localCursor.getString(localCursor
					.getColumnIndex("place"));
			localTopic.time = localCursor.getString(localCursor
					.getColumnIndex("time"));
			localTopic.mode = localCursor.getString(localCursor
					.getColumnIndex("mode"));
			localTopic.status = localCursor.getInt(localCursor
					.getColumnIndex("status"));
			localTopic.ctime = localCursor.getString(localCursor
					.getColumnIndex("ctime"));
			localTopic.cname = localCursor.getString(localCursor
					.getColumnIndex("cname"));
			// localTopic.attachId = localCursor.getInt(localCursor
			// .getColumnIndex("attachId"));
			localTopic.area = localCursor.getString(localCursor
					.getColumnIndex("area"));
			localArrayList.add(localTopic);
		}

		localCursor.close();
		localSQLiteDatabase.close();
		return localArrayList;

	}

	/**
	 * 得到用户信息
	 * 
	 * @return
	 */
	public List<User> get_table_user() {
		ArrayList<User> localArrayList = new ArrayList<User>();
		SQLiteDatabase localSQLiteDatabase = this.helper.getWritableDatabase();
		Cursor localCursor = localSQLiteDatabase.rawQuery(
				"select * from table_user", null);
		if (localCursor.moveToNext()) {
			User localUser = new User();
			localUser.phone = localCursor.getString(localCursor
					.getColumnIndex("phone"));
			localUser.nick = localCursor.getString(localCursor
					.getColumnIndex("nick"));
			localUser.name = localCursor.getString(localCursor
					.getColumnIndex("name"));
			localUser.email = localCursor.getString(localCursor
					.getColumnIndex("email"));
			localUser.gender = localCursor.getString(localCursor
					.getColumnIndex("gender"));
			localUser.age = localCursor.getString(localCursor
					.getColumnIndex("age"));
			localUser.pid = localCursor.getString(localCursor
					.getColumnIndex("pid"));
			localUser.job = localCursor.getString(localCursor
					.getColumnIndex("job"));
			localUser.education = localCursor.getString(localCursor
					.getColumnIndex("education"));
			localUser.address = localCursor.getString(localCursor
					.getColumnIndex("address"));
			localUser.feedback = localCursor.getString(localCursor
					.getColumnIndex("feedback"));
			localUser.userid = localCursor.getString(localCursor
					.getColumnIndex("userid"));
			localUser.islogin = localCursor.getInt(localCursor
					.getColumnIndex("islogin"));
			this.userLoginTAG = localCursor.getInt(localCursor
					.getColumnIndex("islogin"));
			localUser.score = localCursor.getInt(localCursor
					.getColumnIndex("score"));
			localArrayList.add(localUser);
		}

		localCursor.close();
		localSQLiteDatabase.close();
		return localArrayList;

	}

	/**
	 * 插入评论
	 * 
	 * @param paramComment
	 */
	public void insert_table_Comment(Comment paramComment) {
		SQLiteDatabase localSQLiteDatabase = this.helper.getWritableDatabase();
		Object[] arrayOfObject = new Object[10];
		arrayOfObject[0] = paramComment.topicid;
		arrayOfObject[1] = paramComment.body;
		arrayOfObject[2] = paramComment.name;
		arrayOfObject[3] = paramComment.nick;
		arrayOfObject[4] = paramComment.phone;
		arrayOfObject[5] = paramComment.time;
		arrayOfObject[6] = paramComment.level;
		arrayOfObject[7] = paramComment.score1;
		arrayOfObject[8] = paramComment.score2;
		arrayOfObject[9] = paramComment.score3;
		localSQLiteDatabase
				.execSQL(
						"insert into table_Comment(topicid,body,name,nick,phone,time,level,score1,score2,score3) values(?,?,?,?,?,?,?,?,?,?)",
						arrayOfObject);
		localSQLiteDatabase.close();
	}

	/**
	 * 插入上报信息 (该方法还有问题 先留着 以后再改)
	 * 
	 * @param paramTopic
	 */
	public void insert_table_topic(Topic paramTopic) {
		SQLiteDatabase localSQLiteDatabase = this.helper.getWritableDatabase();
		Object[] arrayOfObject = new Object[14];
		arrayOfObject[0] = paramTopic.id;
		arrayOfObject[1] = paramTopic.phone;
		arrayOfObject[2] = paramTopic.subject;
		arrayOfObject[3] = paramTopic.body;
		arrayOfObject[4] = paramTopic.lat;
		arrayOfObject[5] = paramTopic.lng;
		arrayOfObject[6] = paramTopic.place;
		// arrayOfObject[7] = paramTopic.attachId;
		arrayOfObject[8] = paramTopic.time;
		arrayOfObject[9] = paramTopic.mode;
		arrayOfObject[10] = paramTopic.status;
		arrayOfObject[11] = paramTopic.ctime;
		arrayOfObject[12] = paramTopic.cname;
		arrayOfObject[13] = paramTopic.area;
		localSQLiteDatabase
				.execSQL(
						"insert into table_topic(id,phone,subject,body,lat,lng,place,attachId,time,mode,status,ctime,cname,area) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
						arrayOfObject);
		localSQLiteDatabase.close();
	}

	/**
	 * 插入用户信息
	 * 
	 * @param paramUser
	 */
	public void insert_table_user(User paramUser) {
		SQLiteDatabase localSQLiteDatabase = this.helper.getWritableDatabase();
		localSQLiteDatabase.execSQL("delete from table_user");
		// 手机号(作为id关联到投诉表)、昵称、用户名、用户email、性别、年龄、身份证号码、工作、教育、地址、反馈途径、用户id、
		// 是否登陆在线 、积分信息
		Object[] arrayOfObject = new Object[14];
		arrayOfObject[0] = paramUser.phone;
		arrayOfObject[1] = paramUser.nick;
		arrayOfObject[2] = paramUser.name;
		arrayOfObject[3] = paramUser.email;
		arrayOfObject[4] = paramUser.gender;
		arrayOfObject[5] = paramUser.age;
		arrayOfObject[6] = paramUser.pid;
		arrayOfObject[7] = paramUser.job;
		arrayOfObject[8] = paramUser.education;
		arrayOfObject[9] = paramUser.address;
		arrayOfObject[10] = paramUser.feedback;
		arrayOfObject[11] = paramUser.userid;
		arrayOfObject[12] = Integer.valueOf(this.userLoginTAG);
		arrayOfObject[13] = paramUser.score;
		localSQLiteDatabase
				.execSQL(
						"insert into table_user(phone,nick,name,email,gender ,age ,pid ,job ,education ,address ,feedback ,userid, islogin,score) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
						arrayOfObject);
		localSQLiteDatabase.close();
	}

	/**
	 * 通过关联的附件id查找附件信息
	 * 
	 * @param id
	 * @return
	 */
	public ArrayList<Attach> queryByAttachId(int id) {
		ArrayList<Attach> lists = new ArrayList<Attach>();
		SQLiteDatabase localSQLiteDatabase = this.helper.getWritableDatabase();
		Cursor localCursor = localSQLiteDatabase.rawQuery(
				"select * from table_attach where attachId=?",
				new String[] { String.valueOf(id) });
		if (localCursor.moveToNext()) {
			Attach localUser = new Attach();
			localUser.id = localCursor.getInt(localCursor.getColumnIndex("id"));
			localUser.imageUrl = localCursor.getString(localCursor
					.getColumnIndex("imageUrl"));
			localUser.attachId = localCursor.getInt(localCursor
					.getColumnIndex("attachId"));
			lists.add(localUser);
		}
		localCursor.close();
		localSQLiteDatabase.close();
		return lists;
	}

	public void insert_Attach(Attach attach) {
		SQLiteDatabase localSQLiteDatabase = this.helper.getWritableDatabase();
		Object[] arrayOfObject = new Object[3];
		arrayOfObject[0] = attach.id;
		arrayOfObject[1] = attach.imageUrl;
		arrayOfObject[2] = attach.attachId;
		localSQLiteDatabase.execSQL(
				"insert into table_attach(id,imageUrl,attachId) values(?,?,?)",
				arrayOfObject);
		localSQLiteDatabase.close();
	}

	/**
	 * 判断用户是否已经登陆
	 * 
	 * @return
	 */
	public boolean isLogin() {
		return this.userLoginTAG != 0;
	}

	/**
	 * 设置登陆标志位
	 * 
	 * @param paramBoolean
	 */
	public void setLoginCache(boolean paramBoolean) {
		if (paramBoolean) {
			this.userLoginTAG = 1;
		}
	}
}