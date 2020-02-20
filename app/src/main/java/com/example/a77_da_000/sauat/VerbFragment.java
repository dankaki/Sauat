package com.example.a77_da_000.sauat;

import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.Objects;

import static android.content.ContentValues.TAG;

public class VerbFragment extends Fragment {

    String ChangeLastLetter(String s) {
        switch (s.toCharArray()[s.length() - 1]) {
            case 'б':
            case 'Б':
                s = s.substring(0, s.length() - 1) + 'п';
                break;
            case 'г':
            case 'Г':
                s = s.substring(0, s.length() - 1) + 'к';
                break;
            case 'ғ':
            case 'Ғ':
                s = s.substring(0, s.length() - 1) + 'қ';
                break;
        }
        return s;
    }

    boolean ZhuanLetter(char c) {
        String zhuan = "аоұыяАОҰЫЯ";
        String temp = "";
        temp += c;
        return zhuan.contains(temp);
    }

    boolean ZhinLetter(char c) {
        String zhin = "әеіөүьэӘЕІӨҮЬЭ";
        String temp = "";
        temp += c;
        return zhin.contains(temp);
    }

    void formWord() {
        TextView formedWord = (TextView) getView().findViewById(R.id.formedWord);
        EditText editText = (EditText) getView().findViewById(R.id.editTextVerb);
        Spinner zhakSpinner = (Spinner) getView().findViewById(R.id.zhak_spinner);
        Spinner tenseSpinner = (Spinner) getView().findViewById(R.id.tense_spinner);
        String input = editText.getText().toString();
        char[] c = input.toCharArray();
        formedWord.setText(input);
        boolean zhuan = true;
        boolean secondForm = false;
        int zhak, tense;
        zhak = zhakSpinner.getSelectedItemPosition();
        tense = tenseSpinner.getSelectedItemPosition();
        secondForm = (tense == 4 || tense == 7);
        for (int i = c.length - 1; i >= 0; i--) {
            if (ZhuanLetter(c[i])) {
                zhuan = true;
                break;
            }
            if (ZhinLetter(c[i])) {
                zhuan = false;
                break;
            }
        }
        String first, second;

        if (c[c.length - 1] == 'ю' || c[c.length - 1] == 'Ю')
            input = input.substring(0, input.length() - 1) + 'й';
        if (c[c.length - 1] == 'у' || c[c.length - 1] == 'У')
            input = input.substring(0, input.length() - 1);
        input = ChangeLastLetter(input);
        c = input.toCharArray();
        String last = "" + c[c.length - 1];
        boolean pes;
        switch (tense) {
            case 0:
                first = "уәіүұөуеыаоэяиюёУӘІҮҰӨУЕЫАОЭЯИЮЁ";
                if (first.contains(last)) input += "п жатыр";
                else if (zhuan) input += "ып жатыр";
                else input += "іп жатыр";
                break;
            case 1:
                first = "уәіүұөуеыаоэяиюёУӘІҮҰӨУЕЫАОЭЯИЮЁ";
                if (first.contains(last)) input += 'р';
                else if (zhuan){
                    if(last.equals("й") ||last.equals("Й")) input = input.substring(0, input.length()-1)+"яр";
                    else input+="ар";
                }
                else if(last.equals("й") ||last.equals("Й")) input = input.substring(0, input.length()-1)+"ер";
                else input += "ер";
                break;
            case 2:
                first = "мнңжзМНҢЖЗ";
                second = "қкхфптсчцшщКҚХФПТСЧЦШЩ";
                if (first.contains(last)) input += 'б';
                else if (second.contains(last)) input += 'п';
                else input += 'м';
                if (zhuan) input += "ақ";
                else input += "ек";
                break;
            case 3:
                first = "уәіүұөуеыаоэяиюёУӘІҮҰӨУЕЫАОЭЯИЮЁ";
                if (first.contains(last)) input += 'й';
                else if (zhuan){
                    if(last.equals("й") ||last.equals("Й")) input = input.substring(0, input.length()-1)+"я";
                    else input+='а';
                }
                else if(last.equals("й") ||last.equals("Й")) input = input.substring(0, input.length()-1)+"е";
                else input += 'е';
                if (zhak == 3 || zhak == 7) {
                    if (zhuan) input += "ды";
                    else input += "ді";
                }
                break;
            case 4:
                second = "қкхфптсчцшщКҚХФПТСЧЦШЩ";
                if (second.contains(last)) input += 'т';
                else input += 'д';
                if (zhuan) input += 'ы';
                else input += 'і';
                break;
            case 5:
                second = "қкхфптсчцшщКҚХФПТСЧЦШЩ";
                pes = second.contains(last);
                if (pes && zhuan) input += "қан";
                else if (pes) input += "кен";
                else if (zhuan) input += "ған";
                else input += "ген";
                break;
            case 6:
                first = "уәіүұөуеыаоэяиюёУӘІҮҰӨУЕЫАОЭЯИЮЁ";
                pes = first.contains(last);
                if (pes && zhuan) input += "йтын";
                else if (pes) input += "йтін";
                else if (zhuan){
                    if(last.equals("й") ||last.equals("Й")) input = input.substring(0, input.length()-1)+"я";
                    else input+='а';
                }
                else input += 'е';
                break;
            case 7:
                if (zhuan) input += "са";
                else input += "се";
                break;
            case 8:
                first = "уәіүұөуеыаоэяиюёУӘІҮҰӨУЕЫАОЭЯИЮЁ";
                switch (zhak) {
                    case 0:
                        if (!first.contains(last)) {
                            if (zhuan){
                                if(last.equals("й") ||last.equals("Й")) input = input.substring(0, input.length()-1)+"я";
                                else input+='а';
                            }
                            else input += 'е';
                        }
                        if (zhuan) input += "йын";
                        else input += "йін";
                        break;
                    case 2:
                        if (!first.contains(last)) {
                            if (zhuan) input += 'ы';
                            else input += 'і';
                        }
                        if (zhuan) input += "ңыз";
                        else input += "ніз";
                        break;
                    case 3:
                    case 7:
                        if (!first.contains(last)) {
                            if (zhuan){
                                if(last.equals("й") ||last.equals("Й")) input = input.substring(0, input.length()-1)+"я";
                                else input+='а';
                            }
                            else input += 'е';
                        }
                        if (zhuan) input += "сын";
                        else input += "сін";
                        break;
                    case 4:
                        if (!first.contains(last)) {
                            if (zhuan){
                                if(last.equals("й") ||last.equals("Й")) input = input.substring(0, input.length()-1)+"я";
                                else input+='а';
                            }
                            else input += 'е';
                        }
                        if (zhuan) input += "йық";
                        else input += "йік";
                        break;
                    case 5:
                        if (!first.contains(last)) {
                            if (zhuan) input += 'ы';
                            else input += 'і';
                        }
                        if (zhuan) input += "ңдар";
                        else input += "ңдер";
                        break;
                    case 6:
                        if (!first.contains(last)) {
                            if (zhuan) input += 'ы';
                            else input += 'і';
                        }
                        if (zhuan) input += "ңыздар";
                        else input += "ңіздер";
                        break;
                }
                break;
            case 9:
                second = "қкхфптсчцшщКҚХФПТСЧЦШЩ";
                pes = second.contains(last);
                if (pes && zhuan) input += "қы";
                else if (pes) input += "кі";
                else if (zhuan) input += "ғы";
                else input += "гі";
                switch (zhak) {
                    case 0:
                        input += "м келу";
                        break;
                    case 1:
                        input += "ң келу";
                        break;
                    case 2:
                        if (zhuan) input += "ңыз келу";
                        else input += "ңіз келу";
                        break;
                    case 3:
                        if (zhuan) input += "сы келу";
                        else input += "сі келу";
                        break;
                    case 4:
                        if (zhuan) input += "мыз келу";
                        else input += "міз келу";
                        break;
                    case 5:
                        if (zhuan) input += "ларың келу";
                        else input += "лерің келу";
                        break;
                    case 6:
                        if (zhuan) input += "ларыңыз келу";
                        else input += "леріңіз келу";
                        break;
                    case 7:
                        if (zhuan) input += "лары келу";
                        else input += "лері келу";
                        break;
                }
        }
        c = input.toCharArray();
        last = "" + c[c.length - 1];
        if (secondForm) {
            switch (zhak) {
                case 0:
                    input += 'м';
                    break;
                case 1:
                    input += 'ң';
                    break;
                case 2:
                    if (zhuan) input += "ңыз";
                    else input += "ңіз";
                    break;
                case 4:
                    input += 'қ';
                    break;
                case 5:
                    if (zhuan) input += "ңдар";
                    else input += "ңдер";
                    break;
                case 6:
                    if (zhuan) input += "ңыздар";
                    else input += "ңіздер";
                    break;
            }
        }
        else if(tense!=8 && tense!=9){
            switch (zhak){
                case 0:
                    second = "қкхфптсчцшщКҚХФПТСЧЦШЩ";
                    first = "жзЖЗ";
                    if(first.contains(last)) input+='б';
                    else if(second.contains(last)) input+='п';
                    else input+='м';
                    if(zhuan) input+="ын";
                    else input+="ін";
                    break;
                case 1:
                    if(zhuan) input+="сың";
                    else input+="сің";
                    break;
                case 2:
                    if(zhuan) input+="сыз";
                    else input+="сіз";
                    break;
                case 4:
                    second = "қкхфптсчцшщКҚХФПТСЧЦШЩ";
                    first = "жзЖЗ";
                    if(first.contains(last)) input+='б';
                    else if(second.contains(last)) input+='п';
                    else input+='м';
                    if(zhuan) input+="ыз";
                    else input+="із";
                    break;
                case 5:
                    if(zhuan) input+="сыңдар";
                    else input+="сіңдер";
                    break;
                case 6:
                    if(zhuan) input+="сыздар";
                    else input+="сіздер";
                    break;
            }
        }
        formedWord.setText(input);
        c = input.toCharArray();
        String latinWord = "";
        for (int i = 0; i < c.length; i++) {
            switch (c[i]) {
                case 'А':
                case 'а':
                    latinWord += "a";
                    break;
                case 'Ә':
                case 'ә':
                    latinWord += "á";
                    break;
                case 'Б':
                case 'б':
                    latinWord += "b";
                    break;
                case 'В':
                case 'в':
                    latinWord += "v";
                    break;
                case 'Г':
                case 'г':
                    latinWord += "g";
                    break;
                case 'Ғ':
                case 'ғ':
                    latinWord += "ǵ";
                    break;
                case 'Д':
                case 'д':
                    latinWord += "d";
                    break;
                case 'Е':
                case 'е':
                    latinWord += "e";
                    break;
                case 'Ё':
                case 'ё':
                    latinWord += "io";
                    break;
                case 'Ж':
                case 'ж':
                    latinWord += "j";
                    break;
                case 'З':
                case 'з':
                    latinWord += "z";
                    break;
                case 'И':
                case 'и':
                    latinWord += "i";
                    break;
                case 'Й':
                case 'й':
                    latinWord += "i";
                    break;
                case 'К':
                case 'к':
                    latinWord += "k";
                    break;
                case 'Қ':
                case 'қ':
                    latinWord += "q";
                    break;
                case 'Л':
                case 'л':
                    latinWord += "l";
                    break;
                case 'М':
                case 'м':
                    latinWord += "m";
                    break;
                case 'Н':
                case 'н':
                    latinWord += "n";
                    break;
                case 'Ң':
                case 'ң':
                    latinWord += "ń";
                    break;
                case 'О':
                case 'о':
                    latinWord += "o";
                    break;
                case 'Ө':
                case 'ө':
                    latinWord += "ó";
                    break;
                case 'П':
                case 'п':
                    latinWord += "p";
                    break;
                case 'Р':
                case 'р':
                    latinWord += "r";
                    break;
                case 'С':
                case 'с':
                    latinWord += "s";
                    break;
                case 'Т':
                case 'т':
                    latinWord += "t";
                    break;
                case 'У':
                case 'у':
                    latinWord += "ý";
                    break;
                case 'Ұ':
                case 'ұ':
                    latinWord += "u";
                    break;
                case 'Ү':
                case 'ү':
                    latinWord += "ú";
                    break;
                case 'Ф':
                case 'ф':
                    latinWord += "f";
                    break;
                case 'Х':
                case 'х':
                case 'Һ':
                case 'һ':
                    latinWord += "h";
                    break;
                case 'Ц':
                case 'ц':
                    latinWord += "c";
                    break;
                case 'Ч':
                case 'ч':
                    latinWord += "ch";
                    break;
                case 'Ш':
                case 'ш':
                    latinWord += "sh";
                    break;
                case 'Щ':
                case 'щ':
                    latinWord += "sh";
                    break;
                case 'Ы':
                case 'ы':
                    latinWord += "y";
                    break;
                case 'І':
                case 'і':
                    latinWord += "i";
                    break;
                case 'Ъ':
                case 'ъ':
                case 'Ь':
                case 'ь':
                    break;
                case 'Э':
                case 'э':
                    latinWord += "e";
                    break;
                case 'Ю':
                case 'ю':
                    latinWord += "iy";
                    break;
                case 'Я':
                case 'я':
                    latinWord += "ia";
                    break;
                default:
                    latinWord += c[i];
                    break;
            }
            TextView latinView = (TextView) getView().findViewById(R.id.latin);
            latinView.setText(latinWord);
        }
    }

