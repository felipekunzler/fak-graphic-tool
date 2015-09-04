package fak.graphicTool;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DeviationRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;
import javax.swing.JRadioButtonMenuItem;

public class MainWindow {
	
	private static final String[] ALLOWED_EXTENSIONS = {"jpg", "jpeg", "png", "bmp", "gif"};

	private JFrame frame;
	
	@UsesMessagesText(hasToolTip = true)
	private JButton btnA;
	@UsesMessagesText(hasToolTip = true)
	private JButton btnB;	
	@UsesMessagesText(hasToolTip = true)
	private JButton btnC;	
	@UsesMessagesText(hasToolTip = true)
	private JButton btnD;	
	@UsesMessagesText(hasToolTip = true)
	private JButton btnE;	
	@UsesMessagesText(hasToolTip = true)
	private JButton btnOriginal;	
	@UsesMessagesText
	private JLabel lbImageViewer;	
	@UsesMessagesText
	private JLabel lbHistogramViewer;
	@UsesMessagesText
	private JLabel lbMeanUpperHalf;
	@UsesMessagesText
	private JLabel lbMedianLowerHalf;
	@UsesMessagesText
	private JLabel lbVariance;	
	@UsesMessagesText
	private JLabel lbMode;	
	@UsesMessagesText	
	private JLabel lbApplyEffects;
	@UsesMessagesText
	private JMenu mnFile;
	@UsesMessagesText
	private JMenu mnHelp;
	@UsesMessagesText
	private JMenuItem mnLanguage;
	@UsesMessagesText
	private JMenuItem mntmAbout;
	@UsesMessagesText
	private JMenuItem mntmExit;
	@UsesMessagesText
	private JMenuItem mntmOpen;
	@UsesMessagesText
	private JRadioButtonMenuItem rdbMenuEn;
	@UsesMessagesText
	private JRadioButtonMenuItem rdbMenuPt;

	private Picture picture;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		
		initializeComponents();
		
		BufferedImage defaultImage = null;
		try {
			defaultImage = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream("lena.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.picture = new Picture(defaultImage);
		
		this.refreshPictureInfo();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeComponents() {
		
		frame = new JFrame();
		frame.setTitle(Messages.getString("MainWindow.frame.title"));
		frame.setResizable(false);
		frame.setBounds(100, 100, 644, 466);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		try {
			Image icon = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream("icon.png"));
			frame.setIconImage(icon);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		this.lbApplyEffects = new JLabel(Messages.getString("MainWindow.lbApplyEffects.text"));
		this.lbApplyEffects.setOpaque(true);
		this.lbApplyEffects.setBackground(this.frame.getBackground());
		this.lbApplyEffects.setBounds(30, 25, 75, 14);
		frame.getContentPane().add(this.lbApplyEffects);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelButtons.setBounds(8, 32, 215, 114);
		frame.getContentPane().add(panelButtons);
		panelButtons.setLayout(null);
		
		this.btnA = new JButton(Messages.getString("MainWindow.btnA.text"));
		this.btnA.setToolTipText(Messages.getString("MainWindow.btnA.toolTipText"));
		this.btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				picture.modifyImage('a');
				refreshPictureInfo();
			}
		});
		this.btnA.setBounds(14, 11, 89, 23);
		panelButtons.add(this.btnA);
		
