package lemur.urbest.urbestproject;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import static android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

public class MainActivity extends Activity {

	private Button takePhoto;
	private Button scanBarcode;
	private Button showMap;
	private Button showRanking;
	
	private Uri photoUri;
	private final static int TAKE_PHOTO = 1;
	private final static String PHOTO_URI = "photoUri";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		addListenerOnButton();
	}

	

	public void addListenerOnButton() {
 
		showMap = (Button) findViewById(R.id.showMap);
 
		showMap.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			  Intent intent = 
                            new Intent(getApplicationContext(),MapActivity.class);
			    startActivity(intent);
 
			}
 
		});
		
		
		showRanking = (Button) findViewById(R.id.showRanking);
		
		showRanking.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
 
			  Intent intent = 
                            new Intent(getApplicationContext(),ListV.class);
			    startActivity(intent);
 
			}
 
		});
		
		
		takePhoto = (Button) findViewById(R.id.takePhoto);
		
		takePhoto.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
 
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				photoUri = getContentResolver().insert(EXTERNAL_CONTENT_URI, new ContentValues());
				intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
				startActivity(intent);
				
 
			}
 
		});
		
		
		scanBarcode = (Button) findViewById(R.id.scanBarcode);
		
		scanBarcode.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
 
				try {

				    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
				    intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes

				    startActivityForResult(intent, 0);

				} catch (Exception e) {

				    Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
				    Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
				    startActivity(marketIntent);

				}
				
				
 
			}
 
		});
	}
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {           
	    super.onActivityResult(requestCode, resultCode, data);
	    if (requestCode == 0) {

	        if (resultCode == RESULT_OK) {
	            String contents = data.getStringExtra("SCAN_RESULT");
	            Toast.makeText(getApplicationContext(), contents, Toast.LENGTH_LONG).show();
	        }
	        if(resultCode != RESULT_OK){
	            //handle cancel
	        }
	    }
	}
}