    private OnFragmentInteractionListener mListener;

    public VerbFragment() {
    }

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
        View view = inflater.inflate(R.layout.fragment_verb, container, false);
        final TextView formedWord = (TextView) view.findViewById(R.id.formedWord);
        final EditText editText = (EditText) view.findViewById(R.id.editTextVerb);
        Spinner tenseSpinner = (Spinner) view.findViewById(R.id.tense_spinner);
        Spinner zhakSpinner = (Spinner) view.findViewById(R.id.zhak_spinner);
        final LinearLayout wordLayout = (LinearLayout) view.findViewById(R.id.word_layout);
        final TableLayout extraKeys = (TableLayout) view.findViewById(R.id.extra_keys);

        final ScrollView scrollView = (ScrollView) view.findViewById(R.id.scrollview);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        extraKeys.setVisibility(View.INVISIBLE);

        ArrayAdapter<CharSequence> zhakAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.verb_zhak_array, android.R.layout.simple_spinner_item);
        zhakAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        zhakSpinner.setAdapter(zhakAdapter);

        ArrayAdapter<CharSequence> tenseAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.tense_array, android.R.layout.simple_spinner_item);
        tenseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tenseSpinner.setAdapter(tenseAdapter);

        final TextView latinView = (TextView) view.findViewById(R.id.latin);

        editText.setText("Жасау");
        latinView.setText("jasay'");
        formedWord.setText("Жасау");

        Button buttonA = (Button) view.findViewById(R.id.buttonA);
        Button buttonI = (Button) view.findViewById(R.id.buttonI);
        Button buttonN = (Button) view.findViewById(R.id.buttonN);
        Button buttonG = (Button) view.findViewById(R.id.buttonG);
        Button buttonY = (Button) view.findViewById(R.id.buttonY);
        Button buttonU = (Button) view.findViewById(R.id.buttonU);
        Button buttonK = (Button) view.findViewById(R.id.buttonK);
        Button buttonO = (Button) view.findViewById(R.id.buttonO);
        Button buttonH = (Button) view.findViewById(R.id.buttonH);

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                String res = "";
                int indi = editText.getSelectionStart()+1;
                res += s.substring(0, editText.getSelectionStart());
                res+='ә';
                res+=s.substring(editText.getSelectionEnd());
                editText.setText(res);
                editText.setSelection(indi);
            }
        });
        buttonI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                String res = "";
                int indi = editText.getSelectionStart()+1;
                res += s.substring(0, editText.getSelectionStart());
                res+='і';
                res+=s.substring(editText.getSelectionEnd());
                editText.setText(res);
                editText.setSelection(indi);
            }
        });
        buttonN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                String res = "";
                int indi = editText.getSelectionStart()+1;
                res += s.substring(0, editText.getSelectionStart());
                res+='ң';
                res+=s.substring(editText.getSelectionEnd());
                editText.setText(res);
                editText.setSelection(indi);
            }
        });
        buttonG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                String res = "";
                int indi = editText.getSelectionStart()+1;
                res += s.substring(0, editText.getSelectionStart());
                res+='ғ';
                res+=s.substring(editText.getSelectionEnd());
                editText.setText(res);
                editText.setSelection(indi);
            }
        });
        buttonY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                String res = "";
                int indi = editText.getSelectionStart()+1;
                res += s.substring(0, editText.getSelectionStart());
                res+='ү';
                res+=s.substring(editText.getSelectionEnd());
                editText.setText(res);
                editText.setSelection(indi);
            }
        });
        buttonU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                String res = "";
                int indi = editText.getSelectionStart()+1;
                res += s.substring(0, editText.getSelectionStart());
                res+='ұ';
                res+=s.substring(editText.getSelectionEnd());
                editText.setText(res);
                editText.setSelection(indi);
            }
        });
        buttonK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                String res = "";
                int indi = editText.getSelectionStart()+1;
                res += s.substring(0, editText.getSelectionStart());
                res+='қ';
                res+=s.substring(editText.getSelectionEnd());
                editText.setText(res);
                editText.setSelection(indi);
            }
        });
        buttonO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                String res = "";
                int indi = editText.getSelectionStart()+1;
                res += s.substring(0, editText.getSelectionStart());
                res+='ө';
                res+=s.substring(editText.getSelectionEnd());
                editText.setText(res);
                editText.setSelection(indi);
            }
        });
        buttonH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                String res = "";
                int indi = editText.getSelectionStart()+1;
                res += s.substring(0, editText.getSelectionStart());
                res+='һ';
                res+=s.substring(editText.getSelectionEnd());
                editText.setText(res);
                editText.setSelection(indi);
            }
        });


        tenseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (editText.getText().length() > 1) formWord();
                else {
                    formedWord.setText(editText.getText());
                    latinView.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        zhakSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (editText.getText().length() > 1) formWord();
                else {
                    formedWord.setText(editText.getText());
                    latinView.setText("");
                }
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
                if (editText.getText().length() > 1) formWord();
                else {
                    formedWord.setText(editText.getText());
                    latinView.setText("");
                }
            }
        });
        view.post(new Runnable() {
                      @Override
                      public void run() {
                          ViewGroup.LayoutParams params = wordLayout.getLayoutParams();
                          int width = formedWord.getWidth();
                          params.height = (width * 2) / 3;
                          wordLayout.setLayoutParams(params);
                      }
                  }
        );


        final View decorView = getActivity().getWindow().getDecorView();

        decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            private final Rect windowVisibleDisplayFrame = new Rect();
            private int lastVisibleDecorViewHeight;

            @Override
            public void onGlobalLayout() {
                decorView.getWindowVisibleDisplayFrame(windowVisibleDisplayFrame);
                final int visibleDecorViewHeight = windowVisibleDisplayFrame.height();

                if (lastVisibleDecorViewHeight != 0) {
                    if (lastVisibleDecorViewHeight > visibleDecorViewHeight + 150) {
                        extraKeys.setVisibility(View.VISIBLE);
                        ViewGroup.LayoutParams params = wordLayout.getLayoutParams();
                        params.height = -2;
                        wordLayout.setLayoutParams(params);

                    } else if (lastVisibleDecorViewHeight + 150 < visibleDecorViewHeight) {
                        ViewGroup.LayoutParams params = wordLayout.getLayoutParams();
                        int width = formedWord.getWidth();
                        params.height = (width * 2) / 3;
                        wordLayout.setLayoutParams(params);
                        extraKeys.setVisibility(View.INVISIBLE);
                    }
                    scrollView.smoothScrollTo(0,0);
                }
                lastVisibleDecorViewHeight = visibleDecorViewHeight;
            }
        });

        return view;

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
