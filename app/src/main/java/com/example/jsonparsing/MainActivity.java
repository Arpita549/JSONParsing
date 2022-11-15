package com.example.jsonparsing;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import android.os.Bundle;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    //Arraylist for employees name, age and salary
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> ages = new ArrayList<>();
    ArrayList<String> salaries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        try {
            JSONObject jsonObject = new JSONObject(loadJSONFromAsset());
            JSONArray employeeArray = jsonObject.getJSONArray("employees");
            for(int i = 0; i < employeeArray.length(); i++){
                JSONObject empDetail = employeeArray.getJSONObject(i);
                names.add(empDetail.getString("name"));
               /* ages.add(String.valueOf(empDetail.getInt("age")));
                salaries.add(String.valueOf(empDetail.getInt("salaries")));*/
                //or
                JSONObject number = empDetail.getJSONObject("number");
                ages.add(number.getString("age"));
                salaries.add(number.getString("salary"));
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, names, ages,salaries);
        recyclerView.setAdapter(customAdapter);
    }

    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream inputStream = getAssets().open("employees_list.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        }catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}