package swp.swp16_impl_nst.locations.views.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import swp.swp16_impl_nst.R;

/**
 * Created by Simon on 01.06.2016.
 * Fragment for the CategoryAddActivity
 *
 * @author Simon Rommelspacher
 * @version 1.0
 */
public class CategoryAddFragment extends Fragment{

    public CategoryAddFragment (){
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_category_add, container, false);

        return rootView;
    }
}
