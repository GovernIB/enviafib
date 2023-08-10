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
import java.util.ArrayList;
import java.util.List;
/**
 * TipusDocumentalsPaginacio
 */


public class TipusDocumentalsPaginacio {
  @JsonProperty("pagesize")
  private Integer pagesize = null;

  @JsonProperty("page")
  private Integer page = null;

  @JsonProperty("totalpages")
  private Integer totalpages = null;

  @JsonProperty("totalcount")
  private Integer totalcount = null;

  @JsonProperty("data")
  private List<String> data = null;

  public TipusDocumentalsPaginacio pagesize(Integer pagesize) {
    this.pagesize = pagesize;
    return this;
  }

   /**
   * Get pagesize
   * @return pagesize
  **/
  @Schema(description = "")
  public Integer getPagesize() {
    return pagesize;
  }

  public void setPagesize(Integer pagesize) {
    this.pagesize = pagesize;
  }

  public TipusDocumentalsPaginacio page(Integer page) {
    this.page = page;
    return this;
  }

   /**
   * Get page
   * @return page
  **/
  @Schema(description = "")
  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public TipusDocumentalsPaginacio totalpages(Integer totalpages) {
    this.totalpages = totalpages;
    return this;
  }

   /**
   * Get totalpages
   * @return totalpages
  **/
  @Schema(description = "")
  public Integer getTotalpages() {
    return totalpages;
  }

  public void setTotalpages(Integer totalpages) {
    this.totalpages = totalpages;
  }

  public TipusDocumentalsPaginacio totalcount(Integer totalcount) {
    this.totalcount = totalcount;
    return this;
  }

   /**
   * Get totalcount
   * @return totalcount
  **/
  @Schema(description = "")
  public Integer getTotalcount() {
    return totalcount;
  }

  public void setTotalcount(Integer totalcount) {
    this.totalcount = totalcount;
  }

  public TipusDocumentalsPaginacio data(List<String> data) {
    this.data = data;
    return this;
  }

  public TipusDocumentalsPaginacio addDataItem(String dataItem) {
    if (this.data == null) {
      this.data = new ArrayList<String>();
    }
    this.data.add(dataItem);
    return this;
  }

   /**
   * Get data
   * @return data
  **/
  @Schema(description = "")
  public List<String> getData() {
    return data;
  }

  public void setData(List<String> data) {
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
    TipusDocumentalsPaginacio tipusDocumentalsPaginacio = (TipusDocumentalsPaginacio) o;
    return Objects.equals(this.pagesize, tipusDocumentalsPaginacio.pagesize) &&
        Objects.equals(this.page, tipusDocumentalsPaginacio.page) &&
        Objects.equals(this.totalpages, tipusDocumentalsPaginacio.totalpages) &&
        Objects.equals(this.totalcount, tipusDocumentalsPaginacio.totalcount) &&
        Objects.equals(this.data, tipusDocumentalsPaginacio.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pagesize, page, totalpages, totalcount, data);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TipusDocumentalsPaginacio {\n");
    
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
