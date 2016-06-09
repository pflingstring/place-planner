package swp.swp16_impl_nst.categories.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.categories.CategoryAdapter;
import swp.swp16_impl_nst.categories.model.Category;

public class CategoryShowActivity extends AppCompatActivity
{

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
        categories.add(new Category(0, "Restaurant"));
        categories.add(new Category(R.drawable.ic_category_default, "Park"));
        categories.add(new Category(R.drawable.ic_category_default, "Bus station"));
        adapter = new CategoryAdapter(categories);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_category_show, menu);
        return true;
    }
}
