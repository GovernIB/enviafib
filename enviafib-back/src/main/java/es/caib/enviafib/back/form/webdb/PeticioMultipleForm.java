package es.caib.enviafib.back.form.webdb;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 
 * @author ptrias
 *
 */
public class PeticioMultipleForm extends PeticioForm {

    private CommonsMultipartFile[] hiddenFile;
    private CommonsMultipartFile[] attachedFile;
    private String fileInfoFlag;
    
    public CommonsMultipartFile[] getHiddenFile() {
        return hiddenFile;
    }

    public void setHiddenFile(CommonsMultipartFile[] hiddenFile) {
        this.hiddenFile = hiddenFile;
    }

    public CommonsMultipartFile[] getAttachedFile() {
        return attachedFile;
    }

    public void setAttachedFile(CommonsMultipartFile[] attachedFile) {
        this.attachedFile = attachedFile;
    }

    public PeticioMultipleForm() {
    }

    public PeticioMultipleForm(PeticioForm __toClone) {
        super(__toClone);
    }

    public String getFileInfoFlag() {
        return fileInfoFlag;
    }

    public void setFileInfoFlag(String fileInfoFlag) {
        this.fileInfoFlag = fileInfoFlag;
    }
}
