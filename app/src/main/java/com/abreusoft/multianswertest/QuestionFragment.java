package com.abreusoft.multianswertest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;

public class QuestionFragment extends Fragment implements RadioButton.OnCheckedChangeListener {

    private static final String QUESTION = "question";
    private static final String ANSWERS = "selAnswers";
    private String question;
    private String[] answers = new String[4];

    private TextView qTextTV;
    private RadioButton ans1RB, ans2RB, ans3RB, ans4RB;

    public QuestionFragment() {

    }

    public interface OnAnswerRequest {
        void requestAnswer(String selAns);
    }

    OnAnswerRequest answerRequest;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        answerRequest = (OnAnswerRequest) context;
    }

    public static QuestionFragment newInstance(String question, String[] answers) {
        QuestionFragment qFragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putString(QUESTION, question);
        args.putStringArray(ANSWERS, answers);
        qFragment.setArguments(args);
        return qFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            question = this.getArguments().getString(QUESTION);
            answers = this.getArguments().getStringArray(ANSWERS);
        }

        assert answers != null;
        Collections.shuffle(Arrays.asList(answers));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_question, container, false);

        qTextTV = (TextView) rootView.findViewById(R.id.q_text);
        ans1RB = (RadioButton) rootView.findViewById(R.id.ans_1);
        ans2RB = (RadioButton) rootView.findViewById(R.id.ans_2);
        ans3RB = (RadioButton) rootView.findViewById(R.id.ans_3);
        ans4RB = (RadioButton) rootView.findViewById(R.id.ans_4);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        qTextTV.setText(question);
        ans1RB.setText(answers[0]);
        ans2RB.setText(answers[1]);
        ans3RB.setText(answers[2]);
        ans4RB.setText(answers[3]);

        ans1RB.setOnCheckedChangeListener(this);
        ans2RB.setOnCheckedChangeListener(this);
        ans3RB.setOnCheckedChangeListener(this);
        ans4RB.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(b) {
            String selected = compoundButton.getText().toString();
            answerRequest.requestAnswer(selected);
        }
    }
}
