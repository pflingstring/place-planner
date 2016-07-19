package swp.swp16_impl_nst.locations.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.categories.activities.CategoryAddActivity;
import swp.swp16_impl_nst.categories.activities.CategoryShowActivity;
import swp.swp16_impl_nst.friends.activities.FriendAddActivity;
import swp.swp16_impl_nst.friends.activities.FriendShowActivity;
import swp.swp16_impl_nst.locations.LocationProvider;
import swp.swp16_impl_nst.adapters.LocationAdapter;
import swp.swp16_impl_nst.locations.model.Location;
import swp.swp16_impl_nst.map.MapActivity;

/**
 * This is the welcome Page in the App
 * It displays the list of currently loaded Locations
 */
public class LocationsMainActivity extends AppCompatActivity
{
    public  final static String CURRENT_POSITION = "swp.current_location";
    private final static String SAVED_LOCATION_FILENAME = ".current_locations";
    private final List<Location> locations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_main);

        // setup toolbar
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null)
        {
            fab.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    startActivity(new Intent().setClass(getApplicationContext(), LocationAddActivity.class));
                }
            });
        }

        // load locations from internal storage
        try
        {
            FileInputStream inputStream = openFileInput(SAVED_LOCATION_FILENAME);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);

            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null)
                builder.append(line);

            reader.close();

            String result = builder.toString();
            if (!result.isEmpty())
                LocationProvider.locations.addAll(
                        new ArrayList<>(LocationProvider.fromStringToLocationList(result)));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        locations.addAll(LocationProvider.getLocationsCopy());

        RecyclerViewExpandableItemManager expandableManager = new RecyclerViewExpandableItemManager(null);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rview_locations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(expandableManager.createWrappedAdapter(new LocationAdapter(locations, expandableManager)));
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        expandableManager.attachRecyclerView(recyclerView);

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        // save locations to internal storage
        try
        {
            FileOutputStream outputStream = openFileOutput(SAVED_LOCATION_FILENAME, Context.MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            BufferedWriter writer = new BufferedWriter(outputStreamWriter);

            writer.write(LocationProvider.locationsToString(locations));

            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        LocationProvider.locations.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_locations_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_import_location:
                startActivity(new Intent().setClass(getApplicationContext(), LocationImportActivity.class));
                return true;
            case R.id.menu_export_location:
                startActivity(new Intent().setClass(getApplicationContext(), LocationExportActivity.class));
                return true;
            case R.id.menu_filter_location:
                startActivity(new Intent().setClass(getApplicationContext(), LocationFilterActivity.class));
                return true;
            case R.id.menu_show_category:
                startActivity(new Intent().setClass(getApplicationContext(), CategoryShowActivity.class));
                return true;
            case R.id.menu_add_category:
                startActivity(new Intent().setClass(getApplicationContext(), CategoryAddActivity.class));
                return true;
            case R.id.menu_shop_map:
                startActivity(new Intent().setClass(getApplicationContext(), MapActivity.class));
                return true;
            case R.id.menu_show_friend:
                startActivity(new Intent().setClass(getApplicationContext(), FriendShowActivity.class));
                return true;
            case R.id.menu_add_friend:
                startActivity(new Intent().setClass(getApplicationContext(), FriendAddActivity.class));
                return true;
            case R.id.menu_clear_list:
                LocationProvider.locations.clear();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
