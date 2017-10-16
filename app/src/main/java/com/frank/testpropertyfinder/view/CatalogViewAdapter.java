package com.frank.testpropertyfinder.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frank.testpropertyfinder.R;

class CatalogViewAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private CatalogAdaptedDataModel dataModel;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        vh.subject.setText(dataModel.getSubject(position));
        vh.price.setText(dataModel.getPrice(position));
        vh.bedrooms.setText(dataModel.getBedrooms(position));
    }

    @Override
    public int getItemCount() {
        return dataModel == null ? 0 : dataModel.getSize();
    }

    void setDataModel(CatalogAdaptedDataModel model) {
        this.dataModel = model;
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView subject;
        private final TextView price;
        private final TextView bedrooms;

        ViewHolder(View v) {
            super(v);
            subject = v.findViewById(R.id.subject);
            price = v.findViewById(R.id.price);
            bedrooms = v.findViewById(R.id.bedrooms);
        }
    }
}
