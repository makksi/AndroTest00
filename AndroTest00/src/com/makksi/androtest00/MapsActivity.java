package com.makksi.androtest00;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class MapsActivity extends MapActivity {
	
	private MapView mapView = null;
	private CheckBox satellite = null;
	private CheckBox traffic = null;
	private CheckBox streetView = null;
	private MapController mapController;
	private String providerId = LocationManager.GPS_PROVIDER;

	private LocationListener myLocationListener = new LocationListener() {
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
		@Override
		public void onProviderEnabled(String provider) {
		}
		@Override
		public void onProviderDisabled(String provider) {
		}
		@Override
		public void onLocationChanged(Location location) {
			setCurrentLocation(location);
		}
	};
	private GeoPoint currentPosition;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_maps);
		mapView = (MapView) findViewById(R.id.mapView);
		satellite = (CheckBox) findViewById(R.id.satellite);
		traffic = (CheckBox) findViewById(R.id.traffic);
		streetView = (CheckBox) findViewById(R.id.streetView);
		mapView.setSatellite(satellite.isChecked());
		mapView.setStreetView(traffic.isChecked());
		mapView.setTraffic(streetView.isChecked());
		mapView.setClickable(true);
		mapView.setBuiltInZoomControls(true);
		satellite.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				mapView.setSatellite(isChecked);
			}
		});
		traffic.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				mapView.setTraffic(isChecked);
			}
		});
		streetView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				mapView.setStreetView(isChecked);
			}
		});
		mapController = mapView.getController();
		mapController.setZoom(10);
		mapView.getOverlays().add(new CurrentPositionOverlay()); 	// uso l'oggetto CurrentPositionOverlay di una classe che estende la classe
																	// Overlay per disegnare una freccia (youarehere) nella location corrente
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		LocationProvider provider = locationManager.getProvider(providerId);
		if (provider != null && locationManager.isProviderEnabled(providerId)) {
			Location lastKnownLocation = locationManager.getLastKnownLocation(providerId);
			if (lastKnownLocation != null) {
				setCurrentLocation(lastKnownLocation);
			}
			locationManager.requestLocationUpdates(providerId, 1, 1, myLocationListener);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		locationManager.removeUpdates(myLocationListener);
	}

	private void setCurrentLocation(Location location) {
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		int latitudeE6 = (int) Math.floor(latitude * 1.0E6);
		int longitudeE6 = (int) Math.floor(longitude * 1.0E6);
		GeoPoint geoPoint = new GeoPoint(latitudeE6, longitudeE6);
		mapController.setCenter(geoPoint);
		this.currentPosition = geoPoint;
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	private class CurrentPositionOverlay extends Overlay {
		
		private Bitmap youarehere;
		
		public CurrentPositionOverlay() {
			Resources res = MapsActivity.this.getResources();
			youarehere = BitmapFactory.decodeResource(res, R.drawable.youarehere);
		}
		
		@Override
		public void draw(Canvas canvas, MapView mapView, boolean shadow) {
			if (shadow == false && currentPosition != null) {
				Point point = new Point();
				Projection projection = mapView.getProjection();
				projection.toPixels(currentPosition, point);
				float x = point.x - (youarehere.getWidth() / 2);
				float y = point.y - youarehere.getHeight();
				canvas.drawBitmap(youarehere, x, y, null);
			}
		}
		
	}

}
