package es.caib.enviafib.back.form.webdb;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 
 * @author ptrias
 *
 */
public class PeticioMultipleForm extends PeticioForm {

    private CommonsMultipartFile[] hiddenFile;

    public CommonsMultipartFile[] getHiddenFile() {
        return hiddenFile;
    }

    public void setHiddenFile(CommonsMultipartFile[] hiddenFile) {
        this.hiddenFile = hiddenFile;
    }

    public PeticioMultipleForm() {
    }

    public PeticioMultipleForm(PeticioForm __toClone) {
        super(__toClone);
    }
}
