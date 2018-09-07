package pablogarcia.meetup.Modules.Main;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import pablogarcia.meetup.ApiManager;
import pablogarcia.meetup.Modules.Login.LoginActivity;
import pablogarcia.meetup.R;

public class MainActivity extends AppCompatActivity implements MainView{

    @BindView(R.id.drawerLayout) DrawerLayout drawerLayout;
    @BindView(R.id.navigationView) NavigationView navigationView;

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        this.mainPresenter = new MainPresenter(this);

        this.setupNavigationView(this.navigationView);

        this.updateUserInfo();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        this.mainPresenter.onOptionItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setupNavigationView(NavigationView navigationView) {
        this.mainPresenter.setupNavigationView(navigationView);
    }


    @Override
    public void openDrawer() {
        this.drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment).commit();
    }

    @Override
    public void navigateLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void updateUserInfo(){
        ApiManager.getInstance().updateCurrentUser(FirebaseAuth.getInstance().getCurrentUser());
        this.mainPresenter.updateUserImage();
        this.mainPresenter.updateUserName();
    }

    @Override
    public void setUserImage(String image) {
        View headerView = this.navigationView.getHeaderView(0);
        CircleImageView imageView = headerView.findViewById(R.id.imageDrawerHeader);
        Picasso.get().load(image).into(imageView);
    }

    @Override
    public void setUserName(String name) {
        View headerView = this.navigationView.getHeaderView(0);
        TextView textView = headerView.findViewById(R.id.userNameDrawerLayout);
        textView.setText(name);
    }

}
