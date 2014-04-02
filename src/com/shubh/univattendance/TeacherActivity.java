package com.shubh.univattendance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class TeacherActivity extends Activity {
	String t_id;
	String coursename;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacherloggedin);
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

	}

	public void takeAttend(View v) {
		Intent ourIntent = new Intent(TeacherActivity.this,
				AttendanceActivity.class);
		ourIntent.putExtra("keyTID", t_id);
		ourIntent.putExtra("keyCOURSE", coursename);
		
		startActivity(ourIntent);
		finish();
	}

	public void viewStud(View v) {
		Intent ourIntent = new Intent(TeacherActivity.this,
				StudentListActivity.class);
		ourIntent.putExtra("keyTID", t_id);
		ourIntent.putExtra("keyCOURSE", coursename);
		startActivity(ourIntent);
		finish();

	}

	public void checkAttend(View v) {

	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent i = new Intent(TeacherActivity.this, CourseActivity.class);
		i.putExtra("key", t_id);
		startActivity(i);
		finish();
	}

}
