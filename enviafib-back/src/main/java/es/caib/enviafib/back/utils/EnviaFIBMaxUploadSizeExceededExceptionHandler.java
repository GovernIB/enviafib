package es.caib.enviafib.back.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 
 * @author anadal
 * 
 */
public class EnviaFIBMaxUploadSizeExceededExceptionHandler implements
    org.springframework.web.servlet.HandlerExceptionResolver {

  protected final Logger log = Logger.getLogger(getClass());

  @Override
  public ModelAndView resolveException(HttpServletRequest request,
      HttpServletResponse response, Object handler, Exception ex) {

    if (ex instanceof EnviaFIBMaxUploadSizeExceededException
        || ex instanceof MaxUploadSizeExceededException
        || ex instanceof SizeLimitExceededException) {

      /*
       * log.info(" ++++ Scheme: " + request.getScheme());
       * log.info(" ++++ PathInfo: " + request.getPathInfo());
       * log.info(" ++++ PathTrans: " + request.getPathTranslated());
       * log.info(" ++++ ContextPath: " + request.getContextPath());
       * log.info(" ++++ ServletPath: " + request.getServletPath());
       * log.info(" ++++ getRequestURI: " + request.getRequestURI());
       * log.info(" ++++ getRequestURL: " + request.getRequestURL().toString());
       * log.info(" ++++ getQueryString: " + request.getQueryString());
       */
      String maxUploadSize = "???";
      String currentSize = "???";
      String msgCode;
      if (ex instanceof MaxUploadSizeExceededException) {
        MaxUploadSizeExceededException musee = (MaxUploadSizeExceededException) ex;
        if (musee instanceof EnviaFIBMaxUploadSizeExceededException) {
          msgCode = ((EnviaFIBMaxUploadSizeExceededException) musee).getMsgCode();
        } else {
          msgCode = "tamanyfitxerpujatsuperat";
        }

        // log.error(" YYYYYYYYYYYY  CAUSE: " + musee.getCause());
        if (musee.getCause() instanceof SizeLimitExceededException) {
          SizeLimitExceededException slee = (SizeLimitExceededException) musee.getCause();
          maxUploadSize = String.valueOf(slee.getPermittedSize());
          currentSize = String.valueOf(slee.getActualSize());
        } else {
          maxUploadSize = String.valueOf(musee.getMaxUploadSize());
        }

      } else {
        SizeLimitExceededException slee = (SizeLimitExceededException) ex;
        maxUploadSize = String.valueOf(slee.getPermittedSize());
        currentSize = String.valueOf(slee.getActualSize());
        msgCode = "tamanyfitxerpujatsuperat";
      }

      HtmlUtils.saveMessageError(request,
          I18NUtils.tradueix(msgCode, currentSize, maxUploadSize));

      ModelAndView mav = new ModelAndView(new RedirectView(request.getServletPath(), true));
      return mav;
    }
    return null;
  }

}
