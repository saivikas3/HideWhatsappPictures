package com.saivikas.hidewhatsapppictures;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Switch sw;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toast.makeText(getApplicationContext(), "App developed by Sai Vikas", 5).show();
		File thefile = new File(Environment.getExternalStorageDirectory() + File.separator + "WhatsApp/Media/WhatsApp Images/.nomedia");
		if(thefile.exists()) {
			sw = (Switch) findViewById(R.id.switch1);
			sw.setChecked(true);
		}
	}
	

	public void onSwitchClick(View v) {
		sw = (Switch) findViewById(R.id.switch1);
		boolean on = sw.isChecked();
		if(on) {
			File file = new File(Environment.getExternalStorageDirectory() + File.separator + "WhatsApp/Media/WhatsApp Images/.nomedia");
			
			try {
				if(file.exists()) {
					//do nothing
					Toast.makeText(getApplicationContext(), "Feature already enabled", 5).show();
				}
				else {
				boolean done = file.createNewFile();
				sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,Uri.parse("file://"+Environment.getExternalStorageDirectory())));
				if(done)
					Toast.makeText(getApplicationContext(), "Feature is enabled", 5).show();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), e.toString(), 5).show();
			}
		}else{
			File file = new File(Environment.getExternalStorageDirectory() + File.separator + "WhatsApp/Media/WhatsApp Images/.nomedia");
			
			if(file.exists()) {
				boolean deleted = file.delete();
				sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,Uri.parse("file://"+Environment.getExternalStorageDirectory())));
				
				if(deleted)
					Toast.makeText(getApplicationContext(), "Feature is off", 5).show();
			}
			
		}
	}

}
