
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class TraduccioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public TraduccioQueryPath() {
  }

  protected TraduccioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField TRADUCCIOID() {
    return new LongField(getQueryPath(), TraduccioFields.TRADUCCIOID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (TraduccioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public MenuQueryPath MENU_AJUDAMENUIDS() {
    return new MenuQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TraduccioQueryPath.this.getQueryPath() + "menu_ajudamenuids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public MenuQueryPath MENU_TITOLMENUIDS() {
    return new MenuQueryPath(new QueryPath() {
      public String getQueryPath() {
          return TraduccioQueryPath.this.getQueryPath() + "menu_titolmenuids" + ".";
      }
    });
  }
*/

}
