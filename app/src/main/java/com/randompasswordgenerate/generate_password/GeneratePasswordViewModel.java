package com.randompasswordgenerate.generate_password;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.randompasswordgenerate.model.PasswordModel;

public class GeneratePasswordViewModel extends ViewModel
{

    public MutableLiveData<String> stringMutableLiveData;
    private FirebaseFirestore fireStore;

    public GeneratePasswordViewModel()
    {
        stringMutableLiveData = new MutableLiveData<>();
        fireStore = FirebaseFirestore.getInstance();
    }

    public void uploadPassword(String password, String passwordTitle)
    {
            String randomKey = FirebaseDatabase.getInstance().getReference().push().getKey();

            PasswordModel passwordModel = new PasswordModel(randomKey, password, passwordTitle);
            fireStore
                    .collection("Generate Password")
                    .add(passwordModel)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>()
            {
                @Override
                public void onSuccess(DocumentReference documentReference)
                {
                    stringMutableLiveData.setValue("Done");
                }
            }).addOnFailureListener(new OnFailureListener()
            {
                @Override
                public void onFailure(@NonNull Exception e)
                {
                    stringMutableLiveData.setValue(e.getMessage());
                }
            });
    }
}
