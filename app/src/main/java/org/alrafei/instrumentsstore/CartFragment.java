package org.alrafei.instrumentsstore;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {


    List<Instruments> instrumentsList = new ArrayList<>();

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.cart_recyclerView);
        SQLiteOpenHelper sqLiteOpenHelper = new InstrumentsSQLiteOpenHelper(getActivity());
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(getActivity(), sqLiteOpenHelper, db);
        instrumentsList = databaseAdapter.getAllOrderedInstruments();
        Button delete = view.findViewById(R.id.cart_delete_button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete("CART_INSTRUMENTS", null, null);
                Toast.makeText(getActivity(), "Thanks For Your Purchase", Toast.LENGTH_SHORT).show();
                onViewCreated(view, savedInstanceState);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        CartAdapter adapter = new CartAdapter(getContext(), instrumentsList, recyclerView, this);

        recyclerView.setAdapter(adapter);

        TextView textView = view.findViewById(R.id.cart_totalPrice);
        try{
            Cursor cursor = db.rawQuery("SELECT SUM(PRICE) FROM CART_INSTRUMENTS", null);
            if(cursor.moveToFirst()){
                float sum = cursor.getFloat(0);
                String total = "The Total Price of This Cart Is: $" + sum;
                textView.setText(total);
            }
            cursor.close();
        }catch (Exception e){
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }
}