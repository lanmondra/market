package cat.owc.ms.reports.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.security.core.context.SecurityContextHolder;

import cat.owc.ms.reports.ReportsClass;
import cat.owc.ms.reports.entity.ChangeControlable;
import cat.owc.owcauthenticationutils.dtos.JWTUser;


public class AbstractService extends ReportsClass {


    /**
     * Informa los campos de control de cambio (last_update y usuario que realiza la acción)
     * @param entity
     */
    protected void setChangeControlData(ChangeControlable entity) {
        String modificationUser = "";
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        try {
            modificationUser = ((JWTUser) (SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getUsername();
        }
        catch(Exception e) {
            log.error("Error al recuperar usuario del contexto");
            modificationUser = "no se pudo recuperar";
        }
        entity.setLastUpdate(now);
        entity.setLastActionUser(modificationUser);

    }


}
