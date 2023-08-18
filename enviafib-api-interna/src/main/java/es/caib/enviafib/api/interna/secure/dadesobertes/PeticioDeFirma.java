package es.caib.enviafib.api.interna.secure.dadesobertes;

import java.util.Date;

/**
 * 
 * @author anadal
 *
 */
public class PeticioDeFirma {
    private String nif;
    private String titol;

//    @Schema(type = "string", format = "date-time", pattern = "yyyy-MM-dd'T'HH:mm:ss")
//    @JsonSerialize(using = ISO8601DateTimeSerializer.class)
    private Date creada;

//    @Schema(type = "string", format = "date-time", pattern = "yyyy-MM-dd'T'HH:mm:ss")
//    @JsonSerialize(using = ISO8601DateTimeSerializer.class)
    private Date finalitzada;

    private String idiomaCode;
    private String idiomaDescription;

    private String tipusDocumentalCode;
    private String tipusDocumentalDescription;

    private int tipusPeticioCode;
    private String tipusPeticioDescription;

    private String dir3;

    public PeticioDeFirma() {
    }

    public PeticioDeFirma(String nif, String titol, Date creada, Date finalitzada, String idiomaCode,
            String idiomaDescription, String tipusDocumentalCode, String tipusDocumentalDescription,
            int tipusPeticioCode, String tipusPeticioDescription, String dir3) {
        super();
        this.nif = nif;
        this.titol = titol;
        this.creada = creada;
        this.finalitzada = finalitzada;
        this.idiomaCode = idiomaCode;
        this.idiomaDescription = idiomaDescription;
        this.tipusDocumentalCode = tipusDocumentalCode;
        this.tipusDocumentalDescription = tipusDocumentalDescription;
        this.tipusPeticioCode = tipusPeticioCode;
        this.tipusPeticioDescription = tipusPeticioDescription;
        this.dir3 = dir3;
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

    public Date getCreada() {
        return this.creada;
    }

    public void setCreada(final Date creada) {
        this.creada = creada;
    }

    public Date getFinalitzada() {
        return this.finalitzada;
    }

    public void setFinalitzada(final Date finalitzada) {
        this.finalitzada = finalitzada;
    }

    public String getIdiomaCode() {
        return idiomaCode;
    }

    public void setIdiomaCode(String idiomaCode) {
        this.idiomaCode = idiomaCode;
    }

    public String getIdiomaDescription() {
        return idiomaDescription;
    }

    public void setIdiomaDescription(String idiomaDescription) {
        this.idiomaDescription = idiomaDescription;
    }

    public String getTipusDocumentalCode() {
        return tipusDocumentalCode;
    }

    public void setTipusDocumentalCode(String tipusDocumentalCode) {
        this.tipusDocumentalCode = tipusDocumentalCode;
    }

    public String getTipusDocumentalDescription() {
        return tipusDocumentalDescription;
    }

    public void setTipusDocumentalDescription(String tipusDocumentalDescription) {
        this.tipusDocumentalDescription = tipusDocumentalDescription;
    }

    public int getTipusPeticioCode() {
        return tipusPeticioCode;
    }

    public void setTipusPeticioCode(int tipusPeticioCode) {
        this.tipusPeticioCode = tipusPeticioCode;
    }

    public String getTipusPeticioDescription() {
        return tipusPeticioDescription;
    }

    public void setTipusPeticioDescription(String tipusPeticioDescription) {
        this.tipusPeticioDescription = tipusPeticioDescription;
    }

    public String getDir3() {
        return this.dir3;
    }

    public void setDir3(final String dir3) {
        this.dir3 = dir3;
    }

    public String toString() {
        return this.getClass().getName() + " {nif=" + this.nif + ", titol=" + this.titol + ", creada=" + this.creada + ", finalitzada="
                + this.finalitzada + ", idioma=" + this.idiomaCode + ", tipusDocumental=" + this.tipusDocumentalCode
                + ", tipus=" + this.tipusPeticioCode + ", dir3=" + this.dir3 + "}";
    }
}
