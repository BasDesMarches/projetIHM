package event_handler;

import views.WorldView;
import application.Worm;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseReleasedEvent implements EventHandler<MouseEvent>{
	WorldView world;

	public MouseReleasedEvent(WorldView world) {
		super();
		this.world = world;
	}

	@Override
	public void handle(MouseEvent event) {
		if (world.isGameFinished().get()) {
			world.setGameFinished(false);
		} else {
			Worm w = world.getCurrentWorm().getWorm();
			switch (event.getButton()) {
			case PRIMARY:
				if(w.isChoosingWeapon()){
					world.hideWeaponChooser();
				} else {
					world.fire(Math.atan2(event.getSceneY() - (w.yPosProperty().get()+3)*5, event.getSceneX() - (w.xPosProperty().get()+3)*5), 5);
					world.getMap().redrawMap();
				}
				break;
				
			case SECONDARY:
				if (w.isChoosingWeapon()) {
					world.hideWeaponChooser();
				} else {
					world.showWeaponChooser();
				}
	
			default:
				break;
			}
		}
	}
}
