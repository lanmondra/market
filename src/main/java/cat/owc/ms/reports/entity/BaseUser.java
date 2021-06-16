package cat.owc.ms.reports.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import lombok.Data;
import lombok.EqualsAndHashCode;



@Entity
@Data
@Table(name = "base_user")
public class BaseUser implements Serializable {
	
	private static final long serialVersionUID = 1L;

    public static final int REGISTER_STATUS_CREATED = 0;
    public static final int REGISTER_STATUS_VALIDATED = 1;
    public static final int REGISTER_STATUS_COMPLETED = 2;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "base_user_seq_gen")
    @SequenceGenerator(name = "base_user_seq_gen", sequenceName = "base_user_id_seq", allocationSize = 1)
    private Integer id;

    @NotNull
    @Column(name = "uuid", nullable = false)
    private String uuid = UUID.randomUUID().toString();

    @Size(max = 50)
    @Column(name = "email", length = 50)
    private String email;

    @Size(max = 15)
    @Column(name = "phone", length = 15)
    private String phone;

    @NotNull
    @Column(name = "register_status", nullable = false)
    private Integer registerStatus;

    @Size(max = 2)
    @NotNull
    @Column(name = "default_lang", nullable = false)
    private String defaultLang;

    @Column(name = "validation_code")
    private String validationCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    private BaseRole baseRole; 
	

}
