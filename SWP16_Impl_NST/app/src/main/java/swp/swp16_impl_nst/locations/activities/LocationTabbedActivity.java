package swp.swp16_impl_nst.locations.activities;

import android.content.Intent;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.LocationProvider;
import swp.swp16_impl_nst.locations.activities.fragments.LocationDetailsFragment;
import swp.swp16_impl_nst.locations.activities.fragments.LocationEditFragment;
import swp.swp16_impl_nst.locations.model.Location;
import swp.swp16_impl_nst.map.GetLatLngIntentService;

/**
 * Tabbed Activity
 * Includes the tabs:
 *   - show details
 *   - edit details
 */
public class LocationTabbedActivity extends AppCompatActivity
    implements LocationEditFragment.OnClickListener
{
    private int position;
    private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_tabbed);

        // setup toolbar
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // setup adapter
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        if (mViewPager != null)
            mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        if (tabLayout != null)
            tabLayout.setupWithViewPager(mViewPager);

        position = getIntent().getIntExtra(LocationsMainActivity.CURRENT_POSITION, -1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_location_tabbed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.delete_location)
        {
            LocationProvider.locations.remove(position);
            Toast toast = Toast.makeText(this, "Location Deleted", Toast.LENGTH_SHORT);
            toast.show();
            navigateBack(null);
        }


        return super.onOptionsItemSelected(item);
    }

    // edits the location at this.position
    @Override
    public void onOkButtonClicked(final Location location)
    {
        LocationProvider.locations.remove(position);
        LocationProvider.locations.add(position, location);

        mSectionsPagerAdapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(0);

        if (location.getCoordinates().isEmpty())
        {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("Getting gps coordinates")
                    .setView(new ProgressBar(this));

            final AlertDialog alertDialog = builder.show();

            final Intent intent = new Intent(this, GetLatLngIntentService.class);
            String locAddr = location.getAddress().toString();
            intent.putExtra(GetLatLngIntentService.ADDRESS, locAddr);
            intent.putExtra(GetLatLngIntentService.RECEIVER,
                new ResultReceiver(new Handler())
                {
                    @Override
                    protected void onReceiveResult(int resultCode, Bundle data)
                    {
                        String[] res = data.getStringArray(GetLatLngIntentService.RECEIVER);

                        if (resultCode == GetLatLngIntentService.SUCCESS_RESULT)
                        {
                            double lat = Double.parseDouble(res[0]);
                            double lon = Double.parseDouble(res[1]);
                            location.setGpsCoordinates(lat, lon);
                        }
                        alertDialog.dismiss();
                    }
                }
            );
            startService(intent);
        }

        Toast toast = Toast.makeText(this, "Location edited", Toast.LENGTH_SHORT);
        toast.show();
    }

    // `Cancel` button from EditFragment
    public void navigateBack(View view)
    { NavUtils.navigateUpFromSameTask(this); }


    // returns a fragment corresponding to one of the tabs
    public class SectionsPagerAdapter extends FragmentPagerAdapter
    {
        public SectionsPagerAdapter(FragmentManager fm)
        { super(fm); }

        @Override
        public Fragment getItem(int tabPosition)
        {
            if (tabPosition == 0)
                return LocationDetailsFragment.newInstance(position);
            else
                return LocationEditFragment.newInstance(position);
        }

        @Override
        public int getItemPosition(Object object)
        {
            return POSITION_NONE;
        }

        @Override
        public int getCount()
        { return 2; }

        @Override
        public CharSequence getPageTitle(int tabPosition)
        {
            switch (tabPosition)
            {
                case 0:
                    return getResources().getString(R.string.details);
                case 1:
                    return getResources().getString(R.string.edit);
            }
            return null;
        }
    }
}
