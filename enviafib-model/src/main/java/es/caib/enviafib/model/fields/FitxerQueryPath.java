
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class FitxerQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public FitxerQueryPath() {
  }

  protected FitxerQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField FITXERID() {
    return new LongField(getQueryPath(), FitxerFields.FITXERID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), FitxerFields.NOM);
  }

  public StringField MIME() {
    return new StringField(getQueryPath(), FitxerFields.MIME);
  }

  public LongField TAMANY() {
    return new LongField(getQueryPath(), FitxerFields.TAMANY);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), FitxerFields.DESCRIPCIO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (FitxerFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PeticioQueryPath PETICIOS() {
    return new PeticioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FitxerQueryPath.this.getQueryPath() + "peticios" + ".";
      }
    });
  }
*/

}
