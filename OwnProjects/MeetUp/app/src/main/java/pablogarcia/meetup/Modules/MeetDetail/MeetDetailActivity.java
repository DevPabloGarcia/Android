package pablogarcia.meetup.Modules.MeetDetail;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pablogarcia.meetup.Model.Meet;
import pablogarcia.meetup.R;
import pablogarcia.meetup.Utils.Consts;

public class MeetDetailActivity extends AppCompatActivity implements MeetDetailView, ViewPager.OnPageChangeListener, View.OnClickListener {

    private Meet meet;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.viewPagerDetail) ViewPager viewPagerDetail;
    @BindView(R.id.radio1) RadioButton radioButton1;
    @BindView(R.id.radio2) RadioButton radioButton2;
    @BindView(R.id.meetTitle) TextView meetTitle;
    @BindView(R.id.meetDescription) TextView meetDescription;
    @BindView(R.id.meetInitDate) TextView meetInitDate;
    @BindView(R.id.meetEndDate) TextView meetEndDate;

    private MeetDetailPresenter meetDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meet_detail);

        ButterKnife.bind(this);
        this.getIntents();

        this.meetDetailPresenter = new MeetDetailPresenter(this);
        this.setupToolbar();
        this.setupViewPager();
        this.setupRadioButtons();
        this.setupInfo();
    }

    @Override
    public void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void setupViewPager() {
        this.meetDetailPresenter.setupViewPager(this.viewPagerDetail, getSupportFragmentManager(), meet, this);
    }

    @Override
    public void setupRadioButtons(){
        this.radioButton1.setOnClickListener(this);
        this.radioButton2.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.meetDetailPresenter.onOptionItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void navigateBack(){
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void getIntents(){
        Intent intent = getIntent();
        meet = intent.getParcelableExtra(Consts.MEET_KEY);
    }

    @Override
    public void setupInfo() {
        this.meetTitle.setText(this.meet.getTitle());
        this.meetDescription.setText(this.meet.getDescription());
        this.meetInitDate.setText(this.meet.getInitDate());
        this.meetEndDate.setText(this.meet.getEndDate());
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
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.radio1){
            this.viewPagerDetail.setCurrentItem(0);
        }else{
            this.viewPagerDetail.setCurrentItem(1);
        }
    }
}
