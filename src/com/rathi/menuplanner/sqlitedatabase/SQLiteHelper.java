package com.rathi.menuplanner.sqlitedatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

	public static final int DATABASE_VERSION = 1;
	//Database Name
	public static final String DATABASE_NAME = "todoitems.db";
	//Table Names
	// public static final String DATABASE_TABLE = "TODOS";
	public static final String TABLE_MENU="menus";

	//Menu table
	public static final String MENU_ID="menu_id";
	public static final String MENU_DATE="menu_date";
	public static final String MENU_DAY="menu_day";
	public static final String MENU_BREAKFAST="menu_breakfast";
	public static final String MENU_LUNCH="menu_lunch";
	public static final String MENU_DINNER="menu_dinner";

	/*   private static final String DATABASE_CREATE =  "CREATE TABLE " + DATABASE_TABLE
		    + "(" + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT,"
		    + ITEM_COLUMN +"text not null "+")";*/

	private static final String CREATE_MENU_TABLE =  "CREATE TABLE " + TABLE_MENU
			+ "(" + MENU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ MENU_DAY+" text not null,"
			+ MENU_DATE +" text not null,"
			+ MENU_BREAKFAST +" text,"
			+ MENU_LUNCH + " text ,"
			+ MENU_DINNER + " text" +")"; 



	public SQLiteHelper(Context context) {
		super(context,DATABASE_NAME , null, DATABASE_VERSION);
	}



	@Override
	public void onCreate(SQLiteDatabase data) {
		//data.execSQL(DATABASE_CREATE);
		try {
			data.execSQL(CREATE_MENU_TABLE);
		} catch (android.database.SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase data, int oldVersion, int newVersion) {
		//data.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);
		data.execSQL("DROP TABLE IF IT EXISTS " + TABLE_MENU);
		onCreate(data);

	}
	//adding new data
	public void insertData(MenuItem item) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues cv = new ContentValues();
		cv.put(SQLiteHelper.MENU_DAY, item.getDay());
		cv.put(SQLiteHelper.MENU_DATE, item.getDate());
		cv.put(SQLiteHelper.MENU_BREAKFAST, item.getBreakfast());
		cv.put(SQLiteHelper.MENU_LUNCH, item.getLunch());
		cv.put(SQLiteHelper.MENU_DINNER, item.getDinner());
		db.insert(SQLiteHelper.TABLE_MENU, null, cv);
		db.close();
	}

	// Updating single item
	public void updateItem(MenuItem menuItem) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		if(menuItem.getBreakfast() !=null){
			values.put(MENU_BREAKFAST,menuItem.getBreakfast());
		}else if(menuItem.getLunch() !=null){
			values.put(MENU_LUNCH, menuItem.getLunch());
		}else{
			values.put(MENU_DINNER, menuItem.getDinner());
		}
		// updating row
		db.update(TABLE_MENU, values,  MENU_ID + " = ?", new String[] { String.valueOf(menuItem.getId()) });
	}


	//getting all menuitems
	public List<MenuItem> getAllMenuItems(){
		List<MenuItem> itemList=new ArrayList<MenuItem>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_MENU;
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor cursor = db.rawQuery(selectQuery,null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				MenuItem item = new MenuItem(cursor.getString(0), cursor.getString(1) ,cursor.getString(2), 
						cursor.getString(3), cursor.getString(4),cursor.getString(5));

				// Adding contact to list
				itemList.add(item);
			} while (cursor.moveToNext());
		}

		// return contact list
		return itemList;
	}
	

	// Deleting Allitems
	public void deleteAll() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_MENU, null,null);
		db.close();
	}

	// Getting items Count
	public int getItemsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_MENU;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.moveToFirst();
		int count=cursor.getCount();
		cursor.close();

		return count;
	}

}
