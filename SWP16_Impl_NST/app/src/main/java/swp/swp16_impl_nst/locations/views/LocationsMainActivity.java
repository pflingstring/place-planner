package swp.swp16_impl_nst.locations.views;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.LocationProvider;
import swp.swp16_impl_nst.locations.LocationsAdapter;
import swp.swp16_impl_nst.utils.RecyclerItemClickListener;

/**
 * This is the welcome Page in the App
 * It displays the list of currently loaded Locations
 */
public class LocationsMainActivity extends AppCompatActivity
{
    public final static String CURRENT_POSITION = "swp.current_location";

    private RecyclerView recyclerView;
    private LocationsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_main);

        // setup toolbar
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        recyclerView = (RecyclerView) findViewById(R.id.rview_locations);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new LocationsAdapter(LocationProvider.locations);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                this, recyclerView, new RecyclerItemClickListener.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                Intent intent = new Intent();
                intent.putExtra(CURRENT_POSITION, position);
                intent.setClass(getApplicationContext(), LocationTabbedActivity.class);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position)
            {}
        }));

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
        int id = item.getItemId();
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
        }

        return super.onOptionsItemSelected(item);
    }
}
