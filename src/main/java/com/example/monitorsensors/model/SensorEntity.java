package com.example.monitorsensors.model;

import com.example.monitorsensors.model.enums.*;
import com.example.monitorsensors.validation.TypeEnumCheck;
import com.example.monitorsensors.validation.UnitEnumCheck;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sensors")
@TypeDef(
        name = "user_enum",
        typeClass = UserPostgresEnum.class
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SensorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(name = "model")
    @NotBlank(message = "Model is mandatory")
    private String model;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "range_id")
    @NotNull(message = "Range can't be null")
    private RangeEntity range;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "post_status_info")
    @Type( type = "user_enum" )
    @TypeEnumCheck(anyOf = {Types.pressure, Types.voltage, Types.temperature, Types.humidity})
    private Types type;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "post_status_info")
    @Type( type = "user_enum" )
    @UnitEnumCheck(anyOf = {Units.bar, Units.voltage, Units.degree, Units.percent})
    private Units unit;

    @Column(name = "location")
    private String location;

    @Column(name = "description")
    private String description;

}
