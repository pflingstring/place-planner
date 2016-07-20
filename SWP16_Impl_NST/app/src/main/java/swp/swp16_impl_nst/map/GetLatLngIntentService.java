package swp.swp16_impl_nst.map;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import swp.swp16_impl_nst.locations.model.GpsCoordinates;
import swp.swp16_impl_nst.utils.Constants;

public class GetLatLngIntentService
    extends IntentService
{
    public static final String COORDINATES = "swp.impl.map.Coordinates";
    public static final String RECEIVER = "swp.impl.map.Geocoding";
    public static final String ADDRESS  = "swp.impl.map.Address";
    public static final int FROM_GPS_COORDINATES = 1;
    public static final int FROM_ADDRESS_NAME = 0;
    public static final int FAILURE_RESULT = -1;
    protected ResultReceiver resultReceiver;
    private String searchAddr;

    public GetLatLngIntentService()
    { super(GetLatLngIntentService.class.getName()); }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        String errorMessage = "";
        List<Address> addressList = null;
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        resultReceiver = intent.getParcelableExtra(RECEIVER);
        searchAddr = intent.getStringExtra(ADDRESS);
        double[] coordinatesArray = intent.getDoubleArrayExtra(COORDINATES);

        try
        {
            if (searchAddr != null)
                addressList = geocoder.getFromLocationName(searchAddr, 1);

            if (coordinatesArray != null && coordinatesArray.length == 2)
                addressList = geocoder.getFromLocation(coordinatesArray[0], coordinatesArray[1], 1);
        }
        catch (IOException e)
            { e.printStackTrace(); }

        if (searchAddr == null)
        {
            Address address = addressList.get(0);
            ArrayList<String> addressFragments = new ArrayList<String>();

            int addressLength = address.getMaxAddressLineIndex();
            String[] result = new String[addressLength];
            if (addressLength != -1)
            {
                for (int i = 0; i < addressLength; i++)
                    result[i] = address.getAddressLine(i);
            }

            // Fetch the address lines using getAddressLine,
            // join them, and send them to the thread.
            StringBuilder sb = new StringBuilder();
            for (String s : addressFragments)
                sb.append(s);

            Log.i("GPSS", sb.toString());
            deliverResultToReceiver(FROM_GPS_COORDINATES, result);
            return;
        }

        if (coordinatesArray == null)
        {
            Address address = addressList.get(0);
            String lat = "" + address.getLatitude();
            String lng = "" + address.getLongitude();
            deliverResultToReceiver(FROM_ADDRESS_NAME, new String[]{lat, lng});
            return;
        }

        deliverResultToReceiver(FAILURE_RESULT, new String[]{"ERROR: NO ADDRESS FOUND"});
    }

    private void deliverResultToReceiver(int result, String[] message)
    {
        Bundle bundle = new Bundle();
        bundle.putStringArray(RECEIVER, message);
        resultReceiver.send(result, bundle);
    }
}
