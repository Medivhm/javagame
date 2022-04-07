package game;

import java.util.Scanner;

public class Map {
	private int x_length;
	private int y_length;
	
    //设立地图大小  左上为0,0 向下向右为正
	Map(int x_length,int y_length){  
		this.x_length=x_length;
		this.y_length=y_length;
	}
	
    //刷新输出地图
	public void refresh(Gamer g,Gamer c){
		for(int j=0;j<x_length;j++)
			System.out.print("*");
		System.out.println("\n");
		for(int i=1;i<y_length-1;i++){
			System.out.print("*");
			for(int j=1;j<x_length-1;j++){
				if(i==g.getY()&&j==g.getX())
					System.out.print("#");
				else if(i==c.getY()&&j==c.getX())
					System.out.print("$");
				else{
					System.out.print(" ");
				}
			}
			System.out.print("*");
			System.out.println("\n");
		}
		for(int j=0;j<x_length;j++)
			System.out.print("*");
		System.out.println("\n");
	}
	
	public void showHP(Gamer g,Gamer c){
		System.out.println("你的HP："+g.getHP()+"   "+"电脑的HP："+c.getHP());
	}
	
	public int getX_length(){
		return this.x_length;
	}
	
	public int getY_length(){
		return this.y_length;
	}
	
	public static void main(String [] args){
		//游戏开始的位置
		Gamer g=new Gamer(2,3);
		Gamer c=new Gamer(2,2);
		Map m=new Map(8,8);
		Scanner sc=new Scanner(System.in);
		System.out.println("初始化完成，游戏开始");
		System.out.println("你是# 电脑是$");
		m.refresh(g, c);
		m.showHP(g, c);
		while(g.getHP()!=0&&c.getHP()!=0){
			//玩家开始走
			System.out.println("请输入数字0-3 0左 1右 2上 3下\n");
			System.out.print("我想输的数是(请不要测试游戏漏洞):");
			int a=sc.nextInt();
			
			//尝试使用bug的代价
			if(a<0||a>3){
				System.out.print("\n我提醒过你的");
				try {
					for(int o=0;o<6;o++){
						Thread.sleep(1000);
						System.out.print(". ");
					}
					System.out.println("\n");
					System.out.println("由于你好心的帮助制作人测试游戏bug，你的HP减9");
					for(int o=0;o<9;o++)
						g.HPdown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
				if(g.getHP()==0)
					break;
				m.refresh(g, c);
				m.showHP(g, c);
				continue;
			}
			
			
			//储存当前位置
			int x=g.getX(),y=g.getY();
			//0左 1右 2上 3下  
			if(g.getX()==1&&a==0||g.getX()==m.getX_length()-2&&a==1||g.getY()==1&&a==2||g.getY()==m.getY_length()-2){
				System.out.println("你撞墙了，HP减1");
				g.HPdown();
				if(g.getHP()==0)
					break;
			}
			else{
				if(a==0){
					g.left();
				}
				else if(a==1){
					g.right();
				}
					else if(a==2){
					g.up();
				}
				else if(a==3){
					g.down();
				}
				//如果撞上了
				if(g.getX()==c.getX()&&g.getY()==c.getY()){
					//电脑扣血
					System.out.println("你对电脑使用了撞击,电脑的HP减1");
					g.HPdown();
					//玩家返回原来位置
					g.setX(x);
					g.setY(y);
					if(c.getHP()==0)
						break;
				}
				System.out.println("你可以使用一次动感光波，1向上，2向下，3不用\n");
				System.out.print("输入：");
				int k=sc.nextInt();
				if(g.getX()==c.getX()&&(k==1)&&(g.getY()>c.getY())){
					c.HPdown();
					System.out.println("你的动感光波威力巨大，电脑HP减1");
					if(c.getHP()==0)
						break;
				}
				else if(g.getX()==c.getX()&&(k==2)&&(g.getY()<c.getY())){
					c.HPdown();
					System.out.println("你的动感光波威力巨大，电脑HP减1");
					if(c.getHP()==0)
						break;
				}
				else if(k==3){
					System.out.println("你拒绝使用动感光波，这也许是件好事");
				}
				else{
					System.out.println("你兴致勃勃的使用动感光波，但是光刺痛了你的眼睛，HP减2");
					g.HPdown();
					g.HPdown();
					if(g.getHP()<=0)
						break;
				}
			}
			
			
			//电脑走
			int can=0;
			while(can==0){
				//储存当前位置
				x=c.getX();
				y=c.getY();
				a=(int)(Math.random()*4);
				//0左 1右 2上 3下  
				if(c.getX()==1&&a==0||c.getX()==m.getX_length()-2&&a==1||c.getY()==1&&a==2||c.getY()==m.getY_length()-2){
					continue;
				}
				else{
					if(a==0){
						c.left();
					}
					else if(a==1){
						c.right();
					}
					else if(a==2){
						c.up();
					}
					else if(a==3){
						c.down();
					}
					can=1;
					//如果撞上了
					if(g.getX()==c.getX()&&g.getY()==c.getY()){
						//玩家扣血
						System.out.println("电脑对你使用了撞击，你的HP减1");
						g.HPdown();
						//电脑返回原来位置
						c.setX(x);
						c.setY(y);
						if(g.getHP()==0)
							break;
					}
				}
			}
			m.refresh(g,c);
			m.showHP(g, c);
		}
		m.refresh(g,c);
		m.showHP(g, c);
		//游戏结束
		if(g.getHP()<=0)
			System.out.println("游戏结束，你败给了邪恶的电脑");
		else if(c.getHP()<=0)
			System.out.println("游戏结束，你战胜了邪恶的电脑");
	}
}
