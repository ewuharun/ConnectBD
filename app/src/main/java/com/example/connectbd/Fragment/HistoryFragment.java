package com.example.connectbd.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.connectbd.R;

public class HistoryFragment extends Fragment {


    public HistoryFragment() {
    }

    public static HistoryFragment newInstance(){
        HistoryFragment historyFragment = new HistoryFragment();
        Bundle args = new Bundle();

        historyFragment.setArguments(args);
        return historyFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_history,container,false);

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getView()!=null){
            Toast.makeText(requireContext(), "history", Toast.LENGTH_SHORT).show();
        }
    }
}
