package com.hfad.best4pets;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class AccFragment extends Fragment {
    private Button mButton;
    private TextView mEmailTxt;
    private FirebaseAuth mAuth;


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View layout =inflater.inflate(R.layout.fragment_acc, container, false);
        mButton = (Button) layout.findViewById(R.id.exit_button);
        mEmailTxt=(TextView)layout.findViewById(R.id.email_textView);
        ListView listView = layout.findViewById(R.id.listView_acc);
        ListView listView1 = layout.findViewById(R.id.list_opthions_acc);
        final String[] cstegories = new String[] {
                "Контакты", "Оплата и доставка"
        };
        final String[] opthions = new String[] {
                "История заказов"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(layout.getContext(), android.R.layout.simple_list_item_1, cstegories);
        listView.setAdapter(adapter);
        ArrayAdapter<String> adapterOpthoins = new ArrayAdapter<>(layout.getContext(), android.R.layout.simple_list_item_1, opthions);
        listView1.setAdapter(adapterOpthoins);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    Intent intent = new Intent(layout.getContext(), infa_dostavka_activity.class);
                    startActivity(intent);
                }
                if (i==1){
                    Intent intent = new Intent(layout.getContext(), kontakt_activity.class);
                    startActivity(intent);
                }
            }
        });
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(layout.getContext(), HistoryActivity.class);
                startActivity(intent);

            }
        });
        mAuth= FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        mEmailTxt.setText(user.getEmail());
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                RecyclerView_Config.logout();
               Intent intent = new Intent(layout.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

            return layout;
    }
}
