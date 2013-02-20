package com.makksi.androtest00;

import java.util.Timer;
import java.util.TimerTask;
import java.io.IOException;
import java.io.InputStream;


import android.os.Bundle;
import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Log;



public class WallpaperChangerService extends Service {
	public static boolean STARTED=false;
	private String[] availableWallpapers;
	private int currentWallpaperIndex;
	private Timer timer;
	@Override
	public IBinder onBind(Intent intent){
		return null;
	}
    @Override
    public void onCreate() {
        super.onCreate();
        STARTED=true;
        AssetManager assets = getAssets();
        try {
        	availableWallpapers = assets.list("wallpapers");
        }catch(IOException e){
        	Log.e("WallpaperChangerService","Impossibile elencare i wallpapers disponibili", e);
        }
        currentWallpaperIndex=-1;
        if(availableWallpapers !=null && availableWallpapers.length>0){
        	TimerTask task = new TimerTask(){
        		@Override
        		public void run(){
        			nextWallpaper();
        		}
        	};
        	timer = new Timer();
        	timer.schedule(task,0,60000);
        }
    }
    @Override
    public void onDestroy(){
    	super.onDestroy();
    	STARTED=false;
    	timer.cancel();
    	timer=null;
    }
    private void nextWallpaper(){
    	currentWallpaperIndex++;
    	if(currentWallpaperIndex == availableWallpapers.length){
    		currentWallpaperIndex=0;
    	}
    	String currentWallpaper = "wallpapers/" + availableWallpapers[currentWallpaperIndex];
    	Bitmap bitmap = null;
    	InputStream inputStream = null;
    	try{
    		AssetManager assets = getAssets();
    		inputStream= assets.open(currentWallpaper);
    		bitmap = BitmapFactory.decodeStream(inputStream);
    	}catch(IOException e){
    		Log.e("WallpaperChangerService","Impossibile caricare il wallpaper " + currentWallpaper, e);
    	}finally{
    		if(inputStream != null){
    			try{
    				inputStream.close();
    			}catch(Throwable t){
    			}
    		}
    	}
    	if (bitmap != null){
    		WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
    		try{
    			wallpaperManager.setBitmap(bitmap);
    		}catch(Throwable t){
    			Log.e("WallpaperChangerService","Impossibile impostare il wallpaper " + currentWallpaper, t);
    			
    				
        		}
        	}
        }
    }
