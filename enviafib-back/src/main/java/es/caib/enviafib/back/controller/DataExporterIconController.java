package es.caib.enviafib.back.controller;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.web.exportdata.DataExported;
import org.fundaciobit.genapp.common.web.exportdata.DataExporterManager;
import org.fundaciobit.genapp.common.web.exportdata.IDataExporter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping (value = "/common/icon/")
public class DataExporterIconController {

  @RequestMapping("{dataExporterID}")
  public void icon(@PathVariable("dataExporterID") String dataExporterID, 
      HttpServletRequest request, HttpServletResponse response) throws Exception {

    IDataExporter dataExporter = DataExporterManager.getByID(dataExporterID);

    DataExported ef = dataExporter.getIcon();

    byte[] image = ef.getData();
    
    response.setContentType(ef.getContentType());
    response.setHeader("Content-Disposition", "inline; filename=\"" + ef.getFilename()+ "\"");
    response.setContentLength(image.length);
    
    
    OutputStream output = response.getOutputStream();
    output.write(image);
    output.flush();
    output.close();
  }
  
}
