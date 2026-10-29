package com.warehouse_backend.wh_backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.warehouse_backend.wh_backend.entities.SalesEntity;
import com.warehouse_backend.wh_backend.repositories.SalesRepository;
import com.warehouse_backend.wh_backend.web.errors.NotFoundException;
import com.warehouse_backend.wh_backend.web.models.Sales;

@Service
public class SalesService {
    private final SalesRepository salesRepository;

    public SalesService(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public SalesEntity translateWebToDb(Sales sale) {
        SalesEntity entity = new SalesEntity();
        entity.setCustomerID(sale.getCustomerID());
        entity.setEmployeeID(sale.getEmployeeID());
        entity.setVehicleID(sale.getVehicleID());
        entity.setSaleID(sale.getSaleID());
        entity.setSaleAmount(sale.getSaleAmount());
        entity.setSaleTime(sale.getSaleTime());
        return entity;
    }

    public Sales translateDbToWeb(SalesEntity salesEntity) {
        return new Sales(salesEntity.getSaleID(), salesEntity.getEmployeeID(), salesEntity.getSaleID(),
            salesEntity.getSaleAmount(), salesEntity.getCustomerID(), salesEntity.getSaleTime());
    }

    public List<Sales> getAllSales() {
        Iterable<SalesEntity> salesEntities = this.salesRepository.findAll();
        List<Sales> sales = new ArrayList<>();

        salesEntities.forEach(salesEntity -> {
            sales.add(translateDbToWeb(salesEntity));
        });

        return sales;
    }

    public Sales addOrUpdateSale(Sales newSale) {
        SalesEntity entity = translateWebToDb(newSale);
        entity = salesRepository.save(entity);
        return translateDbToWeb(entity);
    }

    public void deleteSale(Long id) {
        this.salesRepository.deleteById(id);
    }

    public Sales getSale(Long id) {
        Optional<SalesEntity> optional = this.salesRepository.findById(id);
        if (optional.isEmpty()){
            throw new NotFoundException("NO SALE WITH THIS ID");
        }
        return this.translateDbToWeb(optional.get());
    }
}
