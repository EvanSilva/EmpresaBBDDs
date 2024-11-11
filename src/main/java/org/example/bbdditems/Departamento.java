package org.example.bbdditems;

public class Departamento {

    private Integer Num_departamento = 0;
    private String Nome_departamento = "";
    private String NSS_dirige = "";
    private String Data_direccion = "";

    public Departamento(Integer num_departamento, String nome_departamento, String NSS_dirige, String data_direccion) {
        Num_departamento = num_departamento;
        Nome_departamento = nome_departamento;
        this.NSS_dirige = NSS_dirige;
        Data_direccion = data_direccion;
    }

    public Integer getNum_departamento() {
        return Num_departamento;
    }

    public void setNum_departamento(Integer num_departamento) {
        Num_departamento = num_departamento;
    }

    public String getNome_departamento() {
        return Nome_departamento;
    }

    public void setNome_departamento(String nome_departamento) {
        Nome_departamento = nome_departamento;
    }

    public String getNSS_dirige() {
        return NSS_dirige;
    }

    public void setNSS_dirige(String NSS_dirige) {
        this.NSS_dirige = NSS_dirige;
    }

    public String getData_direccion() {
        return Data_direccion;
    }

    public void setData_direccion(String data_direccion) {
        Data_direccion = data_direccion;
    }
}
