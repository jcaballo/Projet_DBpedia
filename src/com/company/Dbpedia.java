package com.company;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.openrdf.query.*;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.http.HTTPRepository;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by bob on 08/12/14.
 */
public class Dbpedia {

    private JTextField itemTypeText;
    private JList itemTypeList;
    private JTextField bornInText;
    private JList bornInList;
    private JTextField nameText;
    private JList nameList;
    private JTextField freeSearchText;
    private JButton searchButton;
    private JList searchList;
    private JPanel panel;
    private JTextField bornInYearText;
    private JList bornInYearList;

    private DefaultListModel listModel;
    private ArrayList<ArrayList<String>> infosList;

    public Dbpedia() {

        infosList = new ArrayList<ArrayList<String>>();

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                listModel = new DefaultListModel();

                try {
                    HTTPRepository repo = new HTTPRepository("http://dbpedia.org/sparql", "");
                    RepositoryConnection connection = repo.getConnection();

                    TupleQuery query = connection.prepareTupleQuery(QueryLanguage.SPARQL, "SELECT DISTINCT ?surname ?givenname ?ab ?date ?img\n" +
                            "                WHERE {\n" +
                            "                    ?person dbpedia-owl:birthPlace <http://dbpedia.org/resource/" + bornInText.getText().toString() + ">. \n" +
                            "                    ?person foaf:givenName ?givenname.\n" +
                            "                            ?person foaf:surname ?surname.\n" +
                            "                            ?person dbpedia-owl:abstract ?ab.\n" +
                            "                            ?person dbpedia-owl:birthDate ?date.\n" +
                            "                            ?person dbpedia-owl:thumbnail ?img\n" +
                            "                    FILTER(REGEX(?date, \"" + bornInYearText.getText().toString() + "\"))\n" +
                            "                } ORDER BY ?surname ?givenname LIMIT 1000");
                    TupleQueryResult result = query.evaluate();
                    while (result.hasNext()) {
                        BindingSet bindset = result.next();
                        System.out.println(bindset);
                        System.out.println("Concept :");
                        listModel.addElement(bindset.getValue("surname") + "/" + bindset.getValue("givenname"));
                        ArrayList<String> bob = new ArrayList<String>();
                        bob.add(bindset.getValue("surname") + "");
                        bob.add(bindset.getValue("givenname") + "");
                        bob.add(bindset.getValue("ab") + "");
                        bob.add(bindset.getValue("date") + "");
                        bob.add(bindset.getValue("img") + "");
                        infosList.add(bob);

                    }
                } catch (RepositoryException e1) {
                    e1.printStackTrace();
                } catch (MalformedQueryException e1) {
                    e1.printStackTrace();
                } catch (QueryEvaluationException e1) {
                    e1.printStackTrace();
                }

                searchList.setModel(listModel);
                searchList.addListSelectionListener(new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent evt) {
                        if (evt.getValueIsAdjusting())
                            return;
                        System.out.println("Selected from " + evt.getFirstIndex() + " to " + evt.getLastIndex());

                        infoDialog dialog = new infoDialog(infosList.get(searchList.getSelectedIndex()));
                        dialog.pack();
                        dialog.setVisible(true);
                    }
                });
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    public JTextField getItemTypeText() {
        return itemTypeText;
    }

    public void setItemTypeText(JTextField itemTypeText) {
        this.itemTypeText = itemTypeText;
    }

    public JList getItemTypeList() {
        return itemTypeList;
    }

    public void setItemTypeList(JList itemTypeList) {
        this.itemTypeList = itemTypeList;
    }

    public JTextField getBornInText() {
        return bornInText;
    }

    public void setBornInText(JTextField bornInText) {
        this.bornInText = bornInText;
    }

    public JList getBornInList() {
        return bornInList;
    }

    public void setBornInList(JList bornInList) {
        this.bornInList = bornInList;
    }

    public JTextField getNameText() {
        return nameText;
    }

    public void setNameText(JTextField nameText) {
        this.nameText = nameText;
    }

    public JList getNameList() {
        return nameList;
    }

    public void setNameList(JList nameList) {
        this.nameList = nameList;
    }

    public JTextField getFreeSearchText() {
        return freeSearchText;
    }

    public void setFreeSearchText(JTextField freeSearchText) {
        this.freeSearchText = freeSearchText;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public void setSearchButton(JButton searchButton) {
        this.searchButton = searchButton;
    }

    public JList getSearchList() {
        return searchList;
    }

    public void setSearchList(JList searchList) {
        this.searchList = searchList;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public DefaultListModel getListModel() {
        return listModel;
    }

    public void setListModel(DefaultListModel listModel) {
        this.listModel = listModel;
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(12, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Item type");
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        itemTypeText = new JTextField();
        itemTypeText.setText("");
        panel1.add(itemTypeText, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Born in");
        panel1.add(label2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bornInText = new JTextField();
        bornInText.setText("");
        panel1.add(bornInText, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Name");
        panel1.add(label3, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameText = new JTextField();
        nameText.setText("");
        panel1.add(nameText, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        itemTypeList = new JList();
        scrollPane1.setViewportView(itemTypeList);
        final JScrollPane scrollPane2 = new JScrollPane();
        panel1.add(scrollPane2, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        bornInList = new JList();
        scrollPane2.setViewportView(bornInList);
        final JScrollPane scrollPane3 = new JScrollPane();
        panel1.add(scrollPane3, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        nameList = new JList();
        scrollPane3.setViewportView(nameList);
        final JLabel label4 = new JLabel();
        label4.setText("Born in year");
        panel1.add(label4, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bornInYearText = new JTextField();
        bornInYearText.setText("");
        panel1.add(bornInYearText, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JScrollPane scrollPane4 = new JScrollPane();
        panel1.add(scrollPane4, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        bornInYearList = new JList();
        scrollPane4.setViewportView(bornInYearList);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Free search");
        panel3.add(label5, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        freeSearchText = new JTextField();
        freeSearchText.setText("");
        panel3.add(freeSearchText, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        searchButton = new JButton();
        searchButton.setText("Search");
        panel3.add(searchButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane5 = new JScrollPane();
        panel2.add(scrollPane5, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        searchList = new JList();
        scrollPane5.setViewportView(searchList);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
