package com.hj.recycleraddressbook;

import android.os.Parcel;
import android.os.Parcelable;

public class PersonInfoBean implements Parcelable {
    private String name;
    private String age;
    private String id;
    private String time;

    public PersonInfoBean(String name, String age, String id, String time) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.time = time;
    }

    protected PersonInfoBean(Parcel in) {
        name = in.readString();
        age = in.readString();
        id = in.readString();
        time = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(age);
        dest.writeString(id);
        dest.writeString(time);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PersonInfoBean> CREATOR = new Creator<PersonInfoBean>() {
        @Override
        public PersonInfoBean createFromParcel(Parcel in) {
            return new PersonInfoBean(in);
        }

        @Override
        public PersonInfoBean[] newArray(int size) {
            return new PersonInfoBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
