package controller;


import entity.Catalog;
import service.CatalogService;

import java.util.List;
import java.util.Scanner;

public class CatalogMenu {
    CatalogService catalogService;
    Catalog catalog;
    Scanner sc = new Scanner(System.in);
    private long id;
    private String name;

    public CatalogMenu(CatalogService catalogService, Catalog catalog) {
        this.catalogService = catalogService;
        this.catalog = catalog;
    }

    public Long showAddCatalog() {
        System.out.println("Enter catalog name:");
        name = sc.next();
        catalogService.createCatalog(new Catalog(id, name));
        return id;
    }

    public void showEditCatalog() {
        System.out.println("Enter catalog id:");
        id = sc.nextLong();
        System.out.println("Enter catalog name:");
        name = sc.next();
        catalogService.editCatalog(id, new Catalog(id, name));
    }

    public Catalog getItem(long id) {
        return catalogService.getItem(id);
    }

    public List<Catalog> getAll() {
        return catalogService.getAll();
    }

    public void showDeleteCatalog(long id) {
        catalog.setId(id);
        catalogService.deleteCatalog(catalog);
    }
}
