package org.example.bbdditems;

public class Proxecto {

    private Integer Num_proxecto = 0;
    private String Nome_proxecto = "";
    private String Lugar = "";
    private Integer Num_departamento = 0;

    public Proxecto(Integer num_proxecto, String nome_proxecto, String lugar, Integer num_departamento) {
        Num_proxecto = num_proxecto;
        Nome_proxecto = nome_proxecto;
        Lugar = lugar;
        Num_departamento = num_departamento;
    }

    public Integer getNum_proxecto() {
        return Num_proxecto;
    }

    public void setNum_proxecto(Integer num_proxecto) {
        Num_proxecto = num_proxecto;
    }

    public String getNome_proxecto() {
        return Nome_proxecto;
    }

    public void setNome_proxecto(String nome_proxecto) {
        Nome_proxecto = nome_proxecto;
    }

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String lugar) {
        Lugar = lugar;
    }

    public Integer getNum_departamento() {
        return Num_departamento;
    }

    public void setNum_departamento(Integer num_departamento) {
        Num_departamento = num_departamento;
    }
}
