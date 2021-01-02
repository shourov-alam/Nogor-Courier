package com.sh.nogorcourier.Activity;

public class User_Profile {

    String name,phone,area,address;


    public User_Profile() {

    }

    public User_Profile(String name, String phone, String area, String address) {
        this.name = name;
        this.phone = phone;
        this.area = area;
        this.address = address;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
