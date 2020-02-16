package com.demo.xebia.assignment.views.news;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.demo.xebia.assignment.R;
import com.demo.xebia.assignment.databinding.DetailsFragmentBinding;
import com.demo.xebia.assignment.datasource.response.news.Medium;
import com.demo.xebia.assignment.datasource.response.news.Result;
import com.demo.xebia.assignment.viewmodels.news.NewsDetailsViewModel;
import com.demo.xebia.assignment.views.BaseFragment;
import com.google.gson.Gson;

import java.util.List;

public class NewsDetailsFragment extends BaseFragment {

    public static final String NEWS_DETAILS = "news_details";
    private DetailsFragmentBinding m_binding;
    private NewsDetailsViewModel m_newsDetailsViewModel;
    private Result m_result;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m_newsDetailsViewModel = ViewModelProviders.of(getActivity()).get(NewsDetailsViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        /* Setup view. */
        m_binding =
                DataBindingUtil.inflate(inflater, R.layout.details_fragment, container, false);

        String newsDetails = getArguments().getString(NEWS_DETAILS);
        m_result = new Gson().fromJson(newsDetails, Result.class);
        m_binding.setResult(m_result);
        m_binding.buttonClose.setOnClickListener(v -> {
            final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
            navController.navigateUp();
        });
        return m_binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Medium> media = m_result.getMedia();
        if (media != null && media.size() > 0) {
            String url ="";
            if ( media.get(0).getMediaMetadata().size()==3)
            {
                 url = media.get(0).getMediaMetadata().get(2).getUrl();
            }
            else
                 url = media.get(0).getMediaMetadata().get(0).getUrl();

            Glide.with(view.getContext())
                    .load(url)
                    .into(m_binding.ivThumbnail);
        }
    }
}
