package com.example.a77_da_000.sauat;

import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
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

public class ZatFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    String ChangeLastLetter(String s) {
        switch (s.toCharArray()[s.length() - 1]) {
            case 'к':
            case 'К':
                s = s.substring(0, s.length() - 1);
                s += 'г';
                break;
            case 'қ':
            case 'Қ':
                s = s.substring(0, s.length() - 1);
                s += 'ғ';
                break;
            case 'п':
            case 'П':
                s = s.substring(0, s.length() - 1);
                s += 'б';
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
        EditText editText = (EditText) getView().findViewById(R.id.editText);
        Spinner septikSpinner = (Spinner) getView().findViewById(R.id.septik);
        Spinner zhakSpinner = (Spinner) getView().findViewById(R.id.zhak_spinner);
        CheckBox checkBox = (CheckBox) getView().findViewById(R.id.checkBox);
        boolean count = checkBox.isChecked();
        boolean zhuan = true;
        int sept, zhak;
        sept = septikSpinner.getSelectedItemPosition();
        zhak = zhakSpinner.getSelectedItemPosition();
        String input = editText.getText().toString();
        char[] c = input.toCharArray();
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
        String last = "" + c[c.length - 1];
        if (last.equals("ъ") || last.equals("ь")) last = "" + c[c.length - 2];
        String second = "зжилмнңЗЖИЛМНҢ";
        String first = "бвгдқкхфптсчцшщБВГДКҚХФПТСЧЦШЩ";
        if (count) {
            if (second.contains(last)) {
                if (zhuan) input += "дар";
                else input += "дер";
            } else if (first.contains(last)) {
                if (zhuan) input += "тар";
                else input += "тер";
            } else {
                if (zhuan) input += "лар";
                else input += "лер";
            }
            last = "р";
        }
        first = "уәіүұөуеыаоэяиюёУӘІҮҰӨУЕЫАОЭЯИЮЁ";
        switch (zhak) {
            case 1:
                input = ChangeLastLetter(input);
                if (first.contains(last)) input += "м";
                else if (zhuan) input += "ым";
                else input += "ім";
                break;
            case 2:
            case 6:
                input = ChangeLastLetter(input);
                if (first.contains(last)) input += "ң";
                else if (zhuan) input += "ың";
                else input += "ің";
                break;
            case 3:
            case 7:
                input = ChangeLastLetter(input);
                if (!first.contains(last)) {
                    if (zhuan) input += "ы";
                    else input += "і";
                }
                if (zhuan) input += "ңыз";
                else input += "ңіз";
                break;
            case 4:
            case 8:
                input = ChangeLastLetter(input);
                if (first.contains(last)) input += "с";
                if (zhuan) input += "ы";
                else input += "і";
                break;
            case 5:
                input = ChangeLastLetter(input);
                if (!first.contains(last)) {
                    if (zhuan) input += "ы";
                    else input += "і";
                }
                if (zhuan) input += "мыз";
                else input += "міз";
                break;
        }
        c = input.toCharArray();
        last = "" + c[c.length - 1];
        if (last.equals("ъ") || last.equals("ь")) last = "" + c[c.length - 2];
        switch (sept) {
            case 1:
                first = "июлруйжзИЮЛРУЙЖЗ";
                second = "бвгдқкхфптсчцшщБВГДКҚХФПТСЧЦШЩ";
                if (zhak == 4 || zhak == 8) input += "н";
                else if (first.contains(last)) input += "д";
                else if (second.contains(last)) input += "т";
                else input += "н";
                if (zhuan) input += "ың";
                else input += "ің";
                break;
            case 2:
                first = "бвгдқкхфптсчцшщБВГДКҚХФПТСЧЦШЩ";
                if (zhak == 4 || zhak == 8) input += "н";
                else if (zhak == 1 || zhak == 2 || zhak == 6) ;
                else if (first.contains(last)) {
                    if (zhuan) input += "қ";
                    else input += "к";
                } else {
                    if (zhuan) input += "ғ";
                    else input += "г";
                }
                if (zhuan) input += "а";
                else input += "е";
                break;
            case 3:
                first = "июлруйжзмнңИЮЛРУЙЖЗМНҢ";
                second = "бвгдқкхфптсчцшщБВГДКҚХФПТСЧЦШЩ";
                if (zhak == 4 || zhak == 8) {
                    input += "н";
                    break;
                } else if (first.contains(last)) input += "д";
                else if (second.contains(last)) input += "т";
                else input += "н";
                if (zhuan) input += "ы";
                else input += "і";
                break;
            case 4:
                first = "бвгдқкхфптсчцшщБВГДКҚХФПТСЧЦШЩ";
                if (zhak == 4 || zhak == 8) input += "нд";
                else if (first.contains(last)) input += "т";
                else input += "д";
                if (zhuan) input += "а";
                else input += "е";
                break;
            case 5:
                first = "мнңМНҢ";
                second = "бвгдқкхфптсчцшщБВГДКҚХФПТСЧЦШЩ";
                if (zhak == 4 || zhak == 8 || first.contains(last)) input += "н";
                else if (second.contains(last)) input += "т";
                else input += "д";
                if (zhuan) input += "ан";
                else input += "ен";
                break;
            case 6:
                first = "жзЖЗ";
                second = "бвгдқкхфптсчцшщБВГДКҚХФПТСЧЦШЩ";
                if (first.contains(last)) input += "бен";
                else if (second.contains(last)) input += "пен";
                else input += "мен";
                break;
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
                    latinWord += "a'";
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
                    latinWord += "g'";
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
                    latinWord += "i'";
                    break;
                case 'Й':
                case 'й':
                    latinWord += "i'";
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
                    latinWord += "n'";
                    break;
                case 'О':
                case 'о':
                    latinWord += "o";
                    break;
                case 'Ө':
                case 'ө':
                    latinWord += "o'";
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
                    latinWord += "y'";
                    break;
                case 'Ұ':
                case 'ұ':
                    latinWord += "u";
                    break;
                case 'Ү':
                case 'ү':
                    latinWord += "u'";
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
                    latinWord += "c'";
                    break;
                case 'Ш':
                case 'ш':
                    latinWord += "s'";
                    break;
                case 'Щ':
                case 'щ':
                    latinWord += "s'";
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
                    latinWord += "i'y'";
                    break;
                case 'Я':
                case 'я':
                    latinWord += "i'a";
                    break;
                default:
                    latinWord += c[i];
                    break;
            }
            TextView latinView = (TextView) getView().findViewById(R.id.latin);
            latinView.setText(latinWord);
        }
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
        final View view = inflater.inflate(R.layout.fragment_zat, container, false);
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

        final ScrollView scrollView = (ScrollView) view.findViewById(R.id.scrollview);

        final TextView formedWord = (TextView) view.findViewById(R.id.formedWord);
        final EditText editText = (EditText) view.findViewById(R.id.editText);
        final LinearLayout wordLayout = (LinearLayout) view.findViewById(R.id.word_layout);
        final TextView latinView = (TextView) view.findViewById(R.id.latin);
        final TableLayout extraKeys = (TableLayout) view.findViewById(R.id.extra_keys);

        extraKeys.setVisibility(View.INVISIBLE);

        editText.setText("Сөз");


        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);


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


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (editText.getText().length() > 1) formWord();
                else{
                    formedWord.setText(editText.getText());
                    latinView.setText("");
                }
            }
        });

        septikSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (editText.getText().length() > 1) formWord();
                else{
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
                else{
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
                else{
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
