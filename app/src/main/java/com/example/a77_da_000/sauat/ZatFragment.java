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
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ZatFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ZatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ZatFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    String ChangeLastLetter(String s){
        switch(s.toCharArray()[s.length()-1]){
            case 'к':case 'К':
                s = s.substring(0, s.length()-1);
                s += 'г';
                break;
            case 'қ':case 'Қ':
                s = s.substring(0, s.length()-1);
                s += 'ғ';
                break;
            case 'п':case 'П':
                s = s.substring(0, s.length()-1);
                s += 'б';
                break;
        }
        return s;
    }

    boolean ZhuanLetter(char c){
        String zhuan = "аоұыяАОҰЫЯ";
        String temp = "";
        temp+=c;
        return zhuan.contains(temp);
    }

    boolean ZhinLetter(char c){
        String zhin = "әеіөүьэӘЕІӨҮЬЭ";
        String temp = "";
        temp+=c;
        return zhin.contains(temp);
    }

    void formWord(){
        TextView formedWord = (TextView) getView().findViewById(R.id.formedWord);
        EditText editText = (EditText) getView().findViewById(R.id.editText);
        Spinner septikSpinner = (Spinner) getView().findViewById(R.id.septik);
        Spinner zhakSpinner = (Spinner) getView().findViewById(R.id.zhak_spinner);
        CheckBox checkBox = (CheckBox) getView().findViewById(R.id.checkBox);
        boolean count = checkBox.isChecked();
        boolean zhuan = true;
        int sept,zhak;
        sept = septikSpinner.getSelectedItemPosition();
        zhak = zhakSpinner.getSelectedItemPosition();
        String input = editText.getText().toString();
        char[] c = input.toCharArray();
        for(int i = c.length-1; i>=0; i--){
            if(ZhuanLetter(c[i])){
                zhuan = true;
                break;
            }
            if(ZhinLetter(c[i])){
                zhuan = false;
                break;
            }
        }
        String last = "" + c[c.length-1];
        if(last.equals("ъ")||last.equals("ь")) last = "" + c[c.length-2];
        String second = "зжилмнңЗЖИЛМНҢ";
        String first = "бвгдқкхфптсчцшщБВГДКҚХФПТСЧЦШЩ";
        if(count){
            if(second.contains(last)){
                if(zhuan) input+="дар";
                else input += "дер";
            }
            else if(first.contains(last)){
                if(zhuan) input += "тар";
                else input += "тер";
            }
            else{
                if(zhuan) input += "лар";
                else input += "лер";
            }
            last = "р";
        }
        first = "уәіүұөуеыаоэяиюёУӘІҮҰӨУЕЫАОЭЯИЮЁ";
        switch (zhak){
            case 1:
                input = ChangeLastLetter(input);
                if(first.contains(last)) input+="м";
                else if(zhuan) input+="ым";
                else input+="ім";
                break;
            case 2:case 6:
                input = ChangeLastLetter(input);
                if(first.contains(last)) input+="ң";
                else if(zhuan) input+="ың";
                else input+="ің";
                break;
            case 3:case 7:
                input = ChangeLastLetter(input);
                if(!first.contains(last)){
                    if(zhuan) input+="ы";
                    else input+="і";
                }
                if(zhuan) input+="ңыз";
                else input+="ңіз";
                break;
            case 4:case 8:
                input = ChangeLastLetter(input);
                if(first.contains(last)) input+="с";
                if(zhuan) input+="ы";
                else input+="і";
                break;
            case 5:
                input = ChangeLastLetter(input);
                if(!first.contains(last)){
                    if(zhuan) input+="ы";
                    else input+="і";
                }
                if(zhuan) input+="мыз";
                else input+="міз";
                break;
        }
        c = input.toCharArray();
        last = "" + c[c.length-1];
        if(last.equals("ъ")||last.equals("ь")) last = "" + c[c.length-2];
        switch(sept){
            case 1:
                first = "июлруйжзИЮЛРУЙЖЗ";
                second = "бвгдқкхфптсчцшщБВГДКҚХФПТСЧЦШЩ";
                if(zhak==4 || zhak==8) input+="н";
                else if(first.contains(last)) input+="д";
                else if(second.contains(last)) input+="т";
                else input+="н";
                if(zhuan) input+="ың";
                else input+="ің";
                break;
            case 2:
                first = "бвгдқкхфптсчцшщБВГДКҚХФПТСЧЦШЩ";
                if(zhak==4 || zhak==8) input+="н";
                else if(zhak==1 || zhak==2 || zhak==6);
                else if(first.contains(last)){
                    if(zhuan) input+="қ";
                    else input+="к";
                }
                else{
                    if(zhuan) input+="ғ";
                    else input+="г";
                }
                if(zhuan) input+="а";
                else input+="е";
                break;
            case 3:
                first = "июлруйжзмнңИЮЛРУЙЖЗМНҢ";
                second = "бвгдқкхфптсчцшщБВГДКҚХФПТСЧЦШЩ";
                if(zhak==4 || zhak==8){
                    input+="н";
                    break;
                }
                else if(first.contains(last)) input+="д";
                else if(second.contains(last)) input+="т";
                else input+="н";
                if(zhuan) input+="ы";
                else input+="і";
                break;
            case 4:
                first = "бвгдқкхфптсчцшщБВГДКҚХФПТСЧЦШЩ";
                if(zhak==4 || zhak==8) input+="нд";
                else if(first.contains(last)) input+="т";
                else input+="д";
                if(zhuan) input+="а";
                else input+="е";
                break;
            case 5:
                first = "мнңМНҢ";
                second = "бвгдқкхфптсчцшщБВГДКҚХФПТСЧЦШЩ";
                if(zhak==4 || zhak==8 || first.contains(last)) input+="н";
                else if(second.contains(last)) input+="т";
                else input+="д";
                if(zhuan) input+="ан";
                else input+="ен";
                break;
            case 6:
                first = "жзЖЗ";
                second = "бвгдқкхфптсчцшщБВГДКҚХФПТСЧЦШЩ";
                if(first.contains(last)) input+="бен";
                else if(second.contains(last)) input+="пен";
                else input+="мен";
                break;
        }
        formedWord.setText(input);
    }

    public ZatFragment() {
        // Required empty public constructor
    }

    public static ZatFragment newInstance() {
        ZatFragment fragment = new ZatFragment();
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
        View view = inflater.inflate(R.layout.fragment_zat, container, false);
        Spinner septikSpinner = (Spinner) view.findViewById(R.id.septik);
        ArrayAdapter<CharSequence> septikAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.septik_array, android.R.layout.simple_spinner_item);
        septikAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        septikSpinner.setAdapter(septikAdapter);

        Spinner zhakSpinner = (Spinner) view.findViewById(R.id.zhak_spinner);
        ArrayAdapter<CharSequence> zhakAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.zhak_array, android.R.layout.simple_spinner_item);
        zhakAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        zhakSpinner.setAdapter(zhakAdapter);

        final TextView formedWord = (TextView) view.findViewById(R.id.formedWord);
        final EditText editText = (EditText) view.findViewById(R.id.editText);

        editText.setText("Сөз");

        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(editText.getText().length()>1) formWord();
                else formedWord.setText(editText.getText());
            }
        });

        septikSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(editText.getText().length()>1) formWord();
                else formedWord.setText(editText.getText());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        zhakSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(editText.getText().length()>1) formWord();
                else formedWord.setText(editText.getText());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length()>1) formWord();
                else formedWord.setText(editText.getText());
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
