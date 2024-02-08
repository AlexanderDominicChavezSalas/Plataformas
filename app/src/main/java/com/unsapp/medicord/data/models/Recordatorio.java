package com.unsapp.medicord.data.models;

import com.unsapp.medicord.data.sqlite.Estado;
import com.unsapp.medicord.data.sqlite.Primary;

public class Recordatorio {
    @Primary
    private long RecCod;
    private long MedCod;
    private int RecFre;
    private int RecFecIni;
    private int RecFecFin;
    @Estado
    private String RecEstReg;

    public Recordatorio(long medCod, int recFre, int recFecIni, int recFecFin, String recEstReg) {
        MedCod = medCod;
        RecFre = recFre;
        RecFecIni = recFecIni;
        RecFecFin = recFecFin;
        RecEstReg = recEstReg;
    }

    public Recordatorio(long recCod, long medCod, int recFre, int recFecIni, int recFecFin, String recEstReg) {
        RecCod = recCod;
        MedCod = medCod;
        RecFre = recFre;
        RecFecIni = recFecIni;
        RecFecFin = recFecFin;
        RecEstReg = recEstReg;
    }

    public long getRecCod() {
        return RecCod;
    }

    public void setRecCod(long recCod) {
        RecCod = recCod;
    }

    public long getMedCod() {
        return MedCod;
    }

    public void setMedCod(long medCod) {
        MedCod = medCod;
    }

    public int getRecFre() {
        return RecFre;
    }

    public void setRecFre(int recFre) {
        RecFre = recFre;
    }

    public int getRecFecIni() {
        return RecFecIni;
    }

    public void setRecFecIni(int recFecIni) {
        RecFecIni = recFecIni;
    }

    public int getRecFecFin() {
        return RecFecFin;
    }

    public void setRecFecFin(int recFecFin) {
        RecFecFin = recFecFin;
    }

    public String getRecEstReg() {
        return RecEstReg;
    }

    public void setRecEstReg(String recEstReg) {
        RecEstReg = recEstReg;
    }
}
