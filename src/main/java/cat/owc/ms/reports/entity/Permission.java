package cat.owc.ms.reports.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "permission")
public class Permission implements Serializable {

    public static final String ADMIN = "REPORTS_ADMIN";
    public static final String ADMIN_PREFIX = ADMIN + "_";
    public static final String POLL_ADMIN_PREFIX = "REPORTS_POLL_ADMIN_";
    public static final String REPORT_CAT_EDIT_PREFIX = "REPORTS_CAT_EDIT_";
    public static final String REPORT_CAT_READ_PREFIX = "REPORTS_CAT_READ_";

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "federation")
    private String federation;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "can_edit")
    private Boolean canEdit = false;

    //@ManyToOne(fetch = FetchType.LAZY)
   // @ToString.Exclude
    //@EqualsAndHashCode.Exclude
    //private ReportCategory reportCategory;

   // @Column(name = "deleted")
   // private Timestamp deleted;



    public static String getNameForCategory(Integer categoryId, boolean forEdit) {
        return (forEdit ? REPORT_CAT_EDIT_PREFIX : REPORT_CAT_READ_PREFIX) + categoryId;
    }
}
