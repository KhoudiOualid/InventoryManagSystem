package com.khoudidev.inventorymanagementsystem.repository;

import com.khoudidev.inventorymanagementsystem.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
