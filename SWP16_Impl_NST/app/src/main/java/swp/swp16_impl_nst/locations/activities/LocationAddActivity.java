package swp.swp16_impl_nst.locations.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.LocationProvider;
import swp.swp16_impl_nst.locations.activities.fragments.LocationEditFragment;
import swp.swp16_impl_nst.locations.model.Location;

/**
 * Activity to add Locations to the list
 */
public class LocationAddActivity extends AppCompatActivity
    implements
        LocationEditFragment.OnClickListener
        , OnMapReadyCallback
{
    View mapView;
    private LatLng latLng;
    private GoogleMap map;
    private MapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_add);
    }

    @Override
    public void onOkButtonClicked(Location location)
    {
        if (location.getName().isEmpty())
        {
            // TODO: replace with string resource
            Toast toast = Toast.makeText(this, "Die Location muss einen Namen besitzen", Toast.LENGTH_SHORT);
            toast.show();
        }
        else
        {
            LocationProvider.locations.add(location);
            Toast toast = Toast.makeText(this, "Location added", Toast.LENGTH_SHORT);
            toast.show();
            navigateBack(null);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        map = googleMap;

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener()
        {
            @Override
            public void onMapClick(LatLng coordinates)
            {
                map.clear();
                latLng = coordinates;
                map.addMarker(new MarkerOptions().position(latLng));
            }
        });
    }

    private void showDialogMap()
    {
        if (mapView == null)
            mapView = getLayoutInflater().inflate(R.layout.fragment_map, null);


        if (mapFragment == null)
        {
            mapFragment = (MapFragment) getFragmentManager()
                    .findFragmentById(mapView.findViewById(R.id.map).getId());
            mapFragment.getMapAsync(LocationAddActivity.this);
        }

        DialogInterface.OnClickListener okListener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                LocationEditFragment fragment = (LocationEditFragment)
                    getSupportFragmentManager().findFragmentById(R.id.add_location_fragment);

                if (latLng != null)
                {
                    fragment.setLatText(latLng.latitude);
                    fragment.setLonText(latLng.longitude);
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this)
            .setPositiveButton("OK", okListener)
            .setView(mapView);

        ViewGroup parent = (ViewGroup) mapView.getParent();
        if (parent != null)
            parent.removeView(mapView);

        builder.show();
    }

    public void navigateBack(View view)
        { NavUtils.navigateUpFromSameTask(this); }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_location_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.choose_location_in_map :
                showDialogMap();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
