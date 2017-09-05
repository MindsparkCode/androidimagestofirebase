package com.a2l.cheatoye;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by Saeed Baig on 2/27/2017.
 */

public class FirebaseHelper {
    DatabaseReference db;
    Boolean saved = null;
    ArrayList<String> images = new ArrayList<>();
    String encodedImage;

    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
    }

    public Boolean save(Data data) {
        if (data == null) {
            saved = false;
        } else {
            try {
                db.child("Photo").push().setValue(data);
                saved = true;
            } catch (DatabaseException e) {
                e.printStackTrace();
                saved = false;
            }
        }
        return saved;
    }

    public ArrayList<String> retrieve() {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return images;
    }

    private void fetchData(DataSnapshot dataSnapshot) {
        images.clear();
//        HashMap<String, String> hashMap = (HashMap<String, String>) dataSnapshot.getValue();
//        hashMap.get("Photo");
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            String d = ds.getValue(Data.class).getImag(); //--> at this point, you tried to assign a String to an Object with type "protest" by conversion. This is illegal, so it will throw an exception instead.
            images.add(d);
        }
    }

}


