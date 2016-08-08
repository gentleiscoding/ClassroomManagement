package ser;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpCookie;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Admin;
import entity.Student;
import entity.Wuye;
import pojo.Apply;
import pojo.Examine;
import pojo.Feedback;
import pojo.Login;
import pojo.Search;
import pojo.Searchforwuye;
import pojo.Solve;

public class classroomser extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String state = (String) request.getParameter("state");
		System.out.println(state);
		PrintWriter out = response.getWriter();

		// 判断是否登陆的ser
		if (state.equals("judgelogin")) {
			String identity = (String) request.getParameter("identity");
			if (identity.equals("student")) {
				String sname = (String) request.getSession().getAttribute(
						"sname");
				if (sname == null) {
					out.write("no");
				} else {
					out.write(sname);
				}
			} else if (identity.equals("admin")) {
				String aname = (String) request.getSession().getAttribute(
						"aname");
				if (aname == null) {
					out.write("no");
					return;
				} else {
					out.write(aname);
				}
			} else if (identity.equals("wuye")) {
				String wname = (String) request.getSession().getAttribute(
						"wname");
				if (wname == null) {
					out.write("no");
				} else {
					out.write(wname);
				}
			}
		}

		// 登陆的ser
		if (state.equals("loginin")) {
			Login login = new Login();
			String identity = (String) request.getParameter("identity");
			if (identity.equals("student")) {
				// 判断账号密码是否正确
				long sid = Long.parseLong((String) request.getParameter("id"));
				String password = (String) request.getParameter("password");
				String loginresult = login.studentresult(sid, password);
				if (loginresult.contains("登陆成功")) {
					String[] stu = loginresult.split(",");
					Student student = new Student(Long.parseLong(stu[1]),
							stu[3], stu[2], Integer.parseInt(stu[4]), stu[5],
							stu[6]);
					request.getSession().setAttribute("sname",
							student.getSname());
					request.getSession().setAttribute("sid", student.getSid());
					request.getSession().setMaxInactiveInterval(900);
					out.write("student");
				} else {
					out.write(loginresult);
				}
			} else if (identity.equals("admin")) {
				int aid = Integer.parseInt((String) request.getParameter("id"));
				String password = (String) request.getParameter("password");
				String loginresult = login.adminresult(aid, password);
				if (loginresult.contains("登陆成功")) {
					String[] stu = loginresult.split(",");
					System.out.println(stu[3]);
					Admin admin = new Admin(Integer.parseInt(stu[1]), stu[2],
							stu[3], Integer.parseInt(stu[4]));
					System.out.println(admin.getAid() + admin.getAname());
					request.getSession()
							.setAttribute("aname", admin.getAname());
					request.getSession().setAttribute("aid", admin.getAid());
					request.getSession().setMaxInactiveInterval(900);
					out.write("admin");
				} else {
					out.write(loginresult);
				}
			} else if (identity.equals("wuye")) {
				int wid = Integer.parseInt((String) request.getParameter("id"));
				String password = (String) request.getParameter("password");
				String loginresult = login.wuyeresult(wid, password);
				if (loginresult.contains("登陆成功")) {
					String[] stu = loginresult.split(",");
					Wuye wuye = new Wuye(Integer.parseInt(stu[1]), stu[2],
							stu[3], Integer.parseInt(stu[4]));
					request.getSession().setAttribute("wname", wuye.getwname());
					request.getSession().setAttribute("wid", wuye.getwid());
					request.getSession().setMaxInactiveInterval(900);
					out.write("wuye");
				} else {
					out.write(loginresult);
				}
			}
		}

		// searchroom
		if (state.equals("searchroom")) {
			String week = (String) request.getParameter("week");
			String day = (String) request.getParameter("day");
			String time = (String) request.getParameter("time");
			System.out.println("here");
			Search search = new Search();

			String searchroom = search.searchroom(week, day, time);
			System.out.println(searchroom.toString());
			out.write(searchroom);

		}

		// searchtime
		if (state.equals("searchtime")) {
			String week = (String) request.getParameter("week");
			String day = (String) request.getParameter("day");
			String room = (String) request.getParameter("room");
			System.out.println("here");
			Search search = new Search();

			String searchroom = search.searchtime(room, week, day);
			System.out.println(searchroom.toString());
			out.write(searchroom);

		}

		// 申请教室的ser
		if (state.equals("applyroom")) {
			String sname = (String) request.getSession().getAttribute("sname");
			long sid = (Long) request.getSession().getAttribute("sid");
			if (sname == null) {
				return;
			}

			String week = (String) request.getParameter("week");
			String day = (String) request.getParameter("day");
			String room = (String) request.getParameter("room");
			String time = (String) request.getParameter("time");
			String duomeiti = (String) request.getParameter("duomeiti");
			String purpose = (String) request.getParameter("purpose");
			System.out.println("here");
			if (purpose.equals("")) {
				out.write("请填写用途");
				return;
			}
			if (duomeiti == null) {
				duomeiti = "否";
			}
			Apply apply = new Apply();
			String result = apply.applyroom(week, day, time, room, duomeiti,
					purpose, sid);
			out.write(result);

		}
		// -----------------------------------------------------------------以下为显示个人申请申请后台
		// 显示第一页的个人申请
		if (state.equals("showapply1")) {
			int page = 1;
			String sname = (String) request.getSession().getAttribute("sname");
			long sid = (Long) request.getSession().getAttribute("sid");
			if (sname == null) {
				return;
			}
			request.getSession().setAttribute("applypage", page);
			Apply apply = new Apply();
			String result = apply.showapply(1, sid);
			out.write(result);
		}

		// 显示第几页的个人申请
		if (state.equals("showapply")) {
			int page = Integer.parseInt((String) request.getParameter("page"));
			String sname = (String) request.getSession().getAttribute("sname");
			long sid = (Long) request.getSession().getAttribute("sid");
			if (sname == null) {
				return;
			}
			request.getSession().setAttribute("applypage", page);
			Apply apply = new Apply();
			String result = apply.showapply(page, sid);
			if (result.equals("")) {
				request.getSession().setAttribute("applypage", page + 1);
			}
			out.write(result);
		}
		// 显示后页的个人申请
		if (state.equals("newerapply")) {
			int page = (Integer) request.getSession().getAttribute("applypage");
			page = page + 1;
			String sname = (String) request.getSession().getAttribute("sname");
			long sid = (Long) request.getSession().getAttribute("sid");
			if (sname == null) {
				out.write("");
			}
			System.out.println("page" + page);
			request.getSession().setAttribute("applypage", page);
			Apply apply = new Apply();
			String result = apply.showapply(page, sid);
			if (result.equals("")) {
				request.getSession().setAttribute("applypage", page - 1);
			}
			out.write(result);
		}

		// 显示前页的个人申请
		if (state.equals("olderapply")) {
			int page = (Integer) request.getSession().getAttribute("applypage");
			page = page - 1;
			String sname = (String) request.getSession().getAttribute("sname");
			long sid = (Long) request.getSession().getAttribute("sid");
			if (sname == null) {
				out.write("");
			}
			request.getSession().setAttribute("applypage", page);
			System.out.println("page" + page);
			Apply apply = new Apply();
			String result = apply.showapply(page, sid);
			if (result.equals("")) {
				request.getSession().setAttribute("applypage", page + 1);
			}
			System.out.println(result + "哈哈");
			out.write(result);
		}
		// -----------------------------------------------------------------以上为显示个人申请后台
		// 反馈
		if (state.equals("feedback")) {
			String room = (String) request.getParameter("room");
			String problem = (String) request.getParameter("problem");
			if (room.equals("") || problem.equals("")) {
				out.write("请填写完整");
				return;
			}
			Feedback feedback = new Feedback();
			String result = feedback.sendfeedback(room, problem);
			System.out.println(result);
			out.write(result);
		}

		// -----------------------------------------------------------------以下为显示待审核后台
		// 显示第一页的待审核
		if (state.equals("showun1")) {
			int unpage = 1;
			request.getSession().setAttribute("unpage", unpage);
			Examine examine = new Examine();
			String result = examine.showun(unpage);
			out.write(result);
		}

		// 显示第几页的待审核
		if (state.equals("showun")) {
			int unpage = Integer.parseInt((String) request
					.getParameter("unpage"));
			request.getSession().setAttribute("unpage", unpage);
			Examine examine = new Examine();
			String result = examine.showun(unpage);
			out.write(result);
		}
		// 显示后页的待审核申请
		if (state.equals("newerun")) {
			int unpage = (Integer) request.getSession().getAttribute("unpage");
			unpage = unpage + 1;
			System.out.println("unpage" + unpage);
			request.getSession().setAttribute("unpage", unpage);
			Examine examine = new Examine();
			String result = examine.showun(unpage);
			if (result.equals("")) {
				request.getSession().setAttribute("unpage", unpage - 1);
			}
			out.write(result);
		}


		// -----------------------------------------------------------------以上为显示待审核后台

		// -----------------------------------------------------------------以下为显示已审核后台


		// 显示第几页的已审核
		if (state.equals("showed")) {
			int edpage = Integer.parseInt((String) request
					.getParameter("edpage"));
			request.getSession().setAttribute("edpage", edpage);
			Examine examine = new Examine();
			String result = examine.showed(edpage);
			out.write(result);
		}
		// 显示后页的已审核申请
		if (state.equals("newered")) {
			int edpage = (Integer) request.getSession().getAttribute("edpage");
			edpage = edpage + 1;
			System.out.println("edpage" + edpage);
			request.getSession().setAttribute("edpage", edpage);
			Examine examine = new Examine();
			String result = examine.showed(edpage);
			if (result.equals("")) {
				request.getSession().setAttribute("edpage", edpage - 1);
				out.write("");
				return;
			}
			out.write(result);
		}

		// 显示前页的已审核
		if (state.equals("oldered")) {
			int edpage = (Integer) request.getSession().getAttribute("edpage");
			edpage = edpage - 1;
			if (edpage == 0) {
				request.getSession().setAttribute("edpage", edpage + 1);
				out.write("");
				return;
			}
			System.out.println("edpage" + edpage);
			request.getSession().setAttribute("edpage", edpage);
			Examine examine = new Examine();
			String result = examine.showed(edpage);
			if (result.equals("")) {
				request.getSession().setAttribute("edpage", edpage + 1);
				out.write("");
				return;
			}
			out.write(result);
		}
		// -----------------------------------------------------------------以上为显示已审核后台

		// 通过的后台
		if (state.equals("pass")) {
			int applyid = Integer.parseInt((String) request
					.getParameter("applyid"));
			Examine examine = new Examine();
			String result = "";
			result = examine.pass(applyid);
			out.write(result);
		}

		// 不通过的后台
		if (state.equals("fail")) {
			int applyid = Integer.parseInt((String) request
					.getParameter("applyid"));
			Examine examine = new Examine();
			String result = "";
			result = examine.fail(applyid);
			out.write(result);
		}

		// 物业查看
		if (state.equals("searchforwuye")) {
			String week = (String) request.getParameter("week");
			String day = (String) request.getParameter("day");
			int page = 1;
			request.getSession().setAttribute("wuyesearchpage", page);
			request.getSession().setAttribute("week", week);
			request.getSession().setAttribute("day", day);
			Searchforwuye searchforwuye = new Searchforwuye();
			String result = searchforwuye.search(week, day, page);
			out.write(result);
		}

		// 物业查看后一夜
		if (state.equals("wuyesearchnewer")) {
			int page = (Integer) request.getSession().getAttribute(
					"wuyesearchpage");
			String week = (String) request.getSession().getAttribute("week");
			String day = (String) request.getSession().getAttribute("day");
			page = page + 1;
			request.getSession().setAttribute("wuyesearchpage", page);
			Searchforwuye searchforwuye = new Searchforwuye();
			String result = searchforwuye.search(week, day, page);
			if (result.equals("")) {
				request.getSession().setAttribute("wuyesearchpage", page - 1);
			}
			out.write(result);
		}

		// 物业查看前一页
		if (state.equals("wuyesearcholder")) {
			int page = (Integer) request.getSession().getAttribute(
					"wuyesearchpage");
			String week = (String) request.getSession().getAttribute("week");
			String day = (String) request.getSession().getAttribute("day");
			page = page - 1;
			if (page == 0) {
				request.getSession().setAttribute("wuyesearchpage", page + 1);
				out.write("");
				return;
			}
			request.getSession().setAttribute("wuyesearchpage", page);
			Searchforwuye searchforwuye = new Searchforwuye();
			String result = searchforwuye.search(week, day, page);
			if (result.equals("")) {
				request.getSession().setAttribute("wuyesearchpage", page + 1);
			}
			out.write(result);
		}

		// 查看反馈un第一页
		if (state.equals("showfeedbackun1")) {
			int page = 1;
			request.getSession().setAttribute("feedbackunpage", page);
			Solve solve = new Solve();
			String result = solve.showun(page);
			out.write(result);
		}

		// 查看反馈ed第一页
		if (state.equals("edfeedbackshow1")) {
			int page = 1;
			request.getSession().setAttribute("feedbackedpage", page);
			Solve solve = new Solve();
			String result = solve.showed(page);
			System.out.println(result);
			out.write(result);
		}

		// un反馈查看后一夜
		if (state.equals("newerfeedbackun")) {
			int page = (Integer) request.getSession().getAttribute(
					"feedbackedpage");
			page = page + 1;
			request.getSession().setAttribute("feedbackunpage", page);
			Solve solve = new Solve();
			String result = solve.showun(page);
			if (result.equals("")) {
				request.getSession().setAttribute("feedbackunpage", page - 1);
			}
			out.write(result);
		}

		// un反馈查看前一页
		if (state.equals("olderfeedbackun")) {
			int page = (Integer) request.getSession().getAttribute(
					"feedbackedpage");
			page = page - 1;
			if (page == 0) {
				request.getSession().setAttribute("feedbackunpage", page + 1);
				out.write("");
				return;
			}
			request.getSession().setAttribute("feedbackunpage", page);
			Solve solve = new Solve();
			String result = solve.showun(page);
			if (result.equals("")) {
				request.getSession().setAttribute("feedbackeunpage", page + 1);
			}
			out.write(result);
		}

		// ed反馈查看后一夜
		if (state.equals("newerfeedbacked")) {
			int page = (Integer) request.getSession().getAttribute(
					"feedbackedpage");
			page = page + 1;
			request.getSession().setAttribute("feedbackedpage", page);
			Solve solve = new Solve();
			String result = solve.showed(page);
			if (result.equals("")) {
				request.getSession().setAttribute("feedbackedpage", page - 1);
			}
			out.write(result);
		}

		// ed反馈查看前一页
		if (state.equals("olderfeedbacked")) {
			int page = (Integer) request.getSession().getAttribute(
					"feedbackedpage");
			page = page - 1;
			if (page == 0) {
				request.getSession().setAttribute("feedbackedpage", page + 1);
				out.write("");
				return;
			}
			request.getSession().setAttribute("feedbackedpage", page);
			Solve solve = new Solve();
			String result = solve.showed(page);
			if (result.equals("")) {
				request.getSession().setAttribute("feedbackedpage", page + 1);
			}
			out.write(result);
		}

		// solve故障
		if (state.equals("solve")) {
			String wname = (String) request.getSession().getAttribute("wname");
			int wid = (Integer) request.getSession().getAttribute("wid");
			int fid = Integer.parseInt((String) request.getParameter("fid"));
			if (null == wname) {
				return;
			}
			String solver = wname;
			Solve solve = new Solve();
			String result = solve.solve(fid, solver);
			out.write(result);
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String state = (String) request.getParameter("state");
		System.out.println(state);
		PrintWriter out = response.getWriter();

		// 获取个人申请页数
		if (state.equals("showapplypagenumber")) {
			String sname = (String) request.getSession().getAttribute("sname");
			long sid = (Long) request.getSession().getAttribute("sid");
			if (sname == null) {
				return;
			}
			Apply apply = new Apply();
			String result = apply.pagenumber(sid);
			out.write(result);
		}

		// 获取待申请页数
		if (state.equals("showunpagenumber")) {
			Examine examine = new Examine();
			String result = examine.unpagenumber();
			System.out.println("未审核分页" + result);
			out.write(result);
		}

		// 获取已审核页数
		if (state.equals("showedpagenumber")) {
			Examine examine = new Examine();
			String result = examine.edpagenumber();
			System.out.println("已审核分页" + result);
			out.write(result);
		}
		if (state.equals("loginout")) {
			String identity = (String) request.getParameter("identity");
			Enumeration em = request.getSession().getAttributeNames();
			while (em.hasMoreElements()) {
				request.getSession().removeAttribute(
						em.nextElement().toString());
				System.out.println("aaa");
				response.sendRedirect("/classroommanage/html/home.html");
				return;
			}
		}
	}
}
