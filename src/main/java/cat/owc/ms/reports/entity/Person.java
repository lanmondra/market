package cat.owc.ms.reports.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Table(name = "person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int ID_NUM_TYPE_NIF = 0;
    public static final int ID_NUM_TYPE_NIE = 1;
    public static final int ID_NUM_TYPE_PASSPORT = 2;
    

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq_gen")
    @SequenceGenerator(name = "person_seq_gen", sequenceName = "person_id_seq", allocationSize = 1)
    private Integer id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotNull
    @Column(name = "identification_number_type", nullable = false)
    private Integer identificationNumberType;

    @NotNull
    @Size(max = 20)
    @Column(name = "identification_number", length = 20, nullable = false)
    private String identificationNumber;

    @NotNull
    @Size(max = 100)
    @Column(name = "address", length = 100, nullable = false)
    private String address;

    @Column(name = "phone")
    private String phone;

    @NotNull
    @Size(max = 15)
    @Column(name = "zip_code", length = 15, nullable = false)
    private String zipCode;

    @OneToOne
    @JoinColumn(unique = true)
    private BaseUser baseUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Town town;


    @NotNull
    @Column(name = "created", nullable = false)
    private LocalDateTime created = LocalDateTime.now();;

    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "deleted")
    private LocalDateTime deleted;

	
    
    
    


}
