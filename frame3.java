import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;

 class frame3 extends JFrame implements ActionListener
{
JPanel panel0,panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8,panel9,panel10;
JTextField display;
JButton btn0;
JFrame frame1;
public frame3()
{
frame1=new JFrame();
display=new JTextField();
display.setPreferredSize(new Dimension(300, 100));
display.setFont(new Font("Arial", Font.BOLD, 40));
frame1.setSize(1000,1000);

frame1.setLayout(new BorderLayout());
frame1.add(display,BorderLayout.NORTH);
btn0=new JButton();
btn0.setText("solve");
btn0.setBackground(Color.WHITE);
btn0.setForeground(Color.GREEN);
btn0.setFont(new Font("Arial", Font.BOLD, 100));
btn0.setPreferredSize(new Dimension(10, 10));
btn0.addActionListener(this);
frame1.add(btn0,BorderLayout.CENTER);
frame1.setVisible(true);
}

public void actionPerformed(ActionEvent e)
{try{
if(e.getSource()==btn0)
{
String input = display.getText();
String[] numbers = input.split(",");
int[] intNumbers = new int[numbers.length];
for (int i = 0; i<81; i++)
 {
    intNumbers[i] = Integer.parseInt(numbers[i]);
}
int index=0;
int[][] board1 = new int[9][9];
for(int i=0;i<9;i++)
{
   for(int j=0;j<9;j++)
{
board1[i][j]=intNumbers[index];
index++;
}}

frame1.dispose();
frame1=new JFrame("solved suduku");
frame1.setLayout(new GridLayout(3,3));
String[] labels={"panel1","panel2","panel3","panel4","panel5","panel6","panel7","panel8","panel9"};
sudukusolver obj=new sudukusolver(board1);
int[][][] board2=new int[9][3][3];
board2=obj.printBoard(board1);

for(int i=0;i<9;i++)
{Font myFont = new Font("Arial", Font.BOLD, 40);

JPanel panel=new JPanel();
panel.setLayout(new GridLayout(3,3));
for(int j=0;j<3;j++)
{
for(int k=0;k<3;k++)
{
int number = board2[i][j][k];
String str = Integer.toString(number);
JButton Button=new JButton(str);
Button.setFont(myFont);
Button.setBackground(Color.WHITE);
Button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
panel.add(Button);
panel.setBorder (BorderFactory.createLineBorder (Color.BLACK, 4));

}}
frame1.add(panel);
}
frame1.setVisible(true);
frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
}}
catch(Exception ae)
{
ae.printStackTrace();}
}
public static void main(String args[])
{
new frame3();
}}

class sudukusolver
{
int[][] board= new int[9][9];
sudukusolver(int[][] board)
{
this.board=board;
}
int[][][] printBoard(int[][] board)
{int[][][] board3=new  int[9][3][3];
if(solveBoard(board))
{

int l=0;
int m=0;
for(int k=0;k<9;k++)
{
for(int i=0;i<3;i++)
{
for(int j=0;j<3;j++)
{
board3[k][i][j]=board[l][m];
m++;
}
}l++;
m=0;
}}
return board3;
}
boolean isNumberInRow(int[][] board,int number,int row)
{for(int i=0;i<9;i++)
{if(board[row][i] == number)
{return true;
}}
return false;
}
boolean isNumberInColumn(int[][] board,int number,int column)
{for(int i=0;i<9;i++)
{if(board[i][column] == number)
{return true;
}}
return false;
}
boolean isNumberInBox(
int[][] board,int number,int row,int column)
{int localBoxRow=row-(row%3);
int localBoxColumn=column-(column%3);
for(int i=localBoxRow;i<localBoxRow+3;i++)
{for(int j=localBoxColumn;j<localBoxColumn+3;j++)
{if(board[i][j]==number)
{return true;
}
}
}
return false;
}
boolean isValidPlacement(int[][] board,int number,int row,int column)
{
return !isNumberInRow(board,number,row) && !isNumberInColumn(board,number,column) && !isNumberInBox(board,number,row,column);
}
 boolean solveBoard(int[][] board)
{
for(int row=0;row<9;row++)
{
for(int column=0;column<9;column++)
{
if(board[row][column]==0)
{
for(int numberToTry=1;numberToTry<=9;numberToTry++)
{
if(isValidPlacement(board,numberToTry,row,column))
{
board[row][column]=numberToTry;
if(solveBoard(board))
{
return true;
}
else
{
board[row][column]=0;
}
}}
return false;
}}}
return true;
}

}












