package br.edu.unibratec.rafaelwms.askandanswer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AskAndAnswerHelperDB extends SQLiteOpenHelper {

	public AskAndAnswerHelperDB(Context ctx) {
		super(ctx, "AAADB", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL("create table user (" +
				"_id integer primary key autoincrement, " +
				"login text not null unique, " +
				"password text not null," +
				"master integer not null);");
		
		db.execSQL("create table test (" +
				"_id integer primary key autoincrement, " +
				"user integer not null REFERENCES user(_id) ON DELETE CASCADE, " +
				"title text not null, " +
				"category text not null, " +
				"value_test real not null);");
		
		db.execSQL("create table question (" +
				"_id integer primary key autoincrement, " +
				"test integer not null REFERENCES test(_id) ON DELETE CASCADE, "+
				"number integer not null, " +
				"txt_question text not null);");
		
		db.execSQL("create table answer (" +
				"_id integer primary key autoincrement, " +
				"question integer not null REFERENCES question(_id) ON DELETE CASCADE, " +
				"number integer not null, " +
				"txt_answer text not null, " +
				"correct integer not null, " +
				"answuer_value real not null);");
		
		db.execSQL("create table result (" +
				"user integer not null REFERENCES user(_id) ON DELETE CASCADE, " +
				"test integer not null REFERENCES test(_id) ON DELETE CASCADE, " +
				"result_value real not null);");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
