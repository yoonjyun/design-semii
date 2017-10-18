package com.fastakeout.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fastakeout.model.MemberVO;
import com.fastakeout.model.MenuDetailVO;
import com.fastakeout.model.StoreDAO;
import com.fastakeout.model.StoreVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UploadController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("UploadController 도달: ");
		String workspacePath = "C:\\Users\\kosta\\git\\FASTakeout\\FASTakeout\\WebContent\\upload";
		// String savePath=request.getServletContext().getRealPath("brand");
		System.out.println(workspacePath);
		// System.out.println(savePath);
		int sizeLimit = 1024 * 1024 * 10;
		MultipartRequest multi = new MultipartRequest(request, workspacePath, sizeLimit, "utf-8",
				new DefaultFileRenamePolicy());
		System.out.println("multipart 객체 생성: ");
		String command = multi.getParameter("command");

		HttpSession session = request.getSession(false);
		MemberVO member = (MemberVO) session.getAttribute("mvo");

		if (session != null) {
			System.out.println("member 확인: -------" + member);
			if (command.equals("registerStore")) {
				StoreVO store = new StoreVO();
				ArrayList<String> categoryList = new ArrayList<String>();
				System.out.println("memberId: " + member.getMemberId());
				store.setId(member.getMemberId());
				store.setStorename(multi.getParameter("storeName"));
				store.setAddress(multi.getParameter("storeAddress"));
				store.setTel(multi.getParameter("tel"));
				store.setOpenDay("openday");

				String[] ca = multi.getParameterValues("category");
				for (int i = 0; i < ca.length; i++) {
					categoryList.add(ca[i]);
				}
				store.setCategory(categoryList);

				// 전송받은 데이터가 파일일 경우 getFilesystemName()으로 파일 이름을 받아올 수 있다.
				String imgName = multi.getFilesystemName("img");
				System.out.println("UploadController imgName: " + imgName);
				// share2.jpg
				String saveImgPath = "/upload/" + imgName;
				System.out.println("UploadController saveImgPath: " + saveImgPath);
				store.setStoreImgUrl(saveImgPath);

				// 가맹점 등록
				System.out.println("db insert전 store:" + store);
				StoreDAO.getInstance().registerStore(store);
				System.out.println("db insert후 store:" + store);
				command = "registerStoreOk";

			} else if (multi.getParameter("command").equals("menuRegister")) {
				System.out.println("uploadController 진입4");
				ArrayList<MenuDetailVO> list = makeList(multi);
				System.out.println(list.toString());
				RegisterInfo file = new RegisterInfo();
				file.insertInfo(multi, member.getMemberId(), list);
				command = "registerMenuOk";
			}
		}
		return "redirect:DispatcherServlet?command=" + command;
	}

	private ArrayList<MenuDetailVO> makeList(MultipartRequest multi) {
		ArrayList<MenuDetailVO> list = new ArrayList<MenuDetailVO>();
		String tbNumber = multi.getParameter("tbodyNumber");
		System.out.println("uploadController 진입5");

		if (tbNumber.equals("tb1")) {
			System.out.println("uploadController 진입6 tb1");
			int basicPrice = Integer.parseInt(multi.getParameter("price12"));
			MenuDetailVO mdvo = new MenuDetailVO();
			mdvo.setSize("basic");
			mdvo.setHotIce("basic");
			mdvo.setMenuPrice(basicPrice);
			;
			list.add(mdvo);

		} else if (tbNumber.equals("tb2")) {
			System.out.println("uploadController 진입6 tb2 ");
			int hotPrice = Integer.parseInt(multi.getParameter("price11"));
			int icePrice = Integer.parseInt(multi.getParameter("price10"));
			MenuDetailVO mdvo = new MenuDetailVO();
			MenuDetailVO mdvo2 = new MenuDetailVO();
			mdvo.setSize("basic");
			mdvo.setHotIce("hot");
			mdvo.setMenuPrice(hotPrice);
			list.add(mdvo);

			mdvo2.setSize("basic");
			mdvo2.setHotIce("ice");
			mdvo2.setMenuPrice(icePrice);
			list.add(mdvo2);

		} else if (tbNumber.equals("tb3")) {
			System.out.println("uploadController 진입6 tb3");
			int smallPrice = Integer.parseInt(multi.getParameter("price7"));
			int mediumPrice = Integer.parseInt(multi.getParameter("price8"));
			int largePrice = Integer.parseInt(multi.getParameter("price9"));
			System.out.println(smallPrice + " " + mediumPrice + " " + largePrice);
			MenuDetailVO mdvo = new MenuDetailVO();
			MenuDetailVO mdvo2 = new MenuDetailVO();
			MenuDetailVO mdvo3 = new MenuDetailVO();
			mdvo.setSize("small");
			mdvo.setHotIce("basic");
			mdvo.setMenuPrice(smallPrice);
			list.add(mdvo);

			mdvo2.setSize("medium");
			mdvo2.setHotIce("basic");
			mdvo2.setMenuPrice(mediumPrice);
			list.add(mdvo2);

			mdvo3.setSize("large");
			mdvo3.setHotIce("basic");
			mdvo3.setMenuPrice(largePrice);
			list.add(mdvo3);

		} else {
			System.out.println("uploadController 진입6 tb4");

			int hsPrice = Integer.parseInt(multi.getParameter("price1"));
			int hmPrice = Integer.parseInt(multi.getParameter("price2"));
			int hlPrice = Integer.parseInt(multi.getParameter("price3"));
			int isPrice = Integer.parseInt(multi.getParameter("price4"));
			int imPrice = Integer.parseInt(multi.getParameter("price5"));
			int ilPrice = Integer.parseInt(multi.getParameter("price6"));
			MenuDetailVO mdvo = new MenuDetailVO();
			MenuDetailVO mdvo2 = new MenuDetailVO();
			MenuDetailVO mdvo3 = new MenuDetailVO();
			MenuDetailVO mdvo4 = new MenuDetailVO();
			MenuDetailVO mdvo5 = new MenuDetailVO();
			MenuDetailVO mdvo6 = new MenuDetailVO();

			System.out.println("여섯개");
			mdvo.setSize("small");
			mdvo.setHotIce("hot");
			mdvo.setMenuPrice(hsPrice);
			list.add(mdvo);

			mdvo2.setSize("medium");
			mdvo2.setHotIce("hot");
			mdvo2.setMenuPrice(hmPrice);
			list.add(mdvo2);

			mdvo3.setSize("large");
			mdvo3.setHotIce("hot");
			mdvo3.setMenuPrice(hlPrice);
			list.add(mdvo3);

			mdvo4.setSize("small");
			mdvo4.setHotIce("ice");
			mdvo4.setMenuPrice(isPrice);
			list.add(mdvo4);

			mdvo5.setSize("medium");
			mdvo5.setHotIce("ice");
			mdvo5.setMenuPrice(imPrice);
			list.add(mdvo5);

			mdvo6.setSize("large");
			mdvo6.setHotIce("ice");
			mdvo6.setMenuPrice(ilPrice);
			list.add(mdvo6);

		}
		return list;
	}
}
