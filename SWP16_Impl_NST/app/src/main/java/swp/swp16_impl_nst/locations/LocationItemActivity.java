package swp.swp16_impl_nst.locations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.models.locations.Location;

public class LocationItemActivity extends AppCompatActivity
{
    private TextView name;
    private TextView comment;
    private TextView address;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_item);

        if (getActionBar() != null)
        {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        name = (TextView) findViewById(R.id.location_name);
        comment = (TextView) findViewById(R.id.location_comment);
        address = (TextView) findViewById(R.id.location_address);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            location = bundle.getParcelable("loc");

        if (location != null)
        {
            name.setText(location.getName());
            comment.setText(location.getComment());
            address.setText(location.getAddress().toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.edit_location :
                Toast toast = Toast.makeText(getApplicationContext(), "Edit item " + item.getItemId(), Toast.LENGTH_SHORT);
                toast.show();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
