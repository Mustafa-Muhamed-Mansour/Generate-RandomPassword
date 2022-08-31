package com.randompasswordgenerate.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.randompasswordgenerate.databinding.ItemPasswordBinding;
import com.randompasswordgenerate.generate.GeneratePassword;
import com.randompasswordgenerate.model.PasswordModel;

import java.util.ArrayList;

public class GeneratePasswordAdapter extends RecyclerView.Adapter<GeneratePasswordAdapter.GeneratePasswordViewHolder>
{
    
    private ArrayList<PasswordModel> passwordModels;

    private GeneratePassword generatePassword;

    public GeneratePasswordAdapter(ArrayList<PasswordModel> passwordModels, GeneratePassword generatePassword)
    {
        this.passwordModels = passwordModels;
        this.generatePassword = generatePassword;
    }

    @NonNull
    @Override
    public GeneratePasswordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new GeneratePasswordViewHolder(ItemPasswordBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GeneratePasswordViewHolder holder, int position)
    {
        PasswordModel model = passwordModels.get(position);
        holder.passwordBinding.itemTxtTitlePassword.setText(model.getPasswordTitle());
        holder.passwordBinding.itemTxtPassword.setText(model.getPassword());

        holder.passwordBinding.imgCopyPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                generatePassword.GenePass(model);

                holder.passwordBinding.imgCopyPassword.setVisibility(View.GONE);
                holder.passwordBinding.imgCorrectCopy.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return passwordModels.size();
    }

    public class GeneratePasswordViewHolder extends RecyclerView.ViewHolder
    {

        private ItemPasswordBinding passwordBinding;

        public GeneratePasswordViewHolder(@NonNull ItemPasswordBinding passwordBinding)
        {
            super(passwordBinding.getRoot());
            this.passwordBinding = passwordBinding;
        }
    }
}
