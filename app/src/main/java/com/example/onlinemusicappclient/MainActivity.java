package com.example.onlinemusicappclient;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinemusicappclient.Adapter.recyclerViewAdapter;
import com.example.onlinemusicappclient.Model.Uploads;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    recyclerViewAdapter adapter;
    DatabaseReference mDatabase;
    ProgressDialog progressDialog;
    private List<Uploads> uploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         recyclerView = findViewById(R.id.recyclerview_id);
         recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
         progressDialog = new ProgressDialog(this);
         uploads = new ArrayList<>();
         progressDialog.setMessage("please wait...");
         progressDialog.show();
         mDatabase = FirebaseDatabase.getInstance().getReference("uploads");
         mDatabase.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                   progressDialog.dismiss();
                 for (DataSnapshot postsnapshot : snapshot.getChildren()){
                            Uploads upload = postsnapshot.getValue(Uploads.class);
                            uploads.add(upload);
                   }
                   adapter = new recyclerViewAdapter( getApplicationContext(),uploads);
                   recyclerView.setAdapter(adapter);
                   adapter.notifyDataSetChanged();
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

                 progressDialog.dismiss();
             }
         });
    }
}