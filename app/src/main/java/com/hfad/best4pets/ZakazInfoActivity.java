package com.hfad.best4pets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ZakazInfoActivity extends AppCompatActivity {
    private EditText mNameTxt;
    private EditText mPhoneTxt;
    private EditText mAddressTxt;
    private Button mButtonOplata;
    private String summa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakaz_info);
        mNameTxt=(EditText)findViewById(R.id.name_editText);
        mPhoneTxt=(EditText)findViewById(R.id.number_editText);
        mAddressTxt=(EditText)findViewById(R.id.address_editText);
        mButtonOplata=(Button)findViewById(R.id.enter_button);
        summa= getIntent().getStringExtra("summa");
        mButtonOplata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date currentDate = new Date();
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                String dateText = dateFormat.format(currentDate);
                DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                String timeText = timeFormat.format(currentDate);
                ZakazInfo zakazInfo = new ZakazInfo();
                zakazInfo.setName(mNameTxt.getText().toString());
                zakazInfo.setPhoneNumber(mPhoneTxt.getText().toString());
                zakazInfo.setAddress(mAddressTxt.getText().toString());
                zakazInfo.setDate(dateText);
                zakazInfo.setTime(timeText);
                zakazInfo.setSumma(summa);
                new FirebaseDatabaseHelper("заказы").addZakaz(zakazInfo, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Tovar> items, List<String> keys) {

                    }

                    @Override
                    public void DataIsLoaded2(List<ZakazInfo> zakazInfoList, List<String> keys) {

                    }


                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated(List<Tovar> items, List<String> keys, int prise) {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
                Intent intent = new Intent(ZakazInfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
