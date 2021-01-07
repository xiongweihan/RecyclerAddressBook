package com.hj.recycleraddressbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AddressBookAdapter extends RecyclerView.Adapter<AddressBookAdapter.AddressBookHolder> {


    private Context context;
    private List<PersonInfoBean> list;
    public AddressBookAdapter(Context context, List<PersonInfoBean> list) {
        this.context =context;
        this.list = list;

    }

    @NonNull
    @Override
    public AddressBookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_adapter_layout, parent, false);
        return new AddressBookHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressBookHolder holder, int position) {
        holder.tvTitle.setText(list.get(position).getName());
    }


    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }


    public static class AddressBookHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        public AddressBookHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }

}
