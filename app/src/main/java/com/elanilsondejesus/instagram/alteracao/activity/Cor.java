package com.elanilsondejesus.instagram.alteracao.activity;

public class Cor {
    private int cod;
    private String nome;
    private String energetico;
    private String proteina;
    private String fibra;
    private String sodio;
    private String gordura;
    private String caboidrato;
    private String energeticoValorDiario;
    private String proteinaValorDiario;
    private String fibraValorDiario;
    private String sodioValorDiario;
    private String gorduraValorDiario;
    private String caboidratoValorDiario;

    public Cor() {
    }

    public Cor(int cod, String nome) {
        this.cod = cod;
        this.nome = nome;
    }

    public Cor(int cod, String nome, String energetico) {
        this.cod = cod;
        this.nome = nome;
        this.energetico = energetico;
    }


    public Cor(int cod, String nome, String energetico, String proteina, String fibra, String sodio, String gordura, String caboidrato) {
        this.cod = cod;
        this.nome = nome;
        this.energetico = energetico;
        this.proteina = proteina;
        this.fibra = fibra;
        this.sodio = sodio;
        this.gordura = gordura;
        this.caboidrato = caboidrato;
    }

    public Cor(int cod, String nome, String energetico, String proteina, String fibra, String sodio, String gordura, String caboidrato, String energeticoValorDiario, String proteinaValorDiario, String fibraValorDiario, String sodioValorDiario, String gorduraValorDiario, String caboidratoValorDiario) {
        this.cod = cod;
        this.nome = nome;
        this.energetico = energetico;
        this.proteina = proteina;
        this.fibra = fibra;
        this.sodio = sodio;
        this.gordura = gordura;
        this.caboidrato = caboidrato;
        this.energeticoValorDiario = energeticoValorDiario;
        this.proteinaValorDiario = proteinaValorDiario;
        this.fibraValorDiario = fibraValorDiario;
        this.sodioValorDiario = sodioValorDiario;
        this.gorduraValorDiario = gorduraValorDiario;
        this.caboidratoValorDiario = caboidratoValorDiario;
    }

    public String getEnergeticoValorDiario() {
        return energeticoValorDiario;
    }

    public void setEnergeticoValorDiario(String energeticoValorDiario) {
        this.energeticoValorDiario = energeticoValorDiario;
    }

    public String getProteinaValorDiario() {
        return proteinaValorDiario;
    }

    public void setProteinaValorDiario(String proteinaValorDiario) {
        this.proteinaValorDiario = proteinaValorDiario;
    }

    public String getFibraValorDiario() {
        return fibraValorDiario;
    }

    public void setFibraValorDiario(String fibraValorDiario) {
        this.fibraValorDiario = fibraValorDiario;
    }

    public String getSodioValorDiario() {
        return sodioValorDiario;
    }

    public void setSodioValorDiario(String sodioValorDiario) {
        this.sodioValorDiario = sodioValorDiario;
    }

    public String getGorduraValorDiario() {
        return gorduraValorDiario;
    }

    public void setGorduraValorDiario(String gorduraValorDiario) {
        this.gorduraValorDiario = gorduraValorDiario;
    }

    public String getCaboidratoValorDiario() {
        return caboidratoValorDiario;
    }

    public void setCaboidratoValorDiario(String caboidratoValorDiario) {
        this.caboidratoValorDiario = caboidratoValorDiario;
    }

    public String getCaboidrato() {
        return caboidrato;
    }

    public void setCaboidrato(String caboidrato) {
        this.caboidrato = caboidrato;
    }

    public String getEnergetico() {
        return energetico;
    }

    public void setEnergetico(String energetico) {
        this.energetico = energetico;
    }

    public String getProteina() {
        return proteina;
    }

    public void setProteina(String proteina) {
        this.proteina = proteina;
    }

    public String getFibra() {
        return fibra;
    }

    public void setFibra(String fibra) {
        this.fibra = fibra;
    }

    public String getSodio() {
        return sodio;
    }

    public void setSodio(String sodio) {
        this.sodio = sodio;
    }

    public String getGordura() {
        return gordura;
    }

    public void setGordura(String gordura) {
        this.gordura = gordura;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
