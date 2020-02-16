package com.demo.xebia.assignment.views;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.Fragment;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class BaseFragment extends Fragment
    {
        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
        }
    
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }
    
        @Override
        public void onDetach() {
            super.onDetach();
        }
    
        @Override
        public void onDestroyView() {
            super.onDestroyView();
            hideKeyboard();
        }
    
        @Override
        public void onDestroy() {
            super.onDestroy();
        }
    
        public void hideKeyboard ()
        {
            View curFocus;
            Activity activity = getActivity();
            if (   activity != null
                    && (curFocus = activity.getCurrentFocus()) != null)
            {
                InputMethodManager imm = (InputMethodManager)
                        activity.getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(curFocus.getWindowToken(), 0);
            }
        }
    }