package com.unsapp.medicord.data.models;

import com.unsapp.medicord.data.sqlite.Estado;
import com.unsapp.medicord.data.sqlite.Primary;

public class UnidadMedicina {
    @Primary
    private long UniMedCod;
    private String UniMedNom;
    private String UniMedAli;
    @Estado
    private String UniMedEstReg;

    public UnidadMedicina(String uniMedNom, String uniMedAli, String uniMedEstReg) {
        UniMedNom = uniMedNom;
        UniMedAli = uniMedAli;
        UniMedEstReg = uniMedEstReg;
    }

    public UnidadMedicina(long uniMedCod, String uniMedNom, String uniMedAli, String uniMedEstReg) {
        UniMedCod = uniMedCod;
        UniMedNom = uniMedNom;
        UniMedAli = uniMedAli;
        UniMedEstReg = uniMedEstReg;
    }

    public long getUniMedCod() {
        return UniMedCod;
    }

    public void setUniMedCod(long uniMedCod) {
        UniMedCod = uniMedCod;
    }

    public String getUniMedNom() {
        return UniMedNom;
    }

    public void setUniMedNom(String uniMedNom) {
        UniMedNom = uniMedNom;
    }

    public String getUniMedAli() {
        return UniMedAli;
    }

    public void setUniMedAli(String uniMedAli) {
        UniMedAli = uniMedAli;
    }

    public String getUniMedEstReg() {
        return UniMedEstReg;
    }

    public void setUniMedEstReg(String uniMedEstReg) {
        UniMedEstReg = uniMedEstReg;
    }
}
