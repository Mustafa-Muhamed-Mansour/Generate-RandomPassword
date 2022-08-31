package com.randompasswordgenerate.generate_password;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.randompasswordgenerate.R;
import com.randompasswordgenerate.databinding.FragmentGeneratePasswordBinding;
import com.randompasswordgenerate.generate.GenePassword;

public class GeneratePasswordFragment extends Fragment
{

    private FragmentGeneratePasswordBinding binding;
    private GeneratePasswordViewModel generatePasswordViewModel;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentGeneratePasswordBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        generatePasswordViewModel = new ViewModelProvider(requireActivity()).get(GeneratePasswordViewModel.class);



        binding.btnGenerate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String password = GenePassword.process(binding.seekBar.getProgress());
                binding.txtPassword.setText(password);
            }
        });

        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                binding.txtPassword.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
            }
        });

        binding.btnUpload.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String passwordTitle = binding.editTitle.getText().toString();

                if (TextUtils.isEmpty(passwordTitle))
                {
                    binding.editTitle.setError("Please enter password title");
                    binding.editTitle.requestFocus();
                    return;
                }

                else
                {
                    generatePasswordViewModel.uploadPassword(binding.txtPassword.getText().toString(), passwordTitle);
                }
            }
        });

        generatePasswordViewModel.stringMutableLiveData.observe(getViewLifecycleOwner(), new Observer<String>()
        {
            @Override
            public void onChanged(String s)
            {
                if (s.equals("Done"))
                {
                    Navigation.findNavController(view).navigate(R.id.action_generatePasswordFragment_to_relativeGeneratePasswordFragment);
                }

                else
                {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        generatePasswordViewModel.stringMutableLiveData.removeObservers(requireActivity());
        generatePasswordViewModel.uploadPassword(null, null);
    }
}