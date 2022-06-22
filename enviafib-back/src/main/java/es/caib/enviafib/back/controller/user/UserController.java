package es.caib.enviafib.back.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/user/")
public class UserController {

  
  @RequestMapping(value = "/option1")
  public ModelAndView option1(HttpSession session,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    
    ModelAndView mav = new ModelAndView("option1User");
    mav.addObject("optionNumber", "OPCIÓ USER -1-");
    return mav;
    
  }
  
  
  @RequestMapping(value = "/option2")
  public ModelAndView option2(HttpSession session,
      HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    
    ModelAndView mav = new ModelAndView("option2User");
    mav.addObject("optionNumber", "OPCIÓ USER -2-");
    return mav;
  }
  
  
}
