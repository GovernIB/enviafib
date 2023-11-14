package es.caib.enviafib.logic;

import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureBlock;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplate;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.enviafib.ejb.PeticioService;
import es.caib.enviafib.model.entity.InfoSignatura;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.entity.Usuari;
import es.caib.enviafib.persistence.InfoSignaturaJPA;
import es.caib.enviafib.persistence.PeticioJPA;

/**
 * 
 * @author fbosch
 * @author anadal
 * @author ptrias
 *
 */
@Local
public interface PeticioLogicaService extends PeticioService {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/PeticioLogicaEJB!es.caib.enviafib.logic.PeticioLogicaService";

    public PeticioJPA arrancarPeticio(long peticioID, String languageUI, Usuari solicitant) throws I18NException;

    public PeticioJPA arrancarPeticioFlux(long peticioID, String languageUI, FlowTemplateSimpleFlowTemplate flux, Usuari solicitant)
            throws I18NException;
    
    public void arrancarPeticioBySignatureBlocks(Peticio peticio, String languageUI,
            FirmaAsyncSimpleSignatureBlock[] signatureBlocks, Usuari solicitant) throws I18NException;
    
    public FirmaAsyncSimpleSignatureBlock[] convertFluxToSignatureBlocks(FlowTemplateSimpleFlowTemplate flux)
            throws I18NException;

    public void updatePublic(Peticio peticio) throws I18NException;

    public PeticioJPA findByPrimaryKeyPublic(Long _ID_);

    public void deleteFull(Peticio instance) throws I18NException;

    public void guardarResultatAutofirma(long peticioID, FirmaSimpleSignatureResult fssr) throws I18NException;

    public void cosesAFerPeticioRebutjada(long portafibID, String languageUI, String motiuRebuig) throws I18NException;

    public InfoSignaturaJPA cosesAFerPeticioFirmadaPart1(long portafibID, String languageUI) throws I18NException;
    
    public void cosesAFerPeticioFirmadaPart2(long portafibID, String languageUI, InfoSignatura infoSignatura) throws I18NException;
    
    public void reintentarTancarExpedient(long peticioID, String urlBase) throws I18NException;

    public void tancarExpedientPeticio(long peticioID, String urlBase) throws I18NException;
        
    public String getUrlToViewFlow(long peticioPortaFIB, String languageUI) throws I18NException;

    public void guardarPeticioArxiu(Peticio peticio, String languageUI, InfoSignatura infoSignatura, String urlBase) throws I18NException;

    public String reintentGuardarPeticioArxiu(long peticioID, long infoSignaturaID, String languageUI, String urlBase) throws I18NException;

    public List<StringKeyValue> getTipusDocumentals(String lang, boolean obtenerTodos) throws I18NException;

    public void initScheduler();
    
}
