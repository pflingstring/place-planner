package swp.swp16_impl_nst.locations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
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
                Intent intent = new Intent();
                Bundle bundle = new Bundle();

                bundle.putParcelable("loc", location);
                intent.putExtras(bundle);
                intent.setClass(getApplicationContext(), LocationItemActivity.class);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position)
            {}
        }));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_locations_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch (item.getItemId())
        {
            case R.id.add_location:
                Toast edit = Toast.makeText(getApplicationContext(), "Add item " + id, Toast.LENGTH_SHORT);
                edit.show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
