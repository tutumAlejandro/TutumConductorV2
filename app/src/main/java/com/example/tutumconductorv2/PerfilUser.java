package com.example.tutumconductorv2;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;

public class PerfilUser {


    private int id = 0;

    private String passengerId;
    private String userId;
    private String passengerImg;
    private boolean emailVerified;
    private String travels;
    private String calification;
    private String fb_id;
    private String google_id;
    private String api_token;
    private String Job;
    private String home;
    private String name;
    private String home_dir;
    private String job_dir;
    private String telefono;
    private String password;
    private String email;


    public PerfilUser() {
    }

    public PerfilUser(String home, String job, String name, String telefono, String password, String email,
                      String home_dir, String job_dir, String passengerId, String userId, String passengerImg,
                      boolean emailVerified, String travels, String calification, String fb_id, String google_id,
                      String api_token) {
        this.id = 0;
        this.name = name;
        this.Job = job;
        this.home = home;
        this.telefono = telefono;
        this.password = password;
        this.email = email;
        this.home_dir = home_dir;
        this.job_dir = job_dir;
        this.passengerId = passengerId;
        this.userId = userId;
        this.passengerImg = passengerImg;
        this.emailVerified = emailVerified;
        this.travels = travels;
        this.calification = calification;
        this.fb_id = fb_id;
        this.google_id = google_id;
        this.api_token = api_token;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassengerImg() {
        return passengerImg;
    }

    public void setPassengerImg(String passengerImg) {
        this.passengerImg = passengerImg;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getTravels() {
        return travels;
    }

    public void setTravels(String travels) {
        this.travels = travels;
    }

    public String getCalification() {
        return calification;
    }

    public void setCalification(String calification) {
        this.calification = calification;
    }

    public String getFb_id() {
        return fb_id;
    }

    public void setFb_id(String fb_id) {
        this.fb_id = fb_id;
    }

    public String getGoogle_id() {
        return google_id;
    }

    public void setGoogle_id(String google_id) {
        this.google_id = google_id;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    // ID (pare el perfil siempre es 0)
    public int getId() {
        return id;
    }

    //Titulo de Trabajo
    public String getJob() {
        return Job;
    }

    public void setJob(String job) {
        this.Job = job;
    }

    //Titilo de Casa
    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    //Nombre
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Direccion de la casa
    public String getHome_dir() {
        return home_dir;
    }

    public void setHome_dir(String home_dir) {
        this.home_dir = home_dir;
    }

    //Direccion del trabajo
    public String getJob_dir() {
        return job_dir;
    }

    public void setJob_dir(String job_dir) {
        this.job_dir = job_dir;
    }

    //Telefono
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    //Contraseña
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}