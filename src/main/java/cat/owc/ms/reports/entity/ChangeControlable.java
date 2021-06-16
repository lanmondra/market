package cat.owc.ms.reports.entity;

import java.sql.Timestamp;

public interface ChangeControlable {
    Integer getId();
    Timestamp getLastUpdate();
    void setLastUpdate(Timestamp lastUpdate);
    Timestamp getDeleted();
    void setDeleted(Timestamp deleteTime);
    String getLastActionUser();
    void setLastActionUser(String lastActionUser);
}
