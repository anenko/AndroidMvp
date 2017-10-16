package com.alex.pattern.data.models.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Alex
 */

public class WordRealmModel extends RealmObject {

    public static final String ID = "id";
    public static final String ENGLISH_BOX = "englishBox";
    public static final String ENGLISH_DATE = "englishDate";
    public static final String RUSSIAN_BOX = "russianBox";
    public static final String RUSSIAN_DATE = "russianDate";

    @PrimaryKey
    private int id;
    private String english;
    private String transcription;
    private String russian;
    private int englishBox;
    private long englishDate;
    private int russianBox;
    private long russianDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public String getRussian() {
        return russian;
    }

    public void setRussian(String russian) {
        this.russian = russian;
    }

    public int getEnglishBox() {
        return englishBox;
    }

    public void setEnglishBox(int englishBox) {
        this.englishBox = englishBox;
    }

    public long getEnglishDate() {
        return englishDate;
    }

    public void setEnglishDate(long englishDate) {
        this.englishDate = englishDate;
    }

    public int getRussianBox() {
        return russianBox;
    }

    public void setRussianBox(int russianBox) {
        this.russianBox = russianBox;
    }

    public long getRussianDate() {
        return russianDate;
    }

    public void setRussianDate(long russianDate) {
        this.russianDate = russianDate;
    }
}
