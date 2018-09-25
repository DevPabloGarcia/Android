package pablo.com.mipiso.ui.main.taskList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import pablo.com.mipiso.R;
import pablo.com.mipiso.model.User;

public class TasksListTaskViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.user_image)
    ImageView userImage;

    @BindView(R.id.user_name)
    TextView userName;

    @BindView(R.id.user_task)
    TextView userTask;

    @BindView(R.id.user_money)
    TextView userMoney;

    @BindView(R.id.task_done)
    ImageView imageView;

    public TasksListTaskViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(User user){
        this.userName.setText(user.getName());
        this.userTask.setText(user.getCurrentTask().getName());
        this.userMoney.setText(Integer.toString(user.getNextPaid()));
        if(user.getCurrentTask().getDone()){
            imageView.setImageResource(R.drawable.checked);
        }else{
            imageView.setImageResource(R.drawable.cancel);
        }
        Picasso.get().load(user.getImage()).fit().centerCrop().into(this.userImage);
    }
}
