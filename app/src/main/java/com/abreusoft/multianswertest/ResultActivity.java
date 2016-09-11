package com.abreusoft.multianswertest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    private String[] correctAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Bundle rIntent = getIntent().getExtras();
        String[] results = rIntent.getStringArray("sAns");
        correctAns = rIntent.getStringArray("cAns");
        int[] points = rIntent.getIntArray("pts");
        ArrayAdapter<String> list = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, results);

        ListView resultList = (ListView) findViewById(R.id.result_list);
        resultList.setAdapter(list);
        resultList.setOnItemClickListener(this);

        TextView pointsText = (TextView) findViewById(R.id.points_text);

        int total = 0;
        assert points != null;
        for (int point : points) {
            total += point;
        }
        int percentage = (total*100)/ points.length;

        @SuppressLint("DefaultLocale")
        String pointsString = String.format("Total correctas: %d de %d\n Porcentaje de correctas: %d%%", total, points.length, percentage);
        pointsText.setText(pointsString);
    }

    @Override
    protected void onStop() {
        startActivity(new Intent(this, QuestionActivity.class));
        super.onStop();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, "Alternativa correcta: " +correctAns[i], Toast.LENGTH_SHORT).show();
    }
}
