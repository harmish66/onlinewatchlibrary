package com.example.onlinewatchlibrary;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlinewatchlibrary.Models.onlinewatchModel;
import com.example.onlinewatchlibrary.database.DatabaseHalper;
import com.example.onlinewatchlibrary.network.NetworkClient;
import com.example.onlinewatchlibrary.network.NetworkService;
import com.example.onlinewatchlibrary.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    RecyclerView rv;
    List< onlinewatchModel > list;
    DatabaseHalper databaseHalper;
    TextView total, place;
    LinearLayout nodata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        total = findViewById(R.id.total);
        place = findViewById(R.id.place);
        nodata = findViewById(R.id.nodata);
        list = new ArrayList<>();
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(CartActivity.this));
        rv.setHasFixedSize(true);

        place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placeOrder();
            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //Toast.makeText(CartActivity.this, "" + databaseHalper.getData(), Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    void getData(){
        list.clear();
        databaseHalper = new DatabaseHalper(CartActivity.this);
        Cursor cursor = databaseHalper.getData();
        if(cursor.moveToFirst())
        {
            do{
                list.add(new onlinewatchModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(4),cursor.getString(3)));
            }while (cursor.moveToNext());
        }

        total.setText("");
        if (list != null && list.size() > 0) {
            rv.setAdapter(new Myadapter());
            total.setText(list.get(0).getPrice());
            getTotal(list);
            rv.setVisibility(View.VISIBLE);
            nodata.setVisibility(View.GONE);
            place.setEnabled(true);
            place.setAlpha(1f);
        }else{
            rv.setVisibility(View.GONE);
            nodata.setVisibility(View.VISIBLE);
            place.setEnabled(false);
            place.setAlpha(.5f);
        }
    }

    class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder>
    {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            return new ViewHolder(LayoutInflater.from(CartActivity.this).inflate(R.layout.cart_cotainer,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

            holder.textView.setText(list.get(position).getmodel());
            holder.author.setText(list.get(position).getCompany());
            holder.price.setText(list.get(position).getPrice());
            Glide.with(CartActivity.this).load(list.get(position).getphoto()).into(holder.ImageView);

            holder.remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog dialog = new Dialog(CartActivity.this);
                    dialog.setContentView(R.layout.delete_dialog);
                    dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
                    dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    dialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    dialog.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            databaseHalper.deleteItem(list.get(position).getId());
                            getData();
                            notifyDataSetChanged();
                            getTotal(list);
                            dialog.dismiss();
                        }
                    });

                    dialog.show();

                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class  ViewHolder extends RecyclerView.ViewHolder{

            TextView textView,author,price;
            LinearLayout container;
            android.widget.ImageView ImageView,remove;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                textView = itemView.findViewById(R.id.tname);
                container = itemView.findViewById(R.id.container_main);
                ImageView = itemView.findViewById(R.id.image);
                author = itemView.findViewById(R.id.company);
                price = itemView.findViewById(R.id.price);
                remove = itemView.findViewById(R.id.remove);
            }
        }
    }
    void getTotal(List<onlinewatchModel> list)
    {
        long price = 0;
        for(int i = 0;i< list.size() ;i++ )
        {
            price = price +  Long.parseLong(list.get(i).getPrice());
        }
        total.setText("₹" + price +"");
    }

    void placeOrder() {
       Constants.list = list;
        startActivity(new Intent(CartActivity.this, PlaceOrderActivity.class).putExtra("price",total.getText().toString()));

    }

}