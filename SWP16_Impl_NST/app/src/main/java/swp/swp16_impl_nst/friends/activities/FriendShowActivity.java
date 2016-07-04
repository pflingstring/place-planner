package swp.swp16_impl_nst.friends.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import swp.swp16_impl_nst.R;
import swp.swp16_impl_nst.friends.FriendAdapter;
import swp.swp16_impl_nst.friends.FriendProvider;
import swp.swp16_impl_nst.groups.activities.GroupAddActivity;
import swp.swp16_impl_nst.utils.RecyclerItemClickListener;

/**
 * Created by Simon on 24.06.2016.
 */
public class FriendShowActivity extends AppCompatActivity
{
    public final static String CURRENT_POSITION = "swp.current_category";

    RecyclerView recyclerView;
    FriendAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_show);

        recyclerView = (RecyclerView) findViewById(R.id.rview_friend_show);
        layoutManager = new LinearLayoutManager(this);


        adapter = new FriendAdapter(FriendProvider.friends);

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
                intent.setClass(getApplicationContext(), FriendTabbedActivity.class);
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
        getMenuInflater().inflate(R.menu.menu_friend_show, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {

            case R.id.action_add_friend:
                startActivity(new Intent().setClass(getApplicationContext(), FriendAddActivity.class));
                return true;
            case R.id.action_add_group:
                startActivity(new Intent().setClass(getApplicationContext(), GroupAddActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
