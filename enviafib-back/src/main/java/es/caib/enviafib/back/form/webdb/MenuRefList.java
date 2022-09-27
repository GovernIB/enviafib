
package es.caib.enviafib.back.form.webdb;

import java.util.List;
import javax.ejb.EJB;
import org.springframework.stereotype.Component;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.enviafib.ejb.MenuService;
import es.caib.enviafib.ejb.TraduccioService;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.enviafib.model.fields.MenuFields;
import org.fundaciobit.genapp.common.web.controller.RefListBase;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * 
 */
@Component
public class MenuRefList extends RefListBase
    implements MenuFields {

  @EJB(mappedName = MenuService.JNDI_NAME)
  private MenuService menuEjb;

  @EJB(mappedName = TraduccioService.JNDI_NAME)
  private TraduccioService traduccioEjb;
  public MenuRefList(MenuRefList __clone) {
    super(__clone);
    this.menuEjb = __clone.menuEjb;
    this.traduccioEjb = __clone.traduccioEjb;
  }
  public MenuRefList() {
    setSelects(new Select<?>[] { NOM.select });
    addCampTraduible(TITOLMENUID.select);
    addCampTraduible(AJUDAMENUID.select);
  }
  public List<StringKeyValue> getReferenceList(Field<?> keyField, Where where, OrderBy ... orderBy) throws I18NException {
    Select<Long> _transSelect = checkTranslationFields();
    Select<StringKeyValue> select =  new org.fundaciobit.genapp.common.query.SelectMultipleStringKeyValue(keyField.select, getSeparator(), getSelects());
    List<StringKeyValue> list = menuEjb.executeQuery(select, where, (orderBy==null || orderBy.length == 0) ? getOrderBy() : orderBy);
    if (_transSelect == null) {
      return list;
    }
    // key => TransID | value => menuEjb_PK
    java.util.Map<String,String> keysMap = org.fundaciobit.genapp.common.utils.Utils.listToMapInverse(list);
    org.fundaciobit.genapp.common.query.Where _w1 = es.caib.enviafib.model.fields.TraduccioFields.TRADUCCIOID.in(menuEjb.executeQuery(_transSelect, where));
    List<es.caib.enviafib.model.entity.Traduccio> traduccions = traduccioEjb.select(_w1);
    List<StringKeyValue> _list = new java.util.ArrayList<StringKeyValue>(traduccions.size());
    final String _lang = org.fundaciobit.genapp.common.web.i18n.I18NUtils.getLocale().getLanguage();
    for (es.caib.enviafib.model.entity.Traduccio traduccio : traduccions) {
      es.caib.enviafib.persistence.TraduccioJPA traduccioJPA = (es.caib.enviafib.persistence.TraduccioJPA) traduccio;
      String key = keysMap.get(String.valueOf(traduccioJPA.getTraduccioID()));
      es.caib.enviafib.persistence.TraduccioMapJPA _tm = traduccioJPA.getTraduccio(_lang);
      String value;
      if (_tm == null) {
          value = "NO_TRADUCCIO_PER_CODI_" + traduccio.getTraduccioID() + "_[" + _lang + "]";
      } else {
          value= _tm.getValor();
      }
      StringKeyValue skv = new StringKeyValue(key, value);
      _list.add(skv);
    }
    if (!_list.isEmpty()) {
      java.util.Collections.sort(_list, new org.fundaciobit.genapp.common.KeyValue.KeyValueComparator<String>());
    }
    return _list;

  }
}
