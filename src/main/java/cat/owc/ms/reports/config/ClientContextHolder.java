package cat.owc.ms.reports.config;

import cat.owc.ms.reports.ContextInfo;

public class ClientContextHolder {
	
	private static ThreadLocal<Object> context = new ThreadLocal<>();

	/**
	 * Seteja el context (federació) per a la request actual (ThreadLocal)
	 * @param tenant
	 */
    public static void setCurrentContext(Object tenant) {
        context.set(tenant);
    }

    /**
     * Recupera el context (federació) per a la request actual (ThreadLocal)
     * @return
     */
    public static Object getCurrentContext() {
        return context.get();
    }

    /**
     * Neteja el context (federació) per a la request actual (ThreadLocal)
     */
    public static void clear() { 
    	context.remove(); 
	}


    /**
     * Retorna la federació del context
     * @return
     */
	public static String getFederation() {
        ContextInfo contextInfo = (ContextInfo)context.get();
        return contextInfo.getFederation();
    }


    /**
     * Retorna l'origen de la petició del context
     * @return
     */
    public static String getRequestOrigin() {
        ContextInfo contextInfo = (ContextInfo)context.get();
        return contextInfo.getRequestOrigin() == null ? null : contextInfo.getRequestOrigin().toUpperCase();
    }


    /**
     * Retorna el idioma indicado en el header de la petición
     * @return
     */
    public static String getLanguage() {
        ContextInfo contextInfo = (ContextInfo)context.get();
        return contextInfo.getLanguage();
    }
    
}
