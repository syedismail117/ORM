package com.example.ormnew;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class itemAdapter extends RecyclerView.Adapter<itemAdapter.itemInfo> {

    Context context;

    public itemAdapter(Context context) {
        this.context = context;
    }



    @NonNull
    @Override
    public itemAdapter.itemInfo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        return new itemInfo ( itemView )  ;
    }

    @Override
    public void onBindViewHolder(@NonNull itemAdapter.itemInfo holder, int position) {
        final itemsInfo item = DataBaseController.getInstance().dataList.get(position);

        holder.text1.setText ( item.getTitle () );
        holder.text2.setText ( item.getDescription () );

        holder.edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DataBaseController.getInstance().data = null;
                DataBaseController.getInstance().data = DataBaseController.getInstance().dataList.get(holder.getAdapterPosition());
                context.startActivity(new Intent (context, MainActivity.class));
            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle("Delete User")
                        .setMessage("Are you sure to delete this user?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DataBaseController.getInstance().data = null;
                                DataBaseController.getInstance().data = DataBaseController.getInstance().dataList.get(holder.getAdapterPosition());
                                DataBaseController.getInstance().deleteSingleUserData(DataBaseController.getInstance().data);
                                DataBaseController.getInstance().fetchUserData();
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null).create();dialog.setCancelable(false);
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {

        if (DataBaseController.getInstance().dataList.size() > 0) {
            return DataBaseController.getInstance().dataList.size();
        } else {
            return 0;
        }
    }

    public class itemInfo extends RecyclerView.ViewHolder {

        TextView text1,text2;
        Button edit, delete;

        public itemInfo(@NonNull View itemView) {
            super ( itemView );

            text1=itemView.findViewById ( R.id.cardTitle );
            text2=itemView.findViewById ( R.id.cardDis );

            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
