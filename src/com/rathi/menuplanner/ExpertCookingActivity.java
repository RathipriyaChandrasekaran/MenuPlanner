package com.rathi.menuplanner;

import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ExpertCookingActivity extends ActionBarActivity {
	GridView gv;
	Integer[] thumb={R.drawable.tarladalal,R.drawable.sanjeev_kapoor,R.drawable.harpal_singh,
			R.drawable.vah_chef
	};
	private String[] name={"Tarladalal","Sanjeev Kapoor","Harpal singh sokhi","Sanjay Thumma"};
	String[] web={"http://www.tarladalal.com",
			"http://www.sanjeevkapoor.com","http://www.harpalssokhi.com","http://www.vahrehvah.com"
	};
	int height;
	int width;
	private Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expert_cooking);

		//get the width and height of the screen
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);   
		int screenHeight = metrics.heightPixels;
		int screenWidth = metrics.widthPixels;
		height=screenHeight / 3;
		width=(int) (screenWidth / 2.5);

		//action bar color
		android.app.ActionBar bar=getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#47476B")));

		//Action bar
		ActionBar actionBar = getActionBar();
		// Enabling Up / Back navigation
		actionBar.setDisplayHomeAsUpEnabled(true);


		// Initialize the variables:
		gv = (GridView) findViewById(R.id.gridView1);

		// Set an Adapter to the ListView
		gv.setAdapter(new ImageAdapter(this,width,height));

		// Set on item click listener to the ListView
		gv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {

				// Sending image id to WebviewActivity
				Intent i = new Intent(getApplicationContext(), WebviewActivity.class);
				// passing array index
				i.putExtra("id", position);
				// passing the url based on position
				i.putExtra("web", web[position]);
				startActivity(i);

			}
		});


	}

	public void webView(){
		Intent i=new Intent(ExpertCookingActivity.this,WebviewActivity.class);
		startActivity(i);
	}

	class ImageAdapter extends BaseAdapter {

		private LayoutInflater layoutInflater;

		/*public ImageAdapter(ExpertCookingActivity expertCookingActivity) {
			// TODO Auto-generated constructor stub
			layoutInflater = (LayoutInflater) expertCookingActivity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}*/
		// Constructor
		public ImageAdapter(Context c, int width,int height){
			mContext = c;
			width = width;
			height = height;
		}


		@Override
		public int getCount() {
			// Set the count value to the total number of items in the Array
			return thumb.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			// Inflate the item layout and set the views
			View listItem = convertView;
			int pos = position;
			if (listItem == null) {
				layoutInflater = (LayoutInflater) mContext
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				listItem = layoutInflater.inflate(R.layout.row_expert_cooking, null);
			}
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);

			// Initialize the views in the layout
			ImageView iv = (ImageView) listItem.findViewById(R.id.chefimageView);
			TextView tv=(TextView) listItem.findViewById(R.id.chefnametextView);
			iv.setLayoutParams(layoutParams);

			// Set the views in the layout
			iv.setBackgroundResource(thumb[pos]);
			tv.setText(name[pos]);

			return listItem;
		}


	}
}
