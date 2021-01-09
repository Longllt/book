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
 * �ı��༭�����漰����ʵ����
 * @author longbin
 *
 */
class NoteBook{
	//����һ������
	JFrame frame = new JFrame("�ĵ��༭��");
	//����һ���˵���
	JMenuBar bar = new JMenuBar();
	//���������˵�
	JMenu menuFile = new JMenu("�ļ�(F)");
	JMenu menuEdit = new JMenu("�༭(E)");
	JMenu menuFormat = new JMenu("��ʽ(O)");
	JMenu menuView = new JMenu("�鿴(V)");
	JMenu menuHelp = new JMenu("����(H)");
	//�����ļ��˵��еĸ����˵���
	JMenuItem menuFileNew = new JMenuItem("�½�(N)");
	JMenuItem menuFileOpen = new JMenuItem("��(O)");
	JMenuItem menuFileSave = new JMenuItem("����(S)");
	JMenuItem menuFileSaveAs = new JMenuItem("���Ϊ(A)");
	JMenuItem menuFileExit = new JMenuItem("�˳�(E)");
	//�����༭�˵��еĸ����˵���
	JMenuItem menuEditUndo = new JMenuItem("����(Z)");
	JMenuItem menuEditRepeat = new JMenuItem("�ظ�(Y)");
	JMenuItem menuEditCopy = new JMenuItem("����(C)");
	JMenuItem menuEditPaste = new JMenuItem("ճ��(V)");
	JMenuItem menuEditCut = new JMenuItem("����(X)");
	JMenuItem menuEditDelete = new JMenuItem("ɾ��(D)");
	JMenuItem menuEditFind = new JMenuItem("����(F)");
	JMenuItem menuEditReplace = new JMenuItem("�滻(H)");
	JMenuItem menuEditGoto = new JMenuItem("ת��(G)");
	JMenuItem menuEditSelectAll = new JMenuItem("ȫѡ(A)");
	JMenuItem menuEditTime = new JMenuItem("ʱ��/����(D)");
	//������ʽ�˵��еĸ����˵���
	JMenuItem menuForFont = new JMenuItem("����(T)");
	JMenuItem menuForColor = new JMenuItem("��ɫ(Y)");
	JCheckBoxMenuItem menuForWrap = new JCheckBoxMenuItem("�Զ�����");
	//�����鿴�˵��еĸ����˵���
	JMenuItem menuViewBig = new JMenuItem("�Ŵ�(I)");
	JMenuItem menuViewSmall = new JMenuItem("��С(I)");
	//���������˵��еĸ����˵���
	JMenuItem menuHelpAbout = new JMenuItem("����...");
	//�����һ��˵�
	JPopupMenu popupMenu = new JPopupMenu();
	JMenuItem Undo = new JMenuItem("����(Z)");
	JMenuItem Cut = new JMenuItem("����(X)");
	JMenuItem Copy = new JMenuItem("����(C)");
	JMenuItem Paste = new JMenuItem("ճ��(V)");
	JMenuItem SelectAll = new JMenuItem("ȫѡ(A)");
	JMenuItem Delete = new JMenuItem("ɾ��(D)");
	JMenuItem Time = new JMenuItem("ʱ��/����(D)");
	//�����ı���
	JTextArea text = new JTextArea();
	//�����м��������
	JScrollPane center ;
	//����Ĭ������	
	Font font = new Font("΢���ź�",Font.BOLD,13);
	//�ж��Ƿ����˸Ķ�
	boolean change = false;
	//�ж��Ƿ��½���
	boolean New = false;
	//�����ظ�������
	UndoManager undom ;
	//����Ĭ���ļ�·��
	String url = "C:\\Users\\longbin\\Desktop\\����";//Ĭ��·���ļ���
	File F = new File(url+"\\�ĵ��༭��.txt");
	Color color = Color.black ;
	
