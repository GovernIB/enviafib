
package es.caib.enviafib.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.enviafib.ejb.OrganitzacioService;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.enviafib.model.fields.OrganitzacioFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class OrganitzacioRefList extends RefListBase
    implements OrganitzacioFields {

  @EJB(mappedName = OrganitzacioService.JNDI_NAME)
  private OrganitzacioService organitzacioEjb;

  public OrganitzacioRefList(OrganitzacioRefList __clone) {
    super(__clone);
    this.organitzacioEjb = __clone.organitzacioEjb;
  }
  public OrganitzacioRefList() {
    setSelects(new Select<?>[] { ORGANITZACIOID.select });
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = organitzacioEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    return list;
  }
}
