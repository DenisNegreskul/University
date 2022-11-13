package model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class University implements Serializable {
    @SerializedName("ID университета") private String id;
    @SerializedName("Полное название")private String fullName;
    @SerializedName("Аббревиатура")private String shortName;
    @SerializedName("Год основания")private int yearOfFoundation;
    @SerializedName("Основной профиль обучения")private StudyProfile mainProfile;

    public University() {
    }

    public University(String id, String fullName, String shortName, int yearOfFoundation, StudyProfile mainProfile) {
        this.id = id;
        this.fullName = fullName;
        this.shortName = shortName;
        this.yearOfFoundation = yearOfFoundation;
        this.mainProfile = mainProfile;
    }

    public String getId() {
        return id;
    }

    public University setId(String id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public University setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getShortName() {
        return shortName;
    }

    public University setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public int getYearOfFoundation() {
        return yearOfFoundation;
    }

    public University setYearOfFoundation(int yearOfFoundation) {
        this.yearOfFoundation = yearOfFoundation;
        return this;
    }

    public StudyProfile getMainProfile() {
        return mainProfile;
    }

    public University setMainProfile(StudyProfile mainProfile) {
        this.mainProfile = mainProfile;
        return this;
    }

    @Override
    public String toString() {
        return String.format("University [ id = %-10s, fullName = %-50s, shortName = %5s, yearOfFoundation = %d, mainProfile = %s]",
                id, fullName, shortName, yearOfFoundation, mainProfile);
    }
}
