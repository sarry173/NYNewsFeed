package com.demo.xebia.assignment.views.news;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.demo.xebia.assignment.R;
import com.demo.xebia.assignment.datasource.response.news.Medium;
import com.demo.xebia.assignment.datasource.response.news.Result;
import com.demo.xebia.assignment.databinding.NewsListItemBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {

    private List<Result> dataSet;

    private NewsClickListener m_clickListener;

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        private Result m_result;
        NewsListItemBinding newsListItemBinding;
        public NewsViewHolder(@NonNull NewsListItemBinding itemBinding, NewsClickListener clickListener) {
            super(itemBinding.getRoot());
            newsListItemBinding = itemBinding;
            itemView.setOnClickListener(v -> clickListener.onNewsClicked(getAdapterPosition(), m_result));
        }

        public void onBind (Result result)
        {
            m_result = result;
            newsListItemBinding.setResult(result);

            List<Medium> media = result.getMedia();
            if (media != null && media.size() > 0) {
                String url = media.get(0).getMediaMetadata().get(0).getUrl();
                Glide.with(itemView.getContext())
                        .load(url)
                        .into(newsListItemBinding.ivThumbnail);
            }
        }
    }

    public NewsListAdapter(NewsClickListener clickListener)
    {
        m_clickListener = clickListener;
    }

    public void updateDataSource(List<Result> dataSet)
    {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       NewsListItemBinding view =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.news_list_item, parent, false);
        NewsViewHolder viewHolder = new NewsViewHolder(view, m_clickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, final int listPosition)
    {
        Result result = dataSet.get(listPosition);
        ((NewsViewHolder) holder).onBind(result);
    }

    @Override
    public int getItemCount()
    {
        return dataSet != null ? dataSet.size() : 0;
    }
}