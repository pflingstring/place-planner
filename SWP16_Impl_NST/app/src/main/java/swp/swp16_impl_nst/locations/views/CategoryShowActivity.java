package swp.swp16_impl_nst.locations.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import java.util.ArrayList;
import java.util.Arrays;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.locations.CategoryAdapter;

/**
 * Created by Simon on 02.06.2016.
 */
public class CategoryShowActivity extends AppCompatActivity{

    RecyclerView categoryRecyclerView;
    RecyclerView.Adapter categoryAdapter;
    RecyclerView.LayoutManager categoryLayoutManager;

    public static ArrayList<String> itemListe;
    public static ArrayList<Integer> itemIconId;


    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_category_show);

        itemListe = new ArrayList<>();
        itemIconId = new ArrayList<>();

        itemListe.addAll(Arrays.asList("Restaurant"));
        itemIconId.addAll(Arrays.asList(
           R.drawable.ic_category_default
        ));

        categoryRecyclerView = (RecyclerView) findViewById(R.id.recyclerviewcategory);
        categoryLayoutManager = new LinearLayoutManager(this);
        categoryRecyclerView.setLayoutManager(categoryLayoutManager);
        categoryAdapter = new CategoryAdapter();
        categoryRecyclerView.setAdapter(categoryAdapter);
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_category_show, menu);
        return true;
    }
}
