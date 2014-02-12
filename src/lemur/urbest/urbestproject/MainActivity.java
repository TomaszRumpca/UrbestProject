package lemur.urbest.urbestproject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        int k = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        Log.i("GooglePlayServicesUtil","isGooglePlayServicesAvailable: "+k);
        //Log.i("GooglePlayServicesUtil","GOOGLE_PLAY_SERVICES_VERSION_CODE: "+GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE);
        //Log.i("GooglePlayServicesUtil","GOOGLE_PLAY_STORE_PACKAGE: "+GooglePlayServicesUtil.GOOGLE_PLAY_STORE_PACKAGE);
        
        
        GoogleMap map = ((SupportMapFragment)  getSupportFragmentManager().findFragmentById(R.id.map))
                .getMap();
        
        
        
        map.setMyLocationEnabled(true);
        
        
       map.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Zadanie 1"));
        
        
        Tracker gps = new Tracker(this);
        double latitude=0;
        double longitude=0;
        // check if GPS enabled     
        if(gps.canGetLocation()){
             
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            
            map.addMarker(new MarkerOptions()
            	.position(new LatLng(latitude, longitude))
            	.title("Zadanie 1"));
            
            Log.i("Location", "Latitude: "+latitude+" Longitude: "+longitude);
            // \n is for new line
            //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();    
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
        gps.stopUsingGPS();
      
        
    	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        
    	MenuInflater menuInflater = getMenuInflater();
    	getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()){
		
		case R.id.showList:
			Intent intent = new Intent(getApplicationContext(),ListV.class);
			startActivity(intent);
			break;
			
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
		
	}
    
}
