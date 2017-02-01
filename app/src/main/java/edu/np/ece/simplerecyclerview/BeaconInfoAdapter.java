package edu.np.ece.simplerecyclerview;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by zqi2 on 31/01/2017.
 */

public class BeaconInfoAdapter extends RecyclerView.Adapter<BeaconInfoAdapter.MyViewHolder> {

    private List<BeaconInfo> mDataset;
    private Context context;

    public BeaconInfoAdapter(Context context, List<BeaconInfo> moviesList) {
        this.context = context;
        this.mDataset = moviesList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        public TextView tvSerial;
        public TextView tvUuid, tvMajor, tvMinor;
        public ImageButton btDelete;

        public MyViewHolder(View view) {
            super(view);
            tvSerial = (TextView) view.findViewById(R.id.tvSerial);
            tvUuid = (TextView) view.findViewById(R.id.tvUuid);
            tvMinor = (TextView) view.findViewById(R.id.tvMajor);
            tvMajor = (TextView) view.findViewById(R.id.tvMinor);
            btDelete = (ImageButton) view.findViewById(R.id.btDelete);

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);

            btDelete.setOnClickListener(this);
            btDelete.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final int position = getAdapterPosition();
            if (v.getId() == btDelete.getId()) {
                showToast("Button clicked = " + position);
                //-- Get confirmation from user
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirmation")
                        .setMessage("Delete item " + position + "?")
                        .setCancelable(true)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mDataset.remove(position);
                                BeaconInfoAdapter.this.notifyItemRemoved(position);
                            }
                        });
                builder.create().show();
            } else {
                showToast("Row clicked = " + position);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            final int position = getAdapterPosition();
            if (v.getId() == btDelete.getId()) {
                showToast("Button long clicked = " + position);
            } else {
                showToast("Row long clicked = " + position);
            }
            return true;
        }
    }

    @Override
    public BeaconInfoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_beacon, parent, false);

        return new MyViewHolder(itemView);
    }

    //-- invoked by the layout manager to update the contents of a view
    @Override
    public void onBindViewHolder(BeaconInfoAdapter.MyViewHolder holder, final int position) {
        BeaconInfo bi = mDataset.get(position);
        holder.tvSerial.setText(String.valueOf(bi.getSerial()));
        holder.tvUuid.setText(bi.getUuid());
        holder.tvMinor.setText(String.valueOf(bi.getMajor()));
        holder.tvMajor.setText(String.valueOf(bi.getMinor()));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void addItem(int position, BeaconInfo item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void removeItem(BeaconInfo item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
