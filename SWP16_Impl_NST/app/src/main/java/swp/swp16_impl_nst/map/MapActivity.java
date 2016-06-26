package swp.swp16_impl_nst.map;

import android.content.Intent;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;

import swp.swp16_impl_nst.R;

public class MapActivity
    extends AppCompatActivity
    implements GoogleApiClient.OnConnectionFailedListener
{
    //    private GoogleMap map;
    public static final String TAG = "swp.impl.map";
    protected GoogleApiClient mGoogleApiClient;
    TextView latlng;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, 0 /* clientId */, this)
                .addApi(Places.GEO_DATA_API)
                .build();

        latlng = (TextView) findViewById(R.id.latlng);
        button = (Button) findViewById(R.id.getGpsCoordinates);
    }

    public void getGps(View view)
    {
        startIntentService();
    }

    protected void startIntentService()
    {
        Intent intent = new Intent(this, GetLatLngIntentService.class);
        intent.putExtra(GetLatLngIntentService.RECEIVER,
            new ResultReceiver(new Handler())
            {
                @Override
                protected void onReceiveResult(int result, Bundle data)
                {
                    String[] res = data.getStringArray(GetLatLngIntentService.RECEIVER);
                    Log.d("GEOC", res[0] + " | " + res[1]);
                    latlng.setText(res[0] + " | " + res[1]);
                }
            }
        );
        startService(intent);
    }




    /**
     * Called when the Activity could not connect to Google Play services and the auto manager
     * could resolve the error automatically.
     * In this case the API is not available and notify the user.
     *
     * @param connectionResult can be inspected to determine the cause of the failure
     */
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        Log.e(TAG, "onConnectionFailed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode());

        Toast.makeText(this,
                "Could not connect to Google API Client: Error " + connectionResult.getErrorCode(),
                Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onMapReady(GoogleMap googleMap)
//    {
//        map = googleMap;
//
//         Add a marker in Sydney, Australia, and move the camera.
//        LatLng sydney = new LatLng(-34, 151);
//        map.addMarker(new MarkerOptions().position(sydney).title("LOOOL in Sydney"));
//        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//
//        map.setOnMapClickListener(new GoogleMap.OnMapClickListener()
//        {
//            @Override
//            public void onMapClick(LatLng latLng)
//            {
//                map.addMarker(new MarkerOptions().position(latLng).title("JUST CLICKED HERE"));
//            }
//        });
//
//    }

}
