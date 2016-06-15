package swp.swp16_impl_nst.locations.activities;

import android.app.DatePickerDialog;
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
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.LinkedList;
import java.util.Locale;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.LocationProvider;
import swp.swp16_impl_nst.locations.LocationsAdapter;
import swp.swp16_impl_nst.locations.model.Location;
import swp.swp16_impl_nst.locations.model.filters.CityFilter;
import swp.swp16_impl_nst.locations.model.filters.CountryFilter;
import swp.swp16_impl_nst.locations.model.filters.OwnerFilter;
import swp.swp16_impl_nst.locations.model.filters.Predicate;
import swp.swp16_impl_nst.locations.model.filters.StringFilter;

public class LocationFilterActivity extends AppCompatActivity
{
    private List<Location> locations = LocationProvider.getLocationsCopy();
    private RecyclerView recyclerView;
    private LocationsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Predicate> predicateList;

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

        predicateList = new LinkedList<>();
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
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 0.5f);
        final EditText from = new EditText(this);
        EditText until = new EditText(this);
        from.setInputType(InputType.TYPE_NULL);
        from.setText("From");
        from.setLayoutParams(layoutParams);
        until.setInputType(InputType.TYPE_NULL);
        until.setLayoutParams(layoutParams);
        until.setText("Until");

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.addView(from);
        linearLayout.addView(until);

        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        from.setOnClickListener(new View.OnClickListener()
        {
            Calendar fromDate = Calendar.getInstance();

            @Override
            public void onClick(View v)
            {
                DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                    {
                        fromDate.set(year, monthOfYear, dayOfMonth);
                        from.setText(dateFormat.format(fromDate.getTime()));
                    }
                };

                int year1 = fromDate.get(Calendar.YEAR);
                int month = fromDate.get(Calendar.MONTH);
                int day = fromDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker = new DatePickerDialog(LocationFilterActivity.this, onDateSetListener, year1, month, day);
                datePicker.show();
            }
        });

        until.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DatePickerDialog datePicker = new DatePickerDialog(LocationFilterActivity.this, null, 2016, 5, 1);
                datePicker.show();
            }
        });


        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(linearLayout);
        alert.show();

    }

    public void createdOn_button(View view)
    {
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
