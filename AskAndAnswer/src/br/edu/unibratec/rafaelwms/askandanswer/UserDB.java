package br.edu.unibratec.rafaelwms.askandanswer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDB {

	private AskAndAnswerHelperDB helper;

	public UserDB(Context ctx) {
		helper = new AskAndAnswerHelperDB(ctx);
	}

	private ContentValues userValues(User user) {

		ContentValues values = new ContentValues();
		
		values.put("login", user.getLogin_user());
		values.put("password", user.getPassword_user());
		if (!user.isMaster_user()) {
			values.put("master", 0);
		} else if (user.isMaster_user()){
			values.put("master", 1);
		}
		return values;
	}

	public void insert(User user) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = userValues(user);
		db.insert("user", null, values);
		db.close();
	}

	public void alter(User user) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = userValues(user);
		db.update("user", values, "_id = ?", new String[] { user.getId_user()+"" });
		// +"" = Estagiário com raiva... rsrsrsrs
		db.close();
	}

	public void delete(User user) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.delete("user", "_id = ?", new String[] { user.getId_user() + "" });
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

	public User login(String login, String passwd) {
		SQLiteDatabase db = helper.getReadableDatabase();
		User user = new User();
		Cursor cursor = db.rawQuery(
				"select * from user where login = ? and password = ?",
				new String[] { login, passwd });

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

}
