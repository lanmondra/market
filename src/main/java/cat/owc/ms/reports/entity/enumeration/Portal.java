package cat.owc.ms.reports.entity.enumeration;


public enum Portal implements IReportsEnum<Integer, String> {
    REFEREE(0), FEDERATED(1), CLUB(2), UNKNOWN(3);


    private Integer code;

    Portal(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return this.name();
    }
}

