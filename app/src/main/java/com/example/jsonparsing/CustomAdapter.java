package com.example.jsonparsing;




import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    ArrayList<String> names;
    ArrayList<String> ages;
    ArrayList<String> salaries;

    public CustomAdapter(Context context, ArrayList<String> names, ArrayList<String> ages, ArrayList<String> salaries) {
        this.context = context;
        this.names = names;
        this.ages = ages;
        this.salaries = salaries;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    @NonNull
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.name.setText(names.get(position));
        holder.age.setText(ages.get(position));
        holder.salary.setText(salaries.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, names.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {

        return names.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, age, salary;

        public MyViewHolder(View itemView){
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            age = (TextView) itemView.findViewById(R.id.age);
            salary = (TextView) itemView.findViewById(R.id.salary);
        }
    }
}
