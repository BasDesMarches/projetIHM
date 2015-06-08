package application;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.beans.binding.When;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import views.WorldView;

public class TurnManager {
	WorldView world;
	int turnPeriod;
	SimpleIntegerProperty time;
	SimpleBooleanProperty isFinished;
	TimerTask countdown;
	Timer timer;
	
	public TurnManager(int turnPeriod, WorldView world) {
		this.world = world;
		this.turnPeriod = turnPeriod;
		time = new SimpleIntegerProperty(turnPeriod +1);
		world.getTimer().textProperty().bind(new SimpleStringProperty("").concat(time));
		isFinished = new SimpleBooleanProperty();
		isFinished.bind(new When(time.lessThan(0).or(world.getMap().getMap().getHasChanged())).then(true).otherwise(false));
		isFinished.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				if (newValue) {
					world.checkForVictory();
					world.changeTeam();
					time.set(turnPeriod + 1);
				}
			}
		});
		countdown = new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						time.set(time.get() - 1);
					}
				});
			}
		};
		timer = new Timer(true);
		timer.scheduleAtFixedRate(countdown, 0, 1000);
	}
}
