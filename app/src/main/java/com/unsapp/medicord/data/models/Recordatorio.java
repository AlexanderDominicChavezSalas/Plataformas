package com.unsapp.medicord.data.models;

import com.unsapp.medicord.data.sqlite.Estado;
import com.unsapp.medicord.data.sqlite.Primary;

public class Recordatorio {
    @Primary
    private long RecCod;
    private long MedCod;
    private int RecFre;
    private String RecFecIni;
    private String RecFecFin;
    @Estado
    private String RecEstReg;

    public Recordatorio(long medCod, int recFre, String recFecIni, String recFecFin, String recEstReg) {
        MedCod = medCod;
        RecFre = recFre;
        RecFecIni = recFecIni;
        RecFecFin = recFecFin;
        RecEstReg = recEstReg;
    }

    public Recordatorio(long recCod, long medCod, int recFre, String recFecIni, String recFecFin, String recEstReg) {
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

    public String getRecFecIni() {
        return RecFecIni;
    }

    public void setRecFecIni(String recFecIni) {
        RecFecIni = recFecIni;
    }

    public String getRecFecFin() {
        return RecFecFin;
    }

    public void setRecFecFin(String recFecFin) {
        RecFecFin = recFecFin;
    }

    public String getRecEstReg() {
        return RecEstReg;
    }

    public void setRecEstReg(String recEstReg) {
        RecEstReg = recEstReg;
    }
}
