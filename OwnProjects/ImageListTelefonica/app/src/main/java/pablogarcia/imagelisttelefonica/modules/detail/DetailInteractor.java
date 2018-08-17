package pablogarcia.imagelisttelefonica.modules.detail;

import android.view.MenuItem;

public class DetailInteractor {

    interface OnDetailListener{
        void navigateBack();
    }


    public void onOptionItemSelected(MenuItem item, OnDetailListener listener){

        switch (item.getItemId()){
            case android.R.id.home:
                listener.navigateBack();
                break;
        }
    }

}
