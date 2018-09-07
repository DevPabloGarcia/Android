package pablogarcia.meetup.Managers.DatabaseManager;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import pablogarcia.meetup.Model.Meet;

public class DatabaseManager {

    private DatabaseReference firebaseDatabase;

    public DatabaseManager() {
        this.firebaseDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void putMeet(Meet meet, final OnDatabaseSaveListener listener){
        DatabaseReference meetUrl = this.firebaseDatabase.child("Meets");
        meetUrl.child(meet.getId()).setValue(meet).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    listener.onSaveSuccess(task);
                }else{
                    listener.onSaveFailure(task.getException());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.onSaveFailure(e);
            }
        });
    }

    public void getCurrentMeets(final OnDatabaseGetListener listener){

        firebaseDatabase.child("Meets").orderByChild("initDateMillis").startAt(System.currentTimeMillis()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Meet> meets = new ArrayList<>();
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    meets.add(messageSnapshot.getValue(Meet.class));
                }
                listener.onGetSuccess(meets);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("ERROR", databaseError.getMessage());
            }
        });
    }

    public void getFinishedMeets(final OnDatabaseGetListener listener){

        firebaseDatabase.child("Meets").orderByChild("initDateMillis").endAt(System.currentTimeMillis()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Meet> meets = new ArrayList<>();
                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
                    meets.add(messageSnapshot.getValue(Meet.class));
                }
                listener.onGetSuccess(meets);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("ERROR", databaseError.getMessage());
            }
        });
    }




}
