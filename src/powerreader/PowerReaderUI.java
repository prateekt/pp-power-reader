/*
 * PowerReaderUI.java
 *
 * Created on November 24, 2007, 7:09 PM
 */

package powerreader;

// Import J3D Stuff
import com.sun.j3d.utils.universe.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.*;
import javax.swing.JColorChooser;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;
import edu.stanford.nlp.io.ui.OpenPageDialog;
import java.awt.Component;
import javax.swing.WindowConstants;
import javax.vecmath.Point3d;
import speech.Speech;
import util.HierarchyObject;
import util.RawTextParser;
import util.TextObject3d;
import util.WordHashMap;
/**
 *
 * @author  cleung
 */
public class PowerReaderUI extends javax.swing.JFrame {
    
    public static int DEFAULT_ZOOM = -25;
    private Color DEFAULT_BG_COLOR = Color.ORANGE;
    private Color DEFAULT_FG_COLOR = Color.BLUE;
    private Color DEFAULT_HL_COLOR = Color.RED;
    
    // Manually added variables
    private Canvas3D m_canvas;
    private Background m_background;
    
    private OpenPageDialog opd;
    private BranchGroup m_sceneRoot;
    private TransformGroup rootTransformGroup;
    private HierarchyObject m_hierarchyRoot;
    private TransformGroup root_group;
    private RawTextParser rawTextParser;
    private Pick pick;
    
    // Config panel
    private ConfigUI m_configPanel;
    
    /** Creates new form PowerReaderUI */
    public PowerReaderUI() {
        initComponents();
        
        // Initialize config panel
        m_configPanel = new ConfigUI();
        m_configPanel.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        
        // Set default button colors
        m_button_bgColor.setBackground(DEFAULT_BG_COLOR);
        m_button_fgColor.setBackground(DEFAULT_FG_COLOR);
        m_button_hlColor.setBackground(DEFAULT_HL_COLOR);
        
        TextObject3d.setBaseColor(new Color3f(DEFAULT_FG_COLOR));
        TextObject3d.setHighlightColor(new Color3f(DEFAULT_HL_COLOR));
        
        opd = new OpenPageDialog(this, true);
        
        // Now initialize the 3D Canvas
        create3dCanvas();
    }
    
    private void create3dCanvas() {
        
        // Set up the canvas and scene
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        m_canvas = new Canvas3D(config);
        SimpleUniverse simpleU = new SimpleUniverse(m_canvas);
        createSceneGraph();
        simpleU.getViewingPlatform().setNominalViewingTransform();
        simpleU.addBranchGraph(m_sceneRoot);
        m_panel_textArea.setLayout( new BorderLayout() );
        m_panel_textArea.setOpaque( false );
        m_panel_textArea.add("Center", m_canvas);
        
        // Create picker
        pick = new Pick(m_canvas,m_sceneRoot,rootTransformGroup);
    }
    
    private void createSceneGraph() {
        // Create the root of the branch graph
        m_sceneRoot = new BranchGroup();
        
        // Set the background color
        BoundingSphere boundingSphere =
                new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        m_background = new Background();
        m_background.setApplicationBounds(boundingSphere);
        m_background.setColor(new Color3f(DEFAULT_BG_COLOR));
        m_background.setCapability(Background.ALLOW_COLOR_WRITE);
        m_sceneRoot.addChild(m_background);
        
        Transform3D transform3d = new Transform3D();
        transform3d.setTranslation(new Vector3f(ConfigurationManager.DEFAULT_X,ConfigurationManager.DEFAULT_Y,ConfigurationManager.DEFAULT_Z));
        rootTransformGroup = new TransformGroup();
        
        rootTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE); // allow the mouse behavior to rotate the scene
        rootTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        rootTransformGroup.setTransform(transform3d);
        
        rootTransformGroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        rootTransformGroup.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
        rootTransformGroup.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
        
        m_sceneRoot.addChild( rootTransformGroup );  // this is the local origin  - everyone hangs off this - moving this move every one
        
