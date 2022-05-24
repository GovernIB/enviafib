
package es.caib.enviafib.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.enviafib.ejb.SerieDocumentalService;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.enviafib.model.fields.SerieDocumentalFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class SerieDocumentalRefList extends RefListBase
    implements SerieDocumentalFields {

  @EJB(mappedName = SerieDocumentalService.JNDI_NAME)
  private SerieDocumentalService serieDocumentalEjb;

  public SerieDocumentalRefList(SerieDocumentalRefList __clone) {
    super(__clone);
    this.serieDocumentalEjb = __clone.serieDocumentalEjb;
  }
  public SerieDocumentalRefList() {
    setSelects(new Select<?>[] { NOM.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = serieDocumentalEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
  }
}
