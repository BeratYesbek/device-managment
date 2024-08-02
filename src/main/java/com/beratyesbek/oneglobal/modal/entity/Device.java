package com.beratyesbek.oneglobal.modal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;


import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SQLRestriction(value = "DELETED is false")
@SQLDelete(sql = "UPDATE DEVICE SET DELETED = TRUE WHERE ID=? and VERSION=?")
public class Device extends BaseEntity {

    private String deviceName;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID", referencedColumnName = "ID", nullable = false)
    private Brand brand;

}
