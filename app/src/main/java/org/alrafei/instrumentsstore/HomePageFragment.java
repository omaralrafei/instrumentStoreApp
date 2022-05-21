package org.alrafei.instrumentsstore;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.zip.Inflater;


public class HomePageFragment extends Fragment {

    private int count = 0;
    private int count1 = 0;
    public HomePageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        SQLiteOpenHelper sqLiteOpenHelper = new InstrumentsSQLiteOpenHelper(getActivity());
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        CardView cardView = view.findViewById(R.id.home_cardview);
        CardView cardView1 = view.findViewById(R.id.home_cardview1);
        Fragment fragment = this;
        count1 = 0;
        count = 0;

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InstrumentsFragment instrumentsFragment = InstrumentsFragment.newInstance("guitar", "name");
                FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, instrumentsFragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack("");
                ft.commit();
            }
        });

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InstrumentsFragment instrumentsFragment = InstrumentsFragment.newInstance("bass", "name");
                FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, instrumentsFragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack("");
                ft.commit();
            }
        });

        ImageView imageView = view.findViewById(R.id.instruments_imageview);
        ImageView imageView1 = view.findViewById(R.id.instruments_imageview1);
        ImageView imageView2 = view.findViewById(R.id.instruments_imageview2);
        TextView textView = view.findViewById(R.id.instruments_description);
        TextView textView1 = view.findViewById(R.id.instruments_description1);
        TextView textView2 = view.findViewById(R.id.instruments_description2);

        ImageView imageView_set1 = view.findViewById(R.id.instruments_imageview_set1);
        ImageView imageView1_set1 = view.findViewById(R.id.instruments_imageview1_set1);
        ImageView imageView2_set1 = view.findViewById(R.id.instruments_imageview2_set1);
        TextView textView_set1 = view.findViewById(R.id.instruments_description_set1);
        TextView textView1_set1 = view.findViewById(R.id.instruments_description1_set1);
        TextView textView2_set1 = view.findViewById(R.id.instruments_description2_set1);


        Cursor cursor = db.query("INSTRUMENTS", null, "TYPE= ?", new String[]{"guitar"}, null, null, null);
        while (cursor.moveToNext() && count<3){
            String name = cursor.getString(cursor.getColumnIndex("NAME"));
            int image = cursor.getInt(cursor.getColumnIndex("IMAGE_RESOURCE_ID"));
            float price = cursor.getFloat(cursor.getColumnIndex("PRICE"));
            String fullIns = name + "\n$" + price;
            if (count == 0){
                imageView.setImageResource(image);
                textView.setText(fullIns);
            }
            else if (count == 1){
                imageView1.setImageResource(image);
                textView1.setText(fullIns);
            }
            else{
                imageView2.setImageResource(image);
                textView2.setText(fullIns);
            }
            count++;
        }
        cursor.close();

        Cursor cursor1 = db.query("INSTRUMENTS", null, "TYPE= ?", new String[]{"bass"}, null, null, null);
        while (cursor1.moveToNext() && count1<3){
            String name = cursor1.getString(cursor1.getColumnIndex("NAME"));
            int image = cursor1.getInt(cursor1.getColumnIndex("IMAGE_RESOURCE_ID"));
            float price = cursor1.getFloat(cursor1.getColumnIndex("PRICE"));
            String fullIns = name + "\n$" + price;
            if (count1 == 0){
                imageView_set1.setImageResource(image);
                textView_set1.setText(fullIns);
            }
            else if (count1 == 1){
                imageView1_set1.setImageResource(image);
                textView1_set1.setText(fullIns);
            }
            else{
                imageView2_set1.setImageResource(image);
                textView2_set1.setText(fullIns);
            }
            count1++;
        }
        cursor1.close();
        // Inflate the layout for this fragment
        return view;
    }
}