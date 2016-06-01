package swp.swp16_impl_nst.locations.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import swp.swp16_impl_nst.R;

/**
 * Created by Simon on 18.05.2016.
 * Fragment for the LocationImportActivity
 *
 * @author Nicu Zaporojan, Simon Rommelspacher
 * @version 1.0
 */
public class LocationImportFragment extends Fragment{

    public LocationImportFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_location_import, container, false);

        return rootView;
    }


 //   public JSONObject parseJSONData() {
 //       String data = null;
 //       JSONObject JSONObject = null;

 //   }

}