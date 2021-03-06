package com.tripsters.android.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;

import com.tripsters.android.util.LogUtils;

/**
 * 图片富文本
 */
public class RichMediaInfo extends RichInfo {

    public static final Creator<RichMediaInfo> CREATOR = new Creator<RichMediaInfo>() {

        @Override
        public RichMediaInfo[] newArray(int size) {
            return new RichMediaInfo[size];
        }

        @Override
        public RichMediaInfo createFromParcel(Parcel source) {
            RichMediaInfo richMediaInfo = new RichMediaInfo();

            richMediaInfo.id = source.readString();
            richMediaInfo.mediaInfo = source.readParcelable(MediaInfo.class.getClassLoader());
            richMediaInfo.picInfo = source.readParcelable(PicInfo.class.getClassLoader());

            return richMediaInfo;
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeParcelable(mediaInfo, flags);
        dest.writeParcelable(picInfo, flags);
    }

    private String id;
    private MediaInfo mediaInfo;
    private PicInfo picInfo;

    public RichMediaInfo() {
        setType(Type.PIC);
    }

    public RichMediaInfo(MediaInfo mediaInfo) {
        this();
        this.mediaInfo = mediaInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MediaInfo getMediaInfo() {
        return mediaInfo;
    }

    public void setMediaInfo(MediaInfo mediaInfo) {
        this.mediaInfo = mediaInfo;
    }

    public PicInfo getPicInfo() {
        return picInfo;
    }

    public void setPicInfo(PicInfo picInfo) {
        this.picInfo = picInfo;
    }

    @Override
    public JSONObject getJsonObject(boolean local) {
        try {
            JSONObject jobj = new JSONObject();

            jobj.put(VALUE_TYPE, Type.PIC.value);
            if (local) {
                JSONObject jobjMedia = new JSONObject();
                jobjMedia.put("id", mediaInfo.getId());

                jobj.put("mediaInfo", jobjMedia);
            } else {
                jobj.put("id", id);
            }

            return jobj;
        } catch (JSONException e) {
            LogUtils.loge(e);
        }

        return null;
    }

    @Override
    protected void read(Parcel source) {
        id = source.readString();
        mediaInfo = source.readParcelable(MediaInfo.class.getClassLoader());
        picInfo = source.readParcelable(PicInfo.class.getClassLoader());
    }

    @Override
    protected void write(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeParcelable(mediaInfo, flags);
        dest.writeParcelable(picInfo, flags);
    }
}
