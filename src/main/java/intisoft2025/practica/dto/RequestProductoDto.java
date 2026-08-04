package intisoft2025.practica.dto;

import intisoft2025.practica.model.Producto;

public class RequestProductoDto {
    private Long id;
    private String nombre;
    private Integer precio;
    private Integer cantidad;
    private String nombre_empresa;

    public RequestProductoDto(){
    }

    public RequestProductoDto(Producto producto){
        setId(producto.getId());
        setNombre(producto.getNombre());
        setPrecio(producto.getPrecio());
        setCantidad(producto.getCantidad());
        setNombre_empresa(producto.getEmpresa().getNombre());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }
}
