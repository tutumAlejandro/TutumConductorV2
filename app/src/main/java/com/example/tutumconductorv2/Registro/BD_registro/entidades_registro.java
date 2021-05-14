package com.example.tutumconductorv2.Registro.BD_registro;

public class entidades_registro {

    private String nombre,ApeidoPaterno,ApeidoMaterno,Email,Constraseña,Telefono;

    public entidades_registro(String nombre, String apeidoPaterno, String apeidoMaterno, String email, String constraseña, String telefono) {
        this.nombre = nombre;
        ApeidoPaterno = apeidoPaterno;
        ApeidoMaterno = apeidoMaterno;
        Email = email;
        Constraseña = constraseña;
        Telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApeidoPaterno() {
        return ApeidoPaterno;
    }

    public void setApeidoPaterno(String apeidoPaterno) {
        ApeidoPaterno = apeidoPaterno;
    }

    public String getApeidoMaterno() {
        return ApeidoMaterno;
    }

    public void setApeidoMaterno(String apeidoMaterno) {
        ApeidoMaterno = apeidoMaterno;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getConstraseña() {
        return Constraseña;
    }

    public void setConstraseña(String constraseña) {
        Constraseña = constraseña;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
}
