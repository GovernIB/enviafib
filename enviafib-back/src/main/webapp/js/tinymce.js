
/**
 *  INICIALITZACIÓ TINY RICH TEXT
 *  @author anadal@ibit.org
 */


tinyMCE.init({  
        // General options  
        mode : "specific_textareas",
        editor_selector : "mceEditor",
        theme : "advanced",  
        plugins : "pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,wordcount,advlist,autosave",  
        language : lang, 
        // Theme options  
        /* TTT save,newdocument,|, */
        theme_advanced_buttons1 : "bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,styleselect,formatselect,fontselect,fontsizeselect",
        /* TTT anchor,image,cleanup,help,code, */
        theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,preview,|,forecolor,backcolor,|,cleanup,code",  
        //TTT ,link,unlink,|,insertdate,inserttime 
        //theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,advhr,|,ltr,rtl,|,fullscreen",  
        //TTT theme_advanced_buttons4 : "insertlayer,moveforward,movebackward,absolute,|,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,pagebreak,restoredraft",  
        theme_advanced_toolbar_location : "top",  
        theme_advanced_toolbar_align : "left",  
        
        // TTT
        theme_advanced_statusbar_location : "none",
        //TTT theme_advanced_statusbar_location : "bottom",  
        theme_advanced_resizing : true,  
  
        // Example content CSS (should be your site CSS)  
        // using false to ensure that the default browser settings are used for best Accessibility  
        // ACCESSIBILITY SETTINGS  
        content_css : "/portafib/css/default.css,/portafib/css/bootstrap.css,/portafib/css/bootstrap.min.css",  
        // Use browser preferred colors for dialogs.  
        browser_preferred_colors : true,  
        detect_highcontrast : true,
        
        // TTT
        theme_advanced_default_foreground_color: "#FFFFFF"
    });  



	tinyMCE.init({  
	    // General options  
	    mode : "specific_textareas",
	    editor_selector : "mceEditorReadOnly",
	    theme : "advanced",
	    readonly : "true",
	    //plugins : "pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,wordcount,advlist,autosave",  
	    language : lang, 
	  
	    // ACCESSIBILITY SETTINGS  
	    content_css : "/portafib/css/default.css,/portafib/css/bootstrap.css,/portafib/css/bootstrap.min.css",
	    // Use browser preferred colors for dialogs.  
	    browser_preferred_colors : true,  
	    detect_highcontrast : true,
	    
	    // TTT
	    theme_advanced_default_foreground_color: "#FFFFFF"
	}); 
	
	
	//tinyMCE.ThemeManager.requireLangPack('advanced');
