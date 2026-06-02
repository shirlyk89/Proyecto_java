package model;

public class Celular {
   // Atributos privados (Encapsulamiento)
    private int id;
    private String marca;
    private String modelo;
    private String sistemaOperativo;
    private CategoriaGama gama;     // Usamos el enum
    private double precio;
    private int stock;


    public Celular() {}

    // Constructor
    public Celular(int id, String marca, String modelo, String sistemaOperativo, CategoriaGama gama, double precio, int stock) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.sistemaOperativo = sistemaOperativo;
        this.gama = gama;
        this.precio = precio;
        this.stock = stock;
    }

    // Métodos Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getSistemaOperativo() { return sistemaOperativo; }
    public void setSistemaOperativo(String sistemaOperativo) { this.sistemaOperativo = sistemaOperativo; }

    public CategoriaGama getGama() { return gama; }
    public void setGama(CategoriaGama gama) { this.gama = gama; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    // ToString
    @Override
    public String toString() {
        return "Celular [" + id + "] " + marca + " " + modelo + " (" + gama + ") - Price: $" + precio + " - Stock: " + stock;
    }
}
