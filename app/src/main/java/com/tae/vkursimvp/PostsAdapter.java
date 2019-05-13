package com.tae.vkursimvp;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {


    public interface onItemClickListener {
        public void onItemClick();
    }
    private List<PojoVal> posts;
    Context context;

    public PostsAdapter(Context context) {
        this.posts = new ArrayList<>();
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PojoVal post = posts.get(position);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.post.setText(post.getTxt());
        } else {
            holder.post.setText(Html.fromHtml(post.getCc()));
        }

        // rounding
        double rate;
        rate = post.getRate();
        String rate2 = String.valueOf(Math.round(rate*100.0)/100.0);
        holder.site.setText(rate2);

        //
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             //   Toast.makeText(context, post.getRate().toString(), Toast.LENGTH_SHORT).show();
               // Intent intent = new Intent(context, HistoryActivity.class);
             //   intent.putExtra("Parcel", post);
             //   context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (posts == null)
            return 0;
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView post;
        TextView site;
        CardView cardView;


        public ViewHolder(View itemView) {
            super(itemView);
            post = (TextView) itemView.findViewById(R.id.val);
            site = (TextView) itemView.findViewById(R.id.rate);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }

    public void setData(ArrayList<PojoVal> pojoNbu) {
       this.posts.addAll(pojoNbu);
        notifyDataSetChanged();
    }
}
