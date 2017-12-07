package com.example.a77_da_000.sauat;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class VerbFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public VerbFragment() {}

    public static VerbFragment newInstance() {
        VerbFragment fragment = new VerbFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_verb, container, false);
        final TextView formedWord = (TextView) view.findViewById(R.id.formedWord);
        final EditText editText = (EditText) view.findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                formedWord.setText(editText.getText());
            }
        });
        view.post(new Runnable() {
                      @Override
                      public void run() {
                          // code you want to run when view is visible for the first time
                          int width = formedWord.getWidth();
                          int height = (width*2)/3;
                          formedWord.setHeight(height);
                      }
                  }
        );
        return view;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
