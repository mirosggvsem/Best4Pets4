package com.hfad.best4pets;
public class ZakazInfo {
    private String Name;
    private String PhoneNumber;
    private String Address;
    private String Date;
    private String Time;
    private String Summa;
    public ZakazInfo() {
    }
    public ZakazInfo(String name, String phoneNumber, String address,String date, String time, String summa) {
        this.Name = name;
        this.PhoneNumber = phoneNumber;
        this.Address = address;
        this.Date = date;
        this.Time = time;
        this.Summa =summa;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        this.Name = name;
    }
    public String getPhoneNumber() {
        return PhoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;}
    public String getAddress() {
        return Address; }
    public void setAddress(String address) {
        this.Address = address; }
    public String getDate() {
        return Date; }
    public void setDate(String date) {
        this.Date = date; }
    public String getTime() {
        return Time; }
    public void setTime(String time) {
        this.Time = time; }
    public String getSumma() {
        return Summa; }
    public void setSumma(String summa) {
        Summa = summa; }
}