	//���췽����ʼ���˵�
	public NoteBook() {
		//�ı�������Ĭ������
		text.setFont(font);
		//�ı������������
		center = new JScrollPane(text);
		//���ı����м�������
		frame.add(text);
		//���˵�����������
		frame.setJMenuBar(bar);
		
		//�Ѳ˵����뵽�˵�����
		bar.add(menuFile);
		bar.add(menuEdit);
		bar.add(menuFormat);
		bar.add(menuView);
		bar.add(menuHelp);
				
		//�Ѳ˵�����뵽�˵���
		//===���ļ��˵��ͱ༭�˵�����˵���
		menuFile.add(menuFileNew);
		menuFile.add(menuFileOpen);
		menuFile.addSeparator();//���ӷָ���
		menuFile.add(menuFileSave);
		menuFile.add(menuFileSaveAs);
		menuFile.addSeparator();//���ӷָ���
		menuFile.add(menuFileExit);
						
		//===��༭�˵�����˵���
		menuEdit.add(menuEditUndo);
		menuEdit.add(menuEditRepeat);
		menuEdit.addSeparator();//���ӷָ���
		menuEdit.add(menuEditCopy);
		menuEdit.add(menuEditPaste);
		menuEdit.add(menuEditCut);
		menuEdit.add(menuEditDelete);
		menuEdit.addSeparator();//���ӷָ���
		menuEdit.add(menuEditFind);
		menuEdit.add(menuEditReplace);
		menuEdit.add(menuEditGoto);
		menuEdit.addSeparator();//���ӷָ���
		menuEdit.add(menuEditSelectAll);
		menuEdit.addSeparator();//���ӷָ���
		menuEdit.add(menuEditTime);
						
		//===���ʽ�˵�����˵���
		menuFormat.add(menuForFont);
		menuFormat.add(menuForColor);
		menuFormat.add(menuForWrap);
		
		//===���ʽ�˵�����˵���
		menuView.add(menuViewBig);
		menuView.add(menuViewSmall);

		//===������˵�����˵���
		//menuHelp.add(menuHelpAbout);
		
		//���Ҽ��Ĳ˵�����ӵ��˵���
		popupMenu.add(Undo);
        popupMenu.add(Copy);
        popupMenu.add(Paste);
        popupMenu.add(Cut);
        popupMenu.add(Delete);
        popupMenu.add(SelectAll);
        popupMenu.add(Time);
		//�����ݼ�
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

		//�����в˵����Ӽ�����
		//�����˱�ʾ���˸Ķ�
		text.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				change = true;
				if(frame.getTitle().compareTo("�ޱ���")==0 || frame.getTitle().compareTo("*�ޱ���")==0)frame.setTitle("*�ޱ���");
				else frame.setTitle("*"+F.getName());//δ����״̬
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				change = true;
				if(frame.getTitle().compareTo("�ޱ���")==0 || frame.getTitle().compareTo("*�ޱ���")==0)frame.setTitle("*�ޱ���");
				else frame.setTitle("*"+F.getName());//δ����״̬
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				change = true;
				if(frame.getTitle().compareTo("�ޱ���")==0 || frame.getTitle().compareTo("*�ޱ���")==0)frame.setTitle("*�ޱ���");
				else frame.setTitle("*"+F.getName());//δ����״̬
			}
		});
		
		//�½��˵���Ĺ���ʵ��
		menuFileNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(change) {//�ж��ĵ��Ƿ����˸Ķ�
					//�ж��ǲ����½����ĵ�
					if(!New) {//û���½�
						//�����ǲ��Ǳ���Դ�ļ��ĶԻ���
						int f=JOptionPane.showConfirmDialog(frame, "�Ƿ񱣴�ԭ�ļ���", "�ĵ��༭��", JOptionPane.YES_NO_OPTION);
						//���ѡ�񱣴棬�ͽ��ĵ�����
						if(f==JOptionPane.YES_OPTION)Save(new File(url+"\\�ޱ���.txt"));
					}else {//�½���
						//�����ǲ���Ҫ���浽�ޱ���
						int f=JOptionPane.showConfirmDialog(frame, "����Ҫ�����ı��浽�ޱ�����", "�ĵ��༭��", JOptionPane.YES_NO_OPTION);
						if(f==JOptionPane.YES_OPTION) Save(new File(url+"\\�ޱ���.txt"));//���浽Ĭ��·���ޱ���
					}
				}
				New = true;//�½���
				text.setText("");//���
				undom = new UndoManager();
				text.getDocument().addUndoableEditListener(undom);//�½�ҲҪ���¼��볷������
				change = false;//�½���Ķ����ǻ״̬
				frame.setTitle("�ޱ���");//�½���δ�����û�б���
			}
		});
		//�򿪲˵���Ĺ���ʵ��
		menuFileOpen.addActionListener(new ActionListener() {//��
			@Override
			public void actionPerformed(ActionEvent e) {
				if(change) {
					int res=JOptionPane.showConfirmDialog(frame, "�Ƿ񱣴�ԭ�ļ���", "�ĵ��༭��", JOptionPane.YES_NO_OPTION);
					if(res==JOptionPane.YES_OPTION) {
						if(New) {//���½�����Ҫ����
							int f=JOptionPane.showConfirmDialog(frame, "����Ҫ�����ı��浽�ޱ�����", "�ĵ��༭��", JOptionPane.YES_NO_OPTION);
							if(f==JOptionPane.YES_OPTION) Save(new File(url+"\\�ޱ���.txt"));//���浽Ĭ��·���ޱ���
						}else {
							int f=JOptionPane.showConfirmDialog(frame, "�Ƿ񱣴�ԭ�ļ���", "�ĵ��༭��", JOptionPane.YES_NO_OPTION);
							if(f==JOptionPane.YES_OPTION)Save(F);
						}
					}
				}
				int result=0;
				JFileChooser filec=new JFileChooser(url);//Ĭ���ļ���λ��
				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt�ļ�(*.txt)", "txt");
			   	filec.setFileFilter(filter);//�����ļ�������
				filec.setDialogTitle("��");//�����ı�ѡ������
				result=filec.showOpenDialog(frame);//���ɴ򿪶Ի���
				if(result==JFileChooser.APPROVE_OPTION) {//��ʾѡ����ȷ����ť
					F=filec.getSelectedFile();//�����ļ�λ��
					Read(F);
					New = false;//��ζ�Ų����½�����
				}
			}
		});
		//����˵���Ĺ���ʵ��
		menuFileSave.addActionListener(new ActionListener() {//����
			@Override
			public void actionPerformed(ActionEvent e) {
				if(change) {
					if(New) {//���½��ļ������иĶ�
						int result=0;
						JFileChooser filec=new JFileChooser(url);
						FileNameExtensionFilter filter = new FileNameExtensionFilter("txt�ļ�(*.txt)", "txt");
					   	filec.setFileFilter(filter);//�����ļ�������
						String defaultFileName="�½��ı��ļ�"+".txt";//Ĭ���ļ�������
						filec.setSelectedFile(new File(defaultFileName)); //����Ĭ���ļ���
						filec.setDialogTitle("���Ϊ");//�����ı�ѡ������
						result=filec.showSaveDialog(frame);
						if(result==JFileChooser.APPROVE_OPTION) {//��ʾѡ����ȷ����ť
							File doc = filec.getCurrentDirectory();//Ŀ¼
							File file = filec.getSelectedFile();//Ҫ������ļ�
							String[] st = doc.list();
							boolean falg = false;//�ж�������
							for(String x:st) {
								if(x.compareTo(file.getName())==0)falg = true;//Ŀ¼�´�������һ�����ļ�
							}
							if(falg) {
								int f=JOptionPane.showConfirmDialog(null, "�Ƿ񸲸ǣ�", "�ĵ��༭��", JOptionPane.YES_NO_OPTION);
								if(f==JOptionPane.YES_OPTION) {
									F=file;//����Ĭ���ļ�λ��
									Save(F);
								}
							}
							else {
								F=filec.getSelectedFile();//����Ĭ���ļ�
								Save(F);
							}
							change = false;//��������û�иĶ���
							frame.setTitle(F.getName());//����״̬
							New = false;//û���滹���´���
						}
					}
					else {
						Save(F);
						change = false;//��������û�иĶ���
						frame.setTitle(F.getName());//����״̬
					}
				}
			}
		});
		//���Ϊ�˵���Ĺ���ʵ��
		menuFileSaveAs.addActionListener(new ActionListener() {//���Ϊ
			@Override
			public void actionPerformed(ActionEvent e) {
				int result=0;
				JFileChooser filec=new JFileChooser(url);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt�ļ�(*.txt)", "txt");
			   	filec.setFileFilter(filter);//�����ļ�������
				String defaultFileName="�½��ı��ļ�"+".txt";//Ĭ���ļ�������
				filec.setSelectedFile(new File(defaultFileName)); //����Ĭ���ļ���
				filec.setDialogTitle("���Ϊ");//�����ı�ѡ������
				result=filec.showSaveDialog(frame);
				if(result==JFileChooser.APPROVE_OPTION) {//��ʾѡ����ȷ����ť
					File doc = filec.getCurrentDirectory();//Ŀ¼
					File file = filec.getSelectedFile();//Ҫ������ļ�
					String[] st = doc.list();
					boolean falg = false;
					for(String x:st) {
						if(x.compareTo(file.getName())==0)falg = true;//Ŀ¼�´�������һ�����ļ�
					}
					if(falg) {
						int f=JOptionPane.showConfirmDialog(null, "�Ƿ񸲸ǣ�", "�ĵ��༭��", JOptionPane.YES_NO_OPTION);
						if(f==JOptionPane.YES_OPTION) {
							F=file;//����Ĭ���ļ�λ��
							Save(F);
						}
					}
					else {
						F=filec.getSelectedFile();//����Ĭ���ļ�
						Save(F);
					}
				}
			}
		});
		//�˳��˵���Ĺ���ʵ��
		menuFileExit.addActionListener(new ActionListener(){//�˳�
			@Override
			public void actionPerformed(ActionEvent e) {
				if(change) {
					if(New) {
						int res=JOptionPane.showConfirmDialog(frame, "�Ƿ��˳���", "�ĵ��༭��", JOptionPane.YES_NO_OPTION);
						if(res==JOptionPane.YES_OPTION) {
							int f=JOptionPane.showConfirmDialog(frame, "�Ƿ񱣴浽��" + url + "\\�ޱ���.txt", 
																								"�ĵ��༭��", JOptionPane.YES_NO_OPTION);
							if(f==JOptionPane.YES_OPTION)Save(new File(url+"\\�ޱ���.txt"));
							System.exit(0);
						}
					} else {
						int res=JOptionPane.showConfirmDialog(frame, "�Ƿ��˳���", "�ĵ��༭��", JOptionPane.YES_NO_OPTION);
						if(res==JOptionPane.YES_OPTION) {
							int f=JOptionPane.showConfirmDialog(frame, "�Ƿ񱣴浽��"+F.getPath(), "�ĵ��༭��", JOptionPane.YES_NO_OPTION);
							if(f==JOptionPane.YES_OPTION)Save(F);
							System.exit(0);
						}
					}
				}
				else {

					menuFileExit.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								Object[] options = { "�ǵģ���Ҫ�˳�", "������˼�������" };
								int option = JOptionPane.showOptionDialog(null, "��ȷ��Ҫ�˳���",
										 "�˳���ʾ....",JOptionPane.OK_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE,
										  null,options, options[0]);     
								 if(option == JOptionPane.OK_OPTION){
								 System.exit(0);
								 }
							}
							
							   
						   });
				}
			}
		});
		//�����˵���Ĺ���ʵ��
		menuEditUndo.addActionListener(new ActionListener() {//����
			@Override
			public void actionPerformed(ActionEvent e) {
				if(undom.canUndo()) {//�ж��Ƿ��ܳ���
					undom.undo();//��������
				}
			}
		});
		//�ظ��˵���Ĺ���ʵ��
		menuEditRepeat.addActionListener(new ActionListener() {//�ظ�
			@Override
			public void actionPerformed(ActionEvent e) {
				if(undom.canRedo()) {//�ж��Ƿ����ظ�
					undom.redo();//�ظ�����
				}
			}
		});
		//���в˵���Ĺ���ʵ��
		menuEditCut.addActionListener(new ActionListener() {//����
			@Override
			public void actionPerformed(ActionEvent e) {
				text.cut();
			}
		});
		//���Ʋ˵���Ĺ���ʵ��
		menuEditCopy.addActionListener(new ActionListener() {//����
			@Override
			public void actionPerformed(ActionEvent e) {
				text.copy();
			}
		});
		//ճ���˵���Ĺ���ʵ��
		menuEditPaste.addActionListener(new ActionListener() {//ճ��
			@Override
			public void actionPerformed(ActionEvent e) {
				text.paste();
			}
		});
		
		//ɾ���˵���Ĺ���ʵ��
		menuEditDelete.addActionListener(new ActionListener() {//ɾ��
			@Override
			public void actionPerformed(ActionEvent e) {
				text.replaceSelection("");
			}
		});
		
		//���Ҳ˵���Ĺ���ʵ��
		menuEditFind.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				final JDialog dialog = new JDialog(frame,"�����ַ���...",true);
				dialog.setBounds(560,250,310,130);
				JLabel find = new JLabel("�������ַ��� :");
				final JTextField findtext = new JTextField(1);
				JButton jbu = new JButton("����");
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
				        //���Ҳ��������ַ������򽫹������ĩβ 
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
		//�滻�˵���Ĺ���ʵ��
		menuEditReplace.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				final JDialog dialog = new JDialog(frame,"�ַ����滻...",true);
				  dialog.setBounds(560,250,310,180);
				  final JLabel tihuan = new JLabel("������Ҫ�滻���ַ��� ��");
				  JLabel mubiao = new JLabel("�������滻����ַ��� ��");
				  JTextField jtf1 = new JTextField(10);
				  JTextField jtf2 = new JTextField(10);
				  JButton jb = new JButton("�滻");
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
		                  //���Ҳ��������ַ������򽫹������ĩβ
		                 text.setSelectionStart(end);
		                 text.setSelectionEnd(end);
		                 }
		                
				       }
				   
			        });
				  dialog.setResizable(false);
	              dialog.setVisible(true);
			}
			   
		});
		//ת���˵���Ĺ���ʵ��
		menuEditGoto.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
						
			}
					   
		});
		//ȫѡ�˵���Ĺ���ʵ��
		menuEditSelectAll.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				text.selectAll();
			}
			   
		});
		//ʱ��/���ڲ˵����ʵ��
		menuEditTime.addActionListener(new ActionListener() {//����
			@Override
			public void actionPerformed(ActionEvent e) {
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy �� MM �� dd �� HH ʱ mm �� ss �� ");
			Date date = new Date(System.currentTimeMillis());
			String data = formatter.format(date);
			JOptionPane.showMessageDialog(frame,data, "��ǰʱ��", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		//����˵���Ĺ���ʵ��
		menuForFont.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				 final JFrame ztsz = new JFrame("��������...");//�������ô���
			     //����
			     ztsz.setLocation(150, 200);
			     frame.setEnabled(false);//�ı��༭���岻���ã�
			     final JComboBox jc = new JComboBox(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
			     jc.setLocation(30, 80);
			     Container c = ztsz.getContentPane();
			     JPanel jp = new JPanel();
			     jp.add(jc,new FlowLayout());
	 
			     //����
			     String[]   faceString={"����","����","б��","��б��"};
			     String[]   sizeString={"����","С��","һ��","Сһ","����","С��",
			                  			"����","С��","�ĺ�","С��","���","С��","����","С��","�ߺ�",
			                  			"�˺�","5","8","9","10","11","12","14","16","18","20","22","24",
			                  			"26","28","36","48","72"};
			     final JComboBox zx = new JComboBox(faceString);
			     final JComboBox dx = new JComboBox(sizeString);
			     final JButton sure = new JButton("ȷ��");
			     final JButton cancel = new JButton("ȡ��");
			           
			     jp.add(zx);
			     jp.add(dx);
			     jp.add(sure);
			     jp.add(cancel);
			     c.add(jp);
	 
	 
			     //���ı����ó���ѡ������
			     sure.addActionListener(new ActionListener(){
			    	 public void actionPerformed(ActionEvent e){
			    		 if(!text.getText().equals("")){
			    			 text.setFont(new Font(
			    					 jc.getActionCommand(),zx.getSelectedIndex(),dx.getSelectedIndex()));
			                 frame.setEnabled(true);//�ı��༭�������
			                 ztsz.dispose();
			             }
			             else{
			                 JOptionPane.showMessageDialog(null,
			                 "�����ı��л�û�����ݣ����������ݺ��������ã�" 
			                 ,"��Ϣ...",JOptionPane.INFORMATION_MESSAGE);
			                 frame.setEnabled(true);
			                 ztsz.dispose();
			                 }
			    	 }
			     });
			     cancel.addActionListener(new ActionListener(){//ȡ��
			         public void actionPerformed(ActionEvent e){
			                frame.setEnabled(true);//�ı��༭�������
			                ztsz.dispose();//�ر��������ô���
			         }
			     });
			     ztsz.setSize(360, 100);//���ô��峤��100�Ϳ��360
			     ztsz.setVisible(true);//����ɼ�
			     ztsz.setResizable(false);//��ֹ�Ŵ���
			}
		});
		
		//������ɫ�˵���Ĺ���ʵ��
		menuForColor.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				color=JColorChooser.showDialog(frame,"",color);
	            text.setForeground(color);
			}
				   
		});
		//�Զ����в˵���Ĺ���ʵ��
		menuForWrap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(menuForWrap.getState())text.setLineWrap(true);
				else text.setLineWrap(false);
			}
		});
		//�Ŵ�˵���Ĺ���ʵ��
		menuViewBig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Font font = text.getFont();
				if(font.getSize() < 1000) {
					text.setFont(new Font(font.getFamily(),font.getStyle(),font.getSize()+1));//���ƴ�С������
					if(text.getFont().getSize() > 10)menuViewSmall.setEnabled(true);
					if(font.getSize() == 999)menuViewBig.setEnabled(false);
				}
				else menuViewBig.setEnabled(false);//��ť������
			}
		});
		//��С�˵���Ĺ���ʵ��
		menuViewSmall.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Font font = text.getFont();
				if(font.getSize()>10) {
					text.setFont(new Font(font.getFamily(),font.getStyle(),font.getSize()-1));//���ƴ�С������
					if(text.getFont().getSize() < 1000)menuViewBig.setEnabled(true);
					if(font.getSize() == 11)menuViewSmall.setEnabled(false);
				}
				else menuViewSmall.setEnabled(false);//��ť������
			}
		});
		//���ڲ˵���Ĺ���ʵ��
