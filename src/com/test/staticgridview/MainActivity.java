package com.test.staticgridview;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button btn,btn2;
	List<String> list;
	ExpandableHeightGridView grid,grid1;
	EditText text;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		list=new ArrayList<String>();
		grid  = (ExpandableHeightGridView) findViewById(R.id.gridView1);
		grid1 = (ExpandableHeightGridView) findViewById(R.id.gridView2);
		btn   = (Button) findViewById(R.id.button1);
		btn2   = (Button) findViewById(R.id.button2);
		text  = (EditText) findViewById(R.id.editText1);

		list.add("Grid 1");
		list.add("Grid 2");
		list.add("Grid 3");
		list.add("Grid 4");
		list.add("Grid 5");
		list.add("Grid 6");
		list.add("Grid 7");
		list.add("Grid 8");
		list.add("Grid 9");

		add();
		
		final ArrayAdapter<CharSequence> adp1=ArrayAdapter.createFromResource(this,R.array.str1,
				android.R.layout.simple_dropdown_item_1line);
		grid1.setAdapter(adp1);
		grid.setExpanded(true);

		grid1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapteView, View view, int index,
					long arg3) {
				// TODO Auto-generated method stub

				Toast.makeText(getBaseContext(), adp1.getItem(index),
						Toast.LENGTH_SHORT).show();
			}

		});
		
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				list.add(text.getText().toString());
				add();
			}
		});

		
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), ImageActivity.class);
				startActivity(i);
			}
		});
	}
	
	void add(){

		ArrayAdapter<String> adp=new ArrayAdapter<String> (this,
				android.R.layout.simple_dropdown_item_1line,list);
		grid.setExpanded(true);
		grid.setAdapter(adp);
		
		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapteView, View view, int index,
					long arg3) {
				// TODO Auto-generated method stub

				Toast.makeText(getBaseContext(), list.get(index),
						Toast.LENGTH_SHORT).show();
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}    
}