package swp.swp16_impl_nst.locations;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import swp.swp16_impl_nst.R;

public class LocationAddActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_add);
    }

    public void navigateBack(View view)
    {
        NavUtils.navigateUpFromSameTask(this);
    }

    public void saveChanges(View view)
    {
        Toast toast = Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT);
        toast.show();
    }
}
