package swp.swp16_impl_nst.groups.activities.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.categories.activities.CategoryShowActivity;
import swp.swp16_impl_nst.groups.GroupProvider;
import swp.swp16_impl_nst.groups.model.Group;
import swp.swp16_impl_nst.utils.GroupUtils;

public class GroupEditFragment extends Fragment
        implements View.OnClickListener
{
    private final static String CURRENT_POSITION = CategoryShowActivity.CURRENT_POSITION;
    OnClickListener clickListener;
    private Group group;
    private EditText name;




    public GroupEditFragment()
    { /** Required empty public constructor */ }


    /**
     * Creates a new instance of this fragment
     *
     * @param position - position of the Group in the list
     * @return A new instance of fragment GroupDetailsFragment.
     */
    public static GroupEditFragment newInstance(int position)
    {
        GroupEditFragment fragment = new GroupEditFragment();
        Bundle args = new Bundle();
        args.putInt(CURRENT_POSITION, position);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
        {
            int groupPosition = getArguments().getInt(CURRENT_POSITION);
            group = GroupProvider.groups.get(groupPosition);
        }
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        try
        {
            clickListener = (OnClickListener) context;
        }
        catch (ClassCastException e)
        {
            String className = context.getClass().getSimpleName();
            throw new ClassCastException(className + " must implement OnClickListener");
        }
    }

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_group_edit, container, false);
        Button okButton = (Button) view.findViewById(R.id.okButton);
        okButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        if (getView() != null)
            GroupUtils.populateViews(this, getView(), group);
    }

    @Override
    public void onClick(View button)
    {
        View view = getView();

        EditText nameView  = (EditText) view.findViewById(R.id.group_name);

        String name = nameView.getText().toString();

        Group group = new Group(name);

        clickListener.onOkButtonClicked(group);
    }


    public interface OnClickListener
    { void onOkButtonClicked(Group group); }


    // Setters
    public void setName(EditText name)
    { this.name = name; }

}
