package org.alrafei.instrumentsstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    List<Instruments> instrumentsList;
    RecyclerView recyclerView;
    final View.OnClickListener onClickListener = new MyOnClickListener();
    Fragment fragment;


    public MyAdapter(Context context, List<Instruments> instrumentsList, RecyclerView recyclerView, Fragment f){
        this.context = context;
        this.instrumentsList = instrumentsList;
        this.recyclerView = recyclerView;
        this.fragment = f;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.recycler_grid, parent, false);
        v.setOnClickListener(onClickListener);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Instruments instrument = instrumentsList.get(position);
        String instrumentText = instrument.getName() + "\n$" + instrument.getPrice();
        holder.textView.setText(instrumentText);
        if (instrument.getStock() != 0)
            holder.imageView.setImageResource(instrument.getImageResource());
        else
            holder.imageView.setImageResource(instrument.getOutOfStock());
    }

    @Override
    public int getItemCount() {

        return instrumentsList.size();
    }

    public class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            int itemPosition = recyclerView.getChildLayoutPosition(v);
            int instrument = instrumentsList.get(itemPosition).getId();

            if (instrumentsList.get(itemPosition).getStock() != 0) {
                DisplayInstrumentFragment displayInstrumentFragment = DisplayInstrumentFragment.newInstance(instrument, "my");
                FragmentTransaction fragmentTransaction = fragment.getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, displayInstrumentFragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
            }
            else
                Toast.makeText(context, "This instrument is out of stock", Toast.LENGTH_SHORT).show();
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recycler_imageview);
            textView = itemView.findViewById(R.id.recycler_description);
        }
    }
}
