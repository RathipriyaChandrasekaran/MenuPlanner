package com.rathi.menuplanner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.xml.sax.DTDHandler;

import com.rathi.menuplanner.sqlitedatabase.MenuItem;
import com.rathi.menuplanner.sqlitedatabase.SQLiteHelper;

import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class WeeklyMenuActivity extends ActionBarActivity {

	final Context context = this;
	ListView list;
	TextView timeUpdate;
	ImageView imageView1;
	ImageView imageView2;
	SQLiteHelper db;
	String[] day={};
	String[] item={}; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weekly_menu);

		db = new SQLiteHelper(this);
		
		//action bar color
		android.app.ActionBar bar=getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#47476B")));


		//Action bar
		ActionBar actionBar = getActionBar();
		// Enabling Up / Back navigation
		actionBar.setDisplayHomeAsUpEnabled(true);


		list=(ListView)findViewById(R.id.listView2);
		list.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

		List<String>  dateList = getCalendar();
        update(); 

		int item_count=db.getItemsCount();
		if(item_count==0) {
			//Call the function
			insertItem();
		}

		List<com.rathi.menuplanner.sqlitedatabase.MenuItem> items=db.getAllMenuItems();
		//get the sunday's date from the database
		String dbDate=items.get(0).getDate();
		//Automatically update the week days.
		Calendar calendar = Calendar.getInstance();
		
		
		//if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
			SimpleDateFormat curFormater = new SimpleDateFormat("MM/dd/yyyy");
			String currentDate = curFormater.format(calendar.getTime());

			Date d1=null;
			try {
				d1 = curFormater.parse(dbDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			calendar.setTime(d1);
			Date d2=null;
			try {
				d2 = curFormater.parse(currentDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			calendar.setTime(d2);
			int days = daysBetween(d1, d2);
			
			if(days>6){
				db.deleteAll();
				insertItem();
				
			}
			
			System.out.println("SundaysDate:"+d1);
			System.out.println("Date:"+d2);
			System.out.println("Days= "+daysBetween(d1,d2));

			/*for(MenuItem menuItem : items){
				if(menuItem.getDay().equals("Sunday") && !menuItem.getDate().equals(currentDate)){
					db.deleteAll();
					//Call the function
					insertItem();
					break;
				}
				
			
		}*/
		/*items=db.getAllMenuItems();

		//custom Adapter
		CustomWeeklyMenuAdapter adapter2 = new CustomWeeklyMenuAdapter(this, 0, items);
		list.setAdapter(adapter2);

		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				System.out.println("position : "+position);
				//LinearLayout obj = (LinearLayout) list.getChildAt(position);
				LinearLayout obj = (LinearLayout) view;
				//TextView tv = (TextView) obj.getChildAt(0);
				TextView tv = (TextView) obj.findViewById(R.id.daydatetextView);
				System.out.println("name : "+tv.getText());
				final String name = tv.getText().toString();
				tv = (TextView) obj.findViewById(R.id.idtextView);
				System.out.println("menuid : "+tv.getText());
				final String menuid = tv.getText().toString();

				final Dialog dialog = new Dialog(WeeklyMenuActivity.this);
				dialog.setContentView(R.layout.dialog);
				dialog.setTitle(R.string.menu_title);
				//xml layout for displaying dialog
				final Button dialogButton1=(Button)dialog.findViewById(R.id.addButton);
				final Button dialogButton2=(Button)dialog.findViewById(R.id.menubutton2);
				final Button dialogButton3=(Button)dialog.findViewById(R.id.button3);

				dialogButton1.setOnClickListener(new OnClickListener(){
					public void onClick(View V){

						addItemActivity(name, dialogButton1.getText().toString(), menuid);	
						dialog.dismiss();
					}
				});
				dialog.show();

				dialogButton2.setOnClickListener(new OnClickListener(){
					public void onClick(View V){
						addItemActivity(name, dialogButton2.getText().toString(), menuid);
						dialog.dismiss();
					}
				});
				dialog.show(); 

				dialogButton3.setOnClickListener(new OnClickListener(){
					public void onClick(View V){
						addItemActivity(name, dialogButton3.getText().toString(), menuid);
						dialog.dismiss();
					}
				});
				dialog.show();
			}
		});*/

	}	//oncreate end
	
	//java calendar to display date and day
	public List<String> getCalendar(){

		Calendar calendar = Calendar.getInstance();
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.add(Calendar.DATE, -day_of_week);
		List<String> dateList = new ArrayList<String>();
		for (int i=0; i<7; i++) {
			calendar.add(Calendar.DATE, 1);
			SimpleDateFormat curFormater = new SimpleDateFormat("EEEE (MM/dd/yyyy)");
			dateList.add(curFormater.format(calendar.getTime()));
		}

		String[] days = new String[dateList.size()];
		for(int i=0;i<dateList.size();i++){
			days[i] =  dateList.get(i);
		}
		return dateList;
	}
	//insert menuitem to database
	public void insertItem(){


		for(String date : getCalendar()){
			MenuItem menuItem = new MenuItem();
			menuItem.setDay(date.substring(0, date.indexOf(" ")));
			menuItem.setDate(date.substring(date.indexOf("(")+1,date.indexOf(")")));
			menuItem.setBreakfast("Breakfast : ");
			menuItem.setLunch("Lunch       : ");
			menuItem.setDinner("Dinner      : ");

			db.insertData(menuItem);
		}

	}
	//passing day,date, name and id to AddItemActivity
	public void addItemActivity(String name, String menuName, String id){
		Intent i=new Intent(this,AddItemActivity.class);
		i.putExtra("menuDay", name.substring(0, name.indexOf(" ")));
		i.putExtra("menuDate", name.substring(name.indexOf("(")+1,name.indexOf(")")));
		i.putExtra("menuName", menuName);
		i.putExtra("menuId", id);

		startActivity(i);
	}
	
	public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
   }
	
	public void update(){
		List<com.rathi.menuplanner.sqlitedatabase.MenuItem> items=db.getAllMenuItems();
		items=db.getAllMenuItems();

		//custom Adapter
		CustomWeeklyMenuAdapter adapter2 = new CustomWeeklyMenuAdapter(this, 0, items);
		list.setAdapter(adapter2);

		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				System.out.println("position : "+position);
				//LinearLayout obj = (LinearLayout) list.getChildAt(position);
				LinearLayout obj = (LinearLayout) view;
				//TextView tv = (TextView) obj.getChildAt(0);
				TextView tv = (TextView) obj.findViewById(R.id.daydatetextView);
				System.out.println("name : "+tv.getText());
				final String name = tv.getText().toString();
				tv = (TextView) obj.findViewById(R.id.idtextView);
				System.out.println("menuid : "+tv.getText());
				final String menuid = tv.getText().toString();

				final Dialog dialog = new Dialog(WeeklyMenuActivity.this);
				dialog.setContentView(R.layout.dialog);
				dialog.setTitle(R.string.menu_title);
				//xml layout for displaying dialog
				final Button dialogButton1=(Button)dialog.findViewById(R.id.addButton);
				final Button dialogButton2=(Button)dialog.findViewById(R.id.menubutton2);
				final Button dialogButton3=(Button)dialog.findViewById(R.id.button3);

				dialogButton1.setOnClickListener(new OnClickListener(){
					public void onClick(View V){

						addItemActivity(name, dialogButton1.getText().toString(), menuid);	
						dialog.dismiss();
					}
				});
				dialog.show();

				dialogButton2.setOnClickListener(new OnClickListener(){
					public void onClick(View V){
						addItemActivity(name, dialogButton2.getText().toString(), menuid);
						dialog.dismiss();
					}
				});
				dialog.show(); 

				dialogButton3.setOnClickListener(new OnClickListener(){
					public void onClick(View V){
						addItemActivity(name, dialogButton3.getText().toString(), menuid);
						dialog.dismiss();
					}
				});
				dialog.show();
			}
		});

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		
		super.onResume();
		update();
		//db.updateItem(db.getAllMenuItems());
	}

	





}
