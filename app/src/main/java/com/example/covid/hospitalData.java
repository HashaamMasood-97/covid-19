package com.example.covid;

public class hospitalData {
    public String name, email, city, address , type, phone, doctors, vent, bed, icu, glooves, mask, waste, caps ,sani,time, date, url, uid ;
    public String longitude, latitude;
    public hospitalData(){

    }

    public hospitalData(String name, String email, String phone, String address, String city,  String type
    , String doctors, String vent, String bed, String icu, String glooves, String mask, String waste, String caps, String sani,String time, String date,String url, String uid, String longitude, String latitude) {
        this.name = name;
        this.email = email;
        this.city = city;
        this.address = address;
        this.type = type;
        this.phone = phone;
        this.doctors = doctors;
        this.vent = vent;
        this.bed = bed;
        this.icu = icu;
        this.glooves = glooves;
        this.mask= mask;
        this.waste = waste;
        this.caps = caps;
        this.sani = sani;
        this.time = time;
        this.date = date;
        this.uid = uid;
        this.url=url;
        this.longitude=longitude;
        this.latitude=latitude;

    }


    public String getName() {
        return name;
    }
    public String getUid() {
        return uid;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }
    public String getCity() {
        return city;
    }
    public String getType() {
        return type;
    }
    public String getDoctors() {
        return doctors;
    }
    public String getVent() {
        return vent;
    }
    public String getBed() {
        return bed;
    }
    public String getIcu() {
        return icu;
    }
    public String getGlooves() {
        return glooves;
    }
    public String getMask() {
        return mask;
    }
    public String getSani() {
        return sani;
    }
    public String getWaste() {
        return waste;
    }
    public String getCaps() {
        return caps;
    }
    public String getTime() {
        return time;
    }
    public String getDate() {
        return date;
    }
    public String getUrl() {
        return url;
    }
    public String getLongitude(){return longitude;}
    public String getLatitude(){return latitude;}


}

