package com.randompasswordgenerate.relative_password;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.randompasswordgenerate.model.PasswordModel;

import java.util.ArrayList;

public class RelativeGeneratePasswordViewModel extends ViewModel
{

    private ArrayList<PasswordModel> passwordModels;
    private FirebaseFirestore fireStore;
    private MutableLiveData<String> stringMutableLiveData;
    private MutableLiveData<ArrayList<PasswordModel>> listMutableLiveData;


    public RelativeGeneratePasswordViewModel()
    {
        passwordModels = new ArrayList<>();
        fireStore = FirebaseFirestore.getInstance();
        stringMutableLiveData = new MutableLiveData<>();
        listMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<PasswordModel>> retriveGeneratePassword()
    {
        fireStore
                .collection("Generate Password")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>()
                {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots)
                    {
                        passwordModels.clear();

                        for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments())
                        {
                            PasswordModel passwordModel = snapshot.toObject(PasswordModel.class);
                            passwordModels.add(passwordModel);
                        }
                        listMutableLiveData.postValue(passwordModels);
                        stringMutableLiveData.setValue("Done ya desha");
                    }
                }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                stringMutableLiveData.setValue(e.getMessage());
            }
        });

        return listMutableLiveData;
    }
}