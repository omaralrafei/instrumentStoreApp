package org.alrafei.instrumentsstore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    private Context context;
    List<Instruments> instrumentsList;
    RecyclerView recyclerView;


    Fragment fragment;


    public CartAdapter(Context context, List<Instruments> instrumentsList, RecyclerView recyclerView, Fragment f){
        this.context = context;
        this.instrumentsList = instrumentsList;
        this.recyclerView = recyclerView;
        this.fragment = f;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.cart_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final View.OnClickListener onClickListener1 = new MyButtonOnClickListener(position);
        final View.OnClickListener onClickListener = new MyOnClickListener();
        Instruments instrument = instrumentsList.get(position);
        String instrumentText = "\n$" + instrument.getPrice();
        holder.textView.setText(instrument.getName());
        holder.textView1.setText(instrumentText);
        holder.imageView.setImageResource(instrument.getImageResource());
        holder.button.setOnClickListener(onClickListener1);
        holder.itemView.setOnClickListener(onClickListener);
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
                DisplayInstrumentFragment displayInstrumentFragment = DisplayInstrumentFragment.newInstance(instrument, "cart");
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

    public class MyButtonOnClickListener implements View.OnClickListener {

        int itemPosition1;
        public MyButtonOnClickListener(int itemPosition1){
            this.itemPosition1 = itemPosition1;
        }
        @Override
        public void onClick(View v) {
            Instruments instrument = instrumentsList.get(itemPosition1);
            SQLiteOpenHelper sqLiteOpenHelper = new InstrumentsSQLiteOpenHelper(v.getContext());
            SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
            db.delete("CART_INSTRUMENTS", "_id = ?", new String[]{Integer.toString(instrument.getId())});
            Cursor cursor = db.query("INSTRUMENTS", new String[]{"STOCK", "SOLD"}, "NAME = ?", new String[]{instrument.getName()}, null, null, null);
            if(cursor.moveToFirst()) {
                int inStock = cursor.getInt(0);
                int sold = cursor.getInt(1);
                ContentValues contentValues = new ContentValues();
                contentValues.put("STOCK", inStock + 1);
                contentValues.put("SOLD", sold - 1);
                db.update("INSTRUMENTS", contentValues, "NAME = ?", new String[]{instrument.getName()});
            }
            cursor.close();
            FragmentTransaction fragmentTransaction = fragment.getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, new CartFragment());
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.commit();
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        TextView textView1;
        Button button;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cart_recycler_imageview);
            textView = itemView.findViewById(R.id.cart_recycler_name);
            textView1 = itemView.findViewById(R.id.cart_recycler_price);
            button = itemView.findViewById(R.id.cart_recycler_delete_button);
        }
    }
}
