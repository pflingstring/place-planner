package swp.swp16_impl_nst.locations.activities.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import swp.swp16_impl_nst.R;

/**
 *
 */
public class LocationExportFragment extends Fragment {

    public LocationExportFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_location_export, container, false);

        return rootView;
    }
}
