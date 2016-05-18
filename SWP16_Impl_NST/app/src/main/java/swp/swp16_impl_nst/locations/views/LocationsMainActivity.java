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
import android.widget.Toast;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.LocationProvider;
import swp.swp16_impl_nst.locations.LocationsAdapter;
import swp.swp16_impl_nst.utils.RecyclerItemClickListener;

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
        LocationProvider provider = new LocationProvider();

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
            case R.id.menu_add_location:
                startActivity(new Intent().setClass(getApplicationContext(), LocationAddActivity.class));
                return true;
            case R.id.menu_import_location:
                startActivity(new Intent().setClass(getApplicationContext(), LocationImportActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
