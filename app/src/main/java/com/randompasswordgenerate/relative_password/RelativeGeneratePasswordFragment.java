package com.randompasswordgenerate.relative_password;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.randompasswordgenerate.adapter.GeneratePasswordAdapter;
import com.randompasswordgenerate.databinding.RelativeGeneratePasswordFragmentBinding;
import com.randompasswordgenerate.generate.GeneratePassword;
import com.randompasswordgenerate.model.PasswordModel;

import java.util.ArrayList;

public class RelativeGeneratePasswordFragment extends Fragment implements GeneratePassword
{

    private RelativeGeneratePasswordFragmentBinding binding;
    private RelativeGeneratePasswordViewModel relativeGeneratePasswordViewModel;
    private GeneratePasswordAdapter passwordAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        binding = RelativeGeneratePasswordFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        relativeGeneratePasswordViewModel = new ViewModelProvider(requireActivity()).get(RelativeGeneratePasswordViewModel.class);

        relativeGeneratePasswordViewModel.retriveGeneratePassword().observe(getViewLifecycleOwner(), new Observer<ArrayList<PasswordModel>>()
        {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(ArrayList<PasswordModel> passwordModels)
            {
                passwordAdapter = new GeneratePasswordAdapter(passwordModels, RelativeGeneratePasswordFragment.this);
                binding.rVRelative.setAdapter(passwordAdapter);
                binding.rVRelative.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
                binding.rVRelative.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
                passwordAdapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void GenePass(PasswordModel passwordModel)
    {
        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data = ClipData.newPlainText("Edittext", passwordModel.getPassword());
        clipboard.setPrimaryClip(data);
        Toast.makeText(requireContext(), "text copied", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        relativeGeneratePasswordViewModel.retriveGeneratePassword().removeObservers(requireActivity());
    }
}