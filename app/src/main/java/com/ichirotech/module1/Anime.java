package com.ichirotech.module1;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Anime implements Parcelable {
    /**
     * ini kelas untuk nampung semua data dari list anime nya
     * kelas pojo atau PLAIN OLD JAVA OBJECT
     * semua method getter setter di aplikasikan
     * menggunakan parcelable
     *
     * */
    private String id       = "id";
    private String judul    = "judul";
    private String url      = "url";
    private String srcVideo = "srcVideo";
    private String prev     = "prev";
    private String all      = "all";
    private String next     = "next";
    private String img      = "img";
    private String date     = "date";
    private String ganre    = "genre";
    private String halaman  = "halaman";

    public Anime(JSONObject obj){
        try {
            String id           = obj.getString("id");
            String judul        = obj.getString("judul");
            String url          = obj.getString("url");
            String src_video    = obj.getString("src_video");
            String prev_video   = obj.getString("prev_video");
            String all_video    = obj.getString("all_video");
            String next_video   = obj.getString("next_video");
            String gambar       = obj.getString("gambar");
            String tanggal      = obj.getString("tanggal");
            String genre        = obj.getString("genre");
            String halaman      = obj.getString("halaman");

            this.id         = id;
            this.judul      = judul;
            this.url        = url;
            this.srcVideo   = src_video;
            this.prev       = prev_video;
            this.all        = all_video;
            this.next       = next_video;
            this.img        = gambar;
            this.date       = tanggal;
            this.ganre      = genre;
            this.halaman    = halaman;

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSrcVideo() {
        return srcVideo;
    }

    public void setSrcVideo(String srcVideo) {
        this.srcVideo = srcVideo;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGanre() {
        return ganre;
    }

    public void setGanre(String ganre) {
        this.ganre = ganre;
    }

    public String getHalaman() {
        return halaman;
    }

    public void setHalaman(String halaman) {
        this.halaman = halaman;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.judul);
        dest.writeString(this.url);
        dest.writeString(this.srcVideo);
        dest.writeString(this.prev);
        dest.writeString(this.all);
        dest.writeString(this.next);
        dest.writeString(this.img);
        dest.writeString(this.date);
        dest.writeString(this.ganre);
        dest.writeString(this.halaman);
    }

    public Anime() {
    }

    protected Anime(Parcel in) {
        this.id = in.readString();
        this.judul = in.readString();
        this.url = in.readString();
        this.srcVideo = in.readString();
        this.prev = in.readString();
        this.all = in.readString();
        this.next = in.readString();
        this.img = in.readString();
        this.date = in.readString();
        this.ganre = in.readString();
        this.halaman = in.readString();
    }

    public static final Creator<Anime> CREATOR = new Creator<Anime>() {
        @Override
        public Anime createFromParcel(Parcel source) {
            return new Anime(source);
        }

        @Override
        public Anime[] newArray(int size) {
            return new Anime[size];
        }
    };
}
