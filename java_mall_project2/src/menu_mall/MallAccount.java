package menu_mall;

import Util.Util;
import _mall.MenuCommand;
import controller.MallController;
import member.MemberDAO;

public class MallAccount implements MenuCommand{
	
	Util input = new Util();
	MallController mctrl ;
	MemberDAO mdao ;
	
	@Override
	public void init() {
		mctrl = MallController.getinstance();
		mdao = MemberDAO.getInstance();
	}
	
	@Override
	public boolean update() {
		init();
		System.out.println("[ Account Menu ]");
		String id = "";
		while (true) {
			id = input.getString("[Accont] 아이디 ",false);
			if(!mdao.chkduplicate(id) && !id.equals(""))break;
		}
		String pw = input.getString("[Accont] 패스워드",false);
		String name = input.getString("[Accont] 이름", true);
		
		if (name.equals("")) {
			System.out.println("회원가입 실패");
			mctrl.nextpage = "MallMain"; 
			return true;
		}else {
			mdao.accountMember(id, pw, name);
			System.out.println("회원가입 완료");
			mctrl.nextpage = "MallMain";
			return true;
		}
	}
}





