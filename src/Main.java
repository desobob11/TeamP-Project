import java.util.*;

public class Main {

	
	
	public static void main(String[] args) {
		
		TextBoard board = new TextBoard();
		ArrayList<Student> list = new ArrayList<Student>();
		Student p1 = new Student(1, 100);
		Student p2 = new Student(2, 100);
		Student p3 = new Student(3, 100);
		Student p4 = new Student(4, 100);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		p1.moveForward(20, 20);
		board.updateBoard(p1);
		System.out.println(board.stringConverter() + "\n");
		p2.moveForward(25, 20);
		board.updateBoard(p2);
		System.out.println(board.stringConverter() + "\n");
		p3.moveForward(2, 20);
		board.updateBoard(p3);
		System.out.println(board.stringConverter() + "\n");
		p3.moveForward(3, 20);
		board.updateBoard(p3);
		System.out.println(board.stringConverter() + "\n");
		
		
		
		
	}
}
