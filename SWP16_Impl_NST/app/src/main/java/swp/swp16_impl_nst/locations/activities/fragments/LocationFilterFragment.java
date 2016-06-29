package swp.swp16_impl_nst.locations.activities.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import swp.swp16_impl_nst.R;

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
