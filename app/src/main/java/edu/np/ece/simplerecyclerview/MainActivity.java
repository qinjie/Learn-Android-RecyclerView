package edu.np.ece.simplerecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static String TAG = MainActivity.class.getSimpleName();
    private LinearLayoutManager mLayoutManager;
    private ArrayList<BeaconInfo> mList = new ArrayList<>();
    private BeaconInfoAdapter mAdapter;

    private static final int PAGE_SIZE = 30;
    //-- implement endless scrolling
    private boolean loading = true;
    private int previousTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        //-- Set to true to improve performance if high of list item is fixed
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        //-- Add an item divider
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, mLayoutManager.getOrientation()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    private static final int THRESHOLD_TO_LOAD_MORE = 5;
                    int lastVisibleItem, totalItemCount;

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);

                        //-- Scrolling down
                        if (dy > 0) {
                            totalItemCount = mLayoutManager.getItemCount();
                            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();

                            if (loading) {
                                if (totalItemCount > previousTotal) {
                                    loading = false;
                                    previousTotal = totalItemCount;
                                }
                            }
                            if (totalItemCount <= (lastVisibleItem + THRESHOLD_TO_LOAD_MORE)) {
                                //-- End has been reached, load more
                                int start_index = totalItemCount;
                                int end_index = start_index + PAGE_SIZE - 1;
                                showToast("Loading more " + start_index + " - " + end_index);
                                BeaconInfoGenerator.generateRandomBeacon(mList, PAGE_SIZE);
                                mAdapter.notifyItemRangeInserted(start_index, PAGE_SIZE);
                                loading = true;
                            }
                        }
                    }
                }
        );

        mAdapter = new BeaconInfoAdapter(this, mList);
        recyclerView.setAdapter(mAdapter);
        //-- populate dummy data in list
        BeaconInfoGenerator.generateRandomBeacon(mList, PAGE_SIZE);
        mAdapter.notifyItemRangeChanged(0, PAGE_SIZE);
    }

    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
