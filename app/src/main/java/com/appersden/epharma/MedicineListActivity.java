package com.appersden.epharma;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MedicineListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    SearchView et_search;
    ArrayList<Medicine> medicineList;
    List searchlist;
    ListView lv_medicine;
    EmployeeAdapter customMedicineListAdapter;
    LinearLayout linearLayoutcard;
    String search;
    ImageView img_logo,img_background;
    CardView cardView;
    ActionBar actionBar;
    Toolbar toolbar;
    Button btn_back,btn_notification,btn_signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicinelist);
        actionBar=getSupportActionBar();
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        btn_back=(Button)findViewById(R.id.toolbar_back);
        btn_notification=(Button)findViewById(R.id.toolbar_notification);
        btn_signin=(Button)findViewById(R.id.toolbar_signin);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        et_search=(SearchView) findViewById(R.id.et_search);
        linearLayoutcard=(LinearLayout)findViewById(R.id.llcard);
        lv_medicine=(ListView)findViewById(R.id.lv_medicinelist);
        img_logo=(ImageView)findViewById(R.id.img_logo);
        img_background=(ImageView)findViewById(R.id.img_backgroundmedicinelist);
        cardView=(CardView)findViewById(R.id.cardview);
        medicineList=new ArrayList<Medicine>();
        searchlist=new ArrayList();

        btn_back.setBackground(null);
        btn_back.setText("Login");
        btn_back.setTextColor(getResources().getColor(android.R.color.white));
        lv_medicine.setVisibility(lv_medicine.GONE);
        btn_notification.setVisibility(btn_notification.GONE);
        btn_signin.setVisibility(btn_signin.VISIBLE);
        cardView.setCardBackgroundColor(getResources().getColor(android.R.color.transparent));
        cardView.setCardElevation(0);
        cardView.setPadding(0,0,0,0);
        setupSearchView();

        medicineList.add(new Medicine("Napa Extra","Square","10$"));
        medicineList.add(new Medicine("Paracetamol","Square","10$"));
        medicineList.add(new Medicine("Tusca","Square","10$"));
        medicineList.add(new Medicine("Antacid","Square","10$"));
        medicineList.add(new Medicine("Ace","Square","10$"));
        medicineList.add(new Medicine("Brozedex","Square","10$"));
        medicineList.add(new Medicine("Flexo","Square","10$"));
        customMedicineListAdapter=new EmployeeAdapter(MedicineListActivity.this,medicineList);


         et_search.setOnSearchClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 lv_medicine.setVisibility(lv_medicine.GONE);
             }
         });

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

    private void setupListview()
    {
        lv_medicine.setAdapter(customMedicineListAdapter);
        lv_medicine.setTextFilterEnabled(true);

    }

    @Override
    public boolean onQueryTextSubmit(String query)

    {
        Toast.makeText(this, "searchview is clicked", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        btn_back.setVisibility(btn_back.VISIBLE);
        btn_back.setBackground(getResources().getDrawable(R.drawable.back));
        btn_back.setText("");
        btn_signin.setVisibility(btn_signin.GONE);
        btn_notification.setVisibility(btn_notification.VISIBLE);
        lv_medicine.setVisibility(lv_medicine.GONE);
        btn_notification.setVisibility(btn_notification.GONE);
        cardView.setCardBackgroundColor(getResources().getColor(android.R.color.white));
        cardView.setCardElevation(10);
        cardView.setPadding(0,10,0,0);
        img_background.setVisibility(img_background.GONE);
        lv_medicine.setVisibility(lv_medicine.VISIBLE);
        cardView.setVisibility(cardView.VISIBLE);
        setupListview();
        Filter filter = customMedicineListAdapter.getFilter();
        if (TextUtils.isEmpty(newText)) {
            filter.filter("");
        } else {

            filter.filter(newText);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        btn_back.setBackground(null);
        btn_back.setText("Login");
        btn_back.setTextColor(getResources().getColor(android.R.color.white));
        img_background.setVisibility(img_background.VISIBLE);
        lv_medicine.setVisibility(lv_medicine.GONE);
        cardView.setCardBackgroundColor(getResources().getColor(android.R.color.transparent));
        cardView.setCardElevation(0);
        cardView.setPadding(0,0,0,0);
        setupSearchView();

    }


}
