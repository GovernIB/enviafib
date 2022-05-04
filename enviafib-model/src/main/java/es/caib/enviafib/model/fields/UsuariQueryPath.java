
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class UsuariQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public UsuariQueryPath() {
  }

  protected UsuariQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField USUARIID() {
    return new LongField(getQueryPath(), UsuariFields.USUARIID);
  }

  public StringField USERNAME() {
    return new StringField(getQueryPath(), UsuariFields.USERNAME);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), UsuariFields.NOM);
  }

  public StringField LLINATGE1() {
    return new StringField(getQueryPath(), UsuariFields.LLINATGE1);
  }

  public StringField LLINATGE2() {
    return new StringField(getQueryPath(), UsuariFields.LLINATGE2);
  }

  public StringField NIF() {
    return new StringField(getQueryPath(), UsuariFields.NIF);
  }

  public StringField EMAIL() {
    return new StringField(getQueryPath(), UsuariFields.EMAIL);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (UsuariFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PeticioQueryPath PETICIOS() {
    return new PeticioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariQueryPath.this.getQueryPath() + "peticios" + ".";
      }
    });
  }
*/

}
