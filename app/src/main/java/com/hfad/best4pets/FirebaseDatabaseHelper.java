package com.hfad.best4pets;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceBooks;
    private DatabaseReference mReferenceBooksZakaz;
    private List<Tovar> tovars = new ArrayList<>();
    private List<ZakazInfo> zakazInfoList = new ArrayList<>();
    private FirebaseAuth mAuth;
    private int prise;


    public interface DataStatus{
        void DataIsLoaded(List<Tovar> tovars, List<String> keys);
        void DataIsLoaded2(List<ZakazInfo> zakazInfoList, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated(List<Tovar> tovars, List<String> keys, int prise);
        void DataIsDeleted();
    }



    public  FirebaseDatabaseHelper(String bd) {
        mDatabase= FirebaseDatabase.getInstance();
        mReferenceBooks = mDatabase.getReference(bd);
    }
    public void readItems(final DataStatus dataStatus){
        mReferenceBooks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tovars.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keyNode.getKey();
                    keys.add(keyNode.getKey());
                    Tovar tovar = keyNode.getValue(Tovar.class);
                    tovars.add(tovar);
                }
                dataStatus.DataIsLoaded(tovars, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void readKorzina(final DataStatus dataStatus){
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        String key = user.getUid();

        mReferenceBooks.child(key).child("корзина").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                prise=0;
                tovars.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keyNode.getKey();
                    keys.add(keyNode.getKey());
                    Tovar tovar = keyNode.getValue(Tovar.class);
                    prise=prise+ tovar.getCost()* tovar.getHow();
                    tovars.add(tovar);
                }
                dataStatus.DataIsUpdated(tovars, keys, prise);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void addZakaz(final ZakazInfo zakazInfo, final DataStatus dataStatus){
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        final String uid = user.getUid();
        final String id = mReferenceBooks.push().getKey();
        final String idHistory = mReferenceBooks.push().getKey();
        mReferenceBooks.child(uid).child(id).setValue(zakazInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsInserted();
            }
        });
        mReferenceBooksZakaz = mDatabase.getReference("users");
        mReferenceBooksZakaz.child(uid).child("история").child(id).setValue(zakazInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            }
        });


        mReferenceBooksZakaz.child(uid).child("корзина").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tovars.clear();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keyNode.getKey();
                    Tovar tovar = keyNode.getValue(Tovar.class);
                    tovars.add(tovar);
                    mReferenceBooks.child(uid).child(id).child(keyNode.getKey()).setValue(tovar).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                        }
                    });
                    mReferenceBooksZakaz.child(uid).child("история").child(id).child(keyNode.getKey()).setValue(tovar).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                        }
                    });
                    mReferenceBooksZakaz.child(uid).child("корзина").child(keyNode.getKey()).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            dataStatus.DataIsDeleted();
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void addKorzina(String key, Tovar tovar, final DataStatus dataStatus){
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        String uid = user.getUid();
        mReferenceBooks.child(uid).child("корзина").child(key).setValue(tovar).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsInserted();
            }
        });
    }
    public void deletItems(String key, final DataStatus dataStatus){
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        String uid = user.getUid();
        mReferenceBooks.child(uid).child("корзина").child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsDeleted();
            }
        });
    }
    public void readHistori(final DataStatus dataStatus){
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
       final String key = user.getUid();
        mReferenceBooks.child(key).child("история").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                zakazInfoList.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    keyNode.getKey();
                    keys.add(keyNode.getKey());
                    ZakazInfo zakazInfo = keyNode.getValue(ZakazInfo.class);
                    zakazInfoList.add(zakazInfo);
                }
                dataStatus.DataIsLoaded2(zakazInfoList, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void deleteBook(final DataStatus dataStatus){
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        final String uid = user.getUid();
        final String id = mReferenceBooks.push().getKey();
        mReferenceBooks.child(uid).child("корзина").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tovars.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keyNode.getKey();
                    keys.add(keyNode.getKey());
                    Tovar tovar = keyNode.getValue(Tovar.class);
                    tovars.add(tovar);
                    String idbook = mReferenceBooks.push().getKey();
                    mReferenceBooks.child(uid).child("история").child(id).child(idbook).setValue(tovar).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            dataStatus.DataIsDeleted();
                        }
                    });

                }
                dataStatus.DataIsLoaded(tovars, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void readHistoryIems(String id, final DataStatus dataStatus){
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        String key = user.getUid();
        mReferenceBooks.child(key).child("история").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tovars.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keyNode.getKey();
                    if (keyNode.getKey().equals("address")){
                        break;
                    }
                    keys.add(keyNode.getKey());
                    Tovar tovar = keyNode.getValue(Tovar.class);
                    tovars.add(tovar);
                }
                dataStatus.DataIsLoaded(tovars, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void updateItems(String key, Tovar tovar, final DataStatus dataStatus){

        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        String uid = user.getUid();
        mReferenceBooks.child(uid).child("корзина").child(key).setValue(tovar).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            }
        });
    }
}
