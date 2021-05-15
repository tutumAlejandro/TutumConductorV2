package com.example.tutumconductorv2.Registro.BD_registro.utilidades;

public class cadenas_registro {

    public static String TABLA_REGISTRO = "tabla_registro";
    public static String CAMPO_NOMBRE = "nombre";
    public static String CAMPO_APEIDO_PATERNO = "apeido_paterno";
    public static String CAMPO_APEIDO_MATERNO = "apeido_materno";
    public static String CAMPO_EMAIL = "email";
    public static String CAMPO_PASSWORD = "password";
    public static String CAMPO_TELEFONO = "telefono";

    public static String CREAR_TABLA_REGISTRO = "CREATE TABLE "+TABLA_REGISTRO+" ("+CAMPO_NOMBRE+" TEXT,"+CAMPO_APEIDO_PATERNO+" TEXT,"+CAMPO_APEIDO_MATERNO+" TEXT,"+CAMPO_EMAIL+" TEXT,"+CAMPO_PASSWORD+" TEXT,"+CAMPO_TELEFONO+" TEXT)";

    public static String nombres="";
    public static String apeido_paterno="";
    public static String apeido_materno="";
    public static String email="";
    public static String password="";
    public static String telefono="";

}
