package com.khoudidev.inventorymanagementsystem.service;

import com.khoudidev.inventorymanagementsystem.dto.CategoryDTO;
import com.khoudidev.inventorymanagementsystem.dto.Response;
import com.khoudidev.inventorymanagementsystem.dto.SupplierDTO;

public interface SupplierService {

    Response addSupplier(SupplierDTO supplierDTO);

    Response updateSupplier(Long id, SupplierDTO supplierDTO);

    Response getAllSuppliers();

    Response getSupplierById(Long id );



    Response deleteSupplier(Long id );
}
