package com.atb.my.canteen;

import android.content.Context;
import android.content.Intent;
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

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nameView;
        TextView typeView;
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
            typeView = view.findViewById(R.id.typeMain);
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
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder viewHolder, final int i) {
        final ForRecycleDish thisDish = dishes.get(i);
        viewHolder.counterOfFragment = thisDish.getArrayList().get(i) + "";
        viewHolder.nameView.setText(thisDish.getName());
        viewHolder.typeView.setText(thisDish.getType());
        viewHolder.weightView.setText(thisDish.getWeight());
        viewHolder.priceView.setText(thisDish.getPrice());

        // обработчик нажатия
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                // открываем активити Dish и передаем туда инфу о том, какой продукт мы открыли (с помощью bandle, например)
                Intent intent = new Intent(thisDish.getContext(), Dish.class);
                thisDish.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }
}
