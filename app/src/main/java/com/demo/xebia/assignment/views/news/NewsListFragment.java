package com.demo.xebia.assignment.views.news;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.demo.xebia.assignment.AppController;
import com.demo.xebia.assignment.R;
import com.demo.xebia.assignment.databinding.HomeFragmentBinding;
import com.demo.xebia.assignment.datasource.response.news.Result;
import com.demo.xebia.assignment.utils.ApiResponse;
import com.demo.xebia.assignment.viewmodels.news.NewsListViewModel;
import com.demo.xebia.assignment.utils.ViewModelFactory;
import com.demo.xebia.assignment.views.BaseFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class NewsListFragment extends BaseFragment {
    private SearchView searchView = null;
    List<Result> m_NewsResult = new ArrayList<>();
    HomeFragmentBinding m_Binding;

    @Inject
    ViewModelFactory viewModelFactory;
    private NewsListAdapter m_adapter;
    private NewsListViewModel m_newsListViewModel;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppController.getmInstance().getAppComponent().doInjection(this);
        m_newsListViewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsListViewModel.class);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /* Setup view. */
        m_Binding =  DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        m_Binding.newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        m_Binding.newsRecyclerView.setAdapter( m_adapter = new NewsListAdapter(this::onOrderClicked));

        m_newsListViewModel.getNewsList().observe(this, news ->
        {
            consumeResponse(news);
        });

        m_newsListViewModel.newsApiCall();
        return m_Binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void onOrderClicked (int position, Result result)
    {
        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        Bundle bundle = new Bundle();
        String jsonObject = new Gson().toJson(result);
        bundle.putString(NewsDetailsFragment.NEWS_DETAILS, jsonObject);
        navController.navigate(R.id.action_to_detailsFragment, bundle);
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(item);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.length()>2)
                {
                    List<Result> searchResult = new ArrayList<>();
                    for (int i = 0; i < m_NewsResult.size(); i++) {
                        if (   m_NewsResult.get(i).getByline().toLowerCase().contains(s.toLowerCase())
                            || m_NewsResult.get(i).getTitle().toLowerCase().contains(s.toLowerCase())
                            || m_NewsResult.get(i).getSource().toLowerCase().contains(s.toLowerCase()))
                        {
                            searchResult.add(m_NewsResult.get(i));
                        }
                    }
                    m_adapter.updateDataSource(searchResult);
                }
                else
                {
                    m_adapter.updateDataSource(m_NewsResult);
                }
                return true;
            }
        });
    }

    private void consumeResponse(ApiResponse apiResponse) {

        switch (apiResponse.status) {

            case LOADING:
                m_Binding.progressBar.setVisibility(View.VISIBLE);
                break;

            case SUCCESS:
                m_Binding.progressBar.setVisibility(View.GONE);
                if (m_NewsResult!= null) {
                    m_NewsResult.addAll((ArrayList<Result>)apiResponse.data);
                    m_adapter.updateDataSource((ArrayList<Result>)apiResponse.data);
                }
                break;

            case ERROR:
                m_Binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(),getResources().getString(R.string.api_error), Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }
}
