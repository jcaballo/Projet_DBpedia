package com.company;

import org.openrdf.query.*;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.http.HTTPRepository;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws RepositoryException, MalformedQueryException, QueryEvaluationException {

        HTTPRepository repo = new HTTPRepository("http://fr.dbpedia.org/sparql", "");
        RepositoryConnection connection = repo.getConnection();

        TupleQuery query= connection.prepareTupleQuery(QueryLanguage.SPARQL, "select distinct * where {\n" +
                "?s rdfs:label \"Victor Hugo\"@fr.\n" +
                "?s prop-fr:décès ?deces .\n" +
                "?s prop-fr:dateDeNaissance ?naissance\n" +
                "} LIMIT 100");
        TupleQueryResult result=query.evaluate();
        while (result.hasNext()) {
            BindingSet bindset = result.next();
            //System.out.println(bindset);
            System.out.println("ICI :");
            System.out.println("From : " + bindset.getValue("naissance") + " to " + bindset.getValue("deces"));
        }

        /*query = connection.prepareTupleQuery(QueryLanguage.SPARQL, "Select ?x ?foafName WHERE {?x <http://xmlns.com/foaf/0.1/name> ?foafName} LIMIT 10");
        result=query.evaluate();
        while(result.hasNext()) {
            BindingSet bindset = result.next();
            System.out.println(bindset.getValue("foafName"));
        }*/
        connection.close();

        JFrame frame = new JFrame("Dbpedia");
        frame.setContentPane(new Dbpedia().getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }

}

