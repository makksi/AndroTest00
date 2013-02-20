package com.makksi.androtest00;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;



public class Launcher extends Activity implements OnClickListener{
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher);
        Button button01 = (Button) findViewById(R.id.buttonTest1);
        Button button02 = (Button) findViewById(R.id.buttonTest2);
        Button button03 = (Button) findViewById(R.id.buttonTest3);
        Button button04 = (Button) findViewById(R.id.buttonTest4);
        Button button05 = (Button) findViewById(R.id.buttonTest5);
        Button button06 = (Button) findViewById(R.id.buttonTest6);
        Button button07 = (Button) findViewById(R.id.buttonTest7);
        Button button08 = (Button) findViewById(R.id.buttonTest8);
        Button button09 = (Button) findViewById(R.id.buttonTest9);
        
        button01.setOnClickListener(this);
        button02.setOnClickListener(this);
        button03.setOnClickListener(this);
        button04.setOnClickListener(this);
        button05.setOnClickListener(this);
        button06.setOnClickListener(this);
        button07.setOnClickListener(this);
        button08.setOnClickListener(this);
        button09.setOnClickListener(this);
    }
    public void onClick(View v){
    	String msg="";
    	switch (v.getId()){
    	case R.id.buttonTest1: 	msg="Test1";
    	Intent intent1 = new Intent(this,Notepad.class);
    	startActivity(intent1);
    	break;
    	case R.id.buttonTest2: 	msg="Test2";
    	Intent intent2 = new Intent(this,ContactList.class);
    	startActivity(intent2);
    	break;
    	case R.id.buttonTest3: msg="Test3";
    	Intent intent3 = new Intent(this,MainActivity.class);
    	startActivity(intent3);
    	break;
    	case R.id.buttonTest4: msg="Test4";
    	Intent intent4 = new Intent(this,WallpaperChangerActivity.class);
    	startActivity(intent4);
    	break;
    	case R.id.buttonTest5: msg="Test5";
    	Intent intent5 = new Intent(this,Notepad.class);
    	startActivity(intent5);
    	break;
    	case R.id.buttonTest6: msg="Test6";
    	Intent intent6 = new Intent(this,Notepad.class);
    	startActivity(intent6);
    	break;
    	case R.id.buttonTest7: msg="Test7";
    	Intent intent7 = new Intent(this,Notepad.class);
    	startActivity(intent7);
    	break;
    	case R.id.buttonTest8: msg="Test8";
    	Intent intent8 = new Intent(this,Notepad.class);
    	startActivity(intent8);
    	break;
    	case R.id.buttonTest9: msg="Test9";
    	Intent intent9 = new Intent(this,Notepad.class);
    	startActivity(intent9);
    	break;
    	default: ;
    	break;
    	}
    	Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
