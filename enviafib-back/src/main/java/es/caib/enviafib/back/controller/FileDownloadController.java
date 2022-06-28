package es.caib.enviafib.back.controller;


import es.caib.enviafib.hibernate.HibernateFileUtil;
import es.caib.enviafib.model.entity.Fitxer;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.crypt.FileIDEncrypter;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.activation.MimetypesFileTypeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * @autor anadal
 * 
 */
@Controller
@RequestMapping (value = FileDownloadController.CONTEXTWEB)
public class FileDownloadController {

    protected static final Logger log = Logger.getLogger(FileDownloadController.class);
    
    
    protected static final String CONTEXTWEB = "/common/arxiu/";

    /**
     * 
     * @param arxiuId
     * @param request
     * @param response
     * @author anadal
     */
    @RequestMapping("{arxiuId}")
    public void arxiu(@PathVariable("arxiuId") String encryptedArxiuId, 
        HttpServletRequest request, HttpServletResponse response) throws Exception {

      final String filename = request.getParameter("nom");
      final String contentType = request.getParameter("mime");

      long arxiuId = HibernateFileUtil.decryptFileID(encryptedArxiuId);
      
      if (arxiuId == 0) {
        FileIDEncrypter enc = HibernateFileUtil.getEncrypter();
        log.error("+ Key = ]"+ new String(enc.getKey().getEncoded()) + "[");
        log.error("+ Algorithm = " + enc.getAlgorithm());
        log.error("+ EncryptedArxiuId = ]"+ encryptedArxiuId + "[");

        String msg = I18NUtils.tradueix("filedownload.cant.desencriptar");
        response.setHeader("MsgEnviaFIB", msg);
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
      } else {
        fullDownload(arxiuId, filename, contentType, response);
      }
    }

    /**
     * 
     * @param arxiuId
     * @param filename
     * @param contentType
     * @param response
     */
    public static void fullDownload(long arxiuId, String filename, String contentType, 
      HttpServletResponse response) {

      FileInputStream input = null;
      OutputStream output = null;
      

      try {
        File file = FileSystemManager.getFile(arxiuId);

        if (!file.exists()) {
          I18NUtils.tradueix("error.fitxer.noexist", file.getAbsolutePath());
          String msg = "Fitxer amb ID=" + arxiuId + " no existeix.";
          response.setHeader("MsgEnviaFIB", msg);
          response.sendError(HttpServletResponse.SC_NOT_FOUND);
          return;
        }
        
        if (filename == null) {
          filename = "file"; // arxiu.getNombre()
        }
        if (contentType == null) {
          MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
          contentType = mimeTypesMap.getContentType(file);
        }
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
        response.setContentLength((int) file.length());

        output = response.getOutputStream();
        input = new FileInputStream(file);
        
        FileSystemManager.copy(input, output);
       
        input.close();
        output.close();

      }  catch (Exception e) {
        String msg = "Error descarregant fitxer amb ID = " + arxiuId + "(" + e.getMessage() + ")"; 
        log.error(msg, e);
        response.setHeader("MsgEnviaFIB", msg);
        try {
          response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } catch (IOException e1) {
          response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
      }
    }
    
    
    public static String fileUrl(Fitxer arxiu) {
      if (arxiu == null) {
          log.error("FileDownloadController:: fileUrl: S'ha rebut un arxiu null", new Exception());
          return null;
      } else {
        // {arxiuId}/{filename}/{contentType}
        String idfile = HibernateFileUtil.encryptFileID(arxiu.getFitxerID());

        String base = CONTEXTWEB + idfile;
        String nombre = arxiu.getNom(); 
        if (nombre == null) {
           return base;
        }        
        try {
          base = base + "?nom=" + URLEncoder.encode(nombre,"UTF-8");
        } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
          base = base + "?nom=" + nombre;
        } //  
        String mime = arxiu.getMime();
        if (mime == null) {
          return base;
        }
        try {
          base = base + "&mime=" + URLEncoder.encode(mime,"UTF-8");
        } catch (UnsupportedEncodingException e) {          
          e.printStackTrace();
          base = base + "&mime=" + mime;
        }
        return base;
      }
    }

}
