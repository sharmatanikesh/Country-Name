package com.example.countryname;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.countryname.Model.CountryModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder>implements Filterable {
     Context context;
    ArrayList<CountryModel> countries;
     ArrayList<CountryModel> backup;

    public CountryAdapter(ArrayList<CountryModel> countries, Context context) {
        this.countries = countries;
        this.context = context;
        backup = new ArrayList<>(countries);
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.single_row, parent,false);

        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.textView.setText(countries.get(position).getName());
        holder.CountryCode.setText(countries.get(position).getCode());
    }



    @Override
    public int getItemCount() {
        return countries.size();
    }




    //View Holder
    class CountryViewHolder extends RecyclerView.ViewHolder  {
        TextView textView;
        TextView CountryCode;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView2);
            CountryCode = itemView.findViewById(R.id.countryCode);
        }

    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence keyword) {
            ArrayList<CountryModel> filterData = new ArrayList<>();

            if(keyword.toString().isEmpty()){
                filterData.addAll(backup);
            }else{
                for(CountryModel obj :backup){
                    if(obj.getName().toString().toLowerCase().contains(keyword.toString().toLowerCase())){
                        filterData.add(obj);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterData;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            countries.clear();
            countries.addAll((ArrayList<CountryModel>)results.values);
            notifyDataSetChanged();
        }
    };
}
