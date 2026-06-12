/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tes;

import model.CategoriaGama;

/**
 *
 * @author camper
 */
public class CelularTes {
    private int id;
    private String marca;
    private String modelo;
    private String sistemaOperativo;
    private CategoriaGama gama; // Usamos el enum
    private double precio;
    private int stock;
    private int stockMinimo;

    public CelularTes() {
    }

    public CelularTes(int id, String marca, String modelo, String sistemaOperativo, CategoriaGama gama, double precio,
            int stock, int stockMinimo) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.sistemaOperativo = sistemaOperativo;
        this.gama = gama;
        this.precio = precio;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public CategoriaGama getGama() {
        return gama;
    }

    public void setGama(CategoriaGama gama) {
        this.gama = gama;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    // Método para verificar si el stock está al mínimo o por debajo
    public boolean esStockBajo() {
        return this.stock <= this.stockMinimo;

    }

    @Override
    public String toString() {
        return "Celular [" + id + "] " + marca + " " + modelo + " (" + gama + ") - Price: $" + precio + " - Stock: "
                + stock + "stockMinimo=" + stockMinimo;
    }

}
