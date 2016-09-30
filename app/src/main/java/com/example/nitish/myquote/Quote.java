package com.example.nitish.myquote;

/**
 * Created by nitish on 1/10/16.
 */

public class Quote {

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Quote(int _id, String text) {
        this._id = _id;
        this.text = text;
    }

    public Quote() {
    }

    int _id;
    String text;
}
