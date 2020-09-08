package com.hfad.best4pets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerKorzina_Config {
    FirebaseAuth mAuth;
    private static FirebaseUser user;
    private Context mContext;
    private BooksAdapter mBooksAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Tovar> items, List<String> keys){
        mAuth=FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mContext=context;
        mBooksAdapter = new BooksAdapter(items, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mBooksAdapter);
    }


    class BookItemView extends RecyclerView.ViewHolder {
        private TextView mColichestvo;
        private TextView mName;
        private TextView mPrice;
        private String path;
        private int cost;
        private ImageView mImage;
        private String key;
        private int how;
        private ImageButton mButton;
        private Tovar newTovar;
        private ImageButton mPlusBtn;

        public BookItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.book_list_korzina, parent, false));
            mButton=(ImageButton)itemView.findViewById(R.id.imageButton2_minus);
            mColichestvo = (TextView) itemView.findViewById(R.id.colichestvo_text);
            mPrice = (TextView) itemView.findViewById(R.id.price_text);
            mImage = (ImageView) itemView.findViewById(R.id.image_corzina);
            mPlusBtn=(ImageButton) itemView.findViewById(R.id.imageButton_plus);
            mName=(TextView) itemView.findViewById(R.id.name_text_korzina);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    how--;
                    newTovar.setHow(how);
                    if (how != 0) {
                        new FirebaseDatabaseHelper("users").updateItems(key, newTovar, new FirebaseDatabaseHelper.DataStatus() {
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
                    }else {
                        new FirebaseDatabaseHelper("users").deletItems(key, new FirebaseDatabaseHelper.DataStatus() {
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

                    }
                }

            });
            mPlusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    how++;
                    newTovar.setHow(how);
                    new FirebaseDatabaseHelper("users").updateItems(key, newTovar, new FirebaseDatabaseHelper.DataStatus() {
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
                }
            });



        }
        public void bind(Tovar tovar, String key){
            mPrice.setText(String.valueOf(tovar.getCost()));
            mName.setText(tovar.getName());
            Picasso.get().load(tovar.getImage()).into(mImage);
            how= tovar.getHow();
            mColichestvo.setText(String.valueOf(tovar.getHow()));
            this.key=key;
            newTovar= tovar;
        }
    }
    class BooksAdapter extends RecyclerView.Adapter<BookItemView>{
        private List<Tovar> mTovarList;
        private List<String> mKeys;

        public BooksAdapter(List<Tovar> mTovarList, List<String> mKeys) {
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
}