package es.caib.enviafib.back.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleReviser;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignature;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureBlock;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSigner;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.persistence.PeticioJPA;

/**
 * 
 * @author anadal
 *
 *
 
 
# Es definirà el flux simple emprant les següents normes:
#    (0) Comentaris començaran per '#'
#    (1) Cada Fila representa un bloc de firmes.
#    (2) Els usuaris de cada bloc es representaràn pel seu username o càrrec
#        d'Estructura organitzativa i es separaràn pel caràcter '|' o '&'.
#    (3) Una separació d'usuaris emprant '&' implica que totes les firmes s'han de realitzar.
#    (4) Una separació d'usuaris emprant '|' implica que només es requerirà la firma d'un 
#         d'ells i després es passarà al següent bloc.
#    (5) No es poden mesclar en una mateixa fila separadors '&' i '|'
#    (6) Els càrrecs d'Estructura organitzativa es definiran de la següent forma:
#            - Gerent/President => ${GerentPresident}
#            - Cap Area/Conseller => ${CapAreaConseller}
#            - Cap Departament/Director General => ${CapDepartamentDirectorGeneral}
#            - Secretari => ${Secretari}
#            - Encarregat de Compres => ${EncarregatCompres}
#            - Recursos Humans => ${RecursosHumans}
#            - Carrec Addicional 1 => ${Carrec1}
#            - Carrec Addicional 2 => ${Carrec2}
#            - Usuari Loguejat => ${UsuariActual}
#
# Exemple:
#        anadal|ptrias
#        atrobat&fbosh
#        ${Secretari}
#        ${GerentPresident}
#
# Explicació: Primer firmarà anadal o ptrias (només un dels dos), després atrobat
#             i fbosch en l'ordre que vulguin, despres el secretari i finalment gerent.
#
 
 */
@Controller
@RequestMapping(value = FirmaPerFluxFirmaSimpleUserController.CONTEXT_WEB)
public class FirmaPerFluxFirmaSimpleUserController extends AbstractFirmaUserController {
    
    
    public static final String AJUDA = 
            "# Es definirà el flux simple emprant les següents normes:\n"
          + "#    (0) Comentaris començaran per '#'\n"
          + "#    (1) Cada Fila representa un bloc de firmes.\n"
          + "#    (2) Els usuaris de cada bloc es representaran pel seu username o càrrec\n"
          + "#        d'Estructura organitzativa i es separaran pel caràcter '|' o '&'.\n"
          + "#    (3) Una separació d'usuaris emprant '&' implica que totes les firmes s'han de realitzar.\n"
          + "#    (4) Una separació d'usuaris emprant '|' implica que només es requerirà la firma d'un \n"
          + "#         d'ells i després es passarà al següent bloc.\n"
          + "#    (5) No es poden mesclar en una mateixa fila separadors '&' i '|'\n"
          + "#    (6) Els càrrecs d'Estructura organitzativa es definiran de la següent forma:\n"
          + "#          - Gerent/President => ${GerentPresident}\n"
          + "#          - Cap Area/Conseller => ${CapAreaConseller}\n"
          + "#          - Cap Departament/Director General => ${CapDepartamentDirectorGeneral}\n"
          + "#          - Secretari => ${Secretari}\n"
          + "#          - Encarregat de Compres => ${EncarregatCompres}\n"
          + "#          - Recursos Humans => ${RecursosHumans}\n"
          + "#          - Càrrec Addicional 1 => ${Carrec1}\n"
          + "#          - Càrrec Addicional 2 => ${Carrec2}\n"
          + "#          - Usuari Loguejat => ${UsuariActual}"
          + "#\n"
          + "# Exemple:\n"
          + "#        anadal | ptrias\n"
          + "#        atrobat & fbosh\n"
          + "#        ${Secretari}\n"
          + "#        ${GerentPresident}\n"
          + "#\n"
          + "# Explicació: Primer firmarà anadal o ptrias (només un dels dos), després atrobat\n"
          + "#             i fbosch en l'ordre que vulguin, després el secretari i finalment gerent.\n"
          + "#";

    public static final String CONTEXT_WEB = "/user/fluxfirmasimple";

    public static final String FLUX_SIMPLE_SESSION_KEY = "__FLUX_SIMPLE_SESSION_KEY__";

    @Override
    public PeticioForm getPeticioForm(PeticioJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        PeticioForm peticioForm = super.getPeticioForm(_jpa, __isView, request, mav);

        peticioForm.setAttachedAdditionalJspCode(true);

        return peticioForm;
    }

    @Override
    public int getTipusPeticio() {
        return TIPUS_PETICIO_FLUX_SIMPLE;
    }
    
    

    
    
    

