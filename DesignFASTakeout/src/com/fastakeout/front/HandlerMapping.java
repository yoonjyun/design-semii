package com.fastakeout.front;
import com.fastakeout.controller.*;

/**
 * 컨트롤러 객체 생성을 전담하는 클래스 : simple factory
 */
public class HandlerMapping {
	private static HandlerMapping instance = new HandlerMapping();

	private HandlerMapping() {
	}

	public static HandlerMapping getInstance() {
		return instance;
	}

	public Controller create(String command) {
		Controller controller = null;
		// 만든사람 : 진호
		if(command.equals("login")) {
			System.out.println("HandlerMapping - LoginController, command : "+command);
			controller = new LoginController();
		}else if(command.equals("intro")) {
			System.out.println("HandlerMapping - IntroController, command : "+command);
			controller = new IntroController();
		}else if(command.equals("loginForm")) {
			System.out.println("HandlerMapping - LoginFormController, command : "+command);
			controller = new LoginFormController();
		}else if(command.equals("logout")) {
			System.out.println("HandlerMapping - LogoutController, command : "+command);
			controller = new LogoutController();
		}else if(command.equals("registerIndex")) {
			System.out.println("HandlerMapping - RegisterMemberIndexController, command : "+command);
			controller = new RegisterMemberIndexController();
		}else if(command.equals("registerMemberForm")) {
			System.out.println("HandlerMapping - RegisterMemberFormController, command : "+command);
			controller = new RegisterMemberFormController();
		}else if(command.equals("registerMember")) {
			System.out.println("HandlerMapping - RegisterMemberController, command : "+command);
			controller = new RegisterMemberController();
		}else if(command.equals("regResult")) {
			System.out.println("HandlerMapping - RegisterResultController, command : "+command);
			controller = new RegisterResultController();
		}
		
		// 만든사람 : 지원
		else if(command.equals("getStoreList")) {
			System.out.println("HandlerMapping - StoreListController, command : "+command);
			controller = new StoreListController();
		}else if(command.equals("getMenuList")) {
			System.out.println("HandlerMapping - MenuListController, command : "+command);
			controller = new MenuListController();
		}else if(command.equals("getMenuDetail")) {
			System.out.println("HandlerMapping - MenuDetailController, command : "+command);
			controller = new MenuDetailController();
		}else if(command.equals("getCartList")) {
			System.out.println("HandlerMapping - CartListController, command : "+command);
			controller = new CartListController();
		}else if(command.equals("cartAdd")) {
			System.out.println("HandlerMapping - CartAddController, command : "+command);
			controller = new CartAddController();
		}else if(command.equals("delete")) {
			System.out.println("HandlerMapping - CartDeleteController, command : "+command);
			controller = new CartDeleteController();
		} else if (command.equals("registerStoreForm")) {
			System.out.println("HandlerMapping - RegisterStoreFormController, command : " + command);
			controller = new RegisterStoreFormController();
		} else if (command.equals("upload")) {
			System.out.println("HandlerMapping - UploadController, command : " + command);
			controller = new UploadController();
		} else if (command.equals("registerStoreOk")) {
			System.out.println("HandlerMapping - RegisterStoreOkController, command : " + command);
			controller = new RegisterStoreOkController();
		}
		
		// 만든사람 : 현민
		else if(command.equals("charge")) {
			System.out.println("HandlerMapping - ChargeMoneyController, command : "+command);
			controller = new ChargeMoneyController();
		}else if(command.equals("chargeForm")) {
			System.out.println("HandlerMapping - ChargeMoneyFormController, command : "+command);
			controller = new ChargeMoneyFormController();
		}else if(command.equals("customerCurrentOrderList")) {
			System.out.println("HandlerMapping - CustomerCurrentOrderController, command : "+command);
			controller = new CustomerCurrentOrderController();
		}else if(command.equals("customerAllOrderList")) {
			System.out.println("HandlerMapping - CustomerAllOrderController, command : "+command);
			controller = new CustomerAllOrderController();
		}else if(command.equals("ownerCurrentOrderList")) {
			System.out.println("HandlerMapping - OwnerCurrentOrderController, command : "+command);
			controller = new OwnerCurrentOrderController();
		}else if(command.equals("ownerAllOrderList")) {
			System.out.println("HandlerMapping - OwnerAllOrderController, command : "+command);
			controller = new OwnerAllOrderController();
		}else if(command.equals("registerMenuForm")) {
			System.out.println("HandlerMapping - RegisterMenuFormController, command : "+command);
			controller=new RegisterMenuFormController();
		}else if(command.equals("registerMenuOk")) {
			System.out.println("HandlerMapping - RegisterMenuOkController, command : "+command);
			controller = new RegisterMenuOkController();
		}
		
		// 만든사람 : 지현
		else if(command.equals("viewMypage")) {
			System.out.println("HandlerMapping - MyPageController, command : "+command);
			controller = new MyPageController();
		}else if(command.equals("checkPayPasswordForm")) {
			System.out.println("HandlerMapping - CheckPayPasswordFormController, command : "+command);
			controller=new CheckPayPasswordFormController();
		}else if(command.equals("checkPayPassword")) {
			System.out.println("HandlerMapping - CheckPayPasswordController, command : "+command);
			controller=new CheckPayPasswordController();
		}else if(command.equals("payOrder")) {
			System.out.println("HandlerMapping - PayOrderController, command : "+command);
			controller=new PayOrderController();
		}else if(command.equals("changeOrder")) {
			System.out.println("HandlerMapping - ChangeOrderController, command : "+command);
			controller=new ChangeOrderController();
		}else if(command.equals("managementStore")) {
			System.out.println("HandlerMapping - ManagementStoreController, command : "+command);
			controller=new ManagementStoreController();
		}
		
		
		return controller;
	}
}