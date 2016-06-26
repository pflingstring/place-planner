package swp.swp16_impl_nst.map;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.ResultReceiver;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GetLatLngIntentService
    extends IntentService
{
    public static final String TAG = "swp.impl.map.Geocoding";
    private static final int SUCCESS_RESULT = 0;
    private static final int FAILURE_RESULT = 1;
    protected ResultReceiver resultReceiver;

    public GetLatLngIntentService()
    { super(GetLatLngIntentService.class.getName()); }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        String errorMessage = "";
        List<Address> addressList = null;
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        resultReceiver = intent.getParcelableExtra(TAG);

        try
        { addressList = geocoder.getFromLocationName("35037 marburg biegenstr 10", 1); }
        catch (IOException e)
        { e.printStackTrace(); }

        if (addressList == null || addressList.size() == 0)
        {
            if (errorMessage.isEmpty())
                errorMessage = "No address found";

            deliverResultToReceiver(FAILURE_RESULT, new String[]{errorMessage});
        }
        else
        {
            Address address = addressList.get(0);
            String lat = "" + address.getLatitude();
            String lng = "" + address.getLongitude();
            deliverResultToReceiver(SUCCESS_RESULT, new String[]{lat, lng});
        }
    }

    private void deliverResultToReceiver(int result, String[] message)
    {
        Bundle bundle = new Bundle();
        bundle.putStringArray(TAG, message);
        resultReceiver.send(result, bundle);
    }
}
