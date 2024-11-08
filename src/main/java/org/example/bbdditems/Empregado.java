package org.example.bbdditems;


public class Empregado {
    private String nome;
    private String apelido1;
    private String apelido2;
    private String nss;
    private String rua;
    private int numeroRua;
    private String piso;
    private String cp;
    private String localidade;
    private String dataNacemento;
    private float salario;
    private char sexo;
    private String nssSupervisa;
    private int numDepartamentoPertenece;

    // Constructor
    public Empregado(String nome, String apelido1, String apelido2, String nss, String rua, int numeroRua, String piso,
                     String cp, String localidade, String dataNacemento, float salario, char sexo,
                     String nssSupervisa, int numDepartamentoPertenece) {
        this.nome = nome;
        this.apelido1 = apelido1;
        this.apelido2 = apelido2;
        this.nss = nss;
        this.rua = rua;
        this.numeroRua = numeroRua;
        this.piso = piso;
        this.cp = cp;
        this.localidade = localidade;
        this.dataNacemento = dataNacemento;
        this.salario = salario;
        this.sexo = sexo;
        this.nssSupervisa = nssSupervisa;
        this.numDepartamentoPertenece = numDepartamentoPertenece;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido1() {
        return apelido1;
    }

    public void setApelido1(String apelido1) {
        this.apelido1 = apelido1;
    }

    public String getApelido2() {
        return apelido2;
    }

    public void setApelido2(String apelido2) {
        this.apelido2 = apelido2;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumeroRua() {
        return numeroRua;
    }

    public void setNumeroRua(int numeroRua) {
        this.numeroRua = numeroRua;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getDataNacemento() {
        return dataNacemento;
    }

    public void setDataNacemento(String dataNacemento) {
        this.dataNacemento = dataNacemento;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getNssSupervisa() {
        return nssSupervisa;
    }

    public void setNssSupervisa(String nssSupervisa) {
        this.nssSupervisa = nssSupervisa;
    }

    public int getNumDepartamentoPertenece() {
        return numDepartamentoPertenece;
    }

    public void setNumDepartamentoPertenece(int numDepartamentoPertenece) {
        this.numDepartamentoPertenece = numDepartamentoPertenece;
    }
}

