/*
 * Dades Obertes de EnviaFIB
 * Conjunt de Serveis REST de EnviaFIB que ofereixen Dades Obertes
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package es.caib.enviafib.apiinterna.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.joda.time.DateTime;
/**
 * PeticioDeFirma
 */


public class PeticioDeFirma {
  @JsonProperty("nif")
  private String nif = null;

  @JsonProperty("titol")
  private String titol = null;

  @JsonProperty("creada")
  private DateTime creada = null;

  @JsonProperty("finalitzada")
  private DateTime finalitzada = null;

  @JsonProperty("idiomaCode")
  private String idiomaCode = null;

  @JsonProperty("idiomaDescription")
  private String idiomaDescription = null;

  @JsonProperty("tipusDocumentalCode")
  private String tipusDocumentalCode = null;

  @JsonProperty("tipusDocumentalDescription")
  private String tipusDocumentalDescription = null;

  @JsonProperty("tipusPeticioCode")
  private Integer tipusPeticioCode = null;

  @JsonProperty("tipusPeticioCodeDescription")
  private String tipusPeticioCodeDescription = null;

  @JsonProperty("dir3")
  private String dir3 = null;

  public PeticioDeFirma nif(String nif) {
    this.nif = nif;
    return this;
  }

   /**
   * Get nif
   * @return nif
  **/
  @Schema(description = "")
  public String getNif() {
    return nif;
  }

  public void setNif(String nif) {
    this.nif = nif;
  }

  public PeticioDeFirma titol(String titol) {
    this.titol = titol;
    return this;
  }

   /**
   * Get titol
   * @return titol
  **/
  @Schema(description = "")
  public String getTitol() {
    return titol;
  }

  public void setTitol(String titol) {
    this.titol = titol;
  }

  public PeticioDeFirma creada(DateTime creada) {
    this.creada = creada;
    return this;
  }

   /**
   * Get creada
   * @return creada
  **/
  @Schema(description = "")
  public DateTime getCreada() {
    return creada;
  }

  public void setCreada(DateTime creada) {
    this.creada = creada;
  }

  public PeticioDeFirma finalitzada(DateTime finalitzada) {
    this.finalitzada = finalitzada;
    return this;
  }

   /**
   * Get finalitzada
   * @return finalitzada
  **/
  @Schema(description = "")
  public DateTime getFinalitzada() {
    return finalitzada;
  }

  public void setFinalitzada(DateTime finalitzada) {
    this.finalitzada = finalitzada;
  }

  public PeticioDeFirma idiomaCode(String idiomaCode) {
    this.idiomaCode = idiomaCode;
    return this;
  }

   /**
   * Get idiomaCode
   * @return idiomaCode
  **/
  @Schema(description = "")
  public String getIdiomaCode() {
    return idiomaCode;
  }

  public void setIdiomaCode(String idiomaCode) {
    this.idiomaCode = idiomaCode;
  }

  public PeticioDeFirma idiomaDescription(String idiomaDescription) {
    this.idiomaDescription = idiomaDescription;
    return this;
  }

   /**
   * Get idiomaDescription
   * @return idiomaDescription
  **/
  @Schema(description = "")
  public String getIdiomaDescription() {
    return idiomaDescription;
  }

  public void setIdiomaDescription(String idiomaDescription) {
    this.idiomaDescription = idiomaDescription;
  }

  public PeticioDeFirma tipusDocumentalCode(String tipusDocumentalCode) {
    this.tipusDocumentalCode = tipusDocumentalCode;
    return this;
  }

   /**
   * Get tipusDocumentalCode
   * @return tipusDocumentalCode
  **/
  @Schema(description = "")
  public String getTipusDocumentalCode() {
    return tipusDocumentalCode;
  }

  public void setTipusDocumentalCode(String tipusDocumentalCode) {
    this.tipusDocumentalCode = tipusDocumentalCode;
  }

  public PeticioDeFirma tipusDocumentalDescription(String tipusDocumentalDescription) {
    this.tipusDocumentalDescription = tipusDocumentalDescription;
    return this;
  }

