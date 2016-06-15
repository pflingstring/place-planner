package swp.swp16_impl_nst.locations.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.LocationProvider;
import swp.swp16_impl_nst.locations.LocationsAdapter;
import swp.swp16_impl_nst.locations.model.Location;
import swp.swp16_impl_nst.locations.model.filters.CityFilter;
import swp.swp16_impl_nst.locations.model.filters.CountryFilter;
import swp.swp16_impl_nst.locations.model.filters.LastEditedOnFilter;
import swp.swp16_impl_nst.locations.model.filters.OwnerFilter;
import swp.swp16_impl_nst.locations.model.filters.StringFilter;
import swp.swp16_impl_nst.locations.model.filters.utils.DateUtils;

public class LocationFilterActivity extends AppCompatActivity
{
    private List<Location> locations = LocationProvider.getLocationsCopy();
    private RecyclerView recyclerView;
    private LocationsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_filter);

        // setup toolbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Filter");
        }

        recyclerView = (RecyclerView) findViewById(R.id.rview_filter_locations);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new LocationsAdapter(locations);
        recyclerView.setAdapter(adapter);
    }


    // filter buttons
    public void distance_button(View view)
    {
    }

    public void categories_button(View view)
    {
    }

    public void rating_button(View view)
    {
    }

    public void lastEditOn_button(View view)
    {
        Context context = LocationFilterActivity.this;
        final Calendar fromDate  = Calendar.getInstance();
        final Calendar untilDate = Calendar.getInstance();

        View alertView = getLayoutInflater().inflate(R.layout.dialog_date_picker, null);
        final EditText from  = (EditText) alertView.findViewById(R.id.fromDate);
        final EditText until = (EditText) alertView.findViewById(R.id.untilDate);

        // so that the keyboard does not popup
        from.setInputType(InputType.TYPE_NULL);
        until.setInputType(InputType.TYPE_NULL);

        from.setOnClickListener(DateUtils.showDatePickerDialog(
                context
                , from
                , fromDate));

        until.setOnClickListener(DateUtils.showDatePickerDialog(
                context
                , until
                , untilDate));

        DialogInterface.OnClickListener okListener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                long from  = fromDate.getTimeInMillis();
                long until = untilDate.getTimeInMillis();

                List<Location> result = new ArrayList<>();
                for (Location location : locations)
                    if (new LastEditedOnFilter().invoke(location, from, until))
                        result.add(location);

                locations.clear();
                locations.addAll(result);
                adapter.notifyDataSetChanged();
            }
        };

        AlertDialog.Builder alert = new AlertDialog.Builder(this)
                .setView(alertView)
                .setPositiveButton("OK", okListener);

        alert.show();
    }

    public void createdOn_button(View view)
    {
        Context context = LocationFilterActivity.this;
        final Calendar fromDate  = Calendar.getInstance();
        final Calendar untilDate = Calendar.getInstance();

        View alertView = getLayoutInflater().inflate(R.layout.dialog_date_picker, null);
        final EditText from  = (EditText) alertView.findViewById(R.id.fromDate);
        final EditText until = (EditText) alertView.findViewById(R.id.untilDate);

        // so that the keyboard does not popup
        from.setInputType(InputType.TYPE_NULL);
        until.setInputType(InputType.TYPE_NULL);

        from.setOnClickListener(DateUtils.showDatePickerDialog(
                context
                , from
                , fromDate));

        until.setOnClickListener(DateUtils.showDatePickerDialog(
                context
                , until
                , untilDate));

        DialogInterface.OnClickListener okListener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                long from  = fromDate.getTimeInMillis();
                long until = untilDate.getTimeInMillis();

                List<Location> result = new ArrayList<>();
                for (Location location : locations)
                    if (new LastEditedOnFilter().invoke(location, from, until))
                        result.add(location);

                locations.clear();
                locations.addAll(result);
                adapter.notifyDataSetChanged();
            }
        };

        AlertDialog.Builder alert = new AlertDialog.Builder(this)
                .setView(alertView)
                .setPositiveButton("OK", okListener);

        alert.show();
    }

    public void string_button(View view)
    {
        final EditText editText = new EditText(this);
        DialogInterface.OnClickListener okListener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                List<Location> result = new ArrayList<>();
                String string = editText.getText().toString();
                for (Location location : locations)
                    if (new StringFilter().invoke(location, string))
                        result.add(location);

                locations.clear();
                locations.addAll(result);
                adapter.notifyDataSetChanged();
        }};

        AlertDialog.Builder alert = new AlertDialog.Builder(this)
                .setTitle("String Filter")
                .setView(editText)
                .setPositiveButton("OK", okListener);

        alert.show();
    }

    public void city_button(View view)
    {
        final EditText editText = new EditText(this);
        DialogInterface.OnClickListener okListener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                List<Location> result = new ArrayList<Location>();
                String city = editText.getText().toString();
                for (Location loc : locations)
                {
                    if (new CityFilter().invoke(loc, city))
                        result.add(loc);
                }
                locations.clear();
                locations.addAll(result);
                adapter.notifyDataSetChanged();
        }};

        AlertDialog.Builder alert = new AlertDialog.Builder(this)
                .setTitle("City filter")
                .setView(editText)
                .setPositiveButton("OK", okListener);

        alert.show();
    }

    public void country_button(View view)
    {
        final EditText editText = new EditText(this);
        DialogInterface.OnClickListener okListener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                List<Location> result = new ArrayList<Location>();
                String country = editText.getText().toString();
                for (Location loc : locations)
                {
                    if (new CountryFilter().invoke(loc, country))
                        result.add(loc);
                }
                locations.clear();
                locations.addAll(result);
                adapter.notifyDataSetChanged();
            }};

        AlertDialog.Builder alert = new AlertDialog.Builder(this)
                .setTitle("City filter")
                .setView(editText)
                .setPositiveButton("OK", okListener);

        alert.show();
    }

    public void owner_button(View view)
    {
        final EditText editText = new EditText(this);
        DialogInterface.OnClickListener okListener = new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                List<Location> result = new ArrayList<Location>();
                String owner = editText.getText().toString();
                for (Location loc : locations)
                {
                    if (new OwnerFilter().invoke(loc, owner))
                        result.add(loc);
                }
                locations.clear();
                locations.addAll(result);
                adapter.notifyDataSetChanged();
        }};

        AlertDialog.Builder alert = new AlertDialog.Builder(this)
                .setTitle("Owner Filter")
                .setView(editText)
                .setPositiveButton("OK", okListener);

        alert.show();
    }
}
