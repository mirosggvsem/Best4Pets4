package com.hfad.best4pets;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import java.util.List;


public class KorzinaFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private FirebaseAuth mAuth;
    private Button mButton;
    private TextView mTotalPrice;
    private ImageView mImageKorzina;
    private TextView mText;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
             final View layout = inflater.inflate(R.layout.fragment_korzina, container, false);


            mAuth = FirebaseAuth.getInstance();
            mText=(TextView) layout.findViewById(R.id.textView_itogo);
            mRecyclerView = (RecyclerView) layout.findViewById(R.id.recyclerview_books);
            mButton = (Button) layout.findViewById(R.id.oplata_button);
            mTotalPrice = (TextView) layout.findViewById(R.id.TotalPrice_Txt);
            mImageKorzina=(ImageView)layout.findViewById(R.id.korzina_image);
            new FirebaseDatabaseHelper("users").readKorzina(new FirebaseDatabaseHelper.DataStatus() {
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
                    layout.findViewById(R.id.progressBar).setVisibility(View.GONE);
                    if (prise==0){
                        mImageKorzina.setImageDrawable(getResources().getDrawable(R.drawable.cart));
                    }else{
                    new RecyclerKorzina_Config().setConfig(mRecyclerView, layout.getContext(), items, keys);
                    mTotalPrice.setText(String.valueOf(prise));
                        mButton.setVisibility(View.VISIBLE);
                        mImageKorzina.setVisibility(View.GONE);
                        mText.setVisibility(View.VISIBLE);
                    }

                }
                @Override
                public void DataIsDeleted() {
                }
            });
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(layout.getContext(), ZakazInfoActivity.class);
                    intent.putExtra("summa", mTotalPrice.getText().toString());
                    startActivity(intent);
                }
            });
            return layout;

    }
}
