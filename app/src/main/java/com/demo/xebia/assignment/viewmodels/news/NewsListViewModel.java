package com.demo.xebia.assignment.viewmodels.news;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.demo.xebia.assignment.datasource.apis.RestApiService;
import com.demo.xebia.assignment.datasource.response.news.OptionsDeserilizer;
import com.demo.xebia.assignment.datasource.response.news.Result;
import com.demo.xebia.assignment.utils.ApiResponse;
import com.demo.xebia.assignment.utils.Consts;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.demo.xebia.assignment.utils.Status.ERROR;
import static com.demo.xebia.assignment.utils.Status.LOADING;
import static com.demo.xebia.assignment.utils.Status.SUCCESS;

public class NewsListViewModel extends ViewModel
{
    private RestApiService repository;

    private final CompositeDisposable disposables = new CompositeDisposable();
    private MutableLiveData<ApiResponse> newsResults = new MutableLiveData<>();

    public NewsListViewModel(RestApiService repository)
    {
        this.repository = repository;
    }

    public MutableLiveData<ApiResponse> getNewsList()
    {
        return newsResults;
    }

    public void newsApiCall()
    {
        Observable<String> newsListObservable = this.repository.getNewsList(Consts.RECORD_COUNT, Consts.API_KEY);
        disposables.add(newsListObservable
                .subscribeOn(Schedulers.io())
                .doOnSubscribe((d) -> progressUpdate())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> handleResults(result),
                        throwable -> handleError(throwable)
                ));

    }

    private void handleResults(String newsResponses) throws JSONException
    {
        JSONObject jsonRes = new JSONObject(newsResponses);
        Type resultListType = new TypeToken<ArrayList<Result>>(){}.getType();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(resultListType, new OptionsDeserilizer())
                .create();
        ArrayList<Result> resultList = gson.fromJson( jsonRes.getString("results"), resultListType);
        if (resultList != null && resultList.size()>0)
        {
            if (newsResults.getValue().data == null)
            {
                newsResults.postValue(new ApiResponse<ArrayList<Result>>(SUCCESS, resultList, null));
            }
        }
        else
        {
            newsResults.postValue(new ApiResponse<ArrayList<Result>>(ERROR, null, null));
        }
    }

    private void handleError(Throwable t)
    {
        newsResults.postValue(new ApiResponse<ArrayList<Result>>(ERROR, null, t));
    }

    private void progressUpdate()
    {
        newsResults.postValue(new ApiResponse<ArrayList<Result>>(LOADING, null, null));
    }

    @Override
    protected void onCleared()
    {
        disposables.clear();
    }

}