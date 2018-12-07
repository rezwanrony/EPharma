package com.appersden.epharma;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class MedicineListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    SearchView et_search;
    ArrayList<Medicine> medicineList;
    List searchlist;
    ListView lv_medicine;
    EmployeeAdapter customMedicineListAdapter;
    String search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicinelist);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        et_search=(SearchView) findViewById(R.id.et_search);
        lv_medicine=(ListView)findViewById(R.id.lv_medicinelist);
        medicineList=new ArrayList<Medicine>();
        searchlist=new ArrayList();
        medicineList.add(new Medicine("Napa Extra","Square","10$"));
        medicineList.add(new Medicine("Paracetamol","Square","10$"));
        medicineList.add(new Medicine("Tusca","Square","10$"));
        medicineList.add(new Medicine("Antacid","Square","10$"));
        medicineList.add(new Medicine("Ace","Square","10$"));
        medicineList.add(new Medicine("Brozedex","Square","10$"));
        medicineList.add(new Medicine("Flexo","Square","10$"));
        customMedicineListAdapter=new EmployeeAdapter(MedicineListActivity.this,medicineList);
        lv_medicine.setAdapter(customMedicineListAdapter);

        search=getIntent().getStringExtra("search");
        et_search.setQuery(search,true);
        Filter filter = customMedicineListAdapter.getFilter();
        filter.filter(search);
        lv_medicine.setTextFilterEnabled(true);
        setupSearchView();

        lv_medicine.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MedicineListActivity.this,Main2Activity.class));
            }
        });


    }

    private void setupSearchView()
    {
        et_search.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Filter filter = customMedicineListAdapter.getFilter();
        if (TextUtils.isEmpty(newText)) {
            filter.filter("");
        } else {

            filter.filter(newText);
        }
        return true;
    }
}
