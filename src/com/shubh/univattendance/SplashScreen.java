package com.shubh.univattendance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashScreen extends Activity {

	protected boolean _active = true;

	// time to display the splash screen in ms
	protected int _splashTime = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);

		// thread for displaying the SplashScreen
		Thread splashThread = new Thread() {

			volatile boolean finished = false;

			public void stopMe() {
				finished = true;
			}

			public void run() {
				try {
					int waited = 0;
					while (_active && (waited < _splashTime)) {
						sleep(100);
						if (_active) {
							waited += 100;
						}
					}
				} catch (InterruptedException e) {
					// do nothing
				} finally {
					
					startActivity(new Intent(SplashScreen.this,
							MemberActivity.class));
					finish();
				}
			}
		};
		splashThread.start();
	}

	public void onDestroy() {
		super.onDestroy();
	}

}
