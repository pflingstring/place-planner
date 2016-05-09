package swp.swp16_impl_nst.locations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
}
