package pablogarcia.meetup.Managers.RecyclerViewManager;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import pablogarcia.meetup.Model.Meet;
import pablogarcia.meetup.R;

public class MeetViewHolder extends RecyclerView.ViewHolder {

    private Meet meet;
    private OnClickMeetRow mListener;
    private View row;

    @BindView(R.id.meetImage) ImageView meetImage;
    @BindView(R.id.meetTitle) TextView meetTitle;
    @BindView(R.id.meetDescription) TextView meetDescription;
    @BindView(R.id.meetInitDate) TextView meetInitDate;
    @BindView(R.id.meetEndDate) TextView meetEndDate;


    public MeetViewHolder(View itemView, OnClickMeetRow listener) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        this.mListener = listener;
        this.row = itemView;


    }

    public void bind(final Meet meet){
        this.meet = meet;

        this.meetTitle.setText(meet.getTitle());
        this.meetDescription.setText(meet.getDescription());
        this.meetInitDate.setText(meet.getInitDate());
        this.meetEndDate.setText(meet.getEndDate());

        Picasso.get()
                .load(meet.getImage())
                .fit()
                .centerCrop()
                .into(this.meetImage);

        this.row.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mListener.onClickMeetRow(meet);
            }
        });
    }

    public void setMeet(Meet meet){
        this.meet = meet;
    }

    public Meet getMeet() {
        return meet;
    }
}
