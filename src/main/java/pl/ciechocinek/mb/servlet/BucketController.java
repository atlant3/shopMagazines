package pl.ciechocinek.mb.servlet;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import pl.ciechocinek.mb.dao.impl.UserDaoImpl;
import pl.ciechocinek.mb.domain.Bucket;
import pl.ciechocinek.mb.service.BucketService;
import pl.ciechocinek.mb.service.impl.BucketServiceImpl;

public class BucketController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private BucketService bucketService = BucketServiceImpl.getBucketService();
	private static Logger LOG = Logger.getLogger(UserDaoImpl.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productId = request.getParameter("productId");
		
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		Bucket bucket = new Bucket(userId, Integer.parseInt(productId), new Date());
		bucketService.create(bucket);
		LOG.info("Add a new bucket" + bucket);
		response.setContentType("text");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");
	}

}
