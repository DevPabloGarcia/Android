package pablogarcia.meetup.Modules.Login;

public class LoginInteractor {

    interface OnLoginFinishedListener {
        void onSucces();

        void onFail();

        void onUserNameError();

        void onUserPassError();

    }

    public void login(String userName, String userPass, OnLoginFinishedListener listener){
        boolean doLogin = true;
        if(userName.isEmpty()){
            listener.onUserNameError();
            doLogin = false;
        }
        if(userPass.isEmpty()){
            listener.onUserPassError();
            doLogin = false;
        }
        if(doLogin == true){
            listener.onSucces();
        }
    }
}
