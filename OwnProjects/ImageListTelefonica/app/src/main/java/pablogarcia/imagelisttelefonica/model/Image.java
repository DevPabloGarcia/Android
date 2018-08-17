package pablogarcia.imagelisttelefonica.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import pablogarcia.imagelisttelefonica.utils.Consts;

public class Image implements Comparable<Image>, Parcelable{

    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("filename")
    @Expose
    private String filename;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("author_url")
    @Expose
    private String authorUrl;
    @SerializedName("post_url")
    @Expose
    private String postUrl;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    /**
     * Get the image full path
     * @return
     */
    public String getImgePath(){
        return Consts.IMAGES_PATH.concat(this.id.toString());
    }

    @Override
    public int compareTo(@NonNull Image image) {
        return author.compareTo(image.author);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.format);
        dest.writeString(this.filename);
        dest.writeString(this.author);
        dest.writeString(this.authorUrl);
        dest.writeString(this.postUrl);
        dest.writeInt(this.id);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
    }

    protected Image(Parcel in){
        this.format = in.readString();
        this.filename = in.readString();
        this.author = in.readString();
        this.authorUrl = in.readString();
        this.postUrl = in.readString();
        this.id = in.readInt();
        this.width = in.readInt();
        this.height = in.readInt();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
