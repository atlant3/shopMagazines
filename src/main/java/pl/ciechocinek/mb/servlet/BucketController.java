package pl.ciechocinek.mb.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import pl.ciechocinek.mb.dao.impl.UserDaoImpl;
import pl.ciechocinek.mb.domain.Bucket;
import pl.ciechocinek.mb.domain.Product;
import pl.ciechocinek.mb.domain.User;
import pl.ciechocinek.mb.service.BucketService;
import pl.ciechocinek.mb.service.ProductService;
import pl.ciechocinek.mb.service.UserService;
import pl.ciechocinek.mb.service.impl.BucketServiceImpl;
import pl.ciechocinek.mb.service.impl.ProductServiceImpl;
import pl.ciechocinek.mb.service.impl.UserServiceImpl;

@WebServlet("/bucket")
public class BucketController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private BucketService bucketService = BucketServiceImpl.getBucketService();
	private ProductService productService = ProductServiceImpl.getProductService();
	private UserService userService = UserServiceImpl.getUserService();
	private static Logger LOG = Logger.getLogger(UserDaoImpl.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = request.getParameter("productId");

		Product product = productService.read(Integer.parseInt(productId));

		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		User user = userService.read(userId);

		Bucket bucket = new Bucket();
		bucket.setId(UUID.randomUUID().toString());
		bucket.setProduct(product);
		bucket.setUser(user);
		bucket.setPurchaseDate(new Date());
		bucketService.create(bucket);

		LOG.info("Add a new bucket" + bucket);
		response.setContentType("text");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bucketId = request.getParameter("bucketId");
		bucketService.delete(Integer.parseInt(bucketId));
		response.setContentType("text");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");
	}

}
