package com.pma.ekaa.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.pma.ekaa.R;

import de.hdodenhof.circleimageview.CircleImageView;

    public class ItemHolder extends RecyclerView.ViewHolder {

        public TextView txtName,txtlastName, txtID, txtFamily, txtNation;
        public CircleImageView profileImage;

        public ItemHolder(View view) {
            super(view);
            txtName =  view.findViewById(R.id.txtName);
            txtlastName = view.findViewById(R.id.txtlastname);
            txtID = view.findViewById(R.id.txtID);
            profileImage =  view.findViewById(R.id.profileImage);
            txtNation = view.findViewById(R.id.txtNation);
            txtFamily = view.findViewById(R.id.txtFamily);


        }

    }

