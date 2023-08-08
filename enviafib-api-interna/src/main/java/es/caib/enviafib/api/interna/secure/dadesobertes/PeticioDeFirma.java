package es.caib.enviafib.api.interna.secure.dadesobertes;

/**
 * 
 * @author anadal
 *
 */
public class PeticioDeFirma {
    private String nif;
    private String titol;
    private String creada;
    private String finalitzada;
    private String idioma;
    private String tipusDocumental;
    private String tipus;
    private String dir3;

    public PeticioDeFirma(final String nif, final String titol, final String creada, final String finalitzada,
            final String idioma, final String tipusDocumental, final String tipus, final String dir3) {
        this.nif = nif;
        this.titol = titol;
        this.creada = creada;
        this.finalitzada = finalitzada;
        this.idioma = idioma;
        this.tipusDocumental = tipusDocumental;
        this.tipus = tipus;
        this.dir3 = dir3;
    }

    public PeticioDeFirma() {
    }

    public String getNif() {
        return this.nif;
    }

    public void setNif(final String nif) {
        this.nif = nif;
    }

    public String getTitol() {
        return this.titol;
    }

    public void setTitol(final String titol) {
        this.titol = titol;
    }

    public String getCreada() {
        return this.creada;
    }

    public void setCreada(final String creada) {
        this.creada = creada;
    }

    public String getFinalitzada() {
        return this.finalitzada;
    }

    public void setFinalitzada(final String finalitzada) {
        this.finalitzada = finalitzada;
    }

    public String getIdioma() {
        return this.idioma;
    }

    public void setIdioma(final String idioma) {
        this.idioma = idioma;
    }

    public String getTipusDocumental() {
        return this.tipusDocumental;
    }

    public void setTipusDocumental(final String tipusDocumental) {
        this.tipusDocumental = tipusDocumental;
    }

    public String getTipus() {
        return this.tipus;
    }

    public void setTipus(final String tipus) {
        this.tipus = tipus;
    }

    public String getDir3() {
        return this.dir3;
    }

    public void setDir3(final String dir3) {
        this.dir3 = dir3;
    }

    public String toString() {
        return "PeticioDTO {nif=" + this.nif + ", titol=" + this.titol + ", creada=" + this.creada + ", finalitzada="
                + this.finalitzada + ", idioma=" + this.idioma + ", tipusDocumental=" + this.tipusDocumental
                + ", tipus=" + this.tipus + ", dir3=" + this.dir3 + "}";
    }
}
