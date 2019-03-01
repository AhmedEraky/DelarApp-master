package com.vegeta.my.dealer.model.registration;

/**
 * Created by Eraky on 1/19/2019.
 */

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationResponse implements Parcelable
{

    @SerializedName("$id")
    @Expose
    private String $id;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("ModelState")
    @Expose
    private ModelState modelState;
    public final static Parcelable.Creator<RegistrationResponse> CREATOR = new Creator<RegistrationResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public RegistrationResponse createFromParcel(Parcel in) {
            return new RegistrationResponse(in);
        }

        public RegistrationResponse[] newArray(int size) {
            return (new RegistrationResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = -6973286330175460L;

    protected RegistrationResponse(Parcel in) {
        this.$id = ((String) in.readValue((String.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.modelState = ((ModelState) in.readValue((ModelState.class.getClassLoader())));
    }

    public RegistrationResponse() {
    }

    public String get$id() {
        if($id==null)
            $id="-1";
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ModelState getModelState() {
        return modelState;
    }

    public void setModelState(ModelState modelState) {
        this.modelState = modelState;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue($id);
        dest.writeValue(message);
        dest.writeValue(modelState);
    }

    public int describeContents() {
        return 0;
    }

}