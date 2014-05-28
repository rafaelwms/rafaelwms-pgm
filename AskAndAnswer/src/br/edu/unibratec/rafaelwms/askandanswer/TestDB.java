package br.edu.unibratec.rafaelwms.askandanswer;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TestDB {

	private AskAndAnswerHelperDB helper;

	public TestDB(Context ctx) {
		helper = new AskAndAnswerHelperDB(ctx);
	}

	private ContentValues testValues(Test test) {

		ContentValues values = new ContentValues();

		values.put("user", test.getUser().getId_user());
		values.put("title", test.getText_title());
		values.put("category", test.getCategory());
		values.put("value_test", test.getTest_value());

		return values;
	}

	public void insert(Test test) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = testValues(test);
		db.insert("test", null, values);
		db.close();
	}

	public void alter(Test test) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = testValues(test);
		db.update("test", values, "_id = ?", new String[] { test.getId_test()
				+ "" });
		// +"" = Estagiário com raiva... rsrsrsrs
		db.close();
	}

	public void delete(Test test) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.delete("test", "_id = ?", new String[] { test.getId_test() + "" });
		db.close();
	}
	
	private User fillUser(Cursor cursor) {

		int id = cursor.getInt(cursor.getColumnIndex("_id"));
		String login = cursor.getString(cursor.getColumnIndex("login"));
		String password = cursor.getString(cursor.getColumnIndex("password"));
		boolean master = false;
		if (cursor.getInt(cursor.getColumnIndex("master")) == 1) {
			master = true;
		}

		User user = new User(id, login, password, master);
		return user;
	}
	
	public  User findUserById(int id) {
		SQLiteDatabase db = helper.getReadableDatabase();
		User user = new User();
		Cursor cursor = db.rawQuery(
				"select * from user where _id = ? ",
				new String[] { id+""});

		if (cursor.moveToFirst() && cursor.getCount() >= 1) {
			
			user = fillUser(cursor);
			cursor.close();
			db.close();
			return user;
			
		} else {
			cursor.close();
			db.close();
			return null;
		}
	}

	private Test fillTest(Cursor cursor) {

		int id = cursor.getInt(cursor.getColumnIndex("_id"));
		int user_id = cursor.getInt(cursor.getColumnIndex("user"));
		String title = cursor.getString(cursor.getColumnIndex("title"));
		String category = cursor.getString(cursor.getColumnIndex("category"));
		double value_test = cursor.getDouble(cursor.getColumnIndex("value_test"));
		
		User user = new User();
		user.setId_user(user_id);
	

		Test test = new Test(id, user, title, category, null, value_test);
		return test;
	}
	
	private Test fillUserTest(Cursor cursor, User user) {

		int id = cursor.getInt(cursor.getColumnIndex("_id"));
		String title = cursor.getString(cursor.getColumnIndex("title"));
		String category = cursor.getString(cursor.getColumnIndex("category"));
		double value_test = cursor.getDouble(cursor.getColumnIndex("value_test"));
	

		Test test = new Test(id, user, title, category, null, value_test);
		return test;
	}
	
	public List<Test> allTests(){
		List<Test> tests = new ArrayList<Test>();
		
		SQLiteDatabase db = helper.getReadableDatabase();
		
		Cursor cursor = db.rawQuery(
				"select * from test", null);
		
		while (cursor.moveToNext()){
			Test test = fillTest(cursor);
			tests.add(test);
		}
		cursor.close();
		db.close();
		return tests;
	}
	
	public List<Test> findTestByUser(User user){
		List<Test> tests = new ArrayList<Test>();
		
		SQLiteDatabase db = helper.getReadableDatabase();
		
		Cursor cursor = db.rawQuery(
				"select * from test where user = ?", new String[]{user.getId_user()+""});
		
		while (cursor.moveToNext()){
			Test test = fillUserTest(cursor, user);
			tests.add(test);
		}
		cursor.close();
		db.close();
		return tests;
	}

}
