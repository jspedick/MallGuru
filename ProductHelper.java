package com.example.mall_guru;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class ProductHelper extends SQLiteOpenHelper {
	private static final String TAG = "ProductHelper";
	private static final String DATABASE_NAME = "product.db";
	private static final int DATABASE_VERSION = 1;
	
	static SQLiteDatabase db = null;
	
	ProductHelper(Context context) { 
		super(context, DATABASE_NAME, null, DATABASE_VERSION); 
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d(TAG, "ProductHelper.onCreate() triggered");
		String buildSQL = "CREATE TABLE " + Product.TABLE_NAME + " (" +
				Product._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
				Product.NAME + " TEXT NOT NULL, " +
				Product.BRAND + " TEXT NOT NULL, " +
				Product.COLOR + " TEXT NOT NULL " + ");";
		Log.d(TAG, buildSQL);
		db.execSQL(buildSQL);
	}
	
	public long add(String name, String brand, String color) {
		Log.d(TAG, "ProductHelper.add() triggered");
		long retVal = 0;
		try { 
			retVal = doesExist(name); 
			Log.d(TAG, name + " already exists");
		}
		catch(SQLiteDoneException e) { 
			ContentValues cv = new ContentValues();
			cv.put(Product.NAME, name);
			cv.put(Product.BRAND, brand);
			cv.put(Product.COLOR, color);
			db = getWritableDatabase();
			retVal = db.insert(Product.TABLE_NAME, null, cv);
		}
		return retVal;
	}
	
	public long doesExist(String name) throws SQLiteDoneException {
		Log.d(TAG, "ProductHelper.doesExist() triggered");
		String buildSQL = "SELECT " + Product._ID + " FROM " + Product.TABLE_NAME  +
				" WHERE " + Product.NAME + "='" + name + "';"; 
		Log.d(TAG, buildSQL);
		db = getReadableDatabase();
		SQLiteStatement st = db.compileStatement(buildSQL);
		return st.simpleQueryForLong();
	}
	
	public Cursor getProduct(String message) {
		Log.d(TAG, "ProductHelper.getAll() triggered");
		String[] cols = new String[] { Product._ID, Product.NAME, Product.BRAND, Product.COLOR };
		db = getReadableDatabase();
		return db.query(Product.TABLE_NAME, cols,Product.NAME.toUpperCase()+" =? or "+Product.BRAND.toUpperCase()+" =?",new String[] {message.toUpperCase(),message.toUpperCase()}, null, null, Product.DEFAULT_SORT);
	}
	
	//make a new function getProduct, one of the nulls is the Where clause

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}
	
}
