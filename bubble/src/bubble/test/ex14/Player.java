package bubble.test.ex14;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

// class player -> new 가능한 애들, 게임에 존재할 수 있음.(추상 메서드를 가질 수 없다.)
@Getter
@Setter
public class Player extends JLabel implements Moveable {

	private BubbleFrame_2 mContext;
	
	// 위치 상태
	private int x;
	private int y;
	
	// 플레이어의 방향
	private PlayerDirection playerDirection;
	
	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	// 충돌 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;
	
	// 움직임 속도
	private final int SPEED = 3;
	private final int JUMPSPEED = 3;
	
	private ImageIcon playerR, playerL;

	public Player(BubbleFrame_2 mContext) {
		this.mContext = mContext;
		initObject();
		initSetting();
		initBackgroundPlayerService();
	}

	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
	}

	private void initSetting() {
		x = 80;
		y = 535;
		
		left = false;
		right = false;
		up = false;
		down = false;
		
		leftWallCrash = false;
		rightWallCrash = false;

		playerDirection = PlayerDirection.RIGHT;
		setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y);
	}

	private void initBackgroundPlayerService() {
		new Thread(new BackgroundPlayerService(this)).start();
	}
	
	@Override
	public void attack() {
		new Thread(() -> {
			Bubble bubble = new Bubble(mContext);
			mContext.add(bubble);
			
			if(playerDirection == PlayerDirection.LEFT) {
				bubble.left();
			} else {
				bubble.right();
			}
		}).start();
	}
	
	@Override
	public void left() {
		playerDirection = PlayerDirection.LEFT;
		left = true;
		new Thread(() -> {
			while(left) {
				setIcon(playerL);
				x = x - SPEED;
				setLocation(x, y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void right() {
		playerDirection = PlayerDirection.RIGHT;
		right = true;
		new Thread(() -> {
			while(right) {
				setIcon(playerR);
				x = x + SPEED;
				setLocation(x, y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	// left + up, right + up
	@Override
	public void up() {
		up = true;
		new Thread(() -> {
			for(int i = 0; i < 130 / JUMPSPEED; i++) {
				y = y - JUMPSPEED;
				setLocation(x, y);
				try {
					Thread.sleep(9);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			up = false;
			down();
			
		}).start();
	}

	@Override
	public void down() {
		down = true;
		new Thread(() -> {
			while(down) {
				y = y + JUMPSPEED;
				setLocation(x, y);
				try {
					Thread.sleep(9);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			down = false;
			
		}).start();
	}
}
