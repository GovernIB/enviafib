package es.caib.enviafib.api.interna.secure.peticionsdefirma;

import java.util.Date;

/**
 * 
 * @author ptrias
 *
 */
public class PeticioDTO {

    private String nif;
    private String titol;
    private String creada;
    private String finalitzada;
    private String idioma;
    private String tipusDocumental;
    private String tipus;
    private String dir3;

    public PeticioDTO(String nif, String titol, String creada, String finalitzada, String idioma, String tipusDocumental,
            String tipus, String dir3) {
        super();
        this.nif = nif;
        this.titol = titol;
        this.creada = creada;
        this.finalitzada = finalitzada;
        this.idioma = idioma;
        this.tipusDocumental = tipusDocumental;
        this.tipus = tipus;
        this.dir3 = dir3;
    }

    public PeticioDTO() {
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getCreada() {
        return creada;
    }

    public void setCreada(String creada) {
        this.creada = creada;
    }

    public String getFinalitzada() {
        return finalitzada;
    }

    public void setFinalitzada(String finalitzada) {
        this.finalitzada = finalitzada;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getTipusDocumental() {
        return tipusDocumental;
    }

    public void setTipusDocumental(String tipusDocumental) {
        this.tipusDocumental = tipusDocumental;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public String getDir3() {
        return dir3;
    }

    public void setDir3(String dir3) {
        this.dir3 = dir3;
    }

    @Override
    public String toString() {
        return "PeticioDTO {"
                + "nif=" + nif 
                + ", titol=" + titol 
                + ", creada=" + creada 
                + ", finalitzada=" + finalitzada
                + ", idioma=" + idioma 
                + ", tipusDocumental=" + tipusDocumental 
                + ", tipus=" + tipus 
                + ", dir3=" + dir3
                + "}";
    }

    
    
}
