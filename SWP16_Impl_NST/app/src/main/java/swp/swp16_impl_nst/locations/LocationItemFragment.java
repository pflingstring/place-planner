package swp.swp16_impl_nst.locations;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.models.locations.Location;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LocationItemFragment} interface
 * to handle interaction events.
 * Use the {@link LocationItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class LocationItemFragment extends Fragment {

    public LocationItemFragment()
    { /** Required empty public constructor */ }

    public static final String LOCATION_AND_POSITION = "LOCATION_AND_POSITION";
    private Location location;
    private int      position;
//    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LocationItemFragment.
     */
    public static LocationItemFragment newInstance(Location location, int position)
    {
        LocationItemFragment fragment = new LocationItemFragment();
        Bundle args = new Bundle();
        args.putParcelable(LOCATION_AND_POSITION, location);
        args.putInt(LOCATION_AND_POSITION, position);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            location = getArguments().getParcelable(LOCATION_AND_POSITION);
            position = getArguments().getInt(LOCATION_AND_POSITION);
        }
    }

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup      container,
                              Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_location_item, container, false);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        void onFragmentInteraction(Uri uri);
//    }
}
