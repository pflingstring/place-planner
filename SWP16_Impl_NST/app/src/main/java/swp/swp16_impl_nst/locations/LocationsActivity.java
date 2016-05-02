package swp.swp16_impl_nst.locations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RatingBar;

import java.util.ArrayList;
import java.util.List;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.fields.Address;
import swp.swp16_impl_nst.locations.fields.Category;

public class LocationsActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        recyclerView = (RecyclerView) findViewById(R.id.rview_locations);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Location> data = new ArrayList<>();
        data.add(new Location.Builder("eagles", new Category(1, "bird"), new Address()).build());
        Location l1 = new Location.Builder("stags", new Category(2, "animal"), new Address()).build();
        Location l2 = new Location.Builder("cats", new Category(3, "animal"), new Address()).build();
        Location l3 = new Location.Builder("dogs", new Category(4, "animal"), new Address()).build();
        Location l4 = new Location.Builder("pigs", new Category(5, "animal"), new Address()).build();
        data.add(l1); data.add(l2); data.add(l3); data.add(l4);

        adapter = new LocationsAdapter(data);
        recyclerView.setAdapter(adapter);
    }
}
