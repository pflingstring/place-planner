package swp.swp16_impl_nst.locations.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.CategoryProvider;
import swp.swp16_impl_nst.locations.model.Category;
import swp.swp16_impl_nst.locations.views.CategoryShowActivity;
import swp.swp16_impl_nst.utils.CategoryUtils;

/**
 * Created by Simon on 09.06.2016.
 */
public class CategoryEditFragment extends Fragment
        implements View.OnClickListener
{
    private final static String CURRENT_POSITION = CategoryShowActivity.CURRENT_POSITION;
    OnClickListener clickListener;
    private Category category;
    private EditText name;
    private EditText description;
    private EditText icon;



    public CategoryEditFragment()
    { /** Required empty public constructor */ }


    /**
     * Creates a new instance of this fragment
     *
     * @param position - position of the Category in the list
     * @return A new instance of fragment CategoryDetailsFragment.
     */
    public static CategoryEditFragment newInstance(int position)
    {
        CategoryEditFragment fragment = new CategoryEditFragment();
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
            int categoryPosition = getArguments().getInt(CURRENT_POSITION);
            category = CategoryProvider.categorys.get(categoryPosition);
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
        View view = inflater.inflate(R.layout.fragment_category_edit, container, false);
        Button okButton = (Button) view.findViewById(R.id.okButton);
        okButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        if (getView() != null)
            CategoryUtils.populateViews(this, getView(), category);
    }

    @Override
    public void onClick(View button)
    {
        View view = getView();

        EditText nameView  = (EditText) view.findViewById(R.id.name);
        EditText descriptionView = (EditText) view.findViewById(R.id.description);

        String name = nameView.getText().toString();
        String description = descriptionView.getText().toString();



        Category category = new Category.Builder(name)
                .name(name)
                .description(description)
                .build();


        clickListener.onOkButtonClicked(category);
    }


    public interface OnClickListener
    { void onOkButtonClicked(Category category); }


    // Setters
    public void setName(EditText name)
    { this.name = name; }

    public void setDescription(EditText description)
    { this.description = description; }

    public void  setIconId(EditText iconId)
    { this.icon = iconId;}

}

