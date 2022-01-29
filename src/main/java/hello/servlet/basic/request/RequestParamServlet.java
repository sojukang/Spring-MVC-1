package hello.servlet.basic.request;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 1. parameter 전송 기능
 * http://localhost:8080/request-param?username=hello?age=20
 * 2. 동일한 parameter 전송 기능
 * http://localhost:8080/request-param?username=hello&username=kim&age=20
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[전체 parameter 조회] - start");
		/*
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			System.out.println(paramName + "=" + request.getParameter(paramName));
		}
		*/
		request.getParameterNames().asIterator()
				.forEachRemaining(paramName -> System.out.println(paramName + "=" + request.getParameter(paramName)));
		System.out.println("[전체 parameter 조회] - end");
		System.out.println();

		System.out.println("[단일 parameter 조회]");
		String username = request.getParameter("username");
		System.out.println("request.getParameter(username) = " + username);

		String age = request.getParameter("age");
		System.out.println("request.getParameter(age) = " + age);
		System.out.println();

		System.out.println("[이름이 같은 복수 parameter 조회]");
		System.out.println("request.getParameterValues(username)");
		String[] usernames = request.getParameterValues("username");
		String[] ages = request.getParameterValues("age");
		for (String name : usernames) {
			System.out.println("username=" + name);
		}

		for (String anAge : ages) {
			System.out.println("age=" + anAge);
		}

		response.getWriter().write("ok");
	}
}
