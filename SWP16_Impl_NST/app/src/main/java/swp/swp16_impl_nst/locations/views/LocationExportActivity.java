package swp.swp16_impl_nst.locations.views;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.ImportAdapter;
import swp.swp16_impl_nst.locations.LocationProvider;
import swp.swp16_impl_nst.locations.LocationStorage;
/**
 * Created by Simon
 * Activity to Export a JSON-file with locations
 *
 * @author Simon Rommelspacher, Nicu Zaporojan
 * @version 1.0
 */
public class LocationExportActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImportAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_export);

        editText = (EditText) findViewById(R.id.file_name);

        recyclerView = (RecyclerView) findViewById(R.id.rview_export_locations);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ImportAdapter(LocationStorage.getSavedLocations());
        recyclerView.setAdapter(adapter);

    }

    public void export_locations(View view)
    {
        if (editText != null)
        {
            String fileName = editText.getText().toString();
            LocationProvider.exportCurrentLocations(fileName);

            if (!fileName.equals(""))
                NavUtils.navigateUpFromSameTask(LocationExportActivity.this);
            else
                editText.setError("Choose a file name");
        }
    }

}
