package com.abreusoft.multianswertest;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

public class QuestionActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, QuestionFragment.OnAnswerRequest {

    private String[] sAns = new String[6];
    private int[] pts = new int[6];
    private QuestionAdapter qAdapter;
    private ViewPager pager;
    private TextView qNumber;
    private Button resultsBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        //Esta es la instancia de la clase qAdapter.
        qAdapter = new QuestionAdapter(getSupportFragmentManager());
        pager = (ViewPager) findViewById(R.id.view_pager);
        //El setAdapter adapta las paginas de qAdapter al pager.
        pager.setAdapter(qAdapter);
        //Esto da una respuesta cuando ocurre algun cambio en el pager.
        pager.addOnPageChangeListener(this);

        qNumber = (TextView) findViewById(R.id.q_num);
        //Este TextView recibe la pagina actual y el total de paginas del pager y la coloca en el encabezado.
        qNumber.setText(String.format("Pregunta %d de %d", pager.getCurrentItem() + 1, qAdapter.getCount()));

        resultsBT = (Button) findViewById(R.id.results_bt);
        resultsBT.setVisibility(View.INVISIBLE);
        resultsBT.setEnabled(false);
    }

    //Este metodo es usado por los botones de atras y siguiente para cambiar la pagina.
    public void changePage(View view) {
        int current = pager.getCurrentItem();
        if (view.getId() == R.id.previous_bt) {
            pager.setCurrentItem(current - 1);
        } else if (view.getId() == R.id.next_bt) {
            pager.setCurrentItem(current + 1);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //Este metodo escribe el numero de la pregunta que esta en pantalla
    @Override
    public void onPageSelected(int position) {
        qNumber.setText(String.format("Pregunta %d de %d", pager.getCurrentItem() + 1, qAdapter.getCount()));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private String[] cAns = new String[]{"Fisica", "Gato", "Amarillo", "Barro", "Cuba", "Pintura"};

    @Override
    public void requestAnswer(String s) {
        int qPos = pager.getCurrentItem();

        if (!cAns[qPos].equals(s)) {
            sAns[qPos] = "Respuesta " + (qPos + 1) + " incorrecta";
            pts[qPos] = 0;
        } else {
            sAns[qPos] = "Respuesta " + (qPos + 1) + " correcta";
            pts[qPos] = 1;
        }

        if (!Arrays.asList(sAns).contains(null)) {
            resultsBT.setVisibility(View.VISIBLE);
            resultsBT.setEnabled(true);
        }
    }

    public void onViewResults(View view) {
        Intent rIntent = new Intent("android.intent.action.RESULTS");
        rIntent.putExtra("sAns", sAns);
        rIntent.putExtra("cAns", cAns);
        rIntent.putExtra("pts", pts);
        startActivity(rIntent);
    }
}
