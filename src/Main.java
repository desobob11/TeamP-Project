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
		board.updateBoard(list);
		System.out.println(board.stringConverter());
		
		
		
		
	}
}
