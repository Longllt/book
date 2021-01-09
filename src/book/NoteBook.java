package book;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
/**
 * 文本编辑器界面及功能实现类
 * @author longbin
 *
 */
class NoteBook{
	//创建一个容器
	JFrame frame = new JFrame("文档编辑器");
	//创建一个菜单栏
	JMenuBar bar = new JMenuBar();
	//创建各个菜单
	JMenu menuFile = new JMenu("文件(F)");
	JMenu menuEdit = new JMenu("编辑(E)");
	JMenu menuFormat = new JMenu("格式(O)");
	JMenu menuView = new JMenu("查看(V)");
	JMenu menuHelp = new JMenu("帮助(H)");
	//创建文件菜单中的各个菜单项
	JMenuItem menuFileNew = new JMenuItem("新建(N)");
	JMenuItem menuFileOpen = new JMenuItem("打开(O)");
	JMenuItem menuFileSave = new JMenuItem("保存(S)");
	JMenuItem menuFileSaveAs = new JMenuItem("另存为(A)");
	JMenuItem menuFileExit = new JMenuItem("退出(E)");
	//创建编辑菜单中的各个菜单项
	JMenuItem menuEditUndo = new JMenuItem("撤销(Z)");
	JMenuItem menuEditRepeat = new JMenuItem("重复(Y)");
	JMenuItem menuEditCopy = new JMenuItem("复制(C)");
	JMenuItem menuEditPaste = new JMenuItem("粘贴(V)");
	JMenuItem menuEditCut = new JMenuItem("剪切(X)");
	JMenuItem menuEditDelete = new JMenuItem("删除(D)");
	JMenuItem menuEditFind = new JMenuItem("查找(F)");
	JMenuItem menuEditReplace = new JMenuItem("替换(H)");
	JMenuItem menuEditGoto = new JMenuItem("转到(G)");
	JMenuItem menuEditSelectAll = new JMenuItem("全选(A)");
	JMenuItem menuEditTime = new JMenuItem("时间/日期(D)");
	//创建格式菜单中的各个菜单项
	JMenuItem menuForFont = new JMenuItem("字体(T)");
	JMenuItem menuForColor = new JMenuItem("颜色(Y)");
	JCheckBoxMenuItem menuForWrap = new JCheckBoxMenuItem("自动换行");
	//创建查看菜单中的各个菜单项
	JMenuItem menuViewBig = new JMenuItem("放大(I)");
	JMenuItem menuViewSmall = new JMenuItem("缩小(I)");
	//创建帮助菜单中的各个菜单项
	JMenuItem menuHelpAbout = new JMenuItem("关于...");
	//创建右击菜单
	JPopupMenu popupMenu = new JPopupMenu();
	JMenuItem Undo = new JMenuItem("撤销(Z)");
	JMenuItem Cut = new JMenuItem("剪切(X)");
	JMenuItem Copy = new JMenuItem("复制(C)");
	JMenuItem Paste = new JMenuItem("粘贴(V)");
	JMenuItem SelectAll = new JMenuItem("全选(A)");
	JMenuItem Delete = new JMenuItem("删除(D)");
	JMenuItem Time = new JMenuItem("时间/日期(D)");
	//创建文本区
	JTextArea text = new JTextArea();
	//创建中间滚轮容器
	JScrollPane center ;
	//创建默认字体	
	Font font = new Font("微软雅黑",Font.BOLD,13);
	//判断是否作了改动
	boolean change = false;
	//判断是否新建了
	boolean New = false;
	//撤销重复监听器
	UndoManager undom ;
	//创建默认文件路径
	String url = "C:\\Users\\longbin\\Desktop\\测试";//默认路径文件夹
	File F = new File(url+"\\文档编辑器.txt");
	Color color = Color.black ;
	
