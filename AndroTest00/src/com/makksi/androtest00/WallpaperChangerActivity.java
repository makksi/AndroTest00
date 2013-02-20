package com.makksi.androtest00;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class WallpaperChangerActivity extends Activity {
	
	public Button bStart;
	public Button bStop;
	public Button bHide;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_wallpaper);
        bStart = (Button) findViewById(R.id.startServiceButton);
        bStop =  (Button) findViewById(R.id.stopServiceButton);
        bHide = (Button) findViewById(R.id.hideServiceButton);
        
        bStart.setEnabled(!WallpaperChangerService.STARTED);
        bStop.setEnabled(WallpaperChangerService.STARTED);
        
        bStart.setOnClickListener(new View OnClickListener(){
        	@Override
        	public void onClick(View v){
        		startWallpaperChangerService();
        	}
        });
        bStop.setOnClickListener(new View OnClickListener(){
        	@Override
        	public void onClick(view v){
        		stopWallpaperChangerService();
        	}
        });
        bHide.setOnClickListener(new 
        	}
        }
    }

}
