package bubble.test.ex14;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable{
	
	// 의존성 콤포지션
	private BubbleFrame_2 mContext;
	private Player player; 
	private BackgroundBubbleService backgroundBubbleService;
	
	// 위치 상태
	private int x;
	private int y;

	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	
	// 적을 맞춘 상태
	private int state; 			// 0(물방울), 1(적을 가둔 물방울)
	
	private ImageIcon bubble;
	private ImageIcon bubbled; 	// 적을 가둔 물방울
	private ImageIcon bomb;		// 물방울이 터진 상태

	public Bubble(BubbleFrame_2 mContext) {
		this.mContext = mContext;
		this.player = mContext.getPlayer();
		initObject();
		initSetting();
	}
	
	private void initObject() {
		bubble = new ImageIcon("image/bubble.png");
		bubbled = new ImageIcon("image/bubbled.png");
		bomb = new ImageIcon("image/bomb.png");
		
		backgroundBubbleService = new BackgroundBubbleService(this);
	}
	
	private void initSetting() {
		left = false;
		right = false;
		up = false;
		
		x = player.getX();
		y = player.getY();
		
		setIcon(bubble);
		setSize(50, 50);
		
		state = 0;
	}

	@Override
	public void left() {
		left = true;
		for(int i=0; i < 250; i++) {
			x--;
			setLocation(x, y);
			
			if(backgroundBubbleService.leftWall()) {
				left = false;
				break;
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void right() {
		right = true;
		for(int i=0; i < 250; i++) {
			x++;
			setLocation(x, y);
			
			if(backgroundBubbleService.rightWall()) {
				right = false;
				break;
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void up() {
		up = true;
		while(up) {
			y--;
			setLocation(x, y);
			
			if(backgroundBubbleService.topWall()) {
				up = false;
				break;
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		clearBubble();
	}
	
	private void clearBubble() {
		try {
			Thread.sleep(3000);
			setIcon(bomb);
			
			Thread.sleep(500);
			mContext.remove(this);
			mContext.repaint();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
