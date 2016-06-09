package swp.swp16_impl_nst.locations.views;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.ImportAdapter;
import swp.swp16_impl_nst.locations.LocationProvider;
import swp.swp16_impl_nst.locations.LocationStorage;
import swp.swp16_impl_nst.utils.RecyclerItemClickListener;

/**
 * Activity to Import a JSON-file with locations
 */
public class LocationImportActivity extends AppCompatActivity
{

    private RecyclerView recyclerView;
    private ImportAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_import);

        recyclerView = (RecyclerView) findViewById(R.id.rview_import_locations);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ImportAdapter(LocationStorage.getSavedLocations());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                this,
                recyclerView,
                new RecyclerItemClickListener.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                String fileName = LocationStorage.getSavedLocations().get(position);

                // TODO: fix bug, don't add duplicates
                if (LocationStorage.readFromFile(fileName).charAt(0)  == '{')
                    LocationProvider.locations.addAll(LocationProvider.importLocationFile(fileName));
                else
                    LocationProvider.locations.addAll(LocationProvider.importLocationArray(fileName));

                NavUtils.navigateUpFromSameTask(LocationImportActivity.this);
            }

            @Override
            public void onItemLongClick(View view, int position)
            {
            }
        }));
    }
}