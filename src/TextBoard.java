import java.util.ArrayList;
import java.util.Arrays;


public class TextBoard {
	private String[][] boardList = new String[6][6];
	private int[][] position = {{0,0}, {0,1}, {0,2}, {0,3}, {0,4}, {0,5}, {1,0}, {1,5}, {2,0}, {2,5}, {3,0}, {3,5}, {4,0}, {4,5}, {5,0}, {5,1}, {5,2}, {5,3}, {5,4}, {5,5}};
	public TextBoard() {
		boardList[0][0] = "____";
		boardList[0][1] = "____";
		boardList[0][2] = "____";
		boardList[0][3] = "____";
		boardList[0][4] = "____";
		boardList[0][5] = "____";
		boardList[1][0] = "____";
		boardList[1][1] = "    ";
		boardList[1][2] = "    ";
		boardList[1][3] = "    ";
		boardList[1][4] = "    ";
		boardList[1][5] = "____";
		boardList[2][0] = "____";
		boardList[2][1] = "    ";
		boardList[2][2] = "    ";
		boardList[2][3] = "    ";
		boardList[2][4] = "    ";
		boardList[2][5] = "____";
		boardList[3][0] = "____";
		boardList[3][1] = "    ";
		boardList[3][2] = "    ";
		boardList[3][3] = "    ";
		boardList[3][4] = "    ";
		boardList[3][5] = "____";
		boardList[4][0] = "____";
		boardList[4][1] = "    ";
		boardList[4][2] = "    ";
		boardList[4][3] = "    ";
		boardList[4][4] = "    ";
		boardList[4][5] = "____";
		boardList[5][0] = "____";
		boardList[5][1] = "____";
		boardList[5][2] = "____";
		boardList[5][3] = "____";
		boardList[5][4] = "____";
		boardList[5][5] = "____";
	}


 	public String stringConverter() {
 		String a = Arrays.deepToString(boardList).replace("], ", "]\n");
		String formattedString = a
			    .replace(",", "")
			    .replace("[", "")
			    .replace("]", "")
			    .trim(); 		
		return formattedString;
 	}

 	public void updateBoard(Student student) {
 			String toModify = boardList[position[student.getPlayerPosition()][0]][position[student.getPlayerPosition()][1]];
 			String modified = toModify.substring(0, student.getPlayerNumber() - 1) + student.getPlayerNumber() + toModify.substring(student.getPlayerNumber());
 			boardList[position[student.getPlayerPosition()][0]][position[student.getPlayerPosition()][1]] = modified;
 			
 			
 			
 			
 			

 	}
 		
 		
 		
 		
 		
 }