	//构造方法初始化菜单
	public NoteBook() {
		//文本区设置默认字体
		text.setFont(font);
		//文本区加入滚动条
		center = new JScrollPane(text);
		//将文本框中加入容器
		frame.add(text);
		//将菜单栏加入容器
		frame.setJMenuBar(bar);
		
		//把菜单加入到菜单栏中
		bar.add(menuFile);
		bar.add(menuEdit);
		bar.add(menuFormat);
		bar.add(menuView);
		bar.add(menuHelp);
				
		//把菜单项加入到菜单中
		//===向文件菜单和编辑菜单加入菜单项
		menuFile.add(menuFileNew);
		menuFile.add(menuFileOpen);
		menuFile.addSeparator();//增加分割线
		menuFile.add(menuFileSave);
		menuFile.add(menuFileSaveAs);
		menuFile.addSeparator();//增加分割线
		menuFile.add(menuFileExit);
						
		//===向编辑菜单加入菜单项
		menuEdit.add(menuEditUndo);
		menuEdit.add(menuEditRepeat);
		menuEdit.addSeparator();//增加分割线
		menuEdit.add(menuEditCopy);
		menuEdit.add(menuEditPaste);
		menuEdit.add(menuEditCut);
		menuEdit.add(menuEditDelete);
		menuEdit.addSeparator();//增加分割线
		menuEdit.add(menuEditFind);
		menuEdit.add(menuEditReplace);
		menuEdit.add(menuEditGoto);
		menuEdit.addSeparator();//增加分割线
		menuEdit.add(menuEditSelectAll);
		menuEdit.addSeparator();//增加分割线
		menuEdit.add(menuEditTime);
						
		//===向格式菜单加入菜单项
		menuFormat.add(menuForFont);
		menuFormat.add(menuForColor);
		menuFormat.add(menuForWrap);
		
		//===向格式菜单加入菜单项
		menuView.add(menuViewBig);
		menuView.add(menuViewSmall);

		//===向帮助菜单加入菜单项
		//menuHelp.add(menuHelpAbout);
		
		//将右键的菜单项添加到菜单中
		popupMenu.add(Undo);
        popupMenu.add(Copy);
        popupMenu.add(Paste);
        popupMenu.add(Cut);
        popupMenu.add(Delete);
        popupMenu.add(SelectAll);
        popupMenu.add(Time);
		//加入快捷键
		menuFile.setMnemonic(KeyEvent.VK_F);
		menuEdit.setMnemonic(KeyEvent.VK_E);
		menuFormat.setMnemonic(KeyEvent.VK_O);
		menuView.setMnemonic(KeyEvent.VK_V);
		menuHelp.setMnemonic(KeyEvent.VK_H);
		
		menuFileOpen.setAccelerator(KeyStroke.getKeyStroke("control O"));
		menuFileNew.setAccelerator(KeyStroke.getKeyStroke("control N"));
		menuFileSave.setAccelerator(KeyStroke.getKeyStroke("control S"));
		menuFileSaveAs.setAccelerator(KeyStroke.getKeyStroke("shift control released S"));
		menuFileExit.setAccelerator(KeyStroke.getKeyStroke("control E"));
		
		menuEditUndo.setAccelerator(KeyStroke.getKeyStroke("control Z"));
		menuEditRepeat.setAccelerator(KeyStroke.getKeyStroke("control Y"));
		menuEditCopy.setAccelerator(KeyStroke.getKeyStroke("control C"));
		menuEditCut.setAccelerator(KeyStroke.getKeyStroke("control X"));
		menuEditPaste.setAccelerator(KeyStroke.getKeyStroke("control V"));
		menuEditDelete.setAccelerator(KeyStroke.getKeyStroke("DELETE"));
		menuEditFind.setAccelerator(KeyStroke.getKeyStroke("control F"));
		menuEditReplace.setAccelerator(KeyStroke.getKeyStroke("control H"));
		menuEditGoto.setAccelerator(KeyStroke.getKeyStroke("control G"));
		menuEditSelectAll.setAccelerator(KeyStroke.getKeyStroke("control A"));
		menuEditTime.setAccelerator(KeyStroke.getKeyStroke("F5"));
		
		menuViewBig.setAccelerator(KeyStroke.getKeyStroke("control shift released MINUS"));
		menuViewSmall.setAccelerator(KeyStroke.getKeyStroke("control MINUS"));
		
		menuForFont.setAccelerator(KeyStroke.getKeyStroke("control T"));
		menuForColor.setAccelerator(KeyStroke.getKeyStroke("control shift released Y"));
		//menuHelpAbout.setAccelerator(KeyStroke.getKeyStroke("control shift released H"));

		//向所有菜单增加监听器
		//触发了表示作了改动
		text.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				change = true;
				if(frame.getTitle().compareTo("无标题")==0 || frame.getTitle().compareTo("*无标题")==0)frame.setTitle("*无标题");
				else frame.setTitle("*"+F.getName());//未保存状态
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				change = true;
				if(frame.getTitle().compareTo("无标题")==0 || frame.getTitle().compareTo("*无标题")==0)frame.setTitle("*无标题");
				else frame.setTitle("*"+F.getName());//未保存状态
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				change = true;
				if(frame.getTitle().compareTo("无标题")==0 || frame.getTitle().compareTo("*无标题")==0)frame.setTitle("*无标题");
				else frame.setTitle("*"+F.getName());//未保存状态
			}
		});
		
		//新建菜单项的功能实现
		menuFileNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(change) {//判断文档是否发生了改动
					//判断是不是新建了文档
					if(!New) {//没有新建
						//弹出是不是保存源文件的对话框
						int f=JOptionPane.showConfirmDialog(frame, "是否保存原文件？", "文档编辑器", JOptionPane.YES_NO_OPTION);
						//如果选择保存，就将文档保存
						if(f==JOptionPane.YES_OPTION)Save(new File(url+"\\无标题.txt"));
					}else {//新建了
						//弹出是不是要保存到无标题
						int f=JOptionPane.showConfirmDialog(frame, "你想要将更改保存到无标题吗？", "文档编辑器", JOptionPane.YES_NO_OPTION);
						if(f==JOptionPane.YES_OPTION) Save(new File(url+"\\无标题.txt"));//保存到默认路径无标题
					}
				}
				New = true;//新建的
				text.setText("");//清空
				undom = new UndoManager();
				text.getDocument().addUndoableEditListener(undom);//新建也要重新加入撤销监听
				change = false;//新建后的都不是活动状态
				frame.setTitle("无标题");//新建的未保存的没有标题
			}
		});
		//打开菜单项的功能实现
		menuFileOpen.addActionListener(new ActionListener() {//打开
			@Override
			public void actionPerformed(ActionEvent e) {
				if(change) {
					int res=JOptionPane.showConfirmDialog(frame, "是否保存原文件？", "文档编辑器", JOptionPane.YES_NO_OPTION);
					if(res==JOptionPane.YES_OPTION) {
						if(New) {//是新建的需要保存
							int f=JOptionPane.showConfirmDialog(frame, "你想要将更改保存到无标题吗？", "文档编辑器", JOptionPane.YES_NO_OPTION);
							if(f==JOptionPane.YES_OPTION) Save(new File(url+"\\无标题.txt"));//保存到默认路径无标题
						}else {
							int f=JOptionPane.showConfirmDialog(frame, "是否保存原文件？", "文档编辑器", JOptionPane.YES_NO_OPTION);
							if(f==JOptionPane.YES_OPTION)Save(F);
						}
					}
				}
				int result=0;
				JFileChooser filec=new JFileChooser(url);//默认文件打开位置
				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt文件(*.txt)", "txt");
			   	filec.setFileFilter(filter);//增加文件过滤器
				filec.setDialogTitle("打开");//设置文本选择框标题
				result=filec.showOpenDialog(frame);//生成打开对话框
				if(result==JFileChooser.APPROVE_OPTION) {//表示选择了确定按钮
					F=filec.getSelectedFile();//更新文件位置
					Read(F);
					New = false;//意味着不是新建的了
				}
			}
		});
		//保存菜单项的功能实现
		menuFileSave.addActionListener(new ActionListener() {//保存
			@Override
			public void actionPerformed(ActionEvent e) {
				if(change) {
					if(New) {//是新建文件并且有改动
						int result=0;
						JFileChooser filec=new JFileChooser(url);
						FileNameExtensionFilter filter = new FileNameExtensionFilter("txt文件(*.txt)", "txt");
					   	filec.setFileFilter(filter);//增加文件过滤器
						String defaultFileName="新建文本文件"+".txt";//默认文件夹名字
						filec.setSelectedFile(new File(defaultFileName)); //设置默认文件名
						filec.setDialogTitle("另存为");//设置文本选择框标题
						result=filec.showSaveDialog(frame);
						if(result==JFileChooser.APPROVE_OPTION) {//表示选择了确定按钮
							File doc = filec.getCurrentDirectory();//目录
							File file = filec.getSelectedFile();//要保存的文件
							String[] st = doc.list();
							boolean falg = false;//判断是重名
							for(String x:st) {
								if(x.compareTo(file.getName())==0)falg = true;//目录下存在名字一样的文件
							}
							if(falg) {
								int f=JOptionPane.showConfirmDialog(null, "是否覆盖？", "文档编辑器", JOptionPane.YES_NO_OPTION);
								if(f==JOptionPane.YES_OPTION) {
									F=file;//更改默认文件位置
									Save(F);
								}
							}
							else {
								F=filec.getSelectedFile();//更改默认文件
								Save(F);
							}
							change = false;//保存过后就没有改动了
							frame.setTitle(F.getName());//窗口状态
							New = false;//没保存还是新窗口
						}
					}
					else {
						Save(F);
						change = false;//保存过后就没有改动了
						frame.setTitle(F.getName());//窗口状态
					}
				}
			}
		});
		//另存为菜单项的功能实现
		menuFileSaveAs.addActionListener(new ActionListener() {//另存为
			@Override
			public void actionPerformed(ActionEvent e) {
				int result=0;
				JFileChooser filec=new JFileChooser(url);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt文件(*.txt)", "txt");
			   	filec.setFileFilter(filter);//增加文件过滤器
				String defaultFileName="新建文本文件"+".txt";//默认文件夹名字
				filec.setSelectedFile(new File(defaultFileName)); //设置默认文件名
				filec.setDialogTitle("另存为");//设置文本选择框标题
				result=filec.showSaveDialog(frame);
				if(result==JFileChooser.APPROVE_OPTION) {//表示选择了确定按钮
					File doc = filec.getCurrentDirectory();//目录
					File file = filec.getSelectedFile();//要保存的文件
					String[] st = doc.list();
					boolean falg = false;
					for(String x:st) {
						if(x.compareTo(file.getName())==0)falg = true;//目录下存在名字一样的文件
					}
					if(falg) {
						int f=JOptionPane.showConfirmDialog(null, "是否覆盖？", "文档编辑器", JOptionPane.YES_NO_OPTION);
						if(f==JOptionPane.YES_OPTION) {
							F=file;//更改默认文件位置
							Save(F);
						}
					}
					else {
						F=filec.getSelectedFile();//更改默认文件
						Save(F);
					}
				}
			}
		});
		//退出菜单项的功能实现
		menuFileExit.addActionListener(new ActionListener(){//退出
			@Override
			public void actionPerformed(ActionEvent e) {
				if(change) {
					if(New) {
						int res=JOptionPane.showConfirmDialog(frame, "是否退出？", "文档编辑器", JOptionPane.YES_NO_OPTION);
						if(res==JOptionPane.YES_OPTION) {
							int f=JOptionPane.showConfirmDialog(frame, "是否保存到：" + url + "\\无标题.txt", 
																								"文档编辑器", JOptionPane.YES_NO_OPTION);
							if(f==JOptionPane.YES_OPTION)Save(new File(url+"\\无标题.txt"));
							System.exit(0);
						}
					} else {
						int res=JOptionPane.showConfirmDialog(frame, "是否退出？", "文档编辑器", JOptionPane.YES_NO_OPTION);
						if(res==JOptionPane.YES_OPTION) {
							int f=JOptionPane.showConfirmDialog(frame, "是否保存到："+F.getPath(), "文档编辑器", JOptionPane.YES_NO_OPTION);
							if(f==JOptionPane.YES_OPTION)Save(F);
							System.exit(0);
						}
					}
				}
				else {

					menuFileExit.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								Object[] options = { "是的，我要退出", "不好意思，点错了" };
								int option = JOptionPane.showOptionDialog(null, "您确定要退出吗？",
										 "退出提示....",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,
										  null,options, options[0]);     
								 if(option == JOptionPane.OK_OPTION){
								 System.exit(0);
								 }
							}
							
							   
						   });
				}
			}
		});
		//撤销菜单项的功能实现
		menuEditUndo.addActionListener(new ActionListener() {//撤销
			@Override
			public void actionPerformed(ActionEvent e) {
				if(undom.canUndo()) {//判断是否能撤销
					undom.undo();//撤销操作
				}
			}
		});
		//重复菜单项的功能实现
		menuEditRepeat.addActionListener(new ActionListener() {//重复
			@Override
			public void actionPerformed(ActionEvent e) {
				if(undom.canRedo()) {//判断是否能重复
					undom.redo();//重复操作
				}
			}
		});
		//剪切菜单项的功能实现
		menuEditCut.addActionListener(new ActionListener() {//剪切
			@Override
			public void actionPerformed(ActionEvent e) {
				text.cut();
			}
		});
		//复制菜单项的功能实现
		menuEditCopy.addActionListener(new ActionListener() {//复制
			@Override
			public void actionPerformed(ActionEvent e) {
				text.copy();
			}
		});
		//粘贴菜单项的功能实现
		menuEditPaste.addActionListener(new ActionListener() {//粘贴
			@Override
			public void actionPerformed(ActionEvent e) {
				text.paste();
			}
		});
		
		//删除菜单项的功能实现
		menuEditDelete.addActionListener(new ActionListener() {//删除
			@Override
			public void actionPerformed(ActionEvent e) {
				text.replaceSelection("");
			}
		});
		
		//查找菜单项的功能实现
		menuEditFind.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				final JDialog dialog = new JDialog(frame,"查找字符串...",true);
				dialog.setBounds(560,250,310,130);
				JLabel find = new JLabel("请输入字符串 :");
				final JTextField findtext = new JTextField(1);
				JButton jbu = new JButton("查找");
				dialog.setLayout(null);
				find.setBounds(10,30,90,20);
				findtext.setBounds(100,30,90,20);
				jbu.setBounds(200,30,80,20);
				dialog.add(find);
				dialog.add(findtext);
				dialog.add(jbu);
				jbu.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						String txt = text.getText();
				        String str = findtext.getText();
				        int end = txt.length();
				        int len = str.length();
				        int start = text.getSelectionEnd();
				        if(start == end){
				        	start = 0;
				        }
				        for(;start<=end-len;start++){
				            if(txt.substring(start,start+len).equals(str)){
				            	text.setSelectionStart(start);
				            	text.setSelectionEnd(start+len);
				                return;
				            }
				        }
				        //若找不到待查字符串，则将光标置于末尾 
				        text.setSelectionStart(end);
				        text.setSelectionEnd(end);
					 }
					   
				   });
		        dialog.addWindowListener(new WindowAdapter(){
		            public void windowClosing(WindowEvent e){
		                dialog.dispose();
		            }
		        });
		        dialog.setResizable(false);
				dialog.setVisible(true);
			}
			
			   
		});
		//替换菜单项的功能实现
		menuEditReplace.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				final JDialog dialog = new JDialog(frame,"字符串替换...",true);
				  dialog.setBounds(560,250,310,180);
				  final JLabel tihuan = new JLabel("请输入要替换的字符串 ：");
				  JLabel mubiao = new JLabel("请输入替换后的字符串 ：");
				  JTextField jtf1 = new JTextField(10);
				  JTextField jtf2 = new JTextField(10);
				  JButton jb = new JButton("替换");
				  dialog.setLayout(null);
		          tihuan.setBounds(10,30,150,20);
		          mubiao.setBounds(10,70,150,20);
		          jtf1.setBounds(160,30,110,20);
		          jtf2.setBounds(160,70,110,20);
		          jb.setBounds(100,110,80,20);
		          dialog.add(tihuan);
		          dialog.add(mubiao);
		          dialog.add(jtf1);
		          dialog.add(jtf2);
		          dialog.add(jb);
		          final String txt = text.getText();
		          final String str1 = tihuan.getText();
				  final String str2 = mubiao.getText();
				  jb.addActionListener(new ActionListener(){
				  public void actionPerformed(ActionEvent e) {
		            if(text.getSelectedText().equals(tihuan.getText())){
		            	text.replaceRange(str2,text.getSelectionStart(),text.getSelectionEnd());
		                 }
		            else {
		                 int end=txt.length();
		                 int len=str1.length();
		                 int start=text.getSelectionEnd();
		                 if(start==end) start=0;
		                 for(;start<=end-len;start++){
		                     if(txt.substring(start,start+len).equals(str1)){
		                    	 text.setSelectionStart(start);
		                    	 text.setSelectionEnd(start+len);
		                         return;
		                     }
		                  }
		                  //若找不到待查字符串，则将光标置于末尾
		                 text.setSelectionStart(end);
		                 text.setSelectionEnd(end);
		                 }
		                
				       }
				   
			        });
				  dialog.setResizable(false);
	              dialog.setVisible(true);
			}
			   
		});
		//转到菜单项的功能实现
		menuEditGoto.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
						
			}
					   
		});
		//全选菜单项的功能实现
		menuEditSelectAll.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				text.selectAll();
			}
			   
		});
		//时间/日期菜单项功能实现
		menuEditTime.addActionListener(new ActionListener() {//日期
			@Override
			public void actionPerformed(ActionEvent e) {
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy 年 MM 月 dd 日 HH 时 mm 分 ss 秒 ");
			Date date = new Date(System.currentTimeMillis());
			String data = formatter.format(date);
			JOptionPane.showMessageDialog(frame,data, "当前时间", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		//字体菜单项的功能实现
		menuForFont.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				 final JFrame ztsz = new JFrame("字体设置...");//字体设置窗口
			     //字体
			     ztsz.setLocation(150, 200);
			     frame.setEnabled(false);//文本编辑窗体不可用！
			     final JComboBox jc = new JComboBox(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
			     jc.setLocation(30, 80);
			     Container c = ztsz.getContentPane();
			     JPanel jp = new JPanel();
			     jp.add(jc,new FlowLayout());
	 
			     //字形
			     String[]   faceString={"正常","粗体","斜体","粗斜体"};
			     String[]   sizeString={"初号","小初","一号","小一","二号","小二",
			                  			"三号","小三","四号","小四","五号","小五","六号","小六","七号",
			                  			"八号","5","8","9","10","11","12","14","16","18","20","22","24",
			                  			"26","28","36","48","72"};
			     final JComboBox zx = new JComboBox(faceString);
			     final JComboBox dx = new JComboBox(sizeString);
			     final JButton sure = new JButton("确定");
			     final JButton cancel = new JButton("取消");
			           
			     jp.add(zx);
			     jp.add(dx);
			     jp.add(sure);
			     jp.add(cancel);
			     c.add(jp);
	 
	 
			     //将文本设置成所选的字体
			     sure.addActionListener(new ActionListener(){
			    	 public void actionPerformed(ActionEvent e){
			    		 if(!text.getText().equals("")){
			    			 text.setFont(new Font(
			    					 jc.getActionCommand(),zx.getSelectedIndex(),dx.getSelectedIndex()));
			                 frame.setEnabled(true);//文本编辑窗体可用
			                 ztsz.dispose();
			             }
			             else{
			                 JOptionPane.showMessageDialog(null,
			                 "您的文本中还没有内容，请输入内容后重新设置！" 
			                 ,"消息...",JOptionPane.INFORMATION_MESSAGE);
			                 frame.setEnabled(true);
			                 ztsz.dispose();
			                 }
			    	 }
			     });
			     cancel.addActionListener(new ActionListener(){//取消
			         public void actionPerformed(ActionEvent e){
			                frame.setEnabled(true);//文本编辑窗体可用
			                ztsz.dispose();//关闭字体设置窗体
			         }
			     });
			     ztsz.setSize(360, 100);//设置窗体长度100和宽度360
			     ztsz.setVisible(true);//窗体可见
			     ztsz.setResizable(false);//禁止放大窗体
			}
		});
		
		//字体颜色菜单项的功能实现
		menuForColor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				color=JColorChooser.showDialog(frame,"",color);
	            text.setForeground(color);
			}
				   
		});
		//自动换行菜单项的功能实现
		menuForWrap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(menuForWrap.getState())text.setLineWrap(true);
				else text.setLineWrap(false);
			}
		});
		//放大菜单项的功能实现
		menuViewBig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Font font = text.getFont();
				if(font.getSize() < 1000) {
					text.setFont(new Font(font.getFamily(),font.getStyle(),font.getSize()+1));//控制大小不超限
					if(text.getFont().getSize() > 10)menuViewSmall.setEnabled(true);
					if(font.getSize() == 999)menuViewBig.setEnabled(false);
				}
				else menuViewBig.setEnabled(false);//按钮不可用
			}
		});
		//缩小菜单项的功能实现
		menuViewSmall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Font font = text.getFont();
				if(font.getSize()>10) {
					text.setFont(new Font(font.getFamily(),font.getStyle(),font.getSize()-1));//控制大小不超限
					if(text.getFont().getSize() < 1000)menuViewBig.setEnabled(true);
					if(font.getSize() == 11)menuViewSmall.setEnabled(false);
				}
				else menuViewSmall.setEnabled(false);//按钮不可用
			}
		});
		//关于菜单项的功能实现
