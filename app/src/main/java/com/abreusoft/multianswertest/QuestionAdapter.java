package com.abreusoft.multianswertest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class QuestionAdapter extends FragmentStatePagerAdapter {

    private String[] Questions = {"Es una rama de las Ciencias de la Naturaleza", "Es un animal mamifero",
            "Es un color calido", "Es un material usado en el modelado",
            "Es un pais del Caribe", "Es un arte visual"};

    private String[][] answers = {{"Fisica", "Algebra", "Historia", "Geografia"},
                    {"Gallina", "Rana", "Gato", "Cangrejo"},
                    {"Azul cielo", "Amarillo", "gris", "turquesa"},
                    {"Hierro", "Cristal", "Madera", "Barro"},
                    {"Mexico", "Cuba", "Peru", "Estados Unidos"},
                    {"Teatro", "Danza", "Musica", "Pintura"}};

    public QuestionAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return QuestionFragment.newInstance(Questions[position], answers[position]);
    }

    @Override
    public int getCount() {
        return Questions.length;
    }
}
