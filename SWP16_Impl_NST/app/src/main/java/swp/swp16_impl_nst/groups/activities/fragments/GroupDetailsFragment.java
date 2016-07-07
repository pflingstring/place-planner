package swp.swp16_impl_nst.groups.activities.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.categories.activities.CategoryShowActivity;
import swp.swp16_impl_nst.groups.GroupProvider;
import swp.swp16_impl_nst.groups.model.Group;
import swp.swp16_impl_nst.utils.GroupUtils;


public class GroupDetailsFragment extends android.support.v4.app.Fragment {
    private final static String CURRENT_POSITION = CategoryShowActivity.CURRENT_POSITION;
    private Group group;
    private TextView name;

    public GroupDetailsFragment()
    { /** Required empty public constructor */ }


    /**
     * Creates a new instance of this fragment
     *
     * @param position - position of the Group in the list
     * @return A new instance of fragment GroupDetailsFragment.
     */
    public static GroupDetailsFragment newInstance(int position)
    {
        GroupDetailsFragment fragment = new GroupDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CURRENT_POSITION, position);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState)
    { return inflater.inflate(R.layout.fragment_group_details, container, false); }

    @Override
    public void onStart()
    {
        super.onStart();

        int groupPosition = getArguments().getInt(CURRENT_POSITION);
        group = GroupProvider.groups.get(groupPosition);

        if (getView() != null)
            GroupUtils.populateViews(this, getView(), group);
    }


    // setters
    public void setName(TextView name)
    { this.name = name; }
}
