package _04_Maze_Maker;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import org.omg.Messaging.SyncScopeHelper;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	static int wid = 0;
	static int hgt = 0;
	
	static ArrayList<Cell> cells = new ArrayList<Cell>();
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		wid = w;
		hgt = h;
		maze = new Maze(width, height);
		
		//select a random cell to start
		Cell start = maze.getCell(randGen.nextInt(w),randGen.nextInt(h));
		//call selectNextPath method with the randomly selected cell
		selectNextPath(start);
		return maze;
		
	}

	private static void selectNextPath(Cell currentCell) {
		currentCell.setBeenVisited(true);

		// check for unvisited neighbors
		int curX = currentCell.getX();
		int curY = currentCell.getY();

		cells = getUnvisitedNeighbors(maze.getCell(curX, curY));

		// if has unvisited neighbors,
		// select one at random.
		// push it to the stack
		// remove the wall between the two cells
		// make the new cell the current cell and mark it as visited

		Random rand = new Random();

		if (!cells.isEmpty()) {

			int ran = rand.nextInt(cells.size());
			uncheckedCells.push(cells.get(ran));
			removeWalls(currentCell, cells.get(ran));
			currentCell = cells.get(ran);
			selectNextPath(currentCell);
			

		} else {
			if (!uncheckedCells.isEmpty()) {
				currentCell = uncheckedCells.pop();
				selectNextPath(currentCell);
			}
		}
	}

	private static void removeWalls(Cell c1, Cell c2) {
		if(c1.getX() + 1 == c2.getX()) {
			c1.setEastWall(false);
			c2.setWestWall(false);
		}else if(c1.getX() - 1 == c2.getX()) {
			c1.setWestWall(false);
			c2.setEastWall(false);
		}else if(c1.getY() + 1 == c2.getY()) {
			c1.setNorthWall(false);
			c2.setSouthWall(false);
		}else if(c1.getY() - 1 == c2.getY()) {
			c1.setSouthWall(false);
			c2.setNorthWall(false);
		}
	}

	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {

		ArrayList<Cell> cells = new ArrayList<Cell>();

		int curX = c.getX();
		int curY = c.getY();

		System.out.println("X: " + curX + " Y: " + curY);


		if (curX > 0 && curX < width - 1 && !maze.getCell(curX + 1, curY).hasBeenVisited()) {
			cells.add(maze.getCell(curX + 1, curY));
		} else if (curX < width && curX > 0 && !maze.getCell(curX - 1, curY).hasBeenVisited()) {
			cells.add(maze.getCell(curX - 1, curY));
		}
		if (curY > 0 && curY < height - 1 && !maze.getCell(curX, curY + 1).hasBeenVisited()) {
			cells.add(maze.getCell(curX, curY + 1));
		} else if (curY < height && curY > 0 && !maze.getCell(curX, curY - 1).hasBeenVisited()) {
			cells.add(maze.getCell(curX, curY - 1));
		}
		return cells;
		
	}
}