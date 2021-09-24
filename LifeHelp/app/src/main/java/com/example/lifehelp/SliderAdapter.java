package com.example.lifehelp;

import android.content.Context;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter
{

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context)
    {
        this.context = context;
    }

    public int[] slide_images =
            {
            R.drawable.prim,
            R.drawable.seg,
            R.drawable.terc
            };

    public String[] slide_headings =
            {
                    "AJUDA",
                    "PROFISSIONAIS",
                    "DESIGN"
            };

    public String[] slide_descs =
            {
                    "Em caso de dificuldade, dor, desconforto ou necessidade de ajuda, nós estaremos sempre dispostos a ajudar.",
                    "Com uma variedade de ótimos profissionais na área da saúde para serem chamados.",
                    "Aplicativo com design bem intuitivo para todas as idades."
            };



    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject( View view, Object object)
    {
        return view == (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.img1);
        TextView slideHeading = (TextView) view.findViewById(R.id.tit1);
        TextView slideDescription = (TextView) view.findViewById(R.id.ind1);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView((RelativeLayout)object);

    }
}
