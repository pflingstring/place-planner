package swp.swp16_impl_nst.locations.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.adapters.LocationAdapter;
import swp.swp16_impl_nst.locations.LocationProvider;
import swp.swp16_impl_nst.locations.activities.fragments.LocationListFragment;
import swp.swp16_impl_nst.locations.model.Location;
import swp.swp16_impl_nst.locations.model.filters.CityFilter;
import swp.swp16_impl_nst.locations.model.filters.CountryFilter;
import swp.swp16_impl_nst.locations.model.filters.LastEditedOnFilter;
import swp.swp16_impl_nst.locations.model.filters.OwnerFilter;
import swp.swp16_impl_nst.locations.model.filters.StringFilter;
import swp.swp16_impl_nst.locations.model.filters.utils.DateUtils;
import swp.swp16_impl_nst.map.utils.MapUtils;

public class LocationFilterActivity extends AppCompatActivity
    implements LocationListFragment.LocationListView, OnMapReadyCallback
{
    public static List<Location> locations;

    private LocationListFragment locationsFragment;
    public MapFragment mapFragment;
    private GoogleMap map;
    private Menu menu;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_filter);

        // setup toolbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Filter");
        }

        locations = LocationProvider.getLocationsCopy();
        List<Integer> positions = new ArrayList<>();
        for (int i=0; i<locations.size(); i++)
            positions.add(i);


        // fragments initialization
        mapFragment = MapFragment.newInstance();
        locationsFragment = LocationListFragment.newInstance(positions);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, locationsFragment)
                .commit();

        // TODO: run in a background thread
        getFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, mapFragment)
                .commit();

        showListFragment();
    }

    private void showMapFragment()
    {
        hideList();
        showMap();
    }

    private void showListFragment()
    {
        hideMap();
        showList();
    }

    private void hideMap()
    {
        getFragmentManager()
            .beginTransaction()
            .hide(mapFragment)
            .commit();
    }

    private void showMap()
    {
        getFragmentManager()
            .beginTransaction()
            .show(mapFragment)
            .commit();
    }

    private void hideList()
    {
        getSupportFragmentManager()
            .beginTransaction()
            .hide(locationsFragment)
            .commit();
    }

    private void showList()
    {
        getSupportFragmentManager()
            .beginTransaction()
            .show(locationsFragment)
            .commit();
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        final List<Location> locations = locationsFragment.getAdapter().getDataSet();
        map = googleMap;

        for (Location location : locations)
            map.addMarker(new MarkerOptions()
                    .position(location.getLatLng())
                    .title(location.getName()));

        map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener()
        {
            @Override
            public void onCameraChange(CameraPosition cameraPosition)
            {
                map.moveCamera(CameraUpdateFactory.newLatLngBounds(
                        MapUtils.getLatLngBounds(locations), 35));
                map.setOnCameraChangeListener(null);
            }
        });
    }

    private void hideMenuOption(int id)
    { menu.findItem(id).setVisible(false); }

    private void showMenuOption(int id)
    { menu.findItem(id).setVisible(true); }

    @Override
    public void onItemClick()
    {
        Toast.makeText(this, "RView item clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_locations_filter, menu);
        hideMenuOption(R.id.show_list);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.show_map :
                showMapFragment();
                hideMenuOption(R.id.show_map);
                showMenuOption(R.id.show_list);
                return true;
            case R.id.show_list :
                showListFragment();
                hideMenuOption(R.id.show_list);
                showMenuOption(R.id.show_map);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // filter buttons
//    public void distance_button(View view)
//    {
//    }
//
//    public void categories_button(View view)
//    {
//    }
//
//    public void rating_button(View view)
//    {
//    }

    public void lastEditOn_button(View view)
    {
        Context context = LocationFilterActivity.this;
        final Calendar fromDate  = Calendar.getInstance();
        final Calendar untilDate = Calendar.getInstance();

        View alertView = LocationFilterActivity.this.getLayoutInflater().inflate(R.layout.dialog_date_picker, null);
        final EditText from  = (EditText) alertView.findViewById(R.id.fromDate);
        final EditText until = (EditText) alertView.findViewById(R.id.untilDate);

        // so that the keyboard does not popup
        from.setInputType(InputType.TYPE_NULL);
        until.setInputType(InputType.TYPE_NULL);

        from.setOnClickListener(DateUtils.showDatePickerDialog(
                context
                , from
                , fromDate));

        until.setOnClickListener(DateUtils.showDatePickerDialog(
                context
                , until
                , untilDate));

        DialogInterface.OnClickListener okListener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                long from  = fromDate.getTimeInMillis();
                long until = untilDate.getTimeInMillis();

                LocationAdapter adapter = locationsFragment.getAdapter();
                List<Location> locations = new ArrayList<>(adapter.getDataSet());

                List<Location> result = new ArrayList<>();
                for (Location location : locations)
                    if (new LastEditedOnFilter().invoke(location, from, until))
                        result.add(location);

                adapter.replaceDataSet(result);
                adapter.notifyDataSetChanged();
            }
        };

        AlertDialog.Builder alert = new AlertDialog.Builder(LocationFilterActivity.this)
                .setView(alertView)
                .setPositiveButton("OK", okListener);

        alert.show();
    }

    public void createdOn_button(View view)
    {
        Context context = LocationFilterActivity.this;
        final Calendar fromDate  = Calendar.getInstance();
        final Calendar untilDate = Calendar.getInstance();

        View alertView = LocationFilterActivity.this.getLayoutInflater().inflate(R.layout.dialog_date_picker, null);
        final EditText from  = (EditText) alertView.findViewById(R.id.fromDate);
        final EditText until = (EditText) alertView.findViewById(R.id.untilDate);

        // so that the keyboard does not popup
        from.setInputType(InputType.TYPE_NULL);
        until.setInputType(InputType.TYPE_NULL);

        from.setOnClickListener(DateUtils.showDatePickerDialog(
                context
                , from
                , fromDate));

        until.setOnClickListener(DateUtils.showDatePickerDialog(
                context
                , until
                , untilDate));

        DialogInterface.OnClickListener okListener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                long from  = fromDate.getTimeInMillis();
                long until = untilDate.getTimeInMillis();

                LocationAdapter adapter = locationsFragment.getAdapter();
                List<Location> locations = new ArrayList<>(adapter.getDataSet());

                List<Location> result = new ArrayList<>();
                for (Location location : locations)
                    if (new LastEditedOnFilter().invoke(location, from, until))
                        result.add(location);

                adapter.replaceDataSet(result);
                adapter.notifyDataSetChanged();
                MapUtils.updateLocationsOnMap(result, map);
            }
        };

        AlertDialog.Builder alert = new AlertDialog.Builder(LocationFilterActivity.this)
                .setView(alertView)
                .setPositiveButton("OK", okListener);

        alert.show();
    }

    public void string_button(View view)
    {
        final EditText editText = new EditText(LocationFilterActivity.this);
        DialogInterface.OnClickListener okListener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                LocationAdapter adapter = locationsFragment.getAdapter();
                List<Location> locations = new ArrayList<>(adapter.getDataSet());

                List<Location> result = new ArrayList<>();
                String string = editText.getText().toString();
                for (Location location : locations)
                    if (new StringFilter().invoke(location, string))
                        result.add(location);

                adapter.replaceDataSet(result);
                adapter.notifyDataSetChanged();
                MapUtils.updateLocationsOnMap(result, map);

            }};

        AlertDialog.Builder alert = new AlertDialog.Builder(LocationFilterActivity.this)
                .setTitle("String Filter")
                .setView(editText)
                .setPositiveButton("OK", okListener);

        alert.show();
    }

    public void city_button(View view)
    {
        final EditText editText = new EditText(LocationFilterActivity.this);
        DialogInterface.OnClickListener okListener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                LocationAdapter adapter = locationsFragment.getAdapter();
                List<Location> locations = new ArrayList<>(adapter.getDataSet());

                List<Location> result = new ArrayList<>();
                String city = editText.getText().toString();
                for (Location location : locations)
                {
                    if (new CityFilter().invoke(location, city))
                        result.add(location);
                }

                adapter.replaceDataSet(result);
                adapter.notifyDataSetChanged();
                MapUtils.updateLocationsOnMap(result, map);
            }};

        AlertDialog.Builder alert = new AlertDialog.Builder(LocationFilterActivity.this)
                .setTitle("City filter")
                .setView(editText)
                .setPositiveButton("OK", okListener);

        alert.show();
    }

    public void country_button(View view)
    {
        final EditText editText = new EditText(LocationFilterActivity.this);
        DialogInterface.OnClickListener okListener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                LocationAdapter adapter = locationsFragment.getAdapter();
                List<Location> locations = new ArrayList<>(adapter.getDataSet());

                List<Location> result = new ArrayList<>();
                String country = editText.getText().toString();
                for (Location location : locations)
                {
                    if (new CountryFilter().invoke(location, country))
                        result.add(location);
                }

                adapter.replaceDataSet(result);
                adapter.notifyDataSetChanged();
                MapUtils.updateLocationsOnMap(result, map);
            }};

        AlertDialog.Builder alert = new AlertDialog.Builder(LocationFilterActivity.this)
                .setTitle("City filter")
                .setView(editText)
                .setPositiveButton("OK", okListener);

        alert.show();
    }

    public void owner_button(View view)
    {
        final EditText editText = new EditText(LocationFilterActivity.this);
        DialogInterface.OnClickListener okListener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                LocationAdapter adapter = locationsFragment.getAdapter();
                List<Location> locations = new ArrayList<>(adapter.getDataSet());

                List<Location> result = new ArrayList<>();
                String owner = editText.getText().toString();
                for (Location location : locations)
                {
                    if (new OwnerFilter().invoke(location, owner))
                        result.add(location);
                }

                adapter.replaceDataSet(result);
                adapter.notifyDataSetChanged();
                MapUtils.updateLocationsOnMap(result, map);
            }};

        AlertDialog.Builder alert = new AlertDialog.Builder(LocationFilterActivity.this)
                .setTitle("Owner Filter")
                .setView(editText)
                .setPositiveButton("OK", okListener);

        alert.show();
    }
    
}
