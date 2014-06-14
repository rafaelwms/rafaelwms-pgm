package br.edu.unibratec.qualabastecer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QualAbastecerDBHelper extends SQLiteOpenHelper{
	
	public QualAbastecerDBHelper(Context ctx){
		super(ctx, "WhichFuel", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL("CREATE TABLE carro (" +
				"_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"nome TEXT NOT NULL UNIQUE, " +
				"tipo INTEGER NOT NULL, " +
				"cor INTEGER NOT NULL, " +
				"combustivel INTEGER NOT NULL);");
		
		db.execSQL("CREATE TABLE posto (" +
				"_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"nome TEXT NOT NULL UNIQUE, " +
				"alcool REAL NOT NULL, " +
				"diesel REAL NOT NULL, " +
				"gasolina REAL NOT NULL);");
		
		db.execSQL("CREATE TABLE abastecimento (" +
				"_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"carro INTEGER NOT NULL REFERENCES carro(_id) ON DELETE CASCADE, " +
				"posto INTEGER NOT NULL REFERENCES posto(_id) ON DELETE CASCADE, " +
				"combustivel INTEGER NOT NULL, " +
				"valorpago REAL NOT NULL, " +
				"litros REAL NOT NULL, " +
				"kilometragem REAL);");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
