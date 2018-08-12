package pablogarcia.hastengroupapp.Activities.StartActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import pablogarcia.hastengroupapp.Activities.MainActivity.MainActivity;
import pablogarcia.hastengroupapp.R;

public class StartActivity extends AppCompatActivity implements StartView {

    StartPresenter presenter;

    @BindView(R.id.progressBar) ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        presenter = new StartPresenter(this, new StartInteractor());
        presenter.navigateMainActivity();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void navigateMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
