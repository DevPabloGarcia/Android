package garcia.pablo.recyclerviewadvance;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

/**
 * Created by V on 29/1/16.
 */
public class SwipeCallBack extends ItemTouchHelper.Callback {

    interface OnSwipedListener{
        void onSwipe(RecyclerView.ViewHolder viewHolder);
    }

    OnSwipedListener callback;

    public SwipeCallBack(OnSwipedListener callback) {
        this.callback = callback;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if(viewHolder instanceof BoyViewHolder)
            return makeMovementFlags(0,ItemTouchHelper.START | ItemTouchHelper.LEFT);
        else return makeMovementFlags(0,0);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        callback.onSwipe(viewHolder);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

        if(viewHolder instanceof BoyViewHolder){
            View v = viewHolder.itemView.findViewById(R.id.foreground);
            v.setTranslationX(dX);
        }else{
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

    }
}