   /**
   * Get tipusDocumentalDescription
   * @return tipusDocumentalDescription
  **/
  @Schema(description = "")
  public String getTipusDocumentalDescription() {
    return tipusDocumentalDescription;
  }

  public void setTipusDocumentalDescription(String tipusDocumentalDescription) {
    this.tipusDocumentalDescription = tipusDocumentalDescription;
  }

  public PeticioDeFirma tipusPeticioCode(Integer tipusPeticioCode) {
    this.tipusPeticioCode = tipusPeticioCode;
    return this;
  }

   /**
   * Get tipusPeticioCode
   * @return tipusPeticioCode
  **/
  @Schema(description = "")
  public Integer getTipusPeticioCode() {
    return tipusPeticioCode;
  }

  public void setTipusPeticioCode(Integer tipusPeticioCode) {
    this.tipusPeticioCode = tipusPeticioCode;
  }

  public PeticioDeFirma tipusPeticioCodeDescription(String tipusPeticioCodeDescription) {
    this.tipusPeticioCodeDescription = tipusPeticioCodeDescription;
    return this;
  }

   /**
   * Get tipusPeticioCodeDescription
   * @return tipusPeticioCodeDescription
  **/
  @Schema(description = "")
  public String getTipusPeticioCodeDescription() {
    return tipusPeticioCodeDescription;
  }

  public void setTipusPeticioCodeDescription(String tipusPeticioCodeDescription) {
    this.tipusPeticioCodeDescription = tipusPeticioCodeDescription;
  }

  public PeticioDeFirma dir3(String dir3) {
    this.dir3 = dir3;
    return this;
  }

   /**
   * Get dir3
   * @return dir3
  **/
  @Schema(description = "")
  public String getDir3() {
    return dir3;
  }

  public void setDir3(String dir3) {
    this.dir3 = dir3;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PeticioDeFirma peticioDeFirma = (PeticioDeFirma) o;
    return Objects.equals(this.nif, peticioDeFirma.nif) &&
        Objects.equals(this.titol, peticioDeFirma.titol) &&
        Objects.equals(this.creada, peticioDeFirma.creada) &&
        Objects.equals(this.finalitzada, peticioDeFirma.finalitzada) &&
        Objects.equals(this.idiomaCode, peticioDeFirma.idiomaCode) &&
        Objects.equals(this.idiomaDescription, peticioDeFirma.idiomaDescription) &&
        Objects.equals(this.tipusDocumentalCode, peticioDeFirma.tipusDocumentalCode) &&
        Objects.equals(this.tipusDocumentalDescription, peticioDeFirma.tipusDocumentalDescription) &&
        Objects.equals(this.tipusPeticioCode, peticioDeFirma.tipusPeticioCode) &&
        Objects.equals(this.tipusPeticioCodeDescription, peticioDeFirma.tipusPeticioCodeDescription) &&
        Objects.equals(this.dir3, peticioDeFirma.dir3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nif, titol, creada, finalitzada, idiomaCode, idiomaDescription, tipusDocumentalCode, tipusDocumentalDescription, tipusPeticioCode, tipusPeticioCodeDescription, dir3);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PeticioDeFirma {\n");
    
    sb.append("    nif: ").append(toIndentedString(nif)).append("\n");
    sb.append("    titol: ").append(toIndentedString(titol)).append("\n");
    sb.append("    creada: ").append(toIndentedString(creada)).append("\n");
    sb.append("    finalitzada: ").append(toIndentedString(finalitzada)).append("\n");
    sb.append("    idiomaCode: ").append(toIndentedString(idiomaCode)).append("\n");
    sb.append("    idiomaDescription: ").append(toIndentedString(idiomaDescription)).append("\n");
    sb.append("    tipusDocumentalCode: ").append(toIndentedString(tipusDocumentalCode)).append("\n");
    sb.append("    tipusDocumentalDescription: ").append(toIndentedString(tipusDocumentalDescription)).append("\n");
    sb.append("    tipusPeticioCode: ").append(toIndentedString(tipusPeticioCode)).append("\n");
    sb.append("    tipusPeticioCodeDescription: ").append(toIndentedString(tipusPeticioCodeDescription)).append("\n");
    sb.append("    dir3: ").append(toIndentedString(dir3)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
