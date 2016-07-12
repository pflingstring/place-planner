package swp.swp16_impl_nst.categories.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.categories.CategoryProvider;
import swp.swp16_impl_nst.categories.model.Category;
import swp.swp16_impl_nst.categories.activities.fragments.CategoryDetailsFragment;
import swp.swp16_impl_nst.categories.activities.fragments.CategoryEditFragment;

/**
 * Tabbed Activity
 * Includes the tabs:
 *   - show details
 *   - edit details
 */
public class CategoryTabbedActivity extends AppCompatActivity
        implements CategoryEditFragment.OnClickListener{

    private int position;
    private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_tabbed);

        // setup toolbar
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // setup adapter
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        if (mViewPager != null)
            mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        if (tabLayout != null)
            tabLayout.setupWithViewPager(mViewPager);

        position = getIntent().getIntExtra(CategoryShowActivity.CURRENT_POSITION, -1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_category_tabbed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.delete_category)
        {
            CategoryProvider.categories.remove(position);
            Toast toast = Toast.makeText(this, "Kategorie gelöscht", Toast.LENGTH_SHORT);
            toast.show();
            navigateBack(null);
        }


        return super.onOptionsItemSelected(item);
    }

    // edits the category at this.position
    @Override
    public void onOkButtonClicked(Category category)
    {
        CategoryProvider.categories.remove(position);
        CategoryProvider.categories.add(position, category);

        mSectionsPagerAdapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(0);

        Toast toast = Toast.makeText(this, "Kategorie bearbeitet", Toast.LENGTH_SHORT);
        toast.show();
        navigateBack(null);
    }

    // `Cancel` button from EditFragment
    public void navigateBack(View view)
    { NavUtils.navigateUpFromSameTask(this); }


    // returns a fragment corresponding to one of the tabs
    public class SectionsPagerAdapter extends FragmentPagerAdapter
    {
        public SectionsPagerAdapter(FragmentManager fm)
        { super(fm); }

        @Override
        public Fragment getItem(int tabPosition)
        {
            if (tabPosition == 0)
                return CategoryDetailsFragment.newInstance(position);
            else
                return CategoryEditFragment.newInstance(position);
        }

        @Override
        public int getItemPosition(Object object)
        {
            return POSITION_NONE;
        }

        @Override
        public int getCount()
        { return 2; }

        @Override
        public CharSequence getPageTitle(int tabPosition)
        {
            switch (tabPosition)
            {
                case 0:
                    return getResources().getString(R.string.details);
                case 1:
                    return getResources().getString(R.string.edit);
            }
            return null;
        }
    }
}
