package com.example.vitalii.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;
import static android.view.View.OnClickListener;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = QuizActivity.class.getSimpleName();

    private Question[] mQuestionBank = new Question[]
            {
                    new Question(R.string.question_australia, true),
                    new Question(R.string.question_oceans, true),
                    new Question(R.string.question_mideast, false),
                    new Question(R.string.question_africa, false),
                    new Question(R.string.question_americas, true),
                    new Question(R.string.question_asia, true),
            };
    private int mCurrentIndex = 0;

    private Toast mCurrentToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_quiz);
        updateQuestion();

        View trueButton =  findViewById(R.id.true_button);
        View falseButton = findViewById(R.id.false_button);
        View nextButton = findViewById(R.id.next_button);
        View previousButton = findViewById(R.id.previous_button);
        View textView = findViewById(R.id.question_text_view);

        trueButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        falseButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        previousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementIndex();
                updateQuestion();
            }
        });

        OnClickListener nextQuestionListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementIndex();
                updateQuestion();
            }
        };

        nextButton.setOnClickListener(nextQuestionListener);
        textView.setOnClickListener(nextQuestionListener);
    }

    private void updateQuestion() {
        final Question currentQuestion = mQuestionBank[mCurrentIndex];
        TextView mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setText(currentQuestion.getTextResId());
    }

    private void incrementIndex() {
        mCurrentIndex = ++mCurrentIndex % mQuestionBank.length;
    }

    private void decrementIndex() {
        mCurrentIndex = (mCurrentIndex - 1 + mQuestionBank.length) % mQuestionBank.length;
    }

    private Question getCurrentQuestion() {
        return mQuestionBank[mCurrentIndex];
    }

    private void checkAnswer(boolean userPressedTrue) {
        Question currentQuestion = getCurrentQuestion();
        if (mCurrentToast != null) {
            mCurrentToast.cancel();
        }
        if (currentQuestion.isAnswerTrue() == userPressedTrue) {
            mCurrentToast = makeText(getApplicationContext(), R.string.correct_toast, LENGTH_SHORT);
        } else {
            mCurrentToast = makeText(getApplicationContext(), R.string.incorrect_toast, LENGTH_SHORT);
        }
        mCurrentToast.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }


}
