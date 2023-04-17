package org.fundaciobit.pluginsib.estructuraorganitzativa.ldapcaib;

/**
 * 
 * @author anadal
 *
 */
public class Departament {

    String codi;
    String nom;
    String dir3;
    String dir3pare;

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDir3() {
        return dir3;
    }

    public void setDir3(String dir3) {
        this.dir3 = dir3;
    }

    public String getDir3pare() {
        return dir3pare;
    }

    public void setDir3pare(String dir3pare) {
        this.dir3pare = dir3pare;
    }

    @Override
    public String toString() {
        return "Departament [codi=" + codi + ", nom=" + nom + ", dir3=" + dir3 + ", dir3pare=" + dir3pare + "]";
    }

}
