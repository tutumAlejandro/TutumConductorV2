package com.example.tutumconductorv2.Registro.BD_registro.utilidades;

public class utilidades {

    public static String TABLA_REGISTRO = "Registro_Usuario";
    public static String CAMPO_NOMBRE = "Nombre";
    public static String CAMPO_APEIDO_PATERNO = "ApeidoPaterno";
    public static String CAMPO_APEIDO_MATERNO = "ApeidoMaterno";
    public static String CAMPO_EMAIL = "Email";
    public static String CAMPO_CONTRASEÑA = "Contraseña";
    public static String CAMPO_TELEFONO = "Telefono";
    public static String CAMPO_VIGENCIA_LICENCIA = "VigenciaLicencia";
    public static String CAMPO_VIGENCIA_TARJETA_CIRCULACION = "VigenciaTarjeta";
    public static String CAMPO_VIGENCIA_POLIZA = "VigenciaPoliza";
    public static String CAMPO_VIGENCIA_TARJETON =  "VigenciaTarjeton";
    public static String CAMPO_CODIGO = "CodigoVehiculo";
    public static int CAMPO_NULL = 123;

    public static final String CREAR_TABLA_REGISTRO_USUARIO = "CREATE TABLE "+TABLA_REGISTRO+ " ("+ CAMPO_NOMBRE + "TEXT, "+CAMPO_APEIDO_PATERNO +"TEXT, "+CAMPO_APEIDO_MATERNO+" TEXT, "+CAMPO_EMAIL+" TEXT, "+CAMPO_CONTRASEÑA+" TEXT, "+CAMPO_TELEFONO+" TEXT, "
            +CAMPO_VIGENCIA_TARJETA_CIRCULACION+"TEXT, "+CAMPO_VIGENCIA_LICENCIA+"TEXT, "+CAMPO_VIGENCIA_POLIZA+"TEXT, "+CAMPO_VIGENCIA_TARJETON+"TEXT, "+CAMPO_CODIGO+" TEXT)";
}
//Registro_Usuario es el nombre de la tabla
