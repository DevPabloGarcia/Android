package pablogarcia.meetup.Modules.NewMeet;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import pablogarcia.meetup.ApiManager;
import pablogarcia.meetup.Modules.Fragments.PickImage.PickImageInteractor;
import pablogarcia.meetup.R;

import static pablogarcia.meetup.Utils.Consts.REQ_CAMERA;
import static pablogarcia.meetup.Utils.Consts.REQ_GALLERY;
import static pablogarcia.meetup.Utils.Consts.REQ_PLACE;

public class NewMeetActivity extends AppCompatActivity implements NewMeetView, PickImageInteractor.OnSelectImageDialogListener, View.OnClickListener, TextWatcher{

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.newMeetImageView) ImageView newMeetImageView;
    @BindView(R.id.newMeetTextInputLayoutTitle) TextInputLayout newMeetTextInputLayoutTitle;
    @BindView(R.id.newMeetEditTextTitle) EditText newMeetEditTextTitle;
    @BindView(R.id.newMeetTextInputLayoutDescription) TextInputLayout newMeetTextInputLayoutDescription;
    @BindView(R.id.newMeetEditTextDescription) EditText newMeetEditTextDescription;
    @BindView(R.id.newMeetInputLayoutInitDate) TextInputLayout newMeetInputLayoutInitDate;
    @BindView(R.id.newMeetInputLayoutEndDate) TextInputLayout newMeetInputLayoutEndDate;
    @BindView(R.id.newMeetEditTextInitDate) EditText newMeetEditTextInitDate;
    @BindView(R.id.newMeetEditTextEndDate) EditText newMeetEditTextEndDate;
    @BindView(R.id.newMeetEditTextPlace) EditText newMeetEditTextPlace;

    private NewMeetPresenter newMeetPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_meet);

        ButterKnife.bind(this);

        this.setupToolbar();
        this.setupTextInput();
        this.setupImageView();
        this.setupDatePickers();
        this.setupPlacePicker();
        this.newMeetPresenter = new NewMeetPresenter(this);

    }

    @Override
    public void setupToolbar() {
        setSupportActionBar(this.toolbar);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            this.toolbar.setNavigationIcon(R.drawable.ic_action_cancel);
        }
    }

    @Override
    public void setupTextInput(){
        this.newMeetEditTextTitle.addTextChangedListener(this);
        this.newMeetEditTextDescription.addTextChangedListener(this);
    }

    @Override
    public void setupDatePickers(){
        this.newMeetEditTextEndDate.setOnClickListener(this);
        this.newMeetEditTextInitDate.setOnClickListener(this);
    }

    @Override
    public void setupImageView() {
        this.newMeetImageView.setClickable(true);
        this.newMeetImageView.setOnClickListener(this);
    }

    @Override
    public void setupPlacePicker(){
        this.newMeetEditTextPlace.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.newMeetPresenter.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void navigateBack(){
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void uploadImage() {
        this.newMeetPresenter.uploadImage(((BitmapDrawable)this.newMeetImageView.getDrawable()).getBitmap());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_meet, menu);
        return true;
    }

    @Override
    public void onClickSelectFromGallery() {
        Intent intentGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intentGallery.setType("image/*");
        startActivityForResult(intentGallery, REQ_GALLERY);
    }

    @Override
    public void onClickSelectFromCamera() {
        ApiManager.getInstance().showCamera(this, this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQ_CAMERA){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ApiManager.getInstance().openCamera(this);
            } else {
                Toast.makeText(this, "Camera permission denied.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void showToastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateTextInputInitDate(String date) {
        this.newMeetEditTextInitDate.setText(date);
    }

    @Override
    public void updateTextInputEndDate(String date) {
        this.newMeetEditTextEndDate.setText(date);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.newMeetEditTextInitDate:
            case R.id.newMeetEditTextEndDate:
                this.newMeetPresenter.showDateTimePicker(this.getSupportFragmentManager(), view.getId());
                break;
            case R.id.newMeetEditTextPlace:
                this.newMeetPresenter.openPlaceActivity(this);
                break;
            case R.id.newMeetImageView:
                this.newMeetPresenter.openSelectImageSourceFragment(getSupportFragmentManager());
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if (requestCode == REQ_GALLERY){
                ApiManager.getInstance().loadImageIntoImageView(this.newMeetImageView, data.getData());
            }else if(requestCode == REQ_CAMERA){
                Uri imageUri = ApiManager.getInstance().getCameraImagePath(this);
                ApiManager.getInstance().loadImageIntoImageView(this.newMeetImageView, imageUri);
            }else if(requestCode == REQ_PLACE){
                Place place = PlacePicker.getPlace(this, data);
                this.newMeetPresenter.updatePlace(place);
                this.newMeetEditTextPlace.setText(place.getName());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(this.newMeetEditTextTitle.getText().hashCode() == s.hashCode()){
            this.newMeetPresenter.updateTitle(s.toString());
        }else if(this.newMeetEditTextDescription.getText().hashCode() == s.hashCode()){
            this.newMeetPresenter.updateDescription(s.toString());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {}
}
