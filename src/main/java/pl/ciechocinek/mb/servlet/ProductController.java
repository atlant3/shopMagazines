package pl.ciechocinek.mb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import pl.ciechocinek.mb.dao.impl.UserDaoImpl;
import pl.ciechocinek.mb.domain.Product;
import pl.ciechocinek.mb.service.ProductService;
import pl.ciechocinek.mb.service.impl.ProductServiceImpl;

@WebServlet("/product")
public class ProductController extends HttpServlet {
	private static Logger LOG = Logger.getLogger(UserDaoImpl.class);
	ProductService productService = ProductServiceImpl.getProductService();
	private static final long serialVersionUID = 1L;

	// to create product
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.info("OK a");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String price = request.getParameter("price");

		Product product = new Product(name, description, getValidatedPrice(price));
		productService.create(product);
		LOG.info("OK b");
		response.setContentType("text");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");
	}

	private double getValidatedPrice(String price) {
		if (price == null || price.isEmpty()) {
			return 0;
		}
		return Double.parseDouble(price);
	}

	// to get product
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = request.getParameter("id");
		Product product = productService.read(Integer.parseInt(productId));

		request.setAttribute("product", product);
		request.getRequestDispatcher("singleProduct.jsp").forward(request, response);

	}

	// to update product
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		super.doPut(req, resp);
	}

	// to delete product
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		super.doDelete(req, resp);
	}

}