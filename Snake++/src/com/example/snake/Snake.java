package com.example.snake;

import java.util.LinkedList;

import org.andengine.entity.Entity;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;


public class Snake extends Entity {
	private SnakeHead snakehead;
	private LinkedList<Tail> tail = new LinkedList<Tail>();

	private Direction curDir;
	private Direction prevDir;
	boolean extend;
	private  ITextureRegion TailTextureRegion;
	
	public Snake(Direction initialDir, int cellX, int cellY, ITextureRegion pHeadTextureRegion, ITextureRegion tailPartTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
		super(0, 0);
		this.TailTextureRegion = tailPartTextureRegion;
		this.snakehead = new SnakeHead( cellX, cellY, pHeadTextureRegion, pVertexBufferObjectManager );
		this.attachChild( this.snakehead );
		this.setDir( initialDir );
	}
	
	public Direction getDir() {
		return this.curDir;
	}

	public void setDir(final Direction direction) {
		if(this.prevDir != Direction.opposite(direction)) {
			this.curDir = direction;
			this.snakehead.setRotation(direction);
		}
	}

	public int getTailLength() {
		return this.tail.size();
	}

	public SnakeHead getHead() {
		return this.snakehead;
	}
	
	public LinkedList<Tail> getTail() {
		return this.tail;
	}
	
	public void grow() {
		this.extend = true;
	}

	public int getNextX() {
		return Direction.addToX(this.curDir, this.snakehead.getCX());
	}

	public int getNextY() {
		return Direction.addToY(this.curDir, this.snakehead.getCY());
	}

	public void move() throws SuicideException {
		this.prevDir = this.curDir;
		if(this.extend) {
			this.extend = false;
			/* If the snake should grow,
			 * simply add a new part in the front of the tail,
			 * where the head currently is. */
			Tail newTail = new Tail(this.snakehead, this.TailTextureRegion, this.snakehead.getVertexBufferObjectManager());
			this.attachChild(newTail);
			this.tail.addFirst(newTail);
		} else {
			if(this.tail.isEmpty() == false) {
				/* First move the end of the tail to where the head currently is. */
				Tail tailEnd = this.tail.removeLast();
				tailEnd.setCell(this.snakehead);
				this.tail.addFirst(tailEnd);
			}
		}

		/* The move the head into the direction of the snake. */
		this.snakehead.setCell(this.getNextX(), this.getNextY());
		
		/* Check if head collides with tail. */
		for(int i = 0; i < this.tail.size(); i++) {
			if(this.snakehead.sameCell(this.tail.get(i))) {
				throw new SuicideException();
			}
		}
	}
	
	
	
}