//		menuHelpAbout.addActionListener(new ActionListener() {//关于
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JFrame frame = new JFrame("文档编辑器");
//				JLabel lab = new JLabel("哈哈=====啥也没有=====哈哈");
//				lab.setHorizontalAlignment(JLabel.CENTER);
//				frame.add(lab,BorderLayout.CENTER);
//				frame.setLocation(350,150);
//				frame.setSize(400,100);
//				frame.setVisible(true);
//				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//			}
//		});
		//API文档生成
		menuHelp.addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent e) {
				try {
					Runtime.getRuntime().exec("cmd /c start file:///C:/Users/longbin/eclipse-workspace/book/doc/book/package-summary.html");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		//关闭功能实现
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(change) {
					if(New) {
						int res=JOptionPane.showConfirmDialog(frame, "是否退出？", "文档编辑器", JOptionPane.YES_NO_OPTION);
						if(res==JOptionPane.YES_OPTION) {
							int f=JOptionPane.showConfirmDialog(frame, "是否保存到：" + url + "\\无标题.txt", 
																								"文档编辑器", JOptionPane.YES_NO_OPTION);
							if(f==JOptionPane.YES_OPTION)Save(new File(url+"\\无标题.txt"));
							System.exit(0);
						}
					} else {
						int res=JOptionPane.showConfirmDialog(frame, "是否退出？", "My Note", JOptionPane.YES_NO_OPTION);
						if(res==JOptionPane.YES_OPTION) {
							int f=JOptionPane.showConfirmDialog(frame, "是否保存到："+F.getPath(), "文档编辑器", JOptionPane.YES_NO_OPTION);
							if(f==JOptionPane.YES_OPTION)Save(F);
							System.exit(0);
						}
					}
				}
				else {
					System.exit(0);
				}
			}
		});
		  //鼠标右键的监听       
		        text.addMouseListener( new  MouseAdapter()
		    {
		       public   void  mouseReleased(MouseEvent e)
		        {
		           if (e.isPopupTrigger())
		            {
		              popupMenu.show(e.getComponent(),e.getX(),e.getY());
		          }
		      }
		  } );
	   
		
		frame.setLocation(300,100);
		frame.setSize(1000,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//设置关闭效果减半，仅触发WindowListener中的windowClosing方法
	}
	//读到文本区域
	public void Read(File f) {
		try {
			FileInputStream in = new FileInputStream(f);
			int len = (int )f.length()*2;
			byte bt[] = new byte[len];
			int l = in.read(bt);
			text.setText("");//清空
			text.setText(new String(bt,0,l));
			change = false;//读取肯定作了插入操作会触发事件
			undom = new UndoManager();//每次读取后就添加一个新的撤销监听
			text.getDocument().addUndoableEditListener(undom);//读取之后再添加撤销,监听文本是否能撤销或者重复
			frame.setTitle(F.getName());
			in.close();
		} catch (IOException e) {}
	}
	//保存到文件
	public void Save(File f) {
		frame.setTitle(F.getName());
		try {
			FileOutputStream out = new FileOutputStream(f);
			byte[] bt = text.getText().getBytes();
			out.write(bt);
			out.close();
		} catch (IOException e) {}
	}

}
