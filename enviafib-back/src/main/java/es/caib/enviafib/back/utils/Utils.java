package es.caib.enviafib.back.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.form.BaseFilterForm;


/**
 * 
 * @author anadal
 * 
 */
public class Utils {

  protected static final Logger log = Logger.getLogger(Utils.class);

  public static String getSortIconsAdditionalField(BaseFilterForm baseFilter,
       AdditionalField<?, ?> additionalField) throws Exception {
      
      String code = additionalField.getCodeName();
      String newCode = baseFilter.getLabels().get(additionalField.getValueField());
      if (newCode != null) {
        code = newCode;
      }
      return getSortIcons(baseFilter, additionalField.getOrderBy(), code);
    
  }
  

  public static String getSortIcons(BaseFilterForm baseFilter, Field<?> theField) throws Exception {
    String code = theField.getFullName();
    String newCode = baseFilter.getLabels().get(theField);
    if (newCode != null) {
      code = newCode;
    }
    return getSortIcons(baseFilter, theField, code);
  }
  
  
  protected static String getSortIcons(BaseFilterForm baseFilter, 
      Field<?> theField, String code) throws Exception {
    
    if (baseFilter == null) {
      throw new Exception("FilterForm is null.");
    }

    
    if (baseFilter.isVisibleOrderBy() && theField != null) {

      String field = theField.getJavaName();
      
      String html;
      
      boolean orderedBythisfield = false;
      boolean isOrderedAsc = false;
      if (baseFilter.getOrderBy() == null) {
        // cercam en el valors per defecte
        OrderBy[] orderByDefaultList = baseFilter.getDefaultOrderBy();
        if (orderByDefaultList != null) {
          for (OrderBy orderBy : orderByDefaultList) {          
            if (orderBy.javaName.equals(theField.fullName)) {
              orderedBythisfield = true;
              isOrderedAsc = orderBy.orderType.equals(OrderType.ASC);
              break;
            }
          }
        }
        
      } else {
        orderedBythisfield = field.equals(baseFilter.getOrderBy());
        isOrderedAsc = baseFilter.isOrderAsc();
      }
      
      
      if (orderedBythisfield) {
  
        html = "<span style=\"cursor:row-resize\" onClick=\"javascript:executeOrderBy('"
            + field
            + "', "
            + !isOrderedAsc
            + ");\""
            + " title=\""
            + I18NUtils
                .tradueix(!isOrderedAsc ? "genapp.form.sort.asc" : "genapp.form.sort.desc")
            + "\" >" + getText(code) + "<i class=\""
            + (isOrderedAsc ? "fas fa-sort-alpha-up" : "fas fa-sort-alpha-up-alt")
            + "\"></i></span>";
      } else {
        html = "<span style=\"cursor:row-resize\" onclick=\"javascript:executeOrderBy('" + field + "', true);\" "
            + " title=\"" + I18NUtils.tradueix("genapp.form.sort.asc") + "\">"
            + getText(code) + "<i class=\"fas fa-sort\"></i></span>";
      }
      return html;
    } else {
      return getText(code);
    }

  }
  
  
  private static String getText(String code) {
    if (code == null) {
      return null;
    }
    if (code.startsWith("=")) {
      return code.substring(1);
    } else {
      return I18NUtils.tradueix(code);
    }
    
  }

  public static String intArrayToString(int[] itemsPerPagines) {
    String str = Arrays.toString(itemsPerPagines);
    return str.substring(1, str.length() - 1).replace(" ", "");
  }

 
  
  public static final Comparator<StringKeyValue> STRINGKEYVALUECOMPARATOR = new Comparator<StringKeyValue>() {
    @Override
    public int compare(StringKeyValue o1, StringKeyValue o2) {
      return o1.value.compareToIgnoreCase(o2.value);
    }
  };
  
  
  public static void sortStringKeyValueList(List<StringKeyValue> listNovaFirma) {
    Collections.sort(listNovaFirma, STRINGKEYVALUECOMPARATOR);
  }

 
}
