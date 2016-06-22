package swp.swp16_impl_nst.locations.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.LocationProvider;
import swp.swp16_impl_nst.locations.activities.fragments.LocationEditFragment;
import swp.swp16_impl_nst.locations.model.Location;

/**
 * Activity to add Locations to the list
 */
public class LocationAddActivity extends AppCompatActivity
    implements LocationEditFragment.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_add);
    }

    @Override
    public void onOkButtonClicked(Location location)
    {


        if (location.getName().length()==0){
            Toast toast = Toast.makeText(this, "Die Location muss einen Namen besitzen", Toast.LENGTH_SHORT);
            toast.show();
        }

        else {
            LocationProvider.locations.add(location);
            Toast toast = Toast.makeText(this, "Location added", Toast.LENGTH_SHORT);
            toast.show();
            navigateBack(null);
        }
    }

    public void navigateBack(View view)
        { NavUtils.navigateUpFromSameTask(this); }
}
