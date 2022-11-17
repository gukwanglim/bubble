package bubble.test.ex14;

public interface Moveable {
	public abstract void left();
	public abstract void right();
	public abstract void up();
	default public void down() {}; // default를 사용하면 인터페이스에도 몸체가 있는 메서드를 만들 수 있다.(다중 상속이 안되는 것이 많기 때문에)
	default public void attack() {};
//	public abstract void down();
}
