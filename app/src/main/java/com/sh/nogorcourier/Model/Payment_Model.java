package com.sh.nogorcourier.Model;

public class Payment_Model {

    String b_ac_name,bank_name,bank_branch,bank_acc_number,routing_num,type;



    public Payment_Model() {

    }


    public Payment_Model(String b_ac_name, String bank_name, String bank_branch, String bank_acc_number, String routing_num, String type) {
        this.b_ac_name = b_ac_name;
        this.bank_name = bank_name;
        this.bank_branch = bank_branch;
        this.bank_acc_number = bank_acc_number;
        this.routing_num = routing_num;
        this.type = type;
    }


    public String getB_ac_name() {
        return b_ac_name;
    }

    public void setB_ac_name(String b_ac_name) {
        this.b_ac_name = b_ac_name;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_branch() {
        return bank_branch;
    }

    public void setBank_branch(String bank_branch) {
        this.bank_branch = bank_branch;
    }

    public String getBank_acc_number() {
        return bank_acc_number;
    }

    public void setBank_acc_number(String bank_acc_number) {
        this.bank_acc_number = bank_acc_number;
    }

    public String getRouting_num() {
        return routing_num;
    }

    public void setRouting_num(String routing_num) {
        this.routing_num = routing_num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
