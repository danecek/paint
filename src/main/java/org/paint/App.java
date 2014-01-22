package org.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.Arrays;
import java.util.Observable;
import org.paint.model.AbstractElementFactory;
import org.paint.model.Element;
import org.paint.model.MyStroke;
import org.paint.resource.DAO;
import org.paint.resource.derby.DerbyDAO;
import org.paint.resource.MemoryDAO;
import org.paint.resource.MemoryDAOXML;
import org.paint.resource.hibernate.HibernateDAO;
import org.paint.view.MainFrame;

/**
 * Hello world!
 *
 */
public class App extends Observable {

    public static final Color XOR_COLOR = Color.RED;
    public static final BasicStroke BASIC_STROKE = new BasicStroke();
    public static final App INST = new App();

    private App() {
    }

    private MainFrame mainFrame;
    private boolean fill = false;
    private MyStroke myStroke = new MyStroke();
    private Color color = Color.BLACK;
    private Element selected;
    private boolean selectionMode;

    public static void main(String[] args) {
        ArgEnum argEnum = ArgEnum.sql;
        if (args.length > 0) {
            try {
                argEnum = ArgEnum.valueOf(args[0]);
                switch (argEnum) {
                    case sql:
                        DAO.INST = new DerbyDAO();
                        break;
                    case file:
                        break;
                    case xml:
                        DAO.INST = new MemoryDAOXML();
                        break;
                    case hiber:
                        DAO.INST = new HibernateDAO();
                        break;
                }

            } catch (IllegalArgumentException ex) {
                System.out.println("arguments: " + Arrays.toString(ArgEnum.values()));
                System.exit(0);
            }

        } else {
            DAO.INST = new MemoryDAO();
        }
        INST.setMainFrame(new MainFrame());
        INST.notifyObservers();
    }

    /**
     * @return the mainFrame
     */
    public MainFrame getMainFrame() {
        return mainFrame;
    }

    @Override
    public void notifyObservers() {
        setChanged();
        super.notifyObservers();
    }

    /**
     * @param mainFrame the mainFrame to set
     */
    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void exit() {
        DAO.INST.store();
        mainFrame.dispose();

    }

    /**
     * @return the fill
     */
    public MyStroke getActualStroke() {
        return getMyStroke();
    }

    /**
     * @param fill the fill to set
     */
    public void setFill(boolean fill) {
        this.fill = fill;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    public Color getXORColor() {
        return new Color(~color.getRGB());
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return the myStroke
     */
    public MyStroke getMyStroke() {
        return myStroke;
    }

    /**
     * @param myStroke the myStroke to set
     */
    public void setMyStroke(MyStroke myStroke) {
        this.myStroke = myStroke;
    }

    /**
     * @return the fill
     */
    public boolean isFill() {
        return fill;
    }

    /**
     * @return the selected
     */
    public Element getSelected() {
        return selected;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(Element selected) {
        this.selected = selected;
    }


    /**
     * @return the selectionMode
     */
    public boolean isSelectionMode() {
        return AbstractElementFactory.INST==null;
    }

}
