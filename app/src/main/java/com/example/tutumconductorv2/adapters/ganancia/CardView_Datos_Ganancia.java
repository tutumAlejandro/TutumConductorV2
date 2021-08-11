package com.example.tutumconductorv2.adapters.ganancia;


public class CardView_Datos_Ganancia {
    private String header, totalViajes;
    private int id_ganancia,precio;

    public CardView_Datos_Ganancia(String titulo, String imagen, Double valoracion) {
        this.totalViajes = titulo;
        this.precio = precio;
    }

    public String getTitulo() {
        return totalViajes;
    }



    public int getPrecio() {
        return precio;
    }

    public String getHeader(){
        return header;
    }


}








   /* public CardView_Datos_Ganancia(String header,String direcInicio,String direcFinal,String fecha, int id_ganancia) {
        this.header = header;
        this.id_ganancia = id_ganancia;
    }

    public CardView_Datos_Ganancia(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getId_ganancia() {
        return id_ganancia;
    }

    public void setId_ganancia(int id_historial) {
        this.id_ganancia = id_historial;
    }

    public String getDirecInicio(){
       return direcInicio;
    }

    public void setDirecInicio (String direcInicio){
        this.direcInicio = direcInicio;
    }

    public String getDirecFinal(){
        return direcFinal;
    }

    public void setDirecFinal (String direcFinal){
        this.direcFinal = direcInicio;
    }

    public String getFecha(){
        return fecha;
    }

    public void setFecha (String fecha){
        this.fecha = fecha;
    }

    public int getPrecio(){
        return precio;
    }

    public void setPrecio (int precio){
        this.precio = precio;
    }

}*/

