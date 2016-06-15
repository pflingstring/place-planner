package swp.swp16_impl_nst.categories.activities.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.categories.CategoryProvider;
import swp.swp16_impl_nst.categories.model.Category;
import swp.swp16_impl_nst.categories.activities.CategoryShowActivity;
import swp.swp16_impl_nst.utils.CategoryUtils;

/**
 * Created by Simon on 09.06.2016.
 */
public class CategoryDetailsFragment extends Fragment
{
    private final static String CURRENT_POSITION = CategoryShowActivity.CURRENT_POSITION;
    private Category category;
    private TextView name;
    private TextView description;


    public CategoryDetailsFragment()
    { /** Required empty public constructor */ }


    /**
     * Creates a new instance of this fragment
     *
     * @param position - position of the Category in the list
     * @return A new instance of fragment CategoryDetailsFragment.
     */
    public static CategoryDetailsFragment newInstance(int position)
    {
        CategoryDetailsFragment fragment = new CategoryDetailsFragment();
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
    { return inflater.inflate(R.layout.fragment_category_details, container, false); }

    @Override
    public void onStart()
    {
        super.onStart();

        int categoryPosition = getArguments().getInt(CURRENT_POSITION);
        category = CategoryProvider.categories.get(categoryPosition);

        if (getView() != null)
            CategoryUtils.populateViews(this, getView(), category);
    }


    // setters
    public void setName(TextView name)
    { this.name = name; }

    public void setDescription(TextView description)
    { this.description = description; }


}
