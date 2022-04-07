package game;

public class Gamer {
	private int x;
	private int y;
	private int HP;
	
//	设立初始位置  左上为0,0 向下向右为正
	Gamer(int x,int y){
		this.x=x;
		this.y=y;
		this.HP=10;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getHP(){
		return this.HP;
	}
	
	public void up(){
		this.y--;
	}
	
	public void down(){
		this.y++;
	}
	
	public void left(){
		this.x--;
	}
	
	public void right(){
		this.x++;
	}
	
	public void setX(int x){
		this.x=x;
	}

	public void setY(int y){
		this.y=y;
	}

	public void HPdown(){
		this.HP--;
	}
	
}
