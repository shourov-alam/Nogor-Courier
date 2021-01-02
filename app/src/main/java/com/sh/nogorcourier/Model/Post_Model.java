package com.sh.nogorcourier.Model;

public class Post_Model {

  private String key,cod_paid, pick_received,deliver_received,con_now,bdt,r_name,r_phone,date2,admin_wire_check,pid,delivery_selection,driver_check1,post_type,user_uid, date,name,phone,area,address,d_area,d_address,instruction,type,weight,pickup_selection,admin_checked,driver_checked,condition_amount;



  public Post_Model(){


    }

    public Post_Model(String key,String cod_paid,String pick_received,String deliver_received,String con_now,String bdt,String r_name,String r_phone,String date2,String admin_wire_check,String pid,
                      String delivery_selection,String driver_check1,String post_type,String user_uid,String date,
                      String name, String phone, String area, String address, String d_area, String d_address,
                      String instruction, String type, String weight, String pickup_selection, String admin_checked,
                      String driver_checked, String condition_amount) {
        this.date = date;
        this.key=key;
        this.admin_wire_check=admin_wire_check;
        this.date2=date2;
        this.cod_paid=cod_paid;
        this.bdt=bdt;
        this.con_now=con_now;
        this.pick_received=pick_received;
        this.deliver_received=deliver_received;


        this.r_name=r_name;
        this.r_phone=r_phone;

        this.post_type=post_type;
        this.pid=pid;
        this.delivery_selection=delivery_selection;
        this.driver_check1=driver_check1;

        this.user_uid=user_uid;
        this.name = name;
        this.phone = phone;
        this.area = area;
        this.address = address;
        this.d_area = d_area;
        this.d_address = d_address;
        this.instruction = instruction;
        this.type = type;
        this.weight = weight;

        this.pickup_selection = pickup_selection;
        this.admin_checked = admin_checked;
        this.driver_checked = driver_checked;
        this.condition_amount = condition_amount;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCod_paid() {
        return cod_paid;
    }

    public void setCod_paid(String cod_paid) {
        this.cod_paid = cod_paid;
    }

    public String getPick_received() {
        return pick_received;
    }

    public void setPick_received(String pick_received) {
        this.pick_received = pick_received;
    }

    public String getDeliver_received() {
        return deliver_received;
    }

    public void setDeliver_received(String deliver_received) {
        this.deliver_received = deliver_received;
    }

    public String getCon_now() {
        return con_now;
    }

    public void setCon_now(String con_now) {
        this.con_now = con_now;
    }

    public String getBdt() {
        return bdt;
    }

    public void setBdt(String bdt) {
        this.bdt = bdt;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public String getR_phone() {
        return r_phone;
    }

    public void setR_phone(String r_phone) {
        this.r_phone = r_phone;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public String getAdmin_wire_check() {
        return admin_wire_check;
    }

    public void setAdmin_wire_check(String admin_wire_check) {
        this.admin_wire_check = admin_wire_check;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDelivery_selection() {
        return delivery_selection;
    }

    public void setDelivery_selection(String delivery_selection) {
        this.delivery_selection = delivery_selection;
    }

    public String getDriver_check1() {
        return driver_check1;
    }

    public void setDriver_check1(String driver_check1) {
        this.driver_check1 = driver_check1;
    }

    public String getPost_type() {
        return post_type;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public String getUser_uid() {
        return user_uid;
    }

    public void setUser_uid(String user_uid) {
        this.user_uid = user_uid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getD_area() {
        return d_area;
    }

    public void setD_area(String d_area) {
        this.d_area = d_area;
    }

    public String getD_address() {
        return d_address;
    }

    public void setD_address(String d_address) {
        this.d_address = d_address;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }



    public String getPickup_selection() {
        return pickup_selection;
    }

    public void setPickup_selection(String pickup_selection) {
        this.pickup_selection = pickup_selection;
    }

    public String getAdmin_checked() {
        return admin_checked;
    }

    public void setAdmin_checked(String admin_checked) {
        this.admin_checked = admin_checked;
    }

    public String getDriver_checked() {
        return driver_checked;
    }

    public void setDriver_checked(String driver_checked) {
        this.driver_checked = driver_checked;
    }

    public String getCondition_amount() {
        return condition_amount;
    }

    public void setCondition_amount(String condition_amount) {
        this.condition_amount = condition_amount;
    }
}
