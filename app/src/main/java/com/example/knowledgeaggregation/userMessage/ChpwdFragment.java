package com.example.knowledgeaggregation.userMessage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.knowledgeaggregation.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChpwdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChpwdFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChpwdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChpwdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChpwdFragment newInstance(String param1, String param2) {
        ChpwdFragment fragment = new ChpwdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view1=inflater.inflate(R.layout.pwd_chang, null, false);
        Button chpwd=view1.findViewById(R.id.send);
        chpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText newPass=view1.findViewById(R.id.editText3);
                String newPass1=newPass.getText().toString();

            }
        });
        return view1;
    }
}
