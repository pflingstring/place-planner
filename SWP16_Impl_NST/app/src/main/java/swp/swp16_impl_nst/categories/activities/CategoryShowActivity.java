package swp.swp16_impl_nst.categories.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.categories.CategoryAdapter;
import swp.swp16_impl_nst.categories.CategoryProvider;
import swp.swp16_impl_nst.utils.RecyclerItemClickListener;

public class CategoryShowActivity extends AppCompatActivity
{
    public final static String CURRENT_POSITION = "swp.current_category";

    RecyclerView recyclerView;
    CategoryAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_show);

        recyclerView = (RecyclerView) findViewById(R.id.rview_category_show);
        layoutManager = new LinearLayoutManager(this);


        adapter = new CategoryAdapter(CategoryProvider.categories);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                this, recyclerView, new RecyclerItemClickListener.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                Intent intent = new Intent();
                intent.putExtra(CURRENT_POSITION, position);
                intent.setClass(getApplicationContext(), CategoryTabbedActivity.class);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position)
            {}
        }));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_category_show, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {

            case R.id.action_add_category:
                startActivity(new Intent().setClass(getApplicationContext(), CategoryAddActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
