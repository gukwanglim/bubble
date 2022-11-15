package bubble.test.ex02;

import javax.swing.JFrame;

public class BubbleFrame_2 extends JFrame {
	
	public BubbleFrame_2() {
		setSize(1000, 640);
		setLayout(null);    			 	// absoulte 레이아웃 (자유롭게 그림을 그릴 수 있다.)
		setLocationRelativeTo(null);		// JFrame 가운데 배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// x버튼으로 창을 종료할 때, JVM 같이 종료
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new BubbleFrame_2();
	}
}
