package event_handler;

import views.WorldView;
import views.WormView;
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
			WormView w = world.getCurrentWorm();
			switch (event.getButton()) {
			case PRIMARY:
				if(w.getWorm().isChoosingWeapon().get()){
					world.hideWeaponChooser();
				} else {
					w.fire(Math.atan2(event.getSceneY() - (w.getWorm().yPosProperty().get()+3)*5, event.getSceneX() - (w.getWorm().xPosProperty().get()+3)*5), 5);
					world.getMap().redrawMap();
				}
				break;
				
			case SECONDARY:
				if (w.getWorm().isChoosingWeapon().get()) {
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
