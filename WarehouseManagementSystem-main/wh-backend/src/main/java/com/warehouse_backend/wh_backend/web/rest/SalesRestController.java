package com.warehouse_backend.wh_backend.web.rest;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warehouse_backend.wh_backend.services.SalesService;
import com.warehouse_backend.wh_backend.web.errors.BadRequestException;
import com.warehouse_backend.wh_backend.web.models.Sales;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/sales")
public class SalesRestController {
    
    private final SalesService salesService;   

    public SalesRestController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping
    public List<Sales> getSales() {
        return this.salesService.getAllSales();
    }

    @GetMapping("/{id}")
    public Sales getSaleById(@PathVariable("id") Long id) {
        return this.salesService.getSale(id);
    }

    @PostMapping
    public Sales addSale(@RequestBody Sales newSale) {
        return this.salesService.addOrUpdateSale(newSale);   
    }

    @PutMapping("/{id}")
    public Sales updateSale(@PathVariable("id") Long id, @RequestBody Sales updatedSale) {
        if(id != updatedSale.getSaleID()){
            throw new BadRequestException("MISMATCHED SALE IDs");
        }
        return this.salesService.addOrUpdateSale(updatedSale);
    }

    @DeleteMapping("/{id}")
    public void deleteSale(@PathVariable("id") Long id, @RequestBody Sales saleToDelete){
        if(id != saleToDelete.getSaleID()){
            throw new BadRequestException("MISMATCHED SALE IDs");
        }
        this.salesService.deleteSale(id);
    }
}
