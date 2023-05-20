package com.alura.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alura.modelo.Categoria;
import com.alura.modelo.Producto;

public class CategoriaDAO {

	private Connection con;

	public CategoriaDAO(Connection con) {
		this.con = con;
	}

	public List<Categoria> listar() {
		List<Categoria> resultado = new ArrayList<>();

		try {
			var querySelect = "SELECT id, nombre FROM categoria";
			System.out.println(querySelect);
			final PreparedStatement statement = con.prepareStatement(
					querySelect);
			try (statement) {
				final ResultSet resultSet = statement.executeQuery();
				try (resultSet) {
					while (resultSet.next()) {
						var categoria = new Categoria(resultSet.getInt("id"),
								resultSet.getString("nombre"));

						resultado.add(categoria);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}

	public List<Categoria> listarProducto() {
		List<Categoria> resultado = new ArrayList<>();

		try {
			var querySelect = "SELECT c.id, c.nombre, p.id, p.nombre, p.cantidad FROM categoria as c"
					+ " INNER JOIN producto as p ON c.id = p.categoria_id";
			System.out.println(querySelect);

			final PreparedStatement statement = con.prepareStatement(
					querySelect);

			try (statement) {
				final ResultSet resultSet = statement.executeQuery();
				try (resultSet) {
					while (resultSet.next()) {
						int categoriaId = resultSet.getInt("c.id");
						String categoriaNombre = resultSet.getString("c.nombre");
						var categoria = resultado
								.stream()
								.filter(cat -> cat.getId().equals(categoriaId))
								.findAny().orElseGet(() -> {
									Categoria cat = new Categoria(categoriaId, categoriaNombre);
									resultado.add(cat);

									return cat;
								});

						Producto producto = new Producto(resultSet.getInt("p.id"),
								resultSet.getString("p.nombre"),
								resultSet.getInt("p.cantidad"));

						categoria.agregar(producto);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}
}

