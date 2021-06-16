package cat.owc.ms.reports;

import lombok.Data;

@Data
public class ContextInfo {
    public static final String ORIGIN_REFEREE = "REFEREE";
    public static final String ORIGIN_FEDERATED = "FEDERATED";
    public static final String ORIGIN_CLUB = "CLUB";
    public static final String ORIGIN_OTHERS = "OTHERS";

    public static final String DEFAULT_LANG = "es";

    private String federation;
    private String requestOrigin;
    private String language;
}
