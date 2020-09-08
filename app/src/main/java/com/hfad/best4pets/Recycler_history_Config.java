package com.hfad.best4pets;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.List;

public class Recycler_history_Config {

    FirebaseAuth mAuth;
    private static FirebaseUser user;
    private Context mContext;
    private BooksAdapter mBooksAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<ZakazInfo> zakazInfos, List<String> keys){
        mAuth=FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mContext=context;
        mBooksAdapter = new BooksAdapter(zakazInfos, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mBooksAdapter);
    }


    class BookItemView extends RecyclerView.ViewHolder {
        private TextView mNumber;
        private TextView mDate;
        private TextView mTime;
        private TextView mCost;
        private String key;

        public BookItemView(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.book_list_items, parent, false));
            mNumber = (TextView) itemView.findViewById(R.id.number_zakaz);
            mDate = (TextView) itemView.findViewById(R.id.data_text_history);
            mCost=(TextView) itemView.findViewById(R.id.summa_zakaza);
            mTime=(TextView) itemView.findViewById(R.id.time_text_history);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, HistoryDetailsActivity.class);
                    intent.putExtra("key", key);
                    mContext.startActivity(intent);
                }
            });
        }
        public void bind(ZakazInfo zakazInfo, String key){
            mNumber.setText(key);
            mCost.setText(zakazInfo.getSumma());
            mTime.setText(zakazInfo.getTime());
            mDate.setText(zakazInfo.getDate());
            this.key=key;
        }
    }
    class BooksAdapter extends RecyclerView.Adapter<BookItemView>{
        private List<ZakazInfo> mZakazList;
        private List<String> mKeys;

        public BooksAdapter(List<ZakazInfo> mZakazList, List<String> mKeys) {
            this.mZakazList = mZakazList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public BookItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BookItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull BookItemView holder, int position) {
            holder.bind(mZakazList.get(position), mKeys.get(position));

        }

        @Override
        public int getItemCount() {
            return mZakazList.size();
        }
    }
}

