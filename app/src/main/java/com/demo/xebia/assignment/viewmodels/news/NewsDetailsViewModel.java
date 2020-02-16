package com.demo.xebia.assignment.viewmodels.news;

import androidx.lifecycle.ViewModel;

import com.demo.xebia.assignment.datasource.response.news.Result;

public class NewsDetailsViewModel extends ViewModel
{
    private Result newsResults;

    public NewsDetailsViewModel()
    {
    }

    public Result getNewsResults()
    {
        return newsResults;
    }

    public void setNewsResults(Result newsResults)
    {
        this.newsResults = newsResults;
    }
}
