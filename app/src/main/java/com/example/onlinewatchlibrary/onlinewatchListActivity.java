package com.example.onlinewatchlibrary;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
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
import com.example.onlinewatchlibrary.Models.onlinewatchlibraryResponseModel;
import com.example.onlinewatchlibrary.network.NetworkClient;
import com.example.onlinewatchlibrary.network.NetworkService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class onlinewatchListActivity extends AppCompatActivity {

    RecyclerView rv;
    List< onlinewatchModel > list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlinewatchlibrary_list);

        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(onlinewatchListActivity.this));
        rv.setHasFixedSize(true);
        list = new ArrayList<>();
        Dialog Dialog = new Dialog(onlinewatchListActivity.this);
        Dialog.setContentView(R.layout.loading_dialog);
        Dialog.setTitle("Loading..");
        Dialog.setCancelable(false);
        Dialog.show();
        NetworkService networkService = NetworkClient.getClient().create(NetworkService.class);
        Call< com.example.onlinewatchlibrary.Models.onlinewatchlibraryResponseModel > call = networkService.getonlinewatchlibrary(getIntent().getStringExtra("id"));
        call.enqueue(new Callback< onlinewatchlibraryResponseModel >() {
            @Override
            public void onResponse(Call< onlinewatchlibraryResponseModel > call, Response< onlinewatchlibraryResponseModel > response) {
                Dialog.dismiss();
                if(response != null && response.body() != null )
                {
                    list = response.body().getonlinewatchlibrary();
                    rv.setAdapter(new onlinewatchlibraryadpter());
                    Toast.makeText(onlinewatchListActivity.this, ""+list.size(), Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(onlinewatchListActivity.this, "data failed", Toast.LENGTH_SHORT).show();

                }
            }


            @Override
            public void onFailure(Call<onlinewatchlibraryResponseModel> call, Throwable t) {
                Toast.makeText(onlinewatchListActivity.this, "data failed", Toast.LENGTH_SHORT).show();

            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }

        });

    }


    class onlinewatchlibraryadpter extends RecyclerView.Adapter<onlinewatchlibraryadpter.ViewHolder>{


        @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new ViewHolder(LayoutInflater.from(onlinewatchListActivity.this).inflate(R.layout.onlinewatchlibrarycontainer, parent, false));
            }

            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

                Glide.with(onlinewatchListActivity.this).load(list.get(position).getphoto()).into(holder.imageView);
                holder.model.setText(list.get(position).getmodel());
                holder.company.setText(list.get(position).getCompany());
                //holder.color.setText(list.get(position).getColor());
             //   holder.size.setText(list.get(position).getSize());
                holder.price.setText(list.get(position).getPrice());
           //     holder.description.setText(list.get(position).getdescription());

                holder.main.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(onlinewatchListActivity.this,onlinewatchDetailActivity.class);
                        intent.putExtra("model", list.get(position).getmodel());
                        intent.putExtra("company", list.get(position).getCompany());
                        intent.putExtra("color", list.get(position).getColor());
                        intent.putExtra("size", list.get(position).getSize());
                        intent.putExtra("price", list.get(position).getPrice());
                        intent.putExtra("description", list.get(position).getdescription());
                        intent.putExtra("photo", list.get(position).getphoto() + "");
                        startActivity(intent);
                    }
                });

            }

            @Override
            public int getItemCount() {
                return list.size();
            }

            class ViewHolder extends RecyclerView.ViewHolder {
                LinearLayout main;
                ImageView imageView;
                TextView model, company, color, size, price,description;

                public ViewHolder(@NonNull View view) {
                    super(view);


                    imageView = view.findViewById(R.id.image);
                    main = view.findViewById(R.id.container_main);
                    model = view.findViewById(R.id.model);
                    company = view.findViewById(R.id.company);
                    color = view.findViewById(R.id.color);
                    size = view.findViewById(R.id.size);
                    price = view.findViewById(R.id.price);
                    description = view.findViewById(R.id.description);
                }
            }

        }

    }
