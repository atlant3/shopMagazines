package pl.ciechocinek.mb.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pl.ciechocinek.mb.domain.Product;
import pl.ciechocinek.mb.service.ProductService;
import pl.ciechocinek.mb.service.impl.ProductServiceImpl;

public class Products extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ProductService productService = ProductServiceImpl.getProductService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Product> products = productService.readAll();
		String json = new Gson().toJson(products);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

}
