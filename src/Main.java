import java.util.*;

public class Main {

	
	
	public static void main(String[] args) {
		
		SampleBoard nboard = new SampleBoard(6, 6);
		nboard.boardList[0][0] = "____";
		nboard.boardList[0][1] = "____";
		nboard.boardList[0][2] = "____";
		nboard.boardList[0][3] = "____";
		nboard.boardList[0][4] = "____";
		nboard.boardList[0][5] = "____";
		nboard.boardList[1][0] = "____";
		nboard.boardList[1][1] = "    ";
		nboard.boardList[1][2] = "    ";
		nboard.boardList[1][3] = "    ";
		nboard.boardList[1][4] = "    ";
		nboard.boardList[1][5] = "____";
		nboard.boardList[2][0] = "____";
		nboard.boardList[2][1] = "    ";
		nboard.boardList[2][2] = "    ";
		nboard.boardList[2][3] = "    ";
		nboard.boardList[2][4] = "    ";
		nboard.boardList[2][5] = "____";
		nboard.boardList[3][0] = "____";
		nboard.boardList[3][1] = "    ";
		nboard.boardList[3][2] = "    ";
		nboard.boardList[3][3] = "    ";
		nboard.boardList[3][4] = "    ";
		nboard.boardList[3][5] = "____";
		nboard.boardList[4][0] = "____";
		nboard.boardList[4][1] = "    ";
		nboard.boardList[4][2] = "    ";
		nboard.boardList[4][3] = "    ";
		nboard.boardList[4][4] = "    ";
		nboard.boardList[4][5] = "____";
		nboard.boardList[5][0] = "____";
		nboard.boardList[5][1] = "____";
		nboard.boardList[5][2] = "____";
		nboard.boardList[5][3] = "____";
		nboard.boardList[5][4] = "____";
		nboard.boardList[5][5] = "____";
		String a = Arrays.deepToString(nboard.boardList).replace("], ", "]\n");
		String formattedString = a
			    .replace(",", "")  //remove the commas
			    .replace("[", "")  //remove the right bracket
			    .replace("]", "")  //remove the left bracket
			    .trim(); 
		System.out.println(formattedString);
		
		int[][] boardPositions = {{0,0}, {0,1}, {0,2}, {0,3}, {0,4}, {0,5}, {1,0}, {1,5}, {2,0}, {2,5}, {3,0}, {3,5}, {4,0}, {4,5}, {5,0}, {5,1}, {5,2}, {5,3}, {5,4}, {5,5}};
		
		
		
	}
}
