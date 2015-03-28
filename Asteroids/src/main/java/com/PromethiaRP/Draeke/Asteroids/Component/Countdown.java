package com.PromethiaRP.Draeke.Asteroids.Component;

import java.util.HashMap;
import java.util.Map;

import com.PromethiaRP.Draeke.Asteroids.GameWorld;
import com.PromethiaRP.Draeke.Asteroids.Exceptions.IllegalMessageException;
import com.PromethiaRP.Draeke.Asteroids.Messages.CountdownMessage;
import com.PromethiaRP.Draeke.Asteroids.Messages.Message;
import com.PromethiaRP.Draeke.Asteroids.Messages.MessageType;
import com.PromethiaRP.Draeke.Asteroids.Messages.UpdateMessage;

public class Countdown extends Component {

	// All Countdowns are private to the component
	
	private Map<Component, Counter> counters;
	
	public Countdown() {
		this.addAllTypes(
				MessageType.UPDATE_DELTA,		// Update running counts
				MessageType.COUNTDOWN_BEGIN,	// Start a new count
				MessageType.COUNTDOWN_PAUSE,	// Pause a count
				MessageType.COUNTDOWN_RESUME,	// Resume a count
				MessageType.COUNTDOWN_STOP);	// Discard a count
		
		counters = new HashMap<Component, Counter>();
	}

	@Override
	public void handleMessage(GameWorld gameWorld, MessageType type, Message msg) {
		switch (type) {
		case UPDATE_DELTA:
			if (msg instanceof UpdateMessage) {
				UpdateMessage um = (UpdateMessage) msg;
				for (Component cp : counters.keySet()) {
					if (counters.get(cp).update(um.delta)) {
						// Fill with any info from the counter
						CountdownMessage cdm = new CountdownMessage(this, counters.get(cp).count, counters.get(cp).counting);
						cp.handleMessage(gameWorld, MessageType.COUNTDOWN_FINISH, cdm);
						counters.remove(cp);
					}
				}
			} else {
				throw new IllegalMessageException("Countdown did not receive UpdateMessage with corresponding type UPDATE_DELTA.");
			}
			break;
		case COUNTDOWN_BEGIN:
			if (msg instanceof CountdownMessage) {
				CountdownMessage cdm = (CountdownMessage) msg;
				counters.put(cdm.receiver, new Counter(cdm.length));
			} else {
				throw new IllegalMessageException("Countdown did not receive CountdownMessage with corresponding type COUNTDOWN_BEGIN.");
			}
			break;
		case COUNTDOWN_RESUME:
			if (msg instanceof CountdownMessage) {
				CountdownMessage cm = (CountdownMessage) msg;
				counters.get(cm.receiver).counting = true;
			} else {
				throw new IllegalMessageException("Countdown did not receive CountdownMessage with corresponding type COUNTDOWN_RESUME.");
			}
			break;
		case COUNTDOWN_PAUSE:
			if (msg instanceof CountdownMessage) {
				CountdownMessage cm = (CountdownMessage) msg;
				counters.get(cm.receiver).counting = true;
			} else {
				throw new IllegalMessageException("Countdown did not receive CountdownMessage with corresponding type COUNTDOWN_PAUSE.");
			}
			break;
		case COUNTDOWN_STOP:
			if (msg instanceof CountdownMessage) {
				CountdownMessage cm = (CountdownMessage) msg;
				counters.remove(cm.receiver);
			} else {
				throw new IllegalMessageException("Countdown did not receive CountdownMessage with corresponding type COUNTDOWN_STOP.");
			}
			break;
		default:
			break;
		}
	}
	
	private class Counter {
		
		private int length;
		private int count;
		private boolean counting;
		private Counter(int length) {
			this.length = length;
			this.count = length;
			counting = true;
		}
		
		/**
		 * Updates the counter if it is currently running.
		 * 
		 * @param delta
		 * @return true if the counter has finished.
		 */
		private boolean update(int delta) {
			if (counting) {
				count -= delta;
			}
			return count <= 0;
		}
		
		private void restart() {
			count = length;
		}
	}
}
