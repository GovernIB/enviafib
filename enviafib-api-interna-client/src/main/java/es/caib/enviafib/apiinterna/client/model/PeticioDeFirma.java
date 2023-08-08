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
/**
 * PeticioDeFirma
 */


public class PeticioDeFirma {
  @JsonProperty("nif")
  private String nif = null;

  @JsonProperty("titol")
  private String titol = null;

  @JsonProperty("creada")
  private String creada = null;

  @JsonProperty("finalitzada")
  private String finalitzada = null;

  @JsonProperty("idioma")
  private String idioma = null;

  @JsonProperty("tipusDocumental")
  private String tipusDocumental = null;

  @JsonProperty("tipus")
  private String tipus = null;

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

  public PeticioDeFirma creada(String creada) {
    this.creada = creada;
    return this;
  }

   /**
   * Get creada
   * @return creada
  **/
  @Schema(description = "")
  public String getCreada() {
    return creada;
  }

  public void setCreada(String creada) {
    this.creada = creada;
  }

  public PeticioDeFirma finalitzada(String finalitzada) {
    this.finalitzada = finalitzada;
    return this;
  }

   /**
   * Get finalitzada
   * @return finalitzada
  **/
  @Schema(description = "")
  public String getFinalitzada() {
    return finalitzada;
  }

  public void setFinalitzada(String finalitzada) {
    this.finalitzada = finalitzada;
  }

  public PeticioDeFirma idioma(String idioma) {
    this.idioma = idioma;
    return this;
  }

   /**
   * Get idioma
   * @return idioma
  **/
  @Schema(description = "")
  public String getIdioma() {
    return idioma;
  }

  public void setIdioma(String idioma) {
    this.idioma = idioma;
  }

  public PeticioDeFirma tipusDocumental(String tipusDocumental) {
    this.tipusDocumental = tipusDocumental;
    return this;
  }

   /**
   * Get tipusDocumental
   * @return tipusDocumental
  **/
  @Schema(description = "")
  public String getTipusDocumental() {
    return tipusDocumental;
  }

  public void setTipusDocumental(String tipusDocumental) {
    this.tipusDocumental = tipusDocumental;
  }

  public PeticioDeFirma tipus(String tipus) {
    this.tipus = tipus;
    return this;
  }

   /**
   * Get tipus
   * @return tipus
  **/
  @Schema(description = "")
  public String getTipus() {
    return tipus;
  }

  public void setTipus(String tipus) {
    this.tipus = tipus;
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
        Objects.equals(this.idioma, peticioDeFirma.idioma) &&
        Objects.equals(this.tipusDocumental, peticioDeFirma.tipusDocumental) &&
        Objects.equals(this.tipus, peticioDeFirma.tipus) &&
        Objects.equals(this.dir3, peticioDeFirma.dir3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nif, titol, creada, finalitzada, idioma, tipusDocumental, tipus, dir3);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PeticioDeFirma {\n");
    
    sb.append("    nif: ").append(toIndentedString(nif)).append("\n");
    sb.append("    titol: ").append(toIndentedString(titol)).append("\n");
    sb.append("    creada: ").append(toIndentedString(creada)).append("\n");
    sb.append("    finalitzada: ").append(toIndentedString(finalitzada)).append("\n");
    sb.append("    idioma: ").append(toIndentedString(idioma)).append("\n");
    sb.append("    tipusDocumental: ").append(toIndentedString(tipusDocumental)).append("\n");
    sb.append("    tipus: ").append(toIndentedString(tipus)).append("\n");
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
