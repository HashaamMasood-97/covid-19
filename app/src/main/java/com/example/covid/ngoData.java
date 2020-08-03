package com.example.covid;

public class ngoData {
    public String name, email, city, address , type, phone, glooves, mask, waste, caps ,sani, details,url,uid;

    public ngoData(){

    }

    public ngoData(String name, String email, String phone, String address, String city,  String type
            , String glooves, String mask, String waste, String caps, String sani, String details, String url, String uid) {
        this.name = name;
        this.email = email;
        this.city = city;
        this.address = address;
        this.type = type;
        this.phone = phone;
        this.details = details;
        this.glooves = glooves;
        this.mask= mask;
        this.waste = waste;
        this.caps = caps;
        this.sani = sani;
        this.uid = uid;
        this.url = url;
    }


    public String getName() {
        return name;
    }
    public String getuid() {
        return uid;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public String getType() {
        return type;
    }
    public String getAddress() {
        return address;
    }
    public String getCity() {
        return city;
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
    public String getDetails() {
        return details;
    }
    public String getUrl() {
        return url;
    }

}

