package swp.swp16_impl_nst.groups.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.groups.GroupAdapter;
import swp.swp16_impl_nst.groups.GroupProvider;
import swp.swp16_impl_nst.utils.RecyclerItemClickListener;

public class GroupShowActivity extends AppCompatActivity{
    public final static String CURRENT_POSITION = "swp.current_group";

    RecyclerView recyclerView;
    GroupAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_show);

        recyclerView = (RecyclerView) findViewById(R.id.rview_group_show);
        layoutManager = new LinearLayoutManager(this);


        adapter = new GroupAdapter(GroupProvider.groups);

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
                //intent.setClass(getApplicationContext(), GroupTabbedActivity.class);
                //startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position)
            {}
        }));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_group_show, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_add_group:
                startActivity(new Intent().setClass(getApplicationContext(), GroupAddActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
