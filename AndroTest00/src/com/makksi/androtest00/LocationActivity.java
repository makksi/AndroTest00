package com.makksi.androtest00;

import java.util.Date;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.TextView;

public class LocationActivity extends Activity {

	private String providerId = LocationManager.GPS_PROVIDER;

	private LocationListener myLocationListener = new LocationListener() {

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			if (status == LocationProvider.AVAILABLE) {
				setTextViewValue(R.id.available, "TRUE");
			} else {
				setTextViewValue(R.id.available, "FALSE");
			}
		}

		@Override
		public void onProviderEnabled(String provider) {
			setTextViewValue(R.id.enabled, "TRUE");
		}

		@Override
		public void onProviderDisabled(String provider) {
			setTextViewValue(R.id.enabled, "FALSE");
		}

		@Override
		public void onLocationChanged(Location location) {
			updateLocationData(location);
		}

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_location);
	}

	@Override
	protected void onResume() {
		super.onResume();
		setTextViewValue(R.id.provider, providerId);
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		LocationProvider provider = locationManager.getProvider(providerId);
		if (provider == null) {
			setTextViewValue(R.id.available, "FALSE");
		} else {
			setTextViewValue(R.id.available, "TRUE");
			boolean gpsEnabled = locationManager.isProviderEnabled(providerId);
			if (gpsEnabled) {
				setTextViewValue(R.id.enabled, "TRUE");
			} else {
				setTextViewValue(R.id.enabled, "FALSE");
			}
			Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if (location != null) {
				updateLocationData(location);
			}
			locationManager.requestLocationUpdates(providerId, 5, 1, myLocationListener);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		locationManager.removeUpdates(myLocationListener);
	}

	private void setTextViewValue(int textViewId, String value) {
		TextView textView = (TextView) findViewById(textViewId);
		textView.setText(value);
	}

	private void updateLocationData(Location location) {
		Date timestamp = new Date(location.getTime());
		setTextViewValue(R.id.timestamp, timestamp.toString());
		double latitude = location.getLatitude();
		setTextViewValue(R.id.latitude, String.valueOf(latitude));
		double longitude = location.getLongitude();
		setTextViewValue(R.id.longitude, String.valueOf(longitude));
		if (location.hasAltitude()) {
			double altitude = location.getAltitude();
			setTextViewValue(R.id.altitude, String.valueOf(altitude));
		}
		if (location.hasSpeed()) {
			float speed = location.getSpeed();
			setTextViewValue(R.id.speed, String.valueOf(speed));
		}
	}

}
