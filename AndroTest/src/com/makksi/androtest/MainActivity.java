package com.makksi.androtest;

import android.os.Bundle;
import android.app.Activity;

public class MainActivity extends Activity {
	
	private MyDatabaseHelper myDatabaseHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDatabaseHelper = new MyDatabaseHelper(this);
    }

}
