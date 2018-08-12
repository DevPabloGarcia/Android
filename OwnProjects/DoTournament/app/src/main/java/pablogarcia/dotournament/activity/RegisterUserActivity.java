package pablogarcia.dotournament.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.parse.ParseException;
import com.parse.SignUpCallback;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import pablogarcia.dotournament.R;
import pablogarcia.dotournament.apimanager.ApiManager;
import pablogarcia.dotournament.customview.CircularImageView;
import pablogarcia.dotournament.customview.CustomProgressDialog;

public class RegisterUserActivity extends AppCompatActivity implements SignUpCallback{

    private static final int RESULT_LOAD_IMAGE = 0;

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.image_view) CircularImageView imageView;
    @Bind(R.id.text_view_name) EditText editTextName;
    @Bind(R.id.edit_text_surname) EditText editTextSurname;
    @Bind(R.id.edit_text_mail) EditText editTextMail;
    @Bind(R.id.edit_text_pass1) EditText editTextPass1;
    @Bind(R.id.edit_text_pass2) EditText editTextPass2;
    @Bind(R.id.radio_admin) RadioButton radioAdmin;

    private ApiManager apiManager;
    private CustomProgressDialog customProgressDialog;


    //region Override Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        ButterKnife.bind(this);
        apiManager = ApiManager.getInstance(this);

        this.setupToolbar();
        this.setupImageOnClick();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void done(ParseException e) {
        customProgressDialog.dismiss();
        if(e == null){
            Log.e(getString(R.string.app_name), getString(R.string.log_user_singed));
            navigateToMainActivity();
        }
        else{
            Log.e(getString(R.string.app_name), e.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && null != data) {
            this.loadImageFromStorage(data);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    //endregion

    //region Setup

    private void setupToolbar(){
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(getString(R.string.user_detail));
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        }
    }

    private void setupImageOnClick(){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
            }
        });
    }

    //endregion

    //region Private Methods

    private void navigateToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private ArrayList<EditText> getEditTextList(){

        ArrayList<EditText> list = new ArrayList<>();

        list.add(editTextName);
        list.add(editTextSurname);
        list.add(editTextMail);
        list.add(editTextPass1);
        list.add(editTextPass2);

        return list;
    }

    private void loadImageFromStorage(Intent data){
        Uri selectedImage = data.getData();
        imageView.loadAndRoundImage(this, selectedImage);
    }

    //endregion

    public void onClickSubmit(View view){

        String name = editTextName.getText().toString().trim();
        String surname = editTextSurname.getText().toString().trim();
        String mail = editTextMail.getText().toString().trim();
        String pass1 = editTextPass1.getText().toString().trim();
        Boolean isAdmin = radioAdmin.isChecked();

        ArrayList<EditText> list = getEditTextList();

        if((apiManager.validateInputs(list, getString(R.string.empty_field_error)))
                && apiManager.validateInputsPass(editTextPass1,editTextPass2, getString(R.string.wrong_pass_error))){

            customProgressDialog = new CustomProgressDialog(this,getString(R.string.progress_sign_in_title), getString(R.string.progress_sign_in_message));
            this.apiManager.signUpUser(name, surname, mail, pass1, isAdmin, imageView.createByteImage() ,this);

        }
    }

}
