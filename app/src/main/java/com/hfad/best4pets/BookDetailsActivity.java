package com.hfad.best4pets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;
import java.util.List;

public class BookDetailsActivity extends AppCompatActivity {
    private ImageView mImage;
    private TextView mName;
    private TextView mSostav;
    private TextView mProt;
    private TextView mZhir;
    private TextView mKletchatka;
    private TextView mZola;
    private TextView mCost;
    private TextView mSumma;
    private String name;
    private String letchatka;
    private String protein;
    private String sostav;
    private String zhir;
    private String zola;
    private String key;
    private String path;
    private int cost;
    private int how;
    private int now;
    private ImageButton mMinusBtn;
    private ImageButton mPlussBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        mSumma=(TextView)findViewById(R.id.summa_textView);
        name=getIntent().getStringExtra("name");
        cost=getIntent().getIntExtra("cost",cost);
        how=getIntent().getIntExtra("how",how);
        key= getIntent().getStringExtra("key");
        letchatka= getIntent().getStringExtra("letchatka");
        protein=getIntent().getStringExtra("protein");
        sostav=getIntent().getStringExtra("sostav");
        zhir=getIntent().getStringExtra("zhir");
        zola=getIntent().getStringExtra("zola");
        path=getIntent().getStringExtra("image");
        mImage=(ImageView)findViewById(R.id.detail_image);
        Picasso.get().load(path).into(mImage);
        mName=(TextView)findViewById(R.id.name_text);
        mName.setText(name);
        mSostav=(TextView)findViewById(R.id.sostav_text);
        mSostav.setText(sostav);
        mProt=(TextView)findViewById(R.id.protein_text);
        mProt.setText(protein);
        mZhir=(TextView)findViewById(R.id.zhir_text);
        mZhir.setText(zhir);
        mKletchatka=(TextView)findViewById(R.id.kletchatka_text);
        mKletchatka.setText(letchatka);
        mZola=(TextView)findViewById(R.id.zola_text);
        mZola.setText(zola);
        mCost=(TextView)findViewById(R.id.cost_text_details);
        now=how*cost;
        mCost.setText(String.valueOf(now));
        mMinusBtn=(ImageButton) findViewById(R.id.minus_imageButton);
        mPlussBtn=(ImageButton) findViewById(R.id.plus_imageButton);

        mMinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (how!=1) {
                    how--;
                    now = how * cost;
                    mSumma.setText(String.valueOf(how));
                    mCost.setText(String.valueOf(now));
                }
            }
        });
        mPlussBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                how++;
                now=how*cost;
                mSumma.setText(String.valueOf(how));
                mCost.setText(String.valueOf(now));
            }
        });

    }


    public void onClickDone(View view){
        CharSequence text = "Your order has been updated";
        int duration  = Snackbar.LENGTH_SHORT;
        Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator), text, duration);
        Tovar tovar = new Tovar();
        tovar.setName(mName.getText().toString());
        tovar.setLetchatka(mKletchatka.getText().toString());
        tovar.setProtein(mProt.getText().toString());
        tovar.setSostav(mSostav.getText().toString());
        tovar.setZhir(mZhir.getText().toString());
        tovar.setZola(mZola.getText().toString());
        tovar.setCost(cost);
        tovar.setImage(path);
        tovar.setHow(how);
        new FirebaseDatabaseHelper("users").addKorzina(key, tovar, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Tovar> items, List<String> keys) {
                    }
            @Override
            public void DataIsLoaded2(List<ZakazInfo> zakazInfoList, List<String> keys) {

            }
                    @Override
                    public void DataIsInserted() {
                        Toast.makeText(BookDetailsActivity.this, "Товар добавлен в корзину ", Toast.LENGTH_LONG).show();
                    }
             @Override
             public void DataIsUpdated(List<Tovar> items, List<String> keys, int prise) {
                }
                    @Override
                    public void DataIsDeleted() {
                    }
                });
        snackbar.show();
    }

}
