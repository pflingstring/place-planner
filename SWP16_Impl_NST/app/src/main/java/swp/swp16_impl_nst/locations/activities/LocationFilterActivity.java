package swp.swp16_impl_nst.locations.activities;

import android.support.v4.app.FragmentManager;
import android.app.FragmentTransaction;
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

import com.google.android.gms.maps.MapFragment;

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

public class LocationFilterActivity extends AppCompatActivity
    implements LocationListFragment.LocationListView
{
    public static List<Location> locations = LocationProvider.getLocationsCopy();

    private LocationListFragment locationListFragment;
    private MapFragment mapFragment;
    private Menu menu;
    
    private android.app.FragmentManager mapManager;
    private FragmentManager filterManager;

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

        List<Integer> positions = new ArrayList<>();
        for (int i=0; i<locations.size(); i++)
            positions.add(i);
        
        locationListFragment = LocationListFragment.newInstance(positions);
        filterManager  = getSupportFragmentManager();
        mapFragment = MapFragment.newInstance();
        mapManager  = getFragmentManager();

        showListFragment();
    }

    private void showMapFragment()
    {
        android.support.v4.app.FragmentTransaction filterTransaction = filterManager.beginTransaction();
        filterTransaction.remove(locationListFragment).commit();

        FragmentTransaction mapTransaction = mapManager.beginTransaction();
        mapTransaction.add(R.id.fragment_container, mapFragment);
        mapTransaction.commit();
    }

    private void showListFragment()
    {
        FragmentTransaction mapTransaction = mapManager.beginTransaction();
        mapTransaction.remove(mapFragment);
        mapTransaction.commit();

        android.support.v4.app.FragmentTransaction filterTransaction = filterManager.beginTransaction();
        filterTransaction.add(R.id.fragment_container, locationListFragment);
        filterTransaction.commit();
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

                LocationAdapter adapter = locationListFragment.getAdapter();
                List<Location> locations = new ArrayList<>(adapter.getDataSet());

                List<Location> result = new ArrayList<>();
                for (Location location : locations)
                    if (new LastEditedOnFilter().invoke(location, from, until))
                        result.add(location);

                adapter.changeDataSet(result);
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

                LocationAdapter adapter = locationListFragment.getAdapter();
                List<Location> locations = new ArrayList<>(adapter.getDataSet());

                List<Location> result = new ArrayList<>();
                for (Location location : locations)
                    if (new LastEditedOnFilter().invoke(location, from, until))
                        result.add(location);

                adapter.changeDataSet(result);
                adapter.notifyDataSetChanged();
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
                LocationAdapter adapter = locationListFragment.getAdapter();
                List<Location> locations = new ArrayList<>(adapter.getDataSet());

                List<Location> result = new ArrayList<>();
                String string = editText.getText().toString();
                for (Location location : locations)
                    if (new StringFilter().invoke(location, string))
                        result.add(location);

                adapter.changeDataSet(result);
                adapter.notifyDataSetChanged();
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
                LocationAdapter adapter = locationListFragment.getAdapter();
                List<Location> locations = new ArrayList<>(adapter.getDataSet());

                List<Location> result = new ArrayList<>();
                String city = editText.getText().toString();
                for (Location location : locations)
                {
                    if (new CityFilter().invoke(location, city))
                        result.add(location);
                }

                adapter.changeDataSet(result);
                adapter.notifyDataSetChanged();
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
                LocationAdapter adapter = locationListFragment.getAdapter();
                List<Location> locations = new ArrayList<>(adapter.getDataSet());

                List<Location> result = new ArrayList<>();
                String country = editText.getText().toString();
                for (Location location : locations)
                {
                    if (new CountryFilter().invoke(location, country))
                        result.add(location);
                }

                adapter.changeDataSet(result);
                adapter.notifyDataSetChanged();
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
                LocationAdapter adapter = locationListFragment.getAdapter();
                List<Location> locations = new ArrayList<>(adapter.getDataSet());

                List<Location> result = new ArrayList<>();
                String owner = editText.getText().toString();
                for (Location location : locations)
                {
                    if (new OwnerFilter().invoke(location, owner))
                        result.add(location);
                }

                adapter.changeDataSet(result);
                adapter.notifyDataSetChanged();
            }};

        AlertDialog.Builder alert = new AlertDialog.Builder(LocationFilterActivity.this)
                .setTitle("Owner Filter")
                .setView(editText)
                .setPositiveButton("OK", okListener);

        alert.show();
    }
    
}
