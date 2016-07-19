package swp.swp16_impl_nst.locations.activities.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.adapters.LocationAdapter;
import swp.swp16_impl_nst.locations.activities.LocationFilterActivity;
import swp.swp16_impl_nst.utils.Constants;
import swp.swp16_impl_nst.utils.LocationUtils;

public class LocationListFragment extends Fragment
{
    public final static String LOCATION_TAG =
            Constants.PACKAGE_NAME +
            Constants.FRAGMENT     +
            LocationListFragment.class.getName();

    // private fields
    private LocationAdapter adapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    public static LocationListFragment newInstance(final List<Integer> locations)
    {
        LocationListFragment result = new LocationListFragment();

        Bundle args = new Bundle();
        args.putIntegerArrayList(LOCATION_TAG, new ArrayList<>(locations));
        result.setArguments(args);

        return result;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null)
        {
            List<Integer> positions = args.getIntegerArrayList(LOCATION_TAG);
//            adapter = new LocationAdapter(LocationUtils.positionsToLocations(positions));
            layoutManager = new LinearLayoutManager(getActivity());

            LocationFilterActivity activity = (LocationFilterActivity) getActivity();
            activity.mapFragment.getMapAsync(activity);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup      container,
                             Bundle savedInstanceState)
    {
        View result = inflater.inflate(R.layout.recycler_view, container, false);
        recyclerView = (RecyclerView) result.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);

        return result;
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    public interface LocationListView
    {
        void onItemClick();
    }

    public LocationListFragment()
    { /** Required empty public constructor */ }

    public LocationAdapter getAdapter()
    { return adapter; }

}
