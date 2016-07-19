package swp.swp16_impl_nst.locations.activities;

import android.content.Intent;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.LocationProvider;
import swp.swp16_impl_nst.locations.activities.fragments.LocationEditFragment;
import swp.swp16_impl_nst.locations.model.Location;
import swp.swp16_impl_nst.map.GetLatLngIntentService;
import swp.swp16_impl_nst.utils.Constants;

public class LocationTabbedActivity extends AppCompatActivity
    implements LocationEditFragment.OnClickListener
{
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_tabbed);

        position = getIntent().getIntExtra(Constants.LOCATION_ITEM, -1);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction
                .add(R.id.fragment_container, LocationEditFragment.newInstance(position))
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_location_tabbed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.delete_location)
        {
            LocationProvider.locations.remove(position);
            Toast toast = Toast.makeText(this, "Location Deleted", Toast.LENGTH_SHORT);
            toast.show();
            navigateBack(null);
        }


        return super.onOptionsItemSelected(item);
    }

    // edits the location at this.position
    @Override
    public void onOkButtonClicked(final Location location)
    {
        LocationProvider.locations.remove(position);
        LocationProvider.locations.add(position, location);

        if (location.getCoordinates().isEmpty())
        {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("Getting gps coordinates")
                    .setView(new ProgressBar(this));

            final AlertDialog alertDialog = builder.show();

            final Intent intent = new Intent(this, GetLatLngIntentService.class);
            String locAddr = location.getAddress().toString();
            intent.putExtra(GetLatLngIntentService.ADDRESS, locAddr);
            intent.putExtra(GetLatLngIntentService.RECEIVER,
                new ResultReceiver(new Handler())
                {
                    @Override
                    protected void onReceiveResult(int resultCode, Bundle data)
                    {
                        String[] res = data.getStringArray(GetLatLngIntentService.RECEIVER);

                        if (resultCode == GetLatLngIntentService.SUCCESS_RESULT)
                        {
                            double lat = Double.parseDouble(res[0]);
                            double lon = Double.parseDouble(res[1]);
                            location.setGpsCoordinates(lat, lon);
                        }
                        alertDialog.dismiss();
                    }
                }
            );
            startService(intent);
        }

        Toast toast = Toast.makeText(this, "Location edited", Toast.LENGTH_SHORT);
        toast.show();

        navigateBack(null);
    }

    // `Cancel` button from EditFragment
    public void navigateBack(View view)
    { NavUtils.navigateUpFromSameTask(this); }

}
