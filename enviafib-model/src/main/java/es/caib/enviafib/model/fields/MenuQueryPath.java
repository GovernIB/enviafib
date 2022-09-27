
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class MenuQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public MenuQueryPath() {
  }

  protected MenuQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField MENUID() {
    return new LongField(getQueryPath(), MenuFields.MENUID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), MenuFields.NOM);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), MenuFields.DESCRIPCIO);
  }

  public LongField TITOLMENUID() {
    return new LongField(getQueryPath(), MenuFields.TITOLMENUID);
  }

  public LongField AJUDAMENUID() {
    return new LongField(getQueryPath(), MenuFields.AJUDAMENUID);
  }

  public IntegerField ORDRE() {
    return new IntegerField(getQueryPath(), MenuFields.ORDRE);
  }

  public IntegerField TIPUS() {
    return new IntegerField(getQueryPath(), MenuFields.TIPUS);
  }

  public LongField GRUPID() {
    return new LongField(getQueryPath(), MenuFields.GRUPID);
  }

  public StringField PARAMETRECOMBO() {
    return new StringField(getQueryPath(), MenuFields.PARAMETRECOMBO);
  }

  public StringField PARAMETRETEXT() {
    return new StringField(getQueryPath(), MenuFields.PARAMETRETEXT);
  }

  public BooleanField ACTIU() {
    return new BooleanField(getQueryPath(), MenuFields.ACTIU);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (MenuFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public TraduccioQueryPath TITOLMENU() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return MenuQueryPath.this.getQueryPath() + "titolMenu" + ".";
      }
    });
  }

  public TraduccioQueryPath AJUDAMENU() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return MenuQueryPath.this.getQueryPath() + "ajudaMenu" + ".";
      }
    });
  }

  public GrupQueryPath GRUP() {
    return new GrupQueryPath(new QueryPath() {
      public String getQueryPath() {
          return MenuQueryPath.this.getQueryPath() + "grup" + ".";
      }
    });
  }

}
