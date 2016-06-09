package swp.swp16_impl_nst.locations.views.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import swp.swp16_impl_nst.R;

public class CategoryShowFragment extends Fragment
{
    public CategoryShowFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        { return inflater.inflate(R.layout.fragment_category_show, container, false); }
}
