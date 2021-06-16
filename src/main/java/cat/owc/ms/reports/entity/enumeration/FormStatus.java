package cat.owc.ms.reports.entity.enumeration;


public enum FormStatus implements IReportsEnum<Integer, String> {
    PENDING(0), STARTED(1), ENDED_MODIFIABLE(2), ENDED(3);


    private Integer code;

    FormStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return this.name();
    }
}

