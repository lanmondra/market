package cat.owc.ms.reports.entity.enumeration;


public enum ReportTemplateStatus implements IReportsEnum<Integer, String> {
    EDITABLE(0), NOT_EDITABLE(1);

     private Integer code;

    ReportTemplateStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return this.name();
    }
}
