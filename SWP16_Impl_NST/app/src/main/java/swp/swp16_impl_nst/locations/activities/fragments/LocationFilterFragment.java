package swp.swp16_impl_nst.locations.activities.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.LocationProvider;
import swp.swp16_impl_nst.locations.LocationsAdapter;
import swp.swp16_impl_nst.locations.activities.LocationFilterActivity;
import swp.swp16_impl_nst.locations.model.Location;
import swp.swp16_impl_nst.locations.model.filters.CityFilter;
import swp.swp16_impl_nst.locations.model.filters.CountryFilter;
import swp.swp16_impl_nst.locations.model.filters.LastEditedOnFilter;
import swp.swp16_impl_nst.locations.model.filters.OwnerFilter;
import swp.swp16_impl_nst.locations.model.filters.StringFilter;
import swp.swp16_impl_nst.locations.model.filters.utils.DateUtils;

public class LocationFilterFragment extends Fragment
{
    RecyclerView recyclerView;

    public LocationFilterFragment()
    { /** Required empty public constructor */}

    public static LocationFilterFragment newInstance()
    {
        return new LocationFilterFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_location_filter, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        if (getView() != null)
            recyclerView = (RecyclerView) getView().findViewById(R.id.rview_filter_locations);


    }



}
