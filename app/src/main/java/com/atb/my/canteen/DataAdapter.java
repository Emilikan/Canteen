package com.atb.my.canteen;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nameView;
        TextView weightView;
        TextView priceView;
        String counterOfFragment;
        LinearLayout linearLayout;

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        ViewHolder(final View view){
            super(view);

            imageView = view.findViewById(R.id.imageForBook);

            linearLayout = view.findViewById(R.id.listText);
            nameView = view.findViewById(R.id.nameMain);
            weightView = view.findViewById(R.id.weightMain);
            priceView = view.findViewById(R.id.priceMain);
        }
    }

    private LayoutInflater inflater;
    private List<ForRecycleDish> dishes;

    DataAdapter(Context context, List<ForRecycleDish> dishes) {
        this.dishes = dishes;
        this.inflater = LayoutInflater.from(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycle_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DataAdapter.ViewHolder viewHolder, final int i) {
        final ForRecycleDish thisDish = dishes.get(i);
        viewHolder.nameView.setText(thisDish.getName());
        viewHolder.weightView.setText(thisDish.getWeight());
        viewHolder.priceView.setText(thisDish.getPrice());

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReferenceFromUrl(
                        Objects.requireNonNull(dataSnapshot.child(thisDish.getAllPath()).child("Icon").getValue(String.class)));
                storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //progressBar.setVisibility(ProgressBar.INVISIBLE);
                        Picasso.with(thisDish.getContext()).load(uri).into(viewHolder.imageView);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        if(thisDish.getThisMain()) {
            // обработчик нажатия
            viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {
                    // открываем активити Dish и передаем туда инфу о том, какой продукт мы открыли (с помощью bandle, например)
                    Intent intent = new Intent(thisDish.getContext(), Dish.class);
                    intent.putExtra("name", thisDish.getName());
                    intent.putExtra("price", thisDish.getPrice());
                    intent.putExtra("type", thisDish.getType());
                    intent.putExtra("weight", thisDish.getWeight());
                    intent.putExtra("calorie", thisDish.getCalorie());
                    intent.putExtra("mTrients", thisDish.getmTrients());
                    intent.putExtra("picture", thisDish.getPicture());
                    intent.putExtra("allPath", thisDish.getAllPath());
                    thisDish.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }
}
