package ua.com.tlftgames.ssocoban.movement.direction;

import java.util.LinkedList;

public class DirectionQueue {
	private LinkedList<Integer> queue;

	public DirectionQueue() {
		queue = new LinkedList<Integer>();
	}

	public void add(int direction) {
		this.queue.add(direction);
	}

	public int getCurrent() {
		if (this.queue.isEmpty()) {
			return Direction.NONE;
		}

		return this.queue.getFirst();
	}

	public int getNext() {
		if (this.queue.isEmpty()) {
			return Direction.NONE;
		}

		this.queue.removeFirst();

		return this.getCurrent();
	}

	public int getCount() {
		return this.queue.size();
	}
}