        startPowerReader("default.txt");
        Speech.speak("Hello and welcome to Power Reader, by Christopher Leung, Rubaiz Virk, and Zhan Shi.  To begin, please click the Open File button to your right.");
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        m_panel_textArea = new javax.swing.JPanel();
        m_panel_controlArea = new javax.swing.JPanel();
        m_slider_readSpeed = new javax.swing.JSlider();
        m_label_readSpeed = new javax.swing.JLabel();
        m_button_play = new javax.swing.JButton();
        m_buton_stop = new javax.swing.JButton();
        m_slider_lod = new javax.swing.JSlider();
        m_label_lod = new javax.swing.JLabel();
        m_slider_zoomLevel = new javax.swing.JSlider();
        m_label_zoomLevel = new javax.swing.JLabel();
        m_button_bgColor = new javax.swing.JButton();
        m_label_hlColor = new javax.swing.JLabel();
        m_button_hlColor = new javax.swing.JButton();
        m_checkBox_showImages = new javax.swing.JCheckBox();
        m_checkBox_wordsGrow = new javax.swing.JCheckBox();
        m_checkBox_speechEnabled = new javax.swing.JCheckBox();
        m_button_fgColor = new javax.swing.JButton();
        m_label_bgColor = new javax.swing.JLabel();
        m_label_fgColor = new javax.swing.JLabel();
        m_button_open = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Power Reader Alpha");
        //Test
        org.jdesktop.layout.GroupLayout m_panel_textAreaLayout = new org.jdesktop.layout.GroupLayout(m_panel_textArea);
        m_panel_textArea.setLayout(m_panel_textAreaLayout);
        m_panel_textAreaLayout.setHorizontalGroup(
            m_panel_textAreaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 509, Short.MAX_VALUE)
        );
        m_panel_textAreaLayout.setVerticalGroup(
            m_panel_textAreaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 573, Short.MAX_VALUE)
        );

        m_slider_readSpeed.setMajorTickSpacing(50);
        m_slider_readSpeed.setMaximum(1350);
        m_slider_readSpeed.setMinimum(250);
        m_slider_readSpeed.setPaintTicks(true);
        m_slider_readSpeed.setSnapToTicks(true);
        m_slider_readSpeed.setValue(800);
        m_slider_readSpeed.setName("Read Speed");
        m_slider_readSpeed.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                m_slider_readSpeedStateChanged(evt);
            }
        });

        m_label_readSpeed.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m_label_readSpeed.setText("<--Faster     Read Speed    Slowert-->");

        m_button_play.setText("Play");
        m_button_play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_button_playActionPerformed(evt);
            }
        });

        m_buton_stop.setText("Stop");
        m_buton_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_buton_stopActionPerformed(evt);
            }
        });

        m_slider_lod.setMajorTickSpacing(1);
        m_slider_lod.setMaximum(4);
        m_slider_lod.setMinimum(1);
        m_slider_lod.setPaintTicks(true);
        m_slider_lod.setSnapToTicks(true);

        m_label_lod.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m_label_lod.setText("<--Low   Level of Detail   High-->");

        m_slider_zoomLevel.setMajorTickSpacing(1);

        m_label_zoomLevel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m_label_zoomLevel.setText("<--Low     Zoom Level     High-->");

        m_button_bgColor.setBackground(new java.awt.Color(255, 153, 0));
        m_button_bgColor.setText("Background Color");
        m_button_bgColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_button_bgColorActionPerformed(evt);
            }
        });

        m_label_hlColor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m_label_hlColor.setText("Highlight Color");

        m_button_hlColor.setBackground(new java.awt.Color(255, 0, 0));
        m_button_hlColor.setText("Highlight Color");
        m_button_hlColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_button_hlColorActionPerformed(evt);
            }
        });

        m_checkBox_showImages.setText("Show images");
        m_checkBox_showImages.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        m_checkBox_showImages.setMargin(new java.awt.Insets(0, 0, 0, 0));

        m_checkBox_wordsGrow.setText("Words grow as they are read");
        m_checkBox_wordsGrow.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        m_checkBox_wordsGrow.setMargin(new java.awt.Insets(0, 0, 0, 0));
        m_checkBox_wordsGrow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_checkBox_wordsGrowActionPerformed(evt);
            }
        });

        m_checkBox_speechEnabled.setSelected(true);
        m_checkBox_speechEnabled.setText("Audible speech enabled");
        m_checkBox_speechEnabled.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        m_checkBox_speechEnabled.setMargin(new java.awt.Insets(0, 0, 0, 0));
        m_checkBox_speechEnabled.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_checkBox_speechEnabledActionPerformed(evt);
            }
        });

        m_button_fgColor.setBackground(new java.awt.Color(0, 0, 204));
        m_button_fgColor.setText("Foreground Color");
        m_button_fgColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_button_fgColorActionPerformed(evt);
            }
        });

        m_label_bgColor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m_label_bgColor.setText("Background Color");

        m_label_fgColor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m_label_fgColor.setText("Foreground Color");

        m_button_open.setText("Open File");
        m_button_open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_button_openActionPerformed(evt);
            }
        });

        jButton1.setText("Options");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout m_panel_controlAreaLayout = new org.jdesktop.layout.GroupLayout(m_panel_controlArea);
        m_panel_controlArea.setLayout(m_panel_controlAreaLayout);
        m_panel_controlAreaLayout.setHorizontalGroup(
            m_panel_controlAreaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(m_panel_controlAreaLayout.createSequentialGroup()
                .addContainerGap()
                .add(m_panel_controlAreaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, m_button_open, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                    .add(m_slider_zoomLevel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, m_label_readSpeed, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                    .add(m_panel_controlAreaLayout.createSequentialGroup()
                        .add(m_button_play, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(m_buton_stop, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 129, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(m_panel_controlAreaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(m_slider_readSpeed, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(m_slider_lod, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(m_label_lod, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, m_label_zoomLevel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 260, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(m_button_bgColor, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                        .add(m_label_hlColor, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                        .add(m_button_hlColor, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                        .add(m_checkBox_showImages)
                        .add(m_checkBox_wordsGrow)
                        .add(m_checkBox_speechEnabled)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, m_button_fgColor, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(m_label_bgColor, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                        .add(m_label_fgColor)))
                .addContainerGap())
        );
        m_panel_controlAreaLayout.setVerticalGroup(
            m_panel_controlAreaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(m_panel_controlAreaLayout.createSequentialGroup()
                .addContainerGap()
                .add(jButton1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(m_button_open)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(m_panel_controlAreaLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(m_buton_stop)
                    .add(m_button_play))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(m_label_readSpeed, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(m_slider_readSpeed, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(m_label_zoomLevel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(m_slider_zoomLevel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(m_label_lod, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(m_slider_lod, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(m_label_fgColor)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(m_button_fgColor)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(m_label_bgColor)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(m_button_bgColor)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(m_label_hlColor)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(m_button_hlColor)
                .add(23, 23, 23)
                .add(m_checkBox_showImages)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(m_checkBox_wordsGrow)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(m_checkBox_speechEnabled)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(m_panel_textArea, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(1, 1, 1)
                .add(m_panel_controlArea, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(m_panel_textArea, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .add(m_panel_controlArea, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(11, 11, 11))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void m_checkBox_wordsGrowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_checkBox_wordsGrowActionPerformed
        ConfigurationManager.toggleWordsGrow();
    }//GEN-LAST:event_m_checkBox_wordsGrowActionPerformed

    private void m_checkBox_speechEnabledActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_checkBox_speechEnabledActionPerformed
        ConfigurationManager.toggleAudibleSpeech();
    }//GEN-LAST:event_m_checkBox_speechEnabledActionPerformed
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        m_configPanel.setVisible(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void m_slider_readSpeedStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_m_slider_readSpeedStateChanged
        Player.setSleepDelay(m_slider_readSpeed.getValue());
    }//GEN-LAST:event_m_slider_readSpeedStateChanged
    
    private void m_buton_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_buton_stopActionPerformed
        Player.pause();
    }//GEN-LAST:event_m_buton_stopActionPerformed
    
    private void m_button_openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_button_openActionPerformed
        opd.setLocation(getLocationOnScreen().x + (getWidth() - opd.getWidth()) / 2, getLocationOnScreen().y + (getHeight() - opd.getHeight()) / 2);
        opd.setVisible(true);
        
        if (opd.getStatus() == OpenPageDialog.APPROVE_OPTION) {
            startPowerReader(opd.getPage());
        }
    }//GEN-LAST:event_m_button_openActionPerformed
    
    private void startPowerReader(String file) {
        rawTextParser = new RawTextParser(file);
        
        //reset the scene and memmory
        rootTransformGroup.removeAllChildren();
        TextObject3d.resetLocation();
        WordHashMap.getInstance().clearMap();
        
        // Parse
        rawTextParser.parse();
        
        // Returns the document level hierarchy object
        m_hierarchyRoot = rawTextParser.getHierarchyRoot();
        
        rootTransformGroup.addChild(m_hierarchyRoot.getBranchGroup());
        
        Player.reset();
        Player.setHierarchyRoot(m_hierarchyRoot);
        Player.setFocusLevel(RawTextParser.LEVEL_WORD_ID);
        
    }
    
    private void m_button_hlColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_button_hlColorActionPerformed
        Color c = m_button_hlColor.getBackground();
        // Bring up color selector
        c = JColorChooser.showDialog(((Component)evt.getSource()).getParent(), "Highlight Color", c);
        
        if(c != null) {
            // Assign result to the button
            m_button_hlColor.setBackground(c);
            
            // Assign to text object
            TextObject3d.setHighlightColor(new Color3f(c));
            
            // Recolor only the focused item
            Player.getFocusOn().color(true);
        }
    }//GEN-LAST:event_m_button_hlColorActionPerformed
    
    private void m_button_bgColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_button_bgColorActionPerformed
        Color c = m_button_bgColor.getBackground();
        // Bring up color selector
        c = JColorChooser.showDialog(((Component)evt.getSource()).getParent(), "Choose Background Color", c);
        
        if(c != null) {
            // Assign result to the button
            m_button_bgColor.setBackground(c);
            
            // Assign result to background
            m_background.setColor(new Color3f(c));
        }
    }//GEN-LAST:event_m_button_bgColorActionPerformed
    
    private void m_button_fgColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_button_fgColorActionPerformed
        
        Color c = m_button_fgColor.getBackground();
        // Bring up color selector
        c = JColorChooser.showDialog(((Component)evt.getSource()).getParent(), "Choose Foreground Color", c);
        
        if (c != null) {
            
            // Assign result to the button
            m_button_fgColor.setBackground(c);
            
            // Assign result to foreground
            TextObject3d.setBaseColor(new Color3f(c));
            
            // Color all the foreground
            m_hierarchyRoot.color(false);
            
            // Rehighlight the focused
            Player.getFocusOn().color(true);
        }
    }//GEN-LAST:event_m_button_fgColorActionPerformed
    
    private void m_button_playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_button_playActionPerformed
        Player.play();
    }//GEN-LAST:event_m_button_playActionPerformed
        
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton m_buton_stop;
    private javax.swing.JButton m_button_bgColor;
    private javax.swing.JButton m_button_fgColor;
    private javax.swing.JButton m_button_hlColor;
    private javax.swing.JButton m_button_open;
    private javax.swing.JButton m_button_play;
    private javax.swing.JCheckBox m_checkBox_showImages;
    private javax.swing.JCheckBox m_checkBox_speechEnabled;
    private javax.swing.JCheckBox m_checkBox_wordsGrow;
    private javax.swing.JLabel m_label_bgColor;
    private javax.swing.JLabel m_label_fgColor;
    private javax.swing.JLabel m_label_hlColor;
    private javax.swing.JLabel m_label_lod;
    private javax.swing.JLabel m_label_readSpeed;
    private javax.swing.JLabel m_label_zoomLevel;
    private javax.swing.JPanel m_panel_controlArea;
    private javax.swing.JPanel m_panel_textArea;
    private javax.swing.JSlider m_slider_lod;
    private javax.swing.JSlider m_slider_readSpeed;
    private javax.swing.JSlider m_slider_zoomLevel;
    // End of variables declaration//GEN-END:variables
    
    
}
