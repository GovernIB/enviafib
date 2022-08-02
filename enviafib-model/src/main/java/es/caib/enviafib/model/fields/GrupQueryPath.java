
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class GrupQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public GrupQueryPath() {
  }

  protected GrupQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField GRUPID() {
    return new LongField(getQueryPath(), GrupFields.GRUPID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), GrupFields.NOM);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), GrupFields.DESCRIPCIO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (GrupFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public GrupUsuariQueryPath GRUPUSUARIS() {
    return new GrupUsuariQueryPath(new QueryPath() {
      public String getQueryPath() {
          return GrupQueryPath.this.getQueryPath() + "grupUsuaris" + ".";
      }
    });
  }
*/

}
