package com.makksi.androtest00;

import android.os.Bundle;
import android.app.Activity;



public class MainActivity extends Activity {
	
	public MyDatabaseHelper myDatabaseHelper; // sull'esempio diceva private, ma ho messo public perché dá errore

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDatabaseHelper = new MyDatabaseHelper(this);
    }

}
