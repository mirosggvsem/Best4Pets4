package com.hfad.best4pets;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerView_Config {
    FirebaseAuth mAuth;
    private static FirebaseUser user;
    private Context mContext;
    private TovarsAdapter mTovarsAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Tovar> items, List<String> keys){
        mAuth=FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mContext=context;
        mTovarsAdapter = new TovarsAdapter(items, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mTovarsAdapter);
    }


    class BookItemView extends RecyclerView.ViewHolder {
        private TextView mName;
        private TextView mCost;
        private String path;
        private int cost;
        private ImageView mImage;
        private String key;
        private int how;
        private String letchatka;
        private String protein;
        private String sostav;
        private String zhir;
        private String zola;

        public BookItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.list_items_history, parent, false));
            mName = (TextView) itemView.findViewById(R.id.opisanie_text);
            mImage = (ImageView) itemView.findViewById(R.id.image_corzina);
            mCost=(TextView) itemView.findViewById(R.id.cost_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, BookDetailsActivity.class);
                    intent.putExtra("key", key);
                    intent.putExtra("name", mName.getText().toString());
                    intent.putExtra("cost", cost);
                    intent.putExtra("image",path);
                    intent.putExtra("how", how);
                    intent.putExtra("letchatka", letchatka);
                    intent.putExtra("protein", protein);
                    intent.putExtra("sostav", sostav);
                    intent.putExtra("zhir", zhir);
                    intent.putExtra("zola", zola);
                    mContext.startActivity(intent);
                }
            });
        }
        public void bind(Tovar tovar, String key){
            mName.setText(tovar.getName());
            mCost.setText(String.valueOf(tovar.getCost()));
            path= tovar.getImage();
            cost= tovar.getCost();
            Picasso.get().load(tovar.getImage()).into(mImage);
            how= tovar.getHow();
            letchatka=tovar.getLetchatka();
            protein=tovar.getProtein();
            sostav=tovar.getSostav();
            zhir=tovar.getZhir();
            zola=tovar.getZola();
            this.key=key;
        }
    }
    class TovarsAdapter extends RecyclerView.Adapter<BookItemView>{
        private List<Tovar> mTovarList;
        private List<String> mKeys;

        public TovarsAdapter(List<Tovar> mTovarList, List<String> mKeys) {
            this.mTovarList = mTovarList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public BookItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BookItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull BookItemView holder, int position) {
            holder.bind(mTovarList.get(position), mKeys.get(position));

        }

        @Override
        public int getItemCount() {
            return mTovarList.size();
        }
    }
    public static void logout(){
        user=null;
    }
}