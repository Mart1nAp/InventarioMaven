package com.alura.controller;

import com.alura.factory.ConnectionFactory;
import com.alura.modelo.Categoria;
import com.alura.modelo.Producto;
import com.alura.dao.ProductoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductoController {

	ProductoDAO productoDAO ;
	public ProductoController() {
		var factory = new ConnectionFactory();
		this.productoDAO  = new ProductoDAO(factory.recuperaConexion());
	}
	public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) {
		return productoDAO.modificar(nombre, descripcion, cantidad, id);
	}
	public int eliminar(Integer id) {
		return productoDAO.eliminar(id);
	}
	public List<Producto> listar() {
		return productoDAO.listar();
	}

	public List<Producto> listar(Categoria categoria) {
		return ProductoDAO.listar(categoria.getId());

	}
    public void guardar(Producto producto,Integer categoriaId) {
		producto.setCategoriaId(categoriaId);
		productoDAO.guardar(producto);
	}
}