    public static FirmaAsyncSimpleSignatureBlock[] getFluxFromFluxSimple(String fluxSimple,
            IEstructuraOrganitzativaPlugin plugin, String loginUsername) throws I18NException {
        FirmaAsyncSimpleSignatureBlock[] signatureBlocks = null;

        List<List<String>> destinataris = getUsernamesFromFluxSimple(fluxSimple, plugin, loginUsername);

        if (destinataris == null || destinataris.size() == 0) {
            // XYZ ZZZ TRA
            throw new I18NException("genapp.comodi", "La llista de destinataris està buida");
        }

        signatureBlocks = new FirmaAsyncSimpleSignatureBlock[destinataris.size()];

        int b = 0;
        for (List<String> destinatarisBloc : destinataris) {

            if (destinatarisBloc == null || destinatarisBloc.size() < 2) {
                // XYZ ZZZ TRA
                throw new I18NException("genapp.comodi", "No s'han definit els destinataris del bloc " + b + ".");
            }

            List<FirmaAsyncSimpleSignature> signers = new ArrayList<FirmaAsyncSimpleSignature>();

            String[] destinatarisBlocArray = destinatarisBloc.toArray(new String[destinatarisBloc.size()]);

            final boolean isAnd = "&".equals(destinatarisBlocArray[0]);

            for (int j = 1; j < destinatarisBlocArray.length; j++) {

                String username = destinatarisBlocArray[j].trim();

                if (username.trim().length() == 0) {
                    // XYZ ZZZ TRA
                    throw new I18NException("genapp.comodi",
                            "El destinatari " + j + " del bloc " + b + " està buit o val null");
                }

                FirmaAsyncSimpleSigner personToSign;
                {
                    personToSign = new FirmaAsyncSimpleSigner();
                    personToSign.setUsername(username);
                }

                boolean required = isAnd ? true : false;
                String reason = null; // Usar la de la Petició

                // Revisors
                final int minimumNumberOfRevisers = 0;
                final List<FirmaAsyncSimpleReviser> revisers = null;

                signers.add(new FirmaAsyncSimpleSignature(personToSign, required, reason, minimumNumberOfRevisers,
                        revisers));
            }

            int minimumNumberOfSignaturesRequired = isAnd ? signers.size() : 1;
            signatureBlocks[b] = new FirmaAsyncSimpleSignatureBlock(minimumNumberOfSignaturesRequired, signers);
            b++;
        }

        return signatureBlocks;
    }

    protected static List<List<String>> getUsernamesFromFluxSimple(String fluxSimple,
            IEstructuraOrganitzativaPlugin plugin, String loginUsername) throws I18NException {

        if (fluxSimple == null || fluxSimple.trim().length() == 0) {
            return null;
        }

        fluxSimple = fluxSimple.replace("\r\n", "\n").replace("\r", "\n");

        String[] blocs = fluxSimple.split("\\r?\\n");

        List<List<String>> flux = new ArrayList<List<String>>();

        for (int i = 0; i < blocs.length; i++) {
            
            String line = blocs[i];
            line = line.trim();
            
            if (line.startsWith("#")) {
                continue;
            }
            
            List<String> blocksList = new ArrayList<String>();
            String separator;
            {
                final boolean conteAnd = (line.indexOf('&') != -1);
                final boolean conteOr = (line.indexOf('|') != -1);
                if (conteAnd && conteOr) {
                    // XYZ ZZZ TRA
                    throw new I18NException("genapp.comodi","La línia '" + line + "' conté caràcters '&' i '|' la qual cosa no està permesa.");
                } else {
                    separator = conteOr? "|" : "&";                
                }
            }
            blocksList.add(separator);
            String[] usernames = line.split("|".equals(separator)?"\\|":"&");

            for (String usr : usernames) {
                usr = usr.trim();
                if (usr.startsWith("$")) {
                    try {
                        // És un càrrec en forma ${nom_carrec_estructura_organitzativa}
                        String carrec = usr.substring(1).trim();
                        // llevam claus
                        carrec = carrec.substring(1, carrec.length() - 1);

                        switch (carrec) {
                            case "GerentPresident":
                                usr = plugin.getGerentPresidentUsername();
                            break;

                            case "CapAreaConseller":
                                usr = plugin.getCapAreaConsellerUsername(loginUsername);
                            break;

                            case "CapDepartamentDirectorGeneral":
                                usr = plugin.getCapDepartamentDirectorGeneralUsername(loginUsername);
                            break;
                            case "Secretari":
                                usr = plugin.getSecretariUsername(loginUsername);
                            break;
                            case "EncarregatCompres":
                                usr = plugin.getEncarregatCompresUsername(loginUsername);
                            break;
                            case "RecursosHumans":
                                usr = plugin.getRecursosHumansUsername(loginUsername);
                            break;
                            case "Carrec1":
                                usr = plugin.getCarrec1Username(loginUsername);
                            break;

                            case "Carrec2":
                                usr = plugin.getCarrec2Username(loginUsername);
                            break;
                            
                            case "UsuariActual":
                                usr = loginUsername;
                            break;

                            default:
                                // XYZ ZZZ TRA
                                throw new Exception("El càrrec amb nom ]" + carrec + "[ és desconegut.");
                        }
                        if (usr == null || usr.trim().length() == 0) {
                            // XYZ ZZZ TRA
                            throw new Exception("El càrrec amb nom ]" + carrec
                                    + "[ no té definit username dins de l'Estructura organitzativa.");
                        }
                    } catch (Exception e) {
                        // XYZ ZZZ TRA
                        String msg = "Error intentant esbrinar càrrec a partir de ]" + usr + "[: " + e.getMessage();
                        System.err.println(msg);
                        e.printStackTrace(System.err);
                        // XYZ ZZZ TRA
                        throw new I18NException("genapp.comodi", msg);
                    }

                    blocksList.add(usr);

                } else {
                    // és un username
                    blocksList.add(usr);
                }

            }

            flux.add(blocksList);
        }

        return flux;

    }

}
