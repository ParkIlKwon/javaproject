package menu_admin;

import Util.Util;
import _mall.MenuCommand;
import controller.MallController;
import member.MemberDAO;

public class AdminMember implements MenuCommand {

	private MallController mctrl;
	private MemberDAO mdao;
	Util input = Util.input;

	@Override
	public void init() {
		mctrl = MallController.getinstance();
		mdao = MemberDAO.getInstance();
	}

	@Override
	public boolean update() {
		init();
		while (true) {
			System.out.println("[Addmin Member Menu]");
			int sel = input.getValue("[1]멤버삭제 [0]뒤로가기", 0, 1);
			if (sel == 0) {
				mctrl.nextpage = "AdminMain";
			} else if (sel == 1) {
				mdao.deleteMember();
			}
			return true;
		}
	}
}
