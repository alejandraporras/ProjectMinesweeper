package domain;

import java.util.List;

public class CUConsultLevels {
	public List<Level> getLevels(){
		
		List<Level> levels = DataFactoriaController.getInstance().getLevelController().all();

		return levels;
	}
}
