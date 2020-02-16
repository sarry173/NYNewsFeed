package com.demo.xebia.assignment.views.news;

import com.demo.xebia.assignment.datasource.response.news.Result;

interface NewsClickListener
{
  void onNewsClicked(int position, Result order);
}
