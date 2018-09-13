package pablogarcia.meetup.Modules.Register;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pablogarcia.meetup.ApiManager;
import pablogarcia.meetup.Modules.Fragments.PickImage.PickImageInteractor;
import pablogarcia.meetup.R;

import static pablogarcia.meetup.Utils.Consts.REQ_CAMERA;
import static pablogarcia.meetup.Utils.Consts.REQ_GALLERY;

public class RegisterActivity extends AppCompatActivity implements RegisterView, TextWatcher, View.OnClickListener, PickImageInteractor.OnSelectImageDialogListener {

    private RegisterPresenter registerPresenter;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.userImage) ImageView userImage;
    @BindView(R.id.userName) EditText userName;
    @BindView(R.id.userPass) EditText userPass;
    @BindView(R.id.userConfirmPass) EditText userConfirmPass;
    @BindView(R.id.userEmail) EditText userEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.registerPresenter = new RegisterPresenter(this);

        ButterKnife.bind(this);

        this.setupToolbar();
        this.setupImageView();
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
    public void setupImageView(){
        this.userImage.setClickable(true);
        this.userImage.setOnClickListener(this);
    }


    public void onClickRegisterButton(View view){
        this.registerPresenter.uploadImage(((BitmapDrawable)this.userImage.getDrawable()).getBitmap());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(this.userName.getText().hashCode() == s.hashCode()){
            this.registerPresenter.updateUserName(s.toString());
        }else if(this.userPass.getText().hashCode() == s.hashCode()){
            this.registerPresenter.updateUserPass(s.toString());
        }else if(this.userEmail.getText().hashCode() == s.hashCode()){
            this.registerPresenter.updateUserEmail(s.toString());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {}

    @Override
    public void onClick(View v) {
        Log.i("DEVELOPING", "Image clicked");
        this.registerPresenter.openSelectImageSourceFragment(getSupportFragmentManager());
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
    public void navigateBack(){
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_GALLERY){
            ApiManager.getInstance().loadImageIntoImageView(this.userImage, data.getData());
        }else if(requestCode == REQ_CAMERA){
            Uri imageUri = ApiManager.getInstance().getCameraImagePath(this);
            ApiManager.getInstance().loadImageIntoImageView(this.userImage, imageUri);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.registerPresenter.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }
}
