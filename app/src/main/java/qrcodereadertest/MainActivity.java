package qrcodereadertest;



import com.saxion.nl.retroapptive.R;

import android.app.Activity;
import android.text.TextUtils;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.sourceforge.zbar.Symbol;

import scanner.ZBarConstants;
import scanner.ZBarScannerActivity;

public class MainActivity extends Activity {

	private static final int ZBAR_SCANNER_REQUEST = 0;
	private static final int ZBAR_QR_SCANNER_REQUEST = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Button scanButton = (Button) findViewById(R.id.button1);
		scanButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				launchQRScanner(null);
			}
		});
	}

	public void launchQRScanner(View v) {
		Toast.makeText(this, "Dag vreindjes", Toast.LENGTH_SHORT).show();
		if (isCameraAvailable()) {
			Intent intent = new Intent(this, ZBarScannerActivity.class);
			intent.putExtra(ZBarConstants.SCAN_MODES, new int[] { Symbol.QRCODE });
			startActivityForResult(intent, ZBAR_QR_SCANNER_REQUEST);
		} else {
			Toast.makeText(this, "Rear Facing Camera Unavailable", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Toast.makeText(this, "DHALOO: " + resultCode + ":" + data, Toast.LENGTH_SHORT).show();

		switch (requestCode) {
			case ZBAR_SCANNER_REQUEST:
			case ZBAR_QR_SCANNER_REQUEST:
				if (resultCode == RESULT_OK) {
					final TextView tv = (TextView) findViewById(R.id.textView1);
					tv.setText("QRCodeMessage = " + data.getStringExtra(ZBarConstants.SCAN_RESULT));
					Toast.makeText(this, "Scan Result = " + data.getStringExtra(ZBarConstants.SCAN_RESULT), Toast.LENGTH_SHORT).show();
				} else if (resultCode == RESULT_CANCELED && data != null) {
					String error = data.getStringExtra(ZBarConstants.ERROR_INFO);
					if (!TextUtils.isEmpty(error)) {
						Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
					}
				}
				break;
		}
	}

	public boolean isCameraAvailable() {
		PackageManager pm = getPackageManager();
		return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
