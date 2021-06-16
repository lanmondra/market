package cat.owc.ms.reports.aop;

import cat.owc.ms.reports.ContextInfo;
import cat.owc.ms.reports.OwcReportsApplication;
import cat.owc.ms.reports.config.ClientContextHolder;
import cat.owc.ms.reports.entity.enumeration.Portal;
import cat.owc.ms.reports.utils.ReportUtils;
import fcbq.common.FcbqClass;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class ClientContextAspect extends FcbqClass {

    @Autowired
    HttpServletRequest request;

    // Para setear el contexto antes de verificar los permisos de acceso del usuario ya que puede requerir la federacion
    @Pointcut("within(cat.owc.ms.reports.permissions.PermissionHandler)")
    public void checkPermissions() {}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) || " +
              "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
              "@annotation(org.springframework.web.bind.annotation.DeleteMapping) || " +
              "@annotation(org.springframework.web.bind.annotation.PatchMapping) || " +
              "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
              "@annotation(org.springframework.web.bind.annotation.PutMapping)" )
    public void endpoint() {}


    /**
     * Seteja la variable de federació per al request actual
     * @param joinPoint
     * @throws Throwable
     */
    @Before("endpoint() || checkPermissions()")
    public void setContext(JoinPoint joinPoint) throws Throwable {
        try {
            // Para entorno de test es necesario setear contexto cada vez (por como se ejecutan en junit)
            if (!ReportUtils.isTestProfile() && (ClientContextHolder.getCurrentContext() != null)) {
                return;
            }

            ContextInfo contextInfo = new ContextInfo();

            String federation = request.getHeader(OwcReportsApplication.FEDE_HEADER);
            String requestOrigin = request.getHeader(OwcReportsApplication.REQUEST_ORIGIN_HEADER);
            String lang = request.getHeader(OwcReportsApplication.LANGUAGE_HEADER);

            if(lang == null || lang.length() < 2) {
                lang = ContextInfo.DEFAULT_LANG;
            }

            contextInfo.setFederation(federation);
            contextInfo.setRequestOrigin(requestOrigin == null ? Portal.UNKNOWN.getName() : requestOrigin);
            contextInfo.setLanguage(lang.substring(0, 2));
            ClientContextHolder.setCurrentContext(contextInfo);
        }
        catch(Exception e) {
            log.error("Error in setting context. Path " + request.getRequestURL(), e);
        }
    }

    /**
     * Neteja la variable de federació per al request actual
     * @param joinPoint
     * @throws Throwable
     */
    @After("endpoint()")
    public void clearContext(JoinPoint joinPoint) throws Throwable {
        try {
                ClientContextHolder.clear();
        }
        catch(Exception e) {
            log.error("Error in clear context ", e);
        }
    }
    
}
