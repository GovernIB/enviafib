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
import es.caib.enviafib.apiinterna.client.model.PeticioDeFirma;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
/**
 * PeticioDeFirmaPaginacio
 */


public class PeticioDeFirmaPaginacio {
  @JsonProperty("pagesize")
  private Integer pagesize = null;

  @JsonProperty("page")
  private Integer page = null;

  @JsonProperty("totalpages")
  private Integer totalpages = null;

  @JsonProperty("totalcount")
  private Integer totalcount = null;

  @JsonProperty("data")
  private List<PeticioDeFirma> data = new ArrayList<PeticioDeFirma>();

  public PeticioDeFirmaPaginacio pagesize(Integer pagesize) {
    this.pagesize = pagesize;
    return this;
  }

   /**
   * Mida de pàgina
   * @return pagesize
  **/
  @Schema(required = true, description = "Mida de pàgina")
  public Integer getPagesize() {
    return pagesize;
  }

  public void setPagesize(Integer pagesize) {
    this.pagesize = pagesize;
  }

  public PeticioDeFirmaPaginacio page(Integer page) {
    this.page = page;
    return this;
  }

   /**
   * Número pàgina. Comença per 1.
   * @return page
  **/
  @Schema(required = true, description = "Número pàgina. Comença per 1.")
  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public PeticioDeFirmaPaginacio totalpages(Integer totalpages) {
    this.totalpages = totalpages;
    return this;
  }

   /**
   * Número total de pàgines
   * @return totalpages
  **/
  @Schema(required = true, description = "Número total de pàgines")
  public Integer getTotalpages() {
    return totalpages;
  }

  public void setTotalpages(Integer totalpages) {
    this.totalpages = totalpages;
  }

  public PeticioDeFirmaPaginacio totalcount(Integer totalcount) {
    this.totalcount = totalcount;
    return this;
  }

   /**
   * Numero total d&#x27;elements
   * @return totalcount
  **/
  @Schema(required = true, description = "Numero total d'elements")
  public Integer getTotalcount() {
    return totalcount;
  }

  public void setTotalcount(Integer totalcount) {
    this.totalcount = totalcount;
  }

  public PeticioDeFirmaPaginacio data(List<PeticioDeFirma> data) {
    this.data = data;
    return this;
  }

  public PeticioDeFirmaPaginacio addDataItem(PeticioDeFirma dataItem) {
    this.data.add(dataItem);
    return this;
  }

   /**
   * Elements retornats
   * @return data
  **/
  @Schema(required = true, description = "Elements retornats")
  public List<PeticioDeFirma> getData() {
    return data;
  }

  public void setData(List<PeticioDeFirma> data) {
    this.data = data;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PeticioDeFirmaPaginacio peticioDeFirmaPaginacio = (PeticioDeFirmaPaginacio) o;
    return Objects.equals(this.pagesize, peticioDeFirmaPaginacio.pagesize) &&
        Objects.equals(this.page, peticioDeFirmaPaginacio.page) &&
        Objects.equals(this.totalpages, peticioDeFirmaPaginacio.totalpages) &&
        Objects.equals(this.totalcount, peticioDeFirmaPaginacio.totalcount) &&
        Objects.equals(this.data, peticioDeFirmaPaginacio.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pagesize, page, totalpages, totalcount, data);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PeticioDeFirmaPaginacio {\n");
    
    sb.append("    pagesize: ").append(toIndentedString(pagesize)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    totalpages: ").append(toIndentedString(totalpages)).append("\n");
    sb.append("    totalcount: ").append(toIndentedString(totalcount)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
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
