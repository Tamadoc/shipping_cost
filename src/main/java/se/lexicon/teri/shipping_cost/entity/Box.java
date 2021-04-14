package se.lexicon.teri.shipping_cost.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
//@Data =
//  @Getter
//  @Setter
//  @RequiredArgsConstructor
//  @ToString
//  @EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Box {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, updatable = false)
    private String id;

    @Column(nullable = false, length = 100)
    @NotNull(message =  "Name should not be null")
    @Size(min = 4, max = 20, message = "Name length should be between 4 and 20 characters")
    private String name;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Country should not be empty")
    private String country;

    @Column(nullable = false, length = 50)
    private String packageType;

    @Column(nullable = false)
    private double cost;

    @Column(nullable = false)
    @Min(value = 0, message = "Weight should not be less than 0")
    @Max(value = 1900, message = "Weight should not be greater than 1900")
    private double weight;

    @Column(nullable = false, length = 50)
    private String weightType;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
    private LocalDateTime createDate;

    @Column(nullable = false, columnDefinition = "TINYINT(1) default 1")
    private boolean status = true;


    public double calcShippingCost() {
        double feePerUnit;
        double countryMultiplier;

        if (weightType.equalsIgnoreCase("kg")) {
            feePerUnit = 1000;
        } else {
            feePerUnit = 2;
        }

        if (country.equalsIgnoreCase("sweden")) {
            countryMultiplier = 2.5;
        } else {
            countryMultiplier = 7;
        }

        return weight * feePerUnit * countryMultiplier;
    }
}
