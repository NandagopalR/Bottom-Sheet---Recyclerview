package com.nanda.bottomsheetlistview.fragment;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.StringTokenizer;


/*This is the Parent fragment for all the fragment classes. */
public abstract class BaseFragment extends Fragment {

    public FragmentActivity mActivity;
    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;
    protected View mParentView;
    CheckBox mSelectAll;
    Dialog mContactDialog;
    Button mContactsDone, mCreatedList;
    EditText inputSearch;
    LayoutInflater inflater;
    private Typeface typeface;

    /*This called first in this fragment*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (FragmentActivity) getActivity();
        inflater = (LayoutInflater) mActivity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /*This calls after view is created*/
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mParentView = view;
    }

    /*This is called after restart the current class*/
    @Override
    public void onStart() {
        super.onStart();
        if (mParentView != null)
            changeTypeface((ViewGroup) mParentView);
    }

    /*This resumes the fragment*/
    @Override
    public void onResume() {
        super.onResume();

    }

    public Fragment setPagerFragment(Fragment fragment, int pos) {
        Bundle args = new Bundle();
        args.putInt("PAGER_COUNT", pos);
        fragment.setArguments(args);
        return fragment;
    }

    public Fragment setPagerFragment(Fragment fragment, String key, int pos, String json_key, String json_value) {
        Bundle args = new Bundle();
        args.putInt(key, pos);
        args.putString(json_key, json_value);
        fragment.setArguments(args);
        return fragment;
    }

    /*This method is used to call the new Fragment and sets into the Frame Layout */
    public void setNewFragment(Fragment fragment, int fragment_content, String title,
                               boolean addbackstack) {
        FragmentManager manager = mActivity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(fragment_content, fragment);
        if (addbackstack)
            transaction.addToBackStack(title);
        transaction.commit();

    }


    public void makeToast(String value) {
        Toast.makeText(mActivity, " " + value, Toast.LENGTH_SHORT).show();
    }

    public void hideKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /*This creates the font style*/
    protected void changeTypeface(ViewGroup vGroup) {
        for (int i = 0; i < vGroup.getChildCount(); i++) {
            View v = vGroup.getChildAt(i);
            if (v instanceof TextView) {
                ((TextView) v).setTypeface(typeface);
            } else if (v instanceof RadioButton) {
                ((RadioButton) v).setTypeface(typeface);
            } else if (v instanceof DrawerLayout || v instanceof FrameLayout
                    || v instanceof LinearLayout || v instanceof RelativeLayout
                    || v instanceof RadioGroup || v instanceof ListView) {
                changeTypeface((ViewGroup) v);
            }
        }
    }

    public String splitFromString(String stringName) {
        StringBuilder sb = new StringBuilder();
        String unwanted, wanted, wantedOne, wantedTwo, unWantedone;
        StringTokenizer tokenize = new StringTokenizer(stringName, ".");
        wanted = "" + tokenize.nextToken();
        unwanted = "" + tokenize.nextToken();
        StringTokenizer tokenizer = new StringTokenizer(wanted, "T");
        sb.append("" + tokenizer.nextToken());
        sb.append(" ");
        unWantedone = "" + tokenizer.nextToken();
        String[] wantedTime = unWantedone.split(":");
        wantedOne = wantedTime[0];
        wantedTwo = wantedTime[1];
        sb.append(wantedOne + ":" + wantedTwo);
        Log.e("Date", "" + sb.toString());
        return sb.toString();
    }

    /*This used to pass the fragment with parameter instead of constructor*/
    Fragment setFArgs(Fragment f, String... param) {
        Bundle args = new Bundle();
        for (int i = 0; i < param.length; i += 2) {
            args.putString(param[i], param[i + 1]);
        }
        f.setArguments(args);
        return f;
    }

    // Fragment setPageFragment(Fragment fragment, String title) {
    // Bundle b = new Bundle();
    // b.putString("title", title);
    // fragment.setArguments(b);
    // return fragment;
    // }

}
