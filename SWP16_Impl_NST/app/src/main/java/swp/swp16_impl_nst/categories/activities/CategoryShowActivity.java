package swp.swp16_impl_nst.locations.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.CategoryAdapter;
import swp.swp16_impl_nst.locations.model.Category;
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

        List<Category> categories = new ArrayList<>();
        categories.add(new Category(0, "Restaurant", "Alle Restaurants"));
        categories.add(new Category(R.drawable.ic_category_default, "Park", "Gut zum entspannen"));
        categories.add(new Category(R.drawable.ic_category_default, "Bus station",""));
        categories.add(new Category(0, "Kino", ""));
        adapter = new CategoryAdapter(categories);

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
}
