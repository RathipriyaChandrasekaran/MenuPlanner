package com.rathi.menuplanner;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

	Button planMenuButton;
	Button referWebButton;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		//action bar color
		android.app.ActionBar bar=getActionBar();
		bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#47476B")));



		planMenuButton=(Button)findViewById(R.id.menubutton1);

		referWebButton=(Button)findViewById(R.id.menubutton2);

		planMenuButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				weeklyMenu();	 
			}
		});


		referWebButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				sendIntent();
			}
		});

	}
	// Intent to call weeklymenuActivity
	public void weeklyMenu(){
		Intent intent=new Intent(this,WeeklyMenuActivity.class);
		startActivity(intent);
	}

	//Intent to call ExpertCookingActivity
	public void sendIntent(){
		Intent i = new Intent(this, ExpertCookingActivity.class);
		startActivity(i); 
	}
}
