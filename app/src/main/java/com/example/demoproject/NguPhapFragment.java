package com.example.demoproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class NguPhapFragment extends Fragment {
        private List<EnglishTense> englishTenseList;
        private TenseAdapter adapter;
        private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ngu_phap, container, false);
        addList();
        adapter = new TenseAdapter(getContext(),R.layout.row_english_tense,englishTenseList);
        listView = view.findViewById(R.id.listViewEnglishTense);
        listView.setAdapter(adapter);
        return view;
    }

    private void addList() {
        englishTenseList = new ArrayList<>();
        englishTenseList.add(new EnglishTense("Present Simple Tense","Subject + verb(s/es) + object"));
        englishTenseList.add(new EnglishTense("Present Continuous Tense","Subject + (is / am/ are) + verb(ing) + object"));
        englishTenseList.add(new EnglishTense("Present Perfect Tense","Subject + (have/has) + verb(ed) + object"));
        englishTenseList.add(new EnglishTense("Present perfect Continuous Tense","Subject + (have/has) + been + verb(ing) + object"));
        englishTenseList.add(new EnglishTense("Past Simple Tense","Subject + verb(2nd form) + object"));
        englishTenseList.add(new EnglishTense("Past Continuous Tense","Subject + (was/were) + verb(ing) + object"));
        englishTenseList.add(new EnglishTense("Past Perfect Tense","Subject + had + Verb(ed) + object"));
        englishTenseList.add(new EnglishTense("Past Perfect Continuous Tense","Subject + had been + Verb(ing) + object"));
        englishTenseList.add(new EnglishTense("Future tenses","Subject + shall/will+ verb + object"));
        englishTenseList.add(new EnglishTense("Future Continuous Tense","Subject + shall/will be + Verb(ing) + object"));
        englishTenseList.add(new EnglishTense("Future Perfect Tense","Subject + shall/will + have + verb(3rd form) + object"));
        englishTenseList.add(new EnglishTense("Future Perfect Continuous Tense","Subject + shall/will + have been + verb(ing) + object"));
    }
}