package usama.task.movies.screens.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cat.us.movies.R;
import usama.task.movies.cores.configurations.RequestsUrls;
import usama.task.movies.cores.models.MovieModel;

/**
 * Created by usamaomar on 4/7/19.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private ArrayList<MovieModel> moviesValueModelArrayList;
    private LayoutInflater inflater;
    private MovieAdapter.OnItemClick itemClick;
    private Context context;


    public MovieAdapter(Context context, ArrayList<MovieModel> moviesValueModelArrayList, MovieAdapter.OnItemClick itemClick) {
        this.moviesValueModelArrayList = moviesValueModelArrayList;
        this.itemClick = itemClick;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MovieAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_movies, parent, false);
        return new MovieAdapter.MyViewHolder(view);
    }

    public interface OnItemClick {
        void itemSelectedToShare(MovieModel categoriesModel);

        void pageCount();
    }

    @Override
    public void onBindViewHolder(final MovieAdapter.MyViewHolder holder, int position) {
        try {
            final MovieModel moviesValueModel = moviesValueModelArrayList.get(holder.getAdapterPosition());
            if (moviesValueModel != null) {
                holder.titleTextView.setText(moviesValueModel.getTitle() != null ? moviesValueModel.getTitle() : "No Title");
                holder.descriptionView.setText(moviesValueModel.getOverview() != null ? moviesValueModel.getOverview() : "No Overview");
                holder.mainViewLinearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        itemClick.itemSelectedToShare(moviesValueModel);
                    }
                });
                if (moviesValueModel.getPosterPath() != null) {
                    holder.imageView.setVisibility(View.VISIBLE);
                    Glide.with(context).load(RequestsUrls.IMAGE_URL + moviesValueModel.getPosterPath()).centerCrop().override(200, 200).into(holder.imageView);
                } else {
                    holder.imageView.setVisibility(View.INVISIBLE);
                }
            }
            if (holder.getAdapterPosition() == moviesValueModelArrayList.size() - 1) {
                itemClick.pageCount();
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    @Override
    public int getItemCount() {
        return moviesValueModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView descriptionView;
        private ImageView imageView;
        private LinearLayout mainViewLinearLayout;

        MyViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            descriptionView = (TextView) itemView.findViewById(R.id.descriptionView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            mainViewLinearLayout = (LinearLayout) itemView.findViewById(R.id.mainViewLinearLayout);
        }
    }

}
