package com.rathi.menuplanner;

import com.rathi.menuplanner.sqlitedatabase.MenuItem;
import com.rathi.menuplanner.sqlitedatabase.SQLiteHelper;

import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddItemActivity extends ActionBarActivity implements OnClickListener{

	public EditText editItem;
	public Button saveMenu;
	String menuDay, menuDate, menuId, menuName;
	SQLiteHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item);
		

		//action bar color
		android.app.ActionBar bar=getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#47476B")));

		
		Intent intent = getIntent();
        //get date,day,name and id from WeeklyMenuActivity
		menuDay = intent.getStringExtra("menuDay");
		menuDate = intent.getStringExtra("menuDate");
		menuName=intent.getStringExtra("menuName");
		menuId= intent.getStringExtra("menuId");


		//Action bar
		ActionBar actionBar = getActionBar();

		// Enabling Up / Back navigation
		actionBar.setDisplayHomeAsUpEnabled(true);

		editItem=(EditText) findViewById(R.id.addItemText);
		saveMenu=(Button) findViewById(R.id.addButton);
		db=new SQLiteHelper(this);
		saveMenu.setOnClickListener(this);
	}

    //Perform operations on clicking the save button 
	public void onClick(View v) {
		String name=editItem.getText().toString();
		MenuItem menu=new MenuItem();
		menu.setId(menuId);


		//Alert dialog display if name is empty
		if(name.length()<3){
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Enter a menu item !");
			builder.setCancelable(false);
			builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {          
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();

				}
			});
			AlertDialog alert = builder.create();
			alert.show();
		}
		//if name length is greater than 3
		if(name.length()>3){
			if(menuName.startsWith("Breakfast")){
				menu.setBreakfast("Breakfast : "+name);
			}else if(menuName.startsWith("Lunch")){
				menu.setLunch("Lunch    : " +name);
			}else if(menuName.startsWith("Dinner")){
				menu.setDinner("Dinner  : " +name);
			}



			db.updateItem(menu);
            finish();
		}
	}	

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}



}	


