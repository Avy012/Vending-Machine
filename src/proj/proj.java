package proj;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


class Timer extends Thread{
	JLabel timer;
	boolean flag=false;
	public Timer(JLabel timer) {
		this.timer=timer;
	}
	public void finish() {
		flag=true;
	}
	@Override
	public void run() {
		int n=0;
		while(true) {
			timer.setText(Integer.toString(n));
			n++;
			try {
				Thread.sleep(1000);
				if(flag==true)
					return;
			}
			catch(InterruptedException e) {
				return;
			}
		}
	}
}

public class proj {
	private JFrame frame;
	JTextField inputmoney;
	int output; //거스름돈
	int input; //넣은 돈
	int pick; //음료 선택
	int total=0;//매출
	JLabel changes;
	JLabel ins;
	JLabel time = new JLabel();
	Timer t ;
	int bvg[] = {5,5,5,5,5,5};

	public proj() {
		frame=new JFrame("!음료 자판기!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,300);
		frame.setResizable(false);
		frame.add(money(),BorderLayout.CENTER);
		frame.add(buildGUI(),BorderLayout.SOUTH);
		frame.setJMenuBar(menu());
		frame.setLocationRelativeTo(null);  
		frame.setVisible(true);
		
	}
	private JPanel buildGUI() {
		JPanel panel = new JPanel();
		JLabel empty = new JLabel(" ");
		panel.setLayout(new GridLayout(3,1));
		panel.add(topbtns());
		panel.add(bottombtns());
		panel.add(empty);
		return panel;
	}
	private JMenuBar menu() {
		JMenuBar mb = new JMenuBar();
		JMenu how = new JMenu("사용법");
		JMenu check = new JMenu("음료 옵션");
		mb.add(how);
		mb.add(check);
		String[] howmenu= {"구매 방법","음료 개수 보는 방법","음료 채우는 방법","매출 보는 방법"};
		String[] drinkmenu = {"음료 갯수","음료 채우기","매출 보기"};
		JMenuItem[] menu1 =new JMenuItem[3];
		JMenuItem[] menu2 =new JMenuItem[4];
		for(int i=0;i<drinkmenu.length;i++) {
			menu1[i]=new JMenuItem(drinkmenu[i]);
			menu1[i].addActionListener(drinkmenulisten);
			check.add(menu1[i]);
		}
		for(int i=0;i<howmenu.length;i++) {
			menu2[i]=new JMenuItem(howmenu[i]);
			menu2[i].addActionListener(howmenulisten);
			how.add(menu2[i]);
		}
		return mb;
	}
	private ActionListener howmenulisten = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String s = e.getActionCommand();
			switch(s) {
			case "구매 방법":
				String msg="돈을 입력하고 확인을 누릅니다.\n"
						+ "원하는 음료를 누르면 구매창에 음료를 고르는 시간과 구매 완료 문구가 뜹니다.\n";
				JOptionPane.showMessageDialog(null,
						msg,"구매 방법", JOptionPane.PLAIN_MESSAGE);
				break;
			
			case "음료 개수 보는 방법":
				msg="음료 옵션 메뉴에서 '음료 갯수'를 눌러 확인합니다.";
				JOptionPane.showMessageDialog(null,
						msg,"음료 개수 보는 방법", JOptionPane.PLAIN_MESSAGE);
				break;
			case "음료 채우는 방법":
				msg="음료 옵션 메뉴에서 '음료 채우기'를 누릅니다.\n"
						+ "맞는 비밀번호를 눌러 음료를 채웁니다.\n";
				JOptionPane.showMessageDialog(null,
						msg,"음료 개수 보는 방법", JOptionPane.PLAIN_MESSAGE);
				break;
			case "매출 보는 방법":
				msg="음료 옵션 메뉴에서 '메출 보기'를 누릅니다.\n"
						+ "맞는 비밀번호를 눌러 인출합니다.";
				JOptionPane.showMessageDialog(null,
						msg,"매출 보는 방법", JOptionPane.PLAIN_MESSAGE);
			}
		}
		
	};
	private ActionListener drinkmenulisten = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			String s = e.getActionCommand();
			switch(s) {
			case "음료 갯수":
				String msg = "커피 "+bvg[0]+"\n코카콜라 "+bvg[1]+
				"\n이온음료 "+bvg[2]+"\n코코아 "+bvg[3]+
				"\n사이다 "+bvg[4]+"\n보리차 "+bvg[5];
				JOptionPane.showMessageDialog(null,
						msg,"음료 갯수", JOptionPane.PLAIN_MESSAGE);
				break;
			case "음료 채우기":
				String password=JOptionPane.showInputDialog("비밀번호를 입력하세요.");
				if(password.equals("dahee")) {//비밀번호 맞을때 
					for(int i=0;i<5;i++)
						bvg[i]=5;
					JOptionPane.showMessageDialog(null, "음료 전부 꽉 채웠습니다!",
							"Success", JOptionPane.INFORMATION_MESSAGE);
				}
				else//비밀번호 틀리면 경고문구
					JOptionPane.showMessageDialog(null, "비밀번호가 맞지 않습니다.",
							"Wrong password", JOptionPane.ERROR_MESSAGE);
				break;
			case "매출 보기":
				password=JOptionPane.showInputDialog("비밀번호를 입력하세요.");
				if(password.equals("dahee")) {//비밀번호 맞을때 
					msg="오늘의 매출: "+total+"원\n"
							+ "인출하시겠습니까?";
					int yn = JOptionPane.showConfirmDialog(null,
							msg,"income", JOptionPane.YES_NO_OPTION);
					if(yn==JOptionPane.YES_OPTION) {
						total=0;
						JOptionPane.showMessageDialog(null, "돈을 인출하였습니다.",
								"Success", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						
					}
				}
				else//비밀번호 틀리면 경고문구
					JOptionPane.showMessageDialog(null, "비밀번호가 맞지 않습니다.",
							"Wrong password", JOptionPane.ERROR_MESSAGE);
				break;
			}
		}
	};
	private JPanel money() {
		JPanel panel = new JPanel();
		JLabel empty = new JLabel(" ");
		panel.setLayout(new GridLayout(3,1));
		panel.add(empty);
		panel.add(inputmoney());
		panel.add(change());
		return panel;
	}
	private JPanel inputmoney() {
		JPanel panel = new JPanel();
		inputmoney = new JTextField(8);
		JLabel money = new JLabel("돈 넣기: ");
		JButton ok = new JButton("확인");
		ok.addActionListener(oklistener);
		panel.add(money);
		panel.add(inputmoney);
		panel.add(ok);
		return panel;
	}
	private ActionListener oklistener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			input= Integer.parseInt(inputmoney.getText());
			inputmoney.setText("");
			output=0;
			ins.setText(String.valueOf(input));
			changes.setText(String.valueOf(output));
			t = new Timer(time);
			t.start();	
		}
		
	};
	private JPanel change() {
		JPanel panel = new JPanel();
		changes = new JLabel();
		ins = new JLabel();
		JLabel in = new JLabel("넣은 돈: ");
		JLabel out = new JLabel(" 거스름 돈: ");
		changes.setText(String.valueOf(output));
		ins.setText(String.valueOf(input));
		panel.add(in);
		panel.add(ins);
		panel.add(out);
		panel.add(changes);
		return panel;
	}
	private JPanel topbtns() {
		JPanel panel = new JPanel();
		JButton coffee= new JButton("   커피 500원   ");
		JButton coke=new JButton("코카콜라 700원");
		JButton ion= new JButton("이온음료 700원");
		panel.setLayout(new FlowLayout());
		coffee.addActionListener(hotdrinklistener);
		coke.addActionListener(colddrinklistener);
		ion.addActionListener(colddrinklistener);
		panel.add(coffee);
		panel.add(coke);
		panel.add(ion);
		return panel;
	}
	private ActionListener hotdrinklistener = new ActionListener() {//500원
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			t.finish();
			String tt = time.getText();
			if(b.getText().equals("   커피 500원   ")) pick=0;
			else if(b.getText().equals("  코코아 500원  ")) pick=3;
			if(bvg[pick]>0) {
				bvg[pick]--;
				output=input-500;
				if(output<0) {//넣은 돈이 구매금액 보다 적을 경우
					output=input;
					JOptionPane.showMessageDialog(null, "돈이 부족합니다.","WARNING", JOptionPane.ERROR_MESSAGE);
				}
				else {
					total+=500;
					JOptionPane.showMessageDialog(null, tt+"초 걸림\n구매하였습니다."
							+ "\n거스름돈을 확인하세요.","enjoy your drink!", JOptionPane.PLAIN_MESSAGE);
				}
				input=0;
				ins.setText(String.valueOf(input));
				changes.setText(String.valueOf(output));	
			}
			else
				JOptionPane.showMessageDialog(null, "품절입니다.","OUT OF STOCK", JOptionPane.ERROR_MESSAGE);
		}
	};
	private ActionListener colddrinklistener = new ActionListener() {//700원
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			t.finish();
			String tt = time.getText();
			if(b.getText().equals("코카콜라 700원")) pick=1;
			else if(b.getText().equals("이온음료 700원")) pick=2;
			else if(b.getText().equals("  사이다 700원  ")) pick=4;
			else if(b.getText().equals("  보리차 700원  ")) pick=5;
			if(bvg[pick]>0) {
				bvg[pick]--;
				output=input-700;
				if(output<0) {//넣은 돈이 구매금액 보다 적을 경우
					output=input;
					JOptionPane.showMessageDialog(null, "돈이 부족합니다.","Warning", JOptionPane.ERROR_MESSAGE);
				}
				else {
					total+=700;
					JOptionPane.showMessageDialog(null,  tt+"초 걸림\n"+"구매하였습니다.\n"
							+ "거스름돈을 확인하세요.","enjoy your drink!", JOptionPane.PLAIN_MESSAGE);
				}
				input=0;
				ins.setText(String.valueOf(input));
				changes.setText(String.valueOf(output));
			}
			else
				JOptionPane.showMessageDialog(null, "품절입니다.","OUT OF STOCK", JOptionPane.ERROR_MESSAGE);
		}
	};
	private JPanel bottombtns() {
		JPanel panel = new JPanel();
		JButton coco= new JButton("  코코아 500원  ");
		JButton sprite=new JButton("  사이다 700원  ");
		JButton water= new JButton("  보리차 700원  ");
		coco.addActionListener(hotdrinklistener);
		sprite.addActionListener(colddrinklistener);
		water.addActionListener(colddrinklistener);
		panel.add(coco);
		panel.add(sprite);
		panel.add(water);
		return panel;
	}
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		proj p = new proj();
	}
}


