package com.example.covid;

public class reqData {
    private String reqID, name, email, city, address ,  phone, glooves, mask, waste, caps ,sani, details, uid, status , acc_by, acc_con ,date, url, acc_uid, accdate;

    public reqData(){

    }

       public reqData(String reqID, String name, String email, String phone, String address, String city
            , String glooves, String mask, String waste, String caps, String sani, String details,String status, String uid,
                      String acc_by, String acc_con,String date, String url,  String acc_uid , String accdate) {
        this.reqID=reqID;
        this.name = name;
        this.email = email;
        this.phone=phone;
        this.city = city;
        this.address = address;
        this.glooves = glooves;
        this.mask= mask;
        this.waste = waste;
        this.caps = caps;
        this.sani = sani;
        this.details = details;
        this.uid = uid;
        this.status = status;
        this.acc_by = acc_by;
           this.acc_con= acc_con;
           this.acc_uid = acc_uid;
           this.date = date;
           this.url = url;
           this.accdate=accdate;
    }

    public String getName() {
        return name;
    }
    public String getReqID() {
        return reqID;
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
    public String getStatus() {
        return status;
    }
    public String getAcc_by() {
        return acc_by;
    }
    public String getAcc_con() {
        return acc_con;
    }
    public String getAcc_uid() {
        return acc_uid;
    }
    public String getDate(){return date;}
    public String getUrl(){return url;}
    public String getAccdate(){return accdate;}

}

