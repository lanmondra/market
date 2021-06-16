package cat.owc.ms.reports.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import lombok.Data;

@Entity
@Data
@Table(name = "base_role")
public class BaseRole implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String ROLE_ADMIN = "AFORITY_ADMIN";
    public static final String ROLE_FRAMEWORK_CLIENT = "AFORITY_FRAMEWORK_CLIENT";
    public static final String ROLE_SPECIFIC_CLIENT = "AFORITY_SPECIFIC_CLIENT";
    public static final String ROLE_APP_USER = "AFORITY_APP_USER";
    public static final String ROLE_APP_USER_ADMIN = "AFORITY_APP_USER_ADMIN";


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "base_role_seq_gen")
    @SequenceGenerator(name = "base_role_seq_gen", sequenceName = "base_role_id_seq", allocationSize = 1)
    private Integer id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;
}