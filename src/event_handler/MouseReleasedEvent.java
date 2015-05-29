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
		w.fire(-1, 5);
		world.getMap().redrawMap();
	}

}
