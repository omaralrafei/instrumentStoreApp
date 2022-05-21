package org.alrafei.instrumentsstore;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class DisplayInstrumentFragment extends Fragment {


    private String inName;
    private int inImage;
    private String inType;
    private int inStock;
    private int inOutOfStock;
    private float inPrice;
    private String inDescription;

    public DisplayInstrumentFragment() {
        // Required empty public constructor
    }

    public static DisplayInstrumentFragment newInstance (int id, String from){
        DisplayInstrumentFragment f = new DisplayInstrumentFragment();
        Bundle args = new Bundle();
        args.putInt("ID", id);
        args.putString("from", from);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        int key = args.getInt("ID");
        SQLiteOpenHelper sqLiteOpenHelper = new InstrumentsSQLiteOpenHelper(getActivity());
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        TextView name = view.findViewById(R.id.display_instrument_textview_name);
        TextView description = view.findViewById(R.id.display_instrument_textview_description);
        ImageView imageView = view.findViewById(R.id.display_instrument_imageview);
        TextView priceText = view.findViewById(R.id.display_instrument_price);




        Cursor cursor = db.query("INSTRUMENTS", null, "_id = ?", new String[]{Integer.toString(key)}, null, null, null);
        if (cursor.moveToFirst()){
            inImage = cursor.getInt(cursor.getColumnIndex("IMAGE_RESOURCE_ID"));
            inName = cursor.getString(cursor.getColumnIndex("NAME"));
            inDescription = cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
            inPrice = cursor.getFloat(cursor.getColumnIndex("PRICE"));;
            inOutOfStock = cursor.getInt(cursor.getColumnIndex("OUT_IMAGE_RESOURCE_ID"));
            inStock = cursor.getInt(cursor.getColumnIndex("STOCK"));
            inType = cursor.getString(cursor.getColumnIndex("TYPE"));
            String price = "$" + inPrice;

            imageView.setImageResource(inImage);
            name.setText(inName);
            description.setText(inDescription);
            priceText.setText(price);
        }
        Button button = view.findViewById(R.id.display_instrument_addToCart_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int inStock1 = 0;
                Cursor cursor1 = db.query("INSTRUMENTS", null, "_id = ?", new String[]{Integer.toString(key)}, null, null, null);
                if(cursor1.moveToFirst()) {
                    inStock1 = cursor1.getInt(cursor1.getColumnIndex("STOCK"));
                    int sold = cursor1.getInt(cursor1.getColumnIndex("SOLD"));
                    SQLiteDatabase dbWrite = sqLiteOpenHelper.getWritableDatabase();
                    if (inStock1 > 0) {
                        InstrumentsSQLiteOpenHelper.insertInstrumentCart(dbWrite, inName, inImage, inType, inPrice, inDescription, inStock, inOutOfStock);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("STOCK", inStock1 - 1);
                        contentValues.put("SOLD", sold + 1);
                        dbWrite.update("INSTRUMENTS", contentValues, "_id = ?", new String[]{Integer.toString(key)});
                        Toast.makeText(getActivity(), "Instrument Added To Cart!", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getActivity(), "This Instrument is Out Of Stock", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getActivity(), "This Instrument is Out Of Stock", Toast.LENGTH_SHORT).show();
                cursor1.close();
            }
        });
        cursor.close();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_instrument, container, false);
    }
}