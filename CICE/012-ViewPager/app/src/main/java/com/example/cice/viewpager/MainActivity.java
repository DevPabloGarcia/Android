package com.example.cice.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    ViewPager viewPager;
    RadioButton radioButton1,radioButton2,radioButton3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Aqui configuramos el view Pager con su adapter

        // BIDING
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        radioButton1 = (RadioButton) findViewById(R.id.radio1);
        radioButton2 = (RadioButton) findViewById(R.id.radio2);
        radioButton3 = (RadioButton) findViewById(R.id.radio3);

        ArrayList<Fragment> fragmentArrayList  = new ArrayList<>();
        fragmentArrayList.add(Page1Fragment.newInstance());
        fragmentArrayList.add(Page2Fragment.newInstance());
        fragmentArrayList.add(Page3Fragment.newInstance());

        // Creo el adapter.
        FragmentPagerAdapter fragmentPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),fragmentArrayList); // getSupportFragmentManager -> Pertenece a la activity.

        // Asociamos el adapter con el viewPager
        viewPager.setAdapter(fragmentPagerAdapter);

        viewPager.addOnPageChangeListener(this);
    }

    public void goToPageRadioButton(View v){
        switch (v.getId()){
            case R.id.radio1:
                // Cambio a la pagina 1.
                viewPager.setCurrentItem(0);
                /**
                 * TODO :Añadimos dinamicamente un elemento cada vez que se pulse a un radibutton.
                 */
                ((ViewPagerAdapter)viewPager.getAdapter()).addFramgent(Page1Fragment.newInstance());
                viewPager.getAdapter().notifyDataSetChanged();//Notificar al adapter que a cambiado, en este momento se llama al getCount del adapater.
                break;
            case R.id.radio2:
                // Cambio a la pagina 2.
                viewPager.setCurrentItem(1);
                ((ViewPagerAdapter)viewPager.getAdapter()).addFramgent(Page2Fragment.newInstance());
                viewPager.getAdapter().notifyDataSetChanged();//Notificar al adapter que a cambiado, en este momento se llama al getCount del adapater.
                break;
            case R.id.radio3:
                // Cambio a la pagina 3.
                viewPager.setCurrentItem(2);
                ((ViewPagerAdapter)viewPager.getAdapter()).addFramgent(Page3Fragment.newInstance());
                viewPager.getAdapter().notifyDataSetChanged();//Notificar al adapter que a cambiado, en este momento se llama al getCount del adapater.
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                radioButton1.setChecked(true);
                break;
            case 1:
                radioButton2.setChecked(true);
                break;
            case 2:
                radioButton3.setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if(state==ViewPager.SCROLL_STATE_DRAGGING){

        }else if(state==ViewPager.SCROLL_STATE_SETTLING){

        }else if(state==ViewPager.SCROLL_STATE_IDLE){

        }
    }
}