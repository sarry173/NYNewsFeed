package com.demo.xebia.assignment.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.demo.xebia.assignment.datasource.apis.RestApiService;
import com.demo.xebia.assignment.viewmodels.news.NewsListViewModel;

import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private RestApiService repository;

    @Inject
    public ViewModelFactory(RestApiService repository) {
        this.repository = repository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NewsListViewModel.class)) {
            return (T) new NewsListViewModel(repository);
        }

        throw new IllegalArgumentException("Unknown class name");
    }
}