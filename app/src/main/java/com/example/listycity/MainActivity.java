package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView city_list;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    int selectedCityPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city_list = findViewById(R.id.city_list);
        String[] cities = {"Edmonton", "Vancouver"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        city_list.setAdapter(cityAdapter);

        LinearLayout addCityContainer = findViewById(R.id.add_city_container);
        EditText addCityEditText = findViewById(R.id.add_city_edit_text);
        Button addCityButton = findViewById(R.id.add_city_button);
        Button confirmButton = findViewById(R.id.confirm_button);

        addCityButton.setOnClickListener(v -> addCityContainer.setVisibility(View.VISIBLE));

        confirmButton.setOnClickListener(v -> {
            String cityName = addCityEditText.getText().toString();
            if (!cityName.isEmpty()) {
                dataList.add(cityName);
                cityAdapter.notifyDataSetChanged();
                addCityEditText.setText("");
                addCityContainer.setVisibility(View.GONE);
            }
        });

        city_list.setOnItemClickListener((parent, view, position, id) -> selectedCityPosition = position);

        Button deleteCityButton = findViewById(R.id.delete_city_button);
        deleteCityButton.setOnClickListener(v -> {
            if (selectedCityPosition != -1) {
                dataList.remove(selectedCityPosition);
                cityAdapter.notifyDataSetChanged();
                selectedCityPosition = -1; // Reset selection
            }
        });
    }
}
