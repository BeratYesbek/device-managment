package com.beratyesbek.oneglobal.modal.entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.SQLDelete;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE DEVICE SET DELETED = TRUE WHERE ID=? and VERSION=?")
public class Brand extends BaseEntity{

        @Column(name = "name", nullable = false)
        private String name;

        @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
        private List<Device> devices;
}