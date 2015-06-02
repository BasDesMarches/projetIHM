package event_handler;

import application.World;
import application.Worm;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseReleasedEvent implements EventHandler<MouseEvent>{
	Worm w;
	World world;

	public MouseReleasedEvent(Worm w, World world) {
		super();
		this.w = w;
		this.world = world;
	}

	@Override
	public void handle(MouseEvent event) {
		switch (event.getButton()) {
		case PRIMARY:
			if(w.isChoosingWeapon()){
				
			} else {
				w.fire(Math.atan2(event.getSceneY() - (w.yPosProperty().get()+3)*5, event.getSceneX() - (w.xPosProperty().get()+3)*5), 5);
				world.getMap().redrawMap();
			}
			break;

		default:
			break;
		}
	}

}
