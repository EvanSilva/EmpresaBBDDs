package org.example.bbdditems;

public class Empregado_Proxecto {

    private String NSS_Empregado = "";
    private Integer Num_proxecto = 0;
    private Integer Horas_semanais = 0;

    public Empregado_Proxecto(String NSS_Empregado, Integer num_proxecto, Integer horas_semanais) {
        this.NSS_Empregado = NSS_Empregado;
        Num_proxecto = num_proxecto;
        Horas_semanais = horas_semanais;
    }

    public String getNSS_Empregado() {
        return NSS_Empregado;
    }

    public void setNSS_Empregado(String NSS_Empregado) {
        this.NSS_Empregado = NSS_Empregado;
    }

    public Integer getNum_proxecto() {
        return Num_proxecto;
    }

    public void setNum_proxecto(Integer num_proxecto) {
        Num_proxecto = num_proxecto;
    }

    public Integer getHoras_semanais() {
        return Horas_semanais;
    }

    public void setHoras_semanais(Integer horas_semanais) {
        Horas_semanais = horas_semanais;
    }
}