		this.btnB = new JButton(Messages.getString("MainWindow.btnB.text"));
		this.btnB.setToolTipText(Messages.getString("MainWindow.btnB.toolTipText"));
		this.btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				picture.modifyImage('b');
				refreshPictureInfo();
			}
		});
		this.btnB.setBounds(113, 11, 89, 23);
		panelButtons.add(this.btnB);
		
		this.btnC = new JButton(Messages.getString("MainWindow.btnC.text"));
		this.btnC.setToolTipText(Messages.getString("MainWindow.btnC.toolTipText")); 
		this.btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				picture.modifyImage('c');
				refreshPictureInfo();
			}
		});
		this.btnC.setBounds(14, 45, 89, 23);
		panelButtons.add(this.btnC);
		
		this.btnD = new JButton(Messages.getString("MainWindow.btnD.text"));
		this.btnD.setToolTipText(Messages.getString("MainWindow.btnD.toolTipText"));
		this.btnD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				picture.modifyImage('d');
				refreshPictureInfo();
			}
		});
		this.btnD.setBounds(113, 45, 89, 23);
		panelButtons.add(this.btnD);
		
		this.btnE = new JButton(Messages.getString("MainWindow.btnE.text"));
		this.btnE.setToolTipText(Messages.getString("MainWindow.btnE.toolTipText"));
		this.btnE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				picture.modifyImage('e');
				refreshPictureInfo();
			}
		});
		this.btnE.setBounds(14, 79, 89, 23);
		panelButtons.add(this.btnE);
		
		this.btnOriginal = new JButton(Messages.getString("MainWindow.btnOriginal.text"));
		this.btnOriginal.setToolTipText(Messages.getString("MainWindow.btnOriginal.toolTipText"));
		this.btnOriginal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				picture.restoreOriginalImage();
				refreshPictureInfo();
			}
		});
		this.btnOriginal.setBounds(113, 79, 89, 23);
		panelButtons.add(this.btnOriginal);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 664, 21);
		frame.getContentPane().add(menuBar);
		
		this.mnFile = new JMenu(Messages.getString("MainWindow.mnFile.text"));
		menuBar.add(mnFile);
		
		this.mntmOpen = new JMenuItem(Messages.getString("MainWindow.mntmOpen.text"));
		this.mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPictureDialog();
			}
		});
		
		this.mnFile.add(this.mntmOpen);
		
		JSeparator separator = new JSeparator();
		this.mnFile.add(separator);
		
		this.mntmExit = new JMenuItem(Messages.getString("MainWindow.mntmExit.text"));
		this.mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		this.mnFile.add(mntmExit);
		
		this.mnHelp = new JMenu(Messages.getString("MainWindow.mnHelp.text"));
		menuBar.add(this.mnHelp);
		
		this.mnLanguage = new JMenu(Messages.getString("MainWindow.mnLanguage.text"));
		this.mnHelp.add(this.mnLanguage);
		
		this.rdbMenuEn = new JRadioButtonMenuItem(Messages.getString("MainWindow.rdbMenuEn.text"));
		this.rdbMenuEn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbMenuPt.setSelected(!rdbMenuPt.isSelected());
				Messages.setResourceBundle(Messages.BUNDLE_NAME_EN);
				refreshTexts();
			}
		});
		this.rdbMenuEn.setSelected(true);
		mnLanguage.add(this.rdbMenuEn);
		
		this.rdbMenuPt = new JRadioButtonMenuItem(Messages.getString("MainWindow.rdbMenuPt.text"));
		this.rdbMenuPt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbMenuEn.setSelected(!rdbMenuEn.isSelected());
				Messages.setResourceBundle(Messages.BUNDLE_NAME_PT);
				refreshTexts();
			}
		});
		mnLanguage.add(this.rdbMenuPt);
		
		this.mntmAbout = new JMenuItem(Messages.getString("MainWindow.mntmAbout.text"));
		this.mnHelp.add(this.mntmAbout);

		JPanel panelInformation = new JPanel();
		panelInformation.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelInformation.setBounds(8, 151, 215, 108);
		frame.getContentPane().add(panelInformation);
		panelInformation.setLayout(null);

		this.lbMeanUpperHalf = new JLabel(Messages.getString("MainWindow.lbMeanUpperHalf.text"));
		this.lbMeanUpperHalf.setBounds(13, 11, 192, 14);
		panelInformation.add(this.lbMeanUpperHalf);

		this.lbMedianLowerHalf = new JLabel(Messages.getString("MainWindow.lbMedianLowerHalf.text"));
		this.lbMedianLowerHalf.setBounds(13, 36, 192, 14);
		panelInformation.add(this.lbMedianLowerHalf);

		this.lbVariance = new JLabel(Messages.getString("MainWindow.lbVariance.text"));
		this.lbVariance.setBounds(13, 86, 192, 14);
		panelInformation.add(this.lbVariance);

		this.lbMode = new JLabel(Messages.getString("MainWindow.lbMode.text"));
		this.lbMode.setBounds(13, 61, 192, 14);
		panelInformation.add(this.lbMode);

		this.lbImageViewer = new JLabel("");
		this.lbImageViewer.setBounds(233, 32, 398, 398);
		frame.getContentPane().add(this.lbImageViewer);
		
		this.lbHistogramViewer = new JLabel("");
		this.lbHistogramViewer.setBounds(0, 259, 230, 174);
		frame.getContentPane().add(this.lbHistogramViewer);
	}
	
	private void refreshTexts(){
		for (Field field: MainWindow.class.getDeclaredFields()) {						
			field.setAccessible(true);
			UsesMessagesText messageTextAnnotation = field.getAnnotation(UsesMessagesText.class);
			if (messageTextAnnotation != null){
				this.invokeMethod(field, "setText", "text");
				
				if (messageTextAnnotation.hasToolTip()){
					this.invokeMethod(field, "setToolTipText", "toolTipText");
				}
			}
		}
		
		refreshPictureInfo();
	}
	
	private void invokeMethod(Field field, String methodName, String partialKey){
		String key = MainWindow.class.getSimpleName() + "." + field.getName() + "." + partialKey;
		try {
			Method method = field.getType().getMethod(methodName, String.class);
			method.invoke(field.get(this), Messages.getString(key));
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	private void refreshHistogram(){
		
		int[] histogram = this.picture.getHistogramValues();
		
		final XYSeries series = new XYSeries("");
		for(int i = 0; i < histogram.length; i++) {
			series.add(i+2, histogram[i]);
		}
		
        final XYSeriesCollection dataset = new XYSeriesCollection(series);

        PlotOrientation orientation = PlotOrientation.VERTICAL; 
        JFreeChart chart = ChartFactory.createHistogram(
        		"", // Title
        		"", // X label
        		"", // Y label
                dataset, // Dataset
                orientation, // Orientation
                false, // Legend
                false, // Tooltips
                false); // Urls
        
        chart.setBackgroundPaint(this.frame.getBackground());
        
        XYPlot plot = (XYPlot) chart.getPlot();
        
        ValueAxis range = plot.getRangeAxis();
        range.setVisible(false);      
        
        ValueAxis domain = plot.getDomainAxis();
        domain.setVisible(false);    
        
        DeviationRenderer renderer = new DeviationRenderer(true, false);
        plot.setRenderer(renderer);
        
        plot.setAxisOffset(RectangleInsets.ZERO_INSETS);
        
        int viewerHeight = this.lbHistogramViewer.getHeight();
        int viewerWidth = this.lbHistogramViewer.getWidth();
        BufferedImage bi = chart.createBufferedImage(viewerWidth, viewerHeight);
        
        this.lbHistogramViewer.setIcon(new ImageIcon(bi.getScaledInstance(viewerWidth, viewerHeight, Image.SCALE_SMOOTH)));
	}
	
	private void openPictureDialog() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setFileFilter(new FileNameExtensionFilter(Messages.getString("MainWindow.ImageFilterDialog.text"), ALLOWED_EXTENSIONS));
		int result = fileChooser.showOpenDialog(frame);
		if (result == JFileChooser.APPROVE_OPTION) {
			this.picture = new Picture(fileChooser.getSelectedFile().getAbsolutePath());
			refreshPictureInfo();
		}
	}

	private void refreshPictureInfo() {
		ImageIcon stretchedImage = this.picture.getStretchedImage(this.lbImageViewer.getWidth(),
				this.lbImageViewer.getHeight());
		this.lbImageViewer.setIcon(stretchedImage);
		this.refreshHistogram();
		
		this.lbMeanUpperHalf.setText(Messages.getString("MainWindow.lbMeanUpperHalf.text") + Integer.toString(this.picture.getMeanUpperHalf()));
		this.lbMedianLowerHalf.setText(Messages.getString("MainWindow.lbMedianLowerHalf.text") + Integer.toString(this.picture.getMedianLowerHalf()));
		this.lbMode.setText(Messages.getString("MainWindow.lbMode.text") + Integer.toString(this.picture.getMode()));
		this.lbVariance.setText(Messages.getString("MainWindow.lbVariance.text") + Integer.toString(this.picture.getVariance()));
	}
}
