package top.ourck.api.servlet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/product/image")
public class ProductImageServlet extends HttpServlet {

	private static final long serialVersionUID = -3126034613603779408L;

	// TODO This is a sample...
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		File file = new File("/home/ourck/sample.jpg");
		FileInputStream fis = new FileInputStream(file);
		BufferedOutputStream os = new BufferedOutputStream(resp.getOutputStream());
		
		byte[] buf = new byte[256];
		while(fis.read(buf) != -1) {
			os.write(buf);
		}
		fis.close();
		os.close();
		resp.setHeader("Content-Type", "image/jpeg;charset=UTF-8");
	}

	
}