//		menuHelpAbout.addActionListener(new ActionListener() {//����
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JFrame frame = new JFrame("�ĵ��༭��");
//				JLabel lab = new JLabel("����=====ɶҲû��=====����");
//				lab.setHorizontalAlignment(JLabel.CENTER);
//				frame.add(lab,BorderLayout.CENTER);
//				frame.setLocation(350,150);
//				frame.setSize(400,100);
//				frame.setVisible(true);
//				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//			}
//		});
		//API�ĵ�����
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
		//�رչ���ʵ��
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(change) {
					if(New) {
						int res=JOptionPane.showConfirmDialog(frame, "�Ƿ��˳���", "�ĵ��༭��", JOptionPane.YES_NO_OPTION);
						if(res==JOptionPane.YES_OPTION) {
							int f=JOptionPane.showConfirmDialog(frame, "�Ƿ񱣴浽��" + url + "\\�ޱ���.txt", 
																								"�ĵ��༭��", JOptionPane.YES_NO_OPTION);
							if(f==JOptionPane.YES_OPTION)Save(new File(url+"\\�ޱ���.txt"));
							System.exit(0);
						}
					} else {
						int res=JOptionPane.showConfirmDialog(frame, "�Ƿ��˳���", "My Note", JOptionPane.YES_NO_OPTION);
						if(res==JOptionPane.YES_OPTION) {
							int f=JOptionPane.showConfirmDialog(frame, "�Ƿ񱣴浽��"+F.getPath(), "�ĵ��༭��", JOptionPane.YES_NO_OPTION);
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
		  //����Ҽ��ļ���       
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
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//���ùر�Ч�����룬������WindowListener�е�windowClosing����
	}
	//�����ı�����
	public void Read(File f) {
		try {
			FileInputStream in = new FileInputStream(f);
			int len = (int )f.length()*2;
			byte bt[] = new byte[len];
			int l = in.read(bt);
			text.setText("");//���
			text.setText(new String(bt,0,l));
			change = false;//��ȡ�϶����˲�������ᴥ���¼�
			undom = new UndoManager();//ÿ�ζ�ȡ������һ���µĳ�������
			text.getDocument().addUndoableEditListener(undom);//��ȡ֮������ӳ���,�����ı��Ƿ��ܳ��������ظ�
			frame.setTitle(F.getName());
			in.close();
		} catch (IOException e) {}
	}
	//���浽�ļ�
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
