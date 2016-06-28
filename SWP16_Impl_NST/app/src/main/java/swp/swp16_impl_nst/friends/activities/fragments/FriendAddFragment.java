package swp.swp16_impl_nst.friends.activities.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import swp.swp16_impl_nst.R;

/**
 * Created by Simon on 24.06.2016.
 */
public class FriendAddFragment extends Fragment{
    public FriendAddFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    { return inflater.inflate(R.layout.fragment_friend_add, container, false); }
}
