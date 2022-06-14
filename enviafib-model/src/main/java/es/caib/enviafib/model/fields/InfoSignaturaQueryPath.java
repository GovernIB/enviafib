
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class InfoSignaturaQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public InfoSignaturaQueryPath() {
  }

  protected InfoSignaturaQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField INFOSIGNATURAID() {
    return new LongField(getQueryPath(), InfoSignaturaFields.INFOSIGNATURAID);
  }

  public IntegerField SIGNOPERATION() {
    return new IntegerField(getQueryPath(), InfoSignaturaFields.SIGNOPERATION);
  }

  public StringField SIGNTYPE() {
    return new StringField(getQueryPath(), InfoSignaturaFields.SIGNTYPE);
  }

  public StringField SIGNALGORITHM() {
    return new StringField(getQueryPath(), InfoSignaturaFields.SIGNALGORITHM);
  }

  public IntegerField SIGNMODE() {
    return new IntegerField(getQueryPath(), InfoSignaturaFields.SIGNMODE);
  }

  public IntegerField SIGNATURESTABLELOCATION() {
    return new IntegerField(getQueryPath(), InfoSignaturaFields.SIGNATURESTABLELOCATION);
  }

  public BooleanField TIMESTAMPINCLUDED() {
    return new BooleanField(getQueryPath(), InfoSignaturaFields.TIMESTAMPINCLUDED);
  }

  public BooleanField POLICYINCLUDED() {
    return new BooleanField(getQueryPath(), InfoSignaturaFields.POLICYINCLUDED);
  }

  public StringField ENITIPOFIRMA() {
    return new StringField(getQueryPath(), InfoSignaturaFields.ENITIPOFIRMA);
  }

  public StringField ENIPERFILFIRMA() {
    return new StringField(getQueryPath(), InfoSignaturaFields.ENIPERFILFIRMA);
  }

  public StringField ENIROLFIRMA() {
    return new StringField(getQueryPath(), InfoSignaturaFields.ENIROLFIRMA);
  }

  public StringField ENISIGNERNAME() {
    return new StringField(getQueryPath(), InfoSignaturaFields.ENISIGNERNAME);
  }

  public StringField ENISIGNERADMINISTRATIONID() {
    return new StringField(getQueryPath(), InfoSignaturaFields.ENISIGNERADMINISTRATIONID);
  }

  public StringField ENISIGNLEVEL() {
    return new StringField(getQueryPath(), InfoSignaturaFields.ENISIGNLEVEL);
  }

  public BooleanField CHECKADMINISTRATIONIDOFSIGNER() {
    return new BooleanField(getQueryPath(), InfoSignaturaFields.CHECKADMINISTRATIONIDOFSIGNER);
  }

  public BooleanField CHECKDOCUMENTMODIFICATIONS() {
    return new BooleanField(getQueryPath(), InfoSignaturaFields.CHECKDOCUMENTMODIFICATIONS);
  }

  public BooleanField CHECKVALIDATIONSIGNATURE() {
    return new BooleanField(getQueryPath(), InfoSignaturaFields.CHECKVALIDATIONSIGNATURE);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (InfoSignaturaFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PeticioQueryPath PETICIOS() {
    return new PeticioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return InfoSignaturaQueryPath.this.getQueryPath() + "peticios" + ".";
      }
    });
  }
*/

}
