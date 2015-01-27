package domain;

import presentation.PresentationController;



public class Main {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// First all: create users
				RegisteredUserController userCtrl = DataFactoriaController.getInstance().getUserController();
				userCtrl.createRegisteredUsers();
				LevelController levelCtrl = DataFactoriaController.getInstance().getLevelController();
				levelCtrl.createLevels();
				
				PresentationController cp = new PresentationController();
				cp.showLogin(true);
			}
		});
	}
}
