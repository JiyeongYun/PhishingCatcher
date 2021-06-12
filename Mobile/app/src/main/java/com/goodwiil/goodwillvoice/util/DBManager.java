package com.goodwiil.goodwillvoice.util;

import android.util.Log;

import androidx.annotation.NonNull;

import com.goodwiil.goodwillvoice.model.CallLogInfo;
import com.goodwiil.goodwillvoice.model.CallNumber;
import com.goodwiil.goodwillvoice.model.PhoneCall;
import com.goodwiil.goodwillvoice.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DBManager {
    public static FirebaseFirestore db;
    public static FirebaseAuth auth;

    public DBManager() {
        db = FirebaseFirestore.getInstance();
    }

    public void AuthManger() {auth = FirebaseAuth.getInstance();}

    public static void insertData(User data) {
        Map<String, Object> user = new HashMap<>();
        user.put("gender", data.getGender());
        user.put("year", data.getYear());
        user.put("nickname", data.getNickName());
        user.put("job", data.getCareer());
        user.put("number", data.getNumber());
        user.put("creditRating", data.getCreditRating());

        // Add a new document with a generated ID
        db.collection("User")
                .document(data.getNumber())
                .set(user);

    }

    public static void insertData(PhoneCall data) {
        Map<String, Object> user = new HashMap<>();
        user.put("user", data.getUser());
        user.put("call", data.getCallLogInfo());

        // Add a new document with a generated ID
        db.collection("CallLog")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                    }
                });

    }

    public static void insertUserCallLogData(CallNumber data, User user) {

        Map<String, Object> callLog = new HashMap<>();
        callLog.put(data.getNumber(), data);

        // Add a new document with a generated ID
        db.collection("UserCallLog")
                .document(user.getNumber())
                .collection("CallNumbers")
                .document(data.getNumber())
                .set(data);

        db.collection("UserCallLog")
                .document(user.getNumber())
                .set(user);


    }
}
