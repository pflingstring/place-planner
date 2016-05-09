package swp.swp16_impl_nst;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.view.MenuItem;
import android.view.Menu;
import android.os.Bundle;
import android.view.View;

import swp.swp16_impl_nst.locations.LocationDetailsActivity;
import swp.swp16_impl_nst.locations.LocationsMainActivity;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void toLocationDetails(View view)
    {
        Intent intent = new Intent(this, LocationDetailsActivity.class);
        startActivity(intent);
    }

    public void toLocations(View view)
    {
        Intent intent = new Intent(this, LocationsMainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }
}
