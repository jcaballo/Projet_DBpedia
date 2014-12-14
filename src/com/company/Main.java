package com.company;

import org.openrdf.query.*;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.http.HTTPRepository;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws RepositoryException, MalformedQueryException, QueryEvaluationException {

        JFrame frame = new JFrame("Dbpedia");
        frame.setContentPane(new Dbpedia().getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}

