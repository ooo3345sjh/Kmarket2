package kr.co.kmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "km_member_terms")
public class TermsEntity {
    @Id
    private int tno;
    private String terms;
    private String privacy;
    private String location;
    private String finance;
    private String tax;
}
