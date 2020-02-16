package com.demo.xebia.assignment.views.aboutus;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.demo.xebia.assignment.R;
import com.demo.xebia.assignment.views.BaseFragment;

public class AboutUsFragment extends BaseFragment
{

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.about_us_fragment, container, false);
    }
}
