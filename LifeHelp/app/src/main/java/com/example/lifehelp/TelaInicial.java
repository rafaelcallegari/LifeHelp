package com.example.lifehelp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class TelaInicial extends AppCompatActivity {

    private ViewPager sSlidePager;
    private LinearLayout dDotLayout;

    private TextView[] dDots;

    private SliderAdapter ssliderAdapter;

    private ImageButton vVoltar;
    private ImageButton pProximo;

    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        sSlidePager = (ViewPager) findViewById(R.id.SlidePager);
        dDotLayout = (LinearLayout) findViewById(R.id.dDotLayout);

        pProximo = (ImageButton) findViewById(R.id.proxProximo);
        vVoltar = (ImageButton) findViewById(R.id.voltVoltar);

        ssliderAdapter = new SliderAdapter(this);

        sSlidePager.setAdapter(ssliderAdapter);

        sSlidePager.addOnPageChangeListener(viewListener);

        addDotsIndicator(0);


        pProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sSlidePager.setCurrentItem(mCurrentPage + 1);
            }
        });
        vVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sSlidePager.setCurrentItem(mCurrentPage - 1);
            }
        });
    }

    public void addDotsIndicator(int position)
    {
        dDots = new TextView[3];
        dDotLayout.removeAllViews();

        for(int i = 0; i < dDots.length; i++)
        {
            dDots[i] = new TextView(this);
            dDots[i].setText(Html.fromHtml("&#8226"));
            dDots[i].setTextSize(35);
            dDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            dDotLayout.addView(dDots[i]);
        }

        if(dDots.length > 0)
        {
            dDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener()
    {
        @Override
        public void onPageScrolled(int i, float v, int il)
        {

        }
        public void onPageSelected(int i)
        {

            addDotsIndicator(i);
            mCurrentPage = i;

            if(i == 0){

                pProximo.setEnabled(true);
                vVoltar.setEnabled(false);
                vVoltar.setVisibility(View.INVISIBLE);


            } else if( i == dDots.length - 1){

                pProximo.setEnabled(true);
                vVoltar.setEnabled(true);
                vVoltar.setVisibility(View.VISIBLE);

                pProximo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent ittn = new Intent(TelaInicial.this, TelaLogin.class);
                        startActivity(ittn);



                    }
                });



            } else {

                pProximo.setEnabled(true);
                vVoltar.setEnabled(true);
                vVoltar.setVisibility(View.VISIBLE);



            }

        }
        public void onPageScrollStateChanged(int i)
        {

        }
    };

}