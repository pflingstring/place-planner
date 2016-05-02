package swp.swp16_impl_nst.locations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;

import swp.swp16_impl_nst.R;

public class LocationsActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        RatingBar rb = (RatingBar) findViewById(R.id.rating_bar);
        if (rb != null)
            rb.setRating(3);
    }
}
