package cat.owc.ms.reports.controllers;

import cat.owc.ms.reports.ReportsClass;
import cat.owc.ms.reports.utils.ReportUtils;
import cat.owc.owcauthenticationutils.dtos.JWTUser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

import static cat.owc.ms.reports.controllers.IAbstractControllerErrorCode.NO_USER_UUID;

@CrossOrigin
public abstract class AbstractController extends ReportsClass {

    /**
     * Recupera el uuid del usuario y valida que esté informado. En caso de no estarlo, notifica error y finaliza proceso
     * @param user
     * @return
     */
    protected String getUserUuid(JWTUser user) {
        String uuid = ReportUtils.getUuid(user);
        if (uuid == null) {
            notifyError(NO_USER_UUID, HttpStatus.BAD_REQUEST);
        }

        return uuid;
    }


}
