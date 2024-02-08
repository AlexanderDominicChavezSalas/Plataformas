package com.unsapp.medicord.data.models;

import com.unsapp.medicord.data.sqlite.Estado;
import com.unsapp.medicord.data.sqlite.Primary;

public class Medicina {
    @Primary
    private long MedCod;
    private String MedNom;
    private String MedDes;
    private long UniMedCod;
    private double MedDos;
    private int MedNiv;
    private int MedCon;
    @Estado
    private String MedEstReg;

    public Medicina(String medNom, String medDes, long uniMedCod, double medDos, int medNiv, int medCon, String medEstReg) {
        MedNom = medNom;
        MedDes = medDes;
        UniMedCod = uniMedCod;
        MedDos = medDos;
        MedNiv = medNiv;
        MedCon = medCon;
        MedEstReg = medEstReg;
    }

    public Medicina(long medCod, String medNom, String medDes, long uniMedCod, double medDos, int medNiv, int medCon, String medEstReg) {
        MedCod = medCod;
        MedNom = medNom;
        MedDes = medDes;
        UniMedCod = uniMedCod;
        MedDos = medDos;
        MedNiv = medNiv;
        MedCon = medCon;
        MedEstReg = medEstReg;
    }

    public int getMedCon() {
        return MedCon;
    }

    public void setMedCon(int medCon) {
        MedCon = medCon;
    }

    public long getMedCod() {
        return MedCod;
    }

    public void setMedCod(long medCod) {
        MedCod = medCod;
    }

    public String getMedNom() {
        return MedNom;
    }

    public void setMedNom(String medNom) {
        MedNom = medNom;
    }

    public String getMedDes() {
        return MedDes;
    }

    public void setMedDes(String medDes) {
        MedDes = medDes;
    }

    public long getUniMedCod() {
        return UniMedCod;
    }

    public void setUniMedCod(long uniMedCod) {
        UniMedCod = uniMedCod;
    }

    public double getMedDos() {
        return MedDos;
    }

    public void setMedDos(double medDos) {
        MedDos = medDos;
    }

    public int getMedNiv() {
        return MedNiv;
    }

    public void setMedNiv(int medNiv) {
        MedNiv = medNiv;
    }

    public String getMedEstReg() {
        return MedEstReg;
    }

    public void setMedEstReg(String medEstReg) {
        MedEstReg = medEstReg;
    }
}
