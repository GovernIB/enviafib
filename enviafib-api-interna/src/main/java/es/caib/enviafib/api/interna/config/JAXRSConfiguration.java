package es.caib.enviafib.api.interna.config;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.apache.log4j.Logger;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 *
 * @author anadal
 *
 */
@OpenAPIDefinition(
        info = @Info(
                title = "API REST INTERNA de EnviaFIB",
                description = "Conjunt de Serveis REST de EnviaFIB per ser accedits des de l'interior",
                version = "1.0",
                license = @License(name = "License Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0"),
                contact = @Contact(
                        name = "Departament de Govern Digital a la Fundació Bit",
                        email = "governdigital.enviafib@fundaciobit.org",
                        url = "http://governdigital.fundaciobit.org")),
        servers = { @Server(url = "/enviafibapi/interna"),
                @Server(url = "http://localhost:8080/enviafibapi/interna"),
                @Server(url = "https://dev.caib.es/enviafibapi/interna"),
                @Server(url = "https://proves.caib.es/enviafibapi/interna"),
                @Server(url = "https://se.caib.es/enviafibapi/interna"),
                @Server(url = "https://www.caib.es/enviafibapi/interna") },
        externalDocs = @ExternalDocumentation(
                description = "Java Client (GovernIB Github)",
                url = "https://github.com/GovernIB/enviafib/tree/enviafib-1.0/enviafib-api-interna-client")

)
@ApplicationPath("/")
public class JAXRSConfiguration extends Application {

    protected Logger log = org.apache.log4j.Logger.getLogger(this.getClass());

    /**
     * Les aplicacions JAX-RS necessiten un constructor buid.
     */
    public JAXRSConfiguration() {
    }

    /**
     * Podem introduir tasques a realitzar per la inicialització de l'API REST.
     */
    @PostConstruct
    private void init() {
        log.info("Iniciant API REST INTERNA de EnviaFIB");
    }

}
