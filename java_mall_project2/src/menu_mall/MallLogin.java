package menu_mall;

import Util.Util;
import _mall.MenuCommand;
import controller.MallController;
import member.MemberDAO;
import menu_member.MemberMain;

public class MallLogin implements MenuCommand{
	
	MemberDAO mdao;
	MallController mctrl;
	Util input = new Util();
	
	@Override
	public void init() {
		mdao = MemberDAO.getInstance();
		mctrl = MallController.getinstance();
	}
	
	@Override
	public boolean update() {
		init();
		System.out.println(mdao.getMemberlist());
		System.out.println("[ Login Menu ]");
		String id;
		while (true) {
			id = input.getString("[login] 아이디 ",false);
			if(!id.equals(""))break;
		}
		
		String pw = input.getString("[login] 패스워드",false);
		
		if (mdao.chkMemberInfo(id, pw)) {
			mctrl.nextpage = "MemberMain"; 
			MemberMain.id = id;
			return true;
		}else {
			System.err.println("로그인 실패");
			mctrl.nextpage = "MallMain";  
			return true;
		}
	}
}
