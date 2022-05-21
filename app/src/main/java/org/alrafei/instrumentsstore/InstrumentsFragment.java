package org.alrafei.instrumentsstore;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class InstrumentsFragment extends Fragment{


    List<Instruments> instrumentsList = new ArrayList<>();

    public InstrumentsFragment() {
        // Required empty public constructor
    }

    public static InstrumentsFragment newInstance (String type, String order){
        InstrumentsFragment f = new InstrumentsFragment();
        Bundle args = new Bundle();
        args.putString("Type", type);
        args.putString("Order", order);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        String type = args.getString("Type");
        String choice = args.getString("Order");

        Spinner spinner = getView().findViewById(R.id.spinner);
        if(choice.equalsIgnoreCase("Name"))
            spinner.setSelection(0);
        else if(choice.equalsIgnoreCase("Price"))
            spinner.setSelection(1);
        else if(choice.equalsIgnoreCase("Best Sales"))
            spinner.setSelection(2);
        Button button = getView().findViewById(R.id.button_confirm);
        Fragment fragment = this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String spinnerChoice = spinner.getSelectedItem().toString();
                FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, InstrumentsFragment.newInstance(type, spinnerChoice));
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack("");
                ft.commit();
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.instruments_recyclerView);
        SQLiteOpenHelper sqLiteOpenHelper = new InstrumentsSQLiteOpenHelper(getActivity());
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(getActivity(), sqLiteOpenHelper, db);
        if (type.equalsIgnoreCase("guitar")) {
            if(choice.equalsIgnoreCase("name"))
                instrumentsList = databaseAdapter.getGuitars();
            else if(choice.equalsIgnoreCase("price"))
                instrumentsList = databaseAdapter.getGuitarsPriceOrder();
            else instrumentsList = databaseAdapter.getGuitarsBestSeller();
        }
        else  if (type.equalsIgnoreCase("bass")) {
            if (choice.equalsIgnoreCase("name"))
                instrumentsList = databaseAdapter.getBass();
            else if (choice.equalsIgnoreCase("price"))
                instrumentsList = databaseAdapter.getBassPriceOrder();
            else instrumentsList = databaseAdapter.getBassBestSeller();
        }
        else{
            if (choice.equalsIgnoreCase("name"))
                instrumentsList = databaseAdapter.getAllInstruments();
            else if (choice.equalsIgnoreCase("price"))
                instrumentsList = databaseAdapter.getInstrumentsPriceOrder();
            else instrumentsList = databaseAdapter.getMostSoldInstruments();
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2 );
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        MyAdapter adapter = new MyAdapter(getContext(), instrumentsList, recyclerView, this);

        recyclerView.setAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_instruments, container, false);


    }

}