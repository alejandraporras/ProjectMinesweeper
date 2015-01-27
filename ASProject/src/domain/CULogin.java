package domain;

public class CULogin {

	public CULogin(){
		
	}
	public void login(String username, String password) throws Exception{
		RegisteredUserController userCtrl = DataFactoriaController.getInstance().getUserController();
		RegisteredUser user = userCtrl.getRegisteredUser(username);
		
		if(user == null){
			throw new Exception("NotExistsUser");
		}
		else{
			if(!user.getPassword().equals(password)){
				throw new Exception("LoginFailed");
			}
			if(!user.isPlayer()){
				throw new Exception("IsNotAPlayer");
			}
		}
	}
	
	public static void main(String[] args) {
		CULogin cuLogin = new CULogin();
		try {
			cuLogin.login("ale", "pass");
			System.out.println("Login succesful");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
