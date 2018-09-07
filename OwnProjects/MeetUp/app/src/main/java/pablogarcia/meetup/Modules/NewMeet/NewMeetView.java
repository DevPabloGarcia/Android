package pablogarcia.meetup.Modules.NewMeet;

public interface NewMeetView {

    void setupToolbar();
    void setupTextInput();
    void setupDatePickers();
    void setupImageView();
    void setupPlacePicker();
    void navigateBack();
    void uploadImage();
    void showToastMessage(String message);
    void updateTextInputInitDate(String date);
    void updateTextInputEndDate(String date);
}
