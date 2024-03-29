

package boundary;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import store.POJO.Condominio;
import datatype.Path;
import datatype.list.Condomini;
import enumeration.StatiAmICo;
import executor.GestoreCondomini;
import extras.ImagePanel;

/**
 *
 * @author thewally
 */
@SuppressWarnings("serial")
public class AmICo extends javax.swing.JFrame implements BaseBoundary {

    public static AmICo amico;
    
	public static AmICo getInstance(){
		if (amico==null)
			amico=new AmICo();
		return amico;
	}

    private Condomini condomini;
	private StatiAmICo state;


    /** Creates new form AmICo */
    private AmICo() {
        initComponents();
    }

    public void apriCondominio(Condominio condominio){
    	state=StatiAmICo.inserimentoCondominio;
		GestoreCondomini.getInstance().apriCondominio(condominio);
		setVisible(false);
	}

	public void  inserisciCondominio() {
		GestoreCondomini.getInstance().inserisciCondominio();
		state=StatiAmICo.inserimentoCondominio;
		setVisible(false);
	}

	public void  importaCondominio() {
		state = StatiAmICo.importazioneCondominio;
		setVisible(false);
	}

	public void aggiornaCondomini(Condomini condomini){
		this.condomini=condomini;

		DefaultListModel listModel = new DefaultListModel();

		for (Condominio c : condomini.getCondomini())
			listModel.addElement( c.getDatiC().getId() );

		lista.setModel(listModel);
		setTitle("AmICo");
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void selezioneFile(Path path) {
		state=StatiAmICo.selezionePath;
	}

	public void esciDaAmICo(){
		GestoreCondomini.getInstance().esciDaAmico();
		this.dispose();
	}

    public void ammissibile(Boolean b) {
		// TODO Auto-generated method stub
		
	}

	public void annulla() {
		// TODO Auto-generated method stub
		
	}


	public void fallito() {
		switch (state) {
		case selezionePath:
			break;
		case inserimentoCondominio:
		default:
			break;
		}
		state=StatiAmICo.base;
	}

	public void fatto() {

		switch (state) {
		case condominioAperto:
			break;
		case inserimentoCondominio:
			
			setVisible(false);
			break;
		case selezionePath:
			state=StatiAmICo.condominioAperto;
			return;
		default:setVisible(false);
			break;
		}
		state=StatiAmICo.base;
	}

	public void finito() {
		// TODO Auto-generated method stub

	}

	public void ko() {
		// TODO Auto-generated method stub

	}

	public void ok() {
		// TODO Auto-generated method stub

	}


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        bEsci = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista = new javax.swing.JList();
        logoPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        binserisci = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        bapri = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bEsci.setText("Esci");
        bEsci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               bEsciActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 2, 24));
        jLabel1.setText("AmICo");

        jLabel2.setText("Condomini");

        lista.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lista);

        logoImage = new ImagePanel();
        logoImage.setImage("images/house_logo.png");
        logoPanel.setLayout(new javax.swing.BoxLayout(logoPanel, javax.swing.BoxLayout.LINE_AXIS));
        logoPanel.add(logoImage);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 83, Short.MAX_VALUE)
        );

        jPanel2.setLayout(new java.awt.GridBagLayout());
        jPanel2.add(jLabel4, new java.awt.GridBagConstraints());

        binserisci.setText("Inserisci");
        binserisci.setToolTipText("Inserisci un nuvo condominio");
        binserisci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                binserisciActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        jPanel2.add(binserisci, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 14;
        jPanel2.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        jPanel2.add(jLabel5, gridBagConstraints);

        bapri.setText("Apri");
        bapri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bapriActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(bapri, gridBagConstraints);
        jPanel2.add(jLabel6, new java.awt.GridBagConstraints());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(logoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addComponent(jLabel2))
                        .addGap(0, 0, 0))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bEsci)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(logoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bEsci, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        
        this.addWindowListener(new WindowAdapter() {  
   		 @Override  
   		 public void windowClosing(WindowEvent we) {  
   			 esciDaAmICo();
   		 	}  
   		 });  
        
    }// </editor-fold>//GEN-END:initComponents
    
    protected void bEsciActionPerformed(ActionEvent evt) {
    	int c = JOptionPane.showConfirmDialog(this, "Uscire da AmICo?", "richiesta", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		
		if (c==0){
			esciDaAmICo();
		}
	}

	private void binserisciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_binserisciActionPerformed
        inserisciCondominio();
        setVisible(false);
    }//GEN-LAST:event_binserisciActionPerformed

    private void bapriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bapriActionPerformed
        if(lista.getSelectedIndex()>-1)
		{
			for (Condominio c : condomini.getCondomini())
			{
				if( c.getDatiC().getId().equals((String)lista.getSelectedValue() ) ) {
					apriCondominio(c);
					setVisible(false);
				}
			}
		}
		else JOptionPane.showMessageDialog(this, "devi selezionare un condominio");
    }//GEN-LAST:event_bapriActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bEsci;
    private javax.swing.JButton bapri;
    private javax.swing.JButton binserisci;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JList lista;
    private javax.swing.JPanel logoPanel;
    private ImagePanel logoImage;
    // End of variables declaration//GEN-END:variables
}
