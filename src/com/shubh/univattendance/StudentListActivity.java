package com.shubh.univattendance;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class StudentListActivity extends ListActivity {
	String t_id, coursename;
	String[] crs, crsn;
	String TAG = "Shubh";
	DBAdapter db;
	Integer sem;
	Cursor c, crsr;
	static final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.studentlist);
		Bundle extras = getIntent().getExtras();

		if (savedInstanceState == null) {
			extras = getIntent().getExtras();
			if (extras == null) {
				t_id = (String) null;
				coursename = (String) null;

			} else {
				t_id = extras.getString("keyTID");
				coursename = extras.getString("keyCOURSE");
			}
		} else {
			t_id = (String) savedInstanceState.getSerializable("keyTID");
			coursename = (String) savedInstanceState
					.getSerializable("keyCOURSE");
		}
		db = new DBAdapter(this);
		try {
			db.open();
			sem = Integer.parseInt(db.getSEMByCourse(coursename));

			c = db.getStudentsBySEM(sem);// Getting the list of roll no in a
											// particular subject
			crsn = new String[c.getCount()];
			crs = new String[c.getCount()];
			Log.i("SHUBH", "GETTING " + c.getCount() + " no. of students ");
			for (int i = 0; i < c.getCount(); ++i) {
				crsn[i] = c.getString(0);
				crs[i] = c.getString(1);
				c.moveToNext();
			}
			c.close();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(),
					"Could not retrieve course Details", Toast.LENGTH_LONG)
					.show();
		}

		try {
			SimpleAdapter adapter = new SimpleAdapter(this, list,
					R.layout.custom_row_view, new String[] { "studname",
							"studroll" }, new int[] { R.id.text1, R.id.text2});
			populateList();

			setListAdapter(adapter);
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Null", Toast.LENGTH_LONG)
					.show();
		}
	}

	private void populateList() {
		for (int i = 0; i < crs.length; ++i) {
			HashMap<String, String> temp = new HashMap<String, String>();
			temp.put("studname", crsn[i]);
			temp.put("studroll", crs[i]);
			list.add(temp);
		}
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {

	    super.onListItemClick(l, v, position, id);
	    Object o = this.getListAdapter().getItem(position);
	    String nm = o.toString();
	    Toast.makeText(this, "You have chosen the Student: " + " " + nm, Toast.LENGTH_LONG).show();
	}

	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent i = new Intent(StudentListActivity.this, TeacherActivity.class);
		startActivity(i);
		finish();
	}
	
	
}
