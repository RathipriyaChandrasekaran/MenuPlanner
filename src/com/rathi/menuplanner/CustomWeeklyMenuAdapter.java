package com.rathi.menuplanner;

import java.util.List;

import com.rathi.menuplanner.sqlitedatabase.MenuItem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomWeeklyMenuAdapter extends ArrayAdapter<MenuItem>{

	private Activity context;
	private List<MenuItem> menuItem;



	public CustomWeeklyMenuAdapter(Activity context, int resource,
			List<MenuItem> objects) {
		super(context, R.layout.rowitem_weekly_menu, objects);
		menuItem = objects;
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView= inflater.inflate(R.layout.rowitem_weekly_menu, null, true);
		TextView menuDay = (TextView) rowView.findViewById(R.id.daydatetextView);
		TextView menuBreakfast=(TextView) rowView.findViewById(R.id.breakfasttextView);
		TextView menuLunch=(TextView) rowView.findViewById(R.id.lunchtextView);
		TextView menuDinner=(TextView) rowView.findViewById(R.id.dinnertextView);
		TextView menuId=(TextView) rowView.findViewById(R.id.idtextView);

		menuDay.setText(menuItem.get(position).getDay()+" ("+menuItem.get(position).getDate()+")");
		menuBreakfast.setText(menuItem.get(position).getBreakfast());
		menuLunch.setText(menuItem.get(position).getLunch());
		menuDinner.setText(menuItem.get(position).getDinner());
		menuId.setText(menuItem.get(position).getId());

		return rowView;
	}








}
