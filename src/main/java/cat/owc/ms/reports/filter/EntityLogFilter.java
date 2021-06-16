package cat.owc.ms.reports.filter;

import cat.owc.ms.reports.OwcReportsApplication;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
class EntityLogFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
    	String federation = null;
    	
    	try {
    		federation = request.getHeader(OwcReportsApplication.FEDE_HEADER);
    	}
    	catch(Exception e) {
			federation = null;
    	}

    	if (federation != null) {
    		MDC.put("federation", federation);
    	}
    	
        try {
            chain.doFilter(request, response);
        } finally {
            MDC.remove("federation");
        }
    }

}
