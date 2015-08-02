package com.zjiajun.firstapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhujiajun
 * 15/8/2 15:30
 */
public class PersonParcelable implements Parcelable {

    private String name;
    private Integer age;

    public PersonParcelable(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    private PersonParcelable(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }

    public static final Creator<PersonParcelable> CREATOR = new Creator<PersonParcelable>() {
        @Override
        public PersonParcelable createFromParcel(Parcel in) {
            return new PersonParcelable(in);
        }

        @Override
        public PersonParcelable[] newArray(int size) {
            return new PersonParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }

    @Override
    public String toString() {
        return "PersonParcelable{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}


