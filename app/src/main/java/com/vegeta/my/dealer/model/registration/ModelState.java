package com.vegeta.my.dealer.model.registration;

/**
 * Created by Eraky on 1/19/2019.
 */
import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelState implements Serializable, Parcelable
{

    @SerializedName("$id")
    @Expose
    private String $id;
    @SerializedName("model.Password")
    @Expose
    private List<String> modelPassword = null;
    @SerializedName("model.ConfirmPassword")
    @Expose
    private List<String> modelConfirmPassword = null;
    @SerializedName("model.UserName")
    @Expose
    private List<String> modelUserName = null;
    public final static Parcelable.Creator<ModelState> CREATOR = new Creator<ModelState>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ModelState createFromParcel(Parcel in) {
            return new ModelState(in);
        }

        public ModelState[] newArray(int size) {
            return (new ModelState[size]);
        }

    }
            ;
    private final static long serialVersionUID = 5004232587231679198L;

    protected ModelState(Parcel in) {
        this.$id = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.modelPassword, (java.lang.String.class.getClassLoader()));
        in.readList(this.modelConfirmPassword, (java.lang.String.class.getClassLoader()));
        in.readList(this.modelUserName, (java.lang.String.class.getClassLoader()));
    }

    public ModelState() {
    }

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public List<String> getModelPassword() {
        return modelPassword;
    }

    public void setModelPassword(List<String> modelPassword) {
        this.modelPassword = modelPassword;
    }

    public List<String> getModelConfirmPassword() {
        return modelConfirmPassword;
    }

    public void setModelConfirmPassword(List<String> modelConfirmPassword) {
        this.modelConfirmPassword = modelConfirmPassword;
    }

    public List<String> getModelUserName() {
        return modelUserName;
    }

    public void setModelUserName(List<String> modelUserName) {
        this.modelUserName = modelUserName;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue($id);
        dest.writeList(modelPassword);
        dest.writeList(modelConfirmPassword);
        dest.writeList(modelUserName);
    }

    public int describeContents() {
        return 0;
    }

}