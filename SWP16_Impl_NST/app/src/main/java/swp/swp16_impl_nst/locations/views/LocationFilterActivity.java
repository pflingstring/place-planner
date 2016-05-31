package swp.swp16_impl_nst.locations.views;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.LocationProvider;
import swp.swp16_impl_nst.locations.LocationsAdapter;
import swp.swp16_impl_nst.locations.model.filters.DistanceFilter;
import swp.swp16_impl_nst.locations.model.filters.Predicate;

public class LocationFilterActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private LocationsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Predicate> predicateList;

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

        recyclerView = (RecyclerView) findViewById(R.id.rview_filter_locations);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new LocationsAdapter(LocationProvider.getLocationsCopy());
        recyclerView.setAdapter(adapter);
    }

    // filter buttons
    public void distance_button(View view)
    {
        predicateList.add(new DistanceFilter());
    }

    public void string_button(View view)
    {

    }

    public void rating_button(View view)
    {

    }

    public void lastEditOn_button(View view)
    {

    }

    public void createdOn_button(View view)
    {

    }

    public void city_button(View view)
    {

    }

    public void country_button(View view)
    {

    }

    public void categories_button(View view)
    {

    }

    public void owner_button(View view)
    {

    }
}
