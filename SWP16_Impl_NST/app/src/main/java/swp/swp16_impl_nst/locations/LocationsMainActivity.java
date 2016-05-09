package swp.swp16_impl_nst.locations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.models.locations.fields.Address;
import swp.swp16_impl_nst.models.locations.fields.Category;
import swp.swp16_impl_nst.models.locations.Location;
import swp.swp16_impl_nst.utils.RecyclerItemClickListener;

public class LocationsMainActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private LocationsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_main);

        recyclerView = (RecyclerView) findViewById(R.id.rview_locations);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Location> data = new ArrayList<>();

        Category inMarburg = new Category(1, "Marburg");
        Location l1 = new Location.Builder("Schloss")
                .address(new Address())
                .category(inMarburg)
                .comment("Auf dem Berg")
                .build();

        Location l2 = new Location.Builder("E-Kirche")
                .address(new Address())
                .category(inMarburg)
                .comment("Unten in der Stadt")
                .build();

        data.add(l1); data.add(l2);

        adapter = new LocationsAdapter(data);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                this, recyclerView, new RecyclerItemClickListener.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                Location location = adapter.getDataSet().get(position);

                String msg = location.getComment();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getApplicationContext(), msg, duration);
                toast.show();
            }

            @Override
            public void onItemLongClick(View view, int position)
            {}
        }));
    }
}
