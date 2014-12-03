package com.company;

import org.openrdf.query.*;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.http.HTTPRepository;

public class Main {

    public static void main(String[] args) throws RepositoryException, MalformedQueryException, QueryEvaluationException {

        HTTPRepository repo = new HTTPRepository("http://dbpedia.org/sparql", "");
        RepositoryConnection connection = repo.getConnection();

        TupleQuery query= connection.prepareTupleQuery(QueryLanguage.SPARQL, "select distinct ?Concept wher {?x a ?Concept} LIMIT 10");
        TupleQueryResult result=query.evaluate();
        while (result.hasNext()) {
            BindingSet bindset = result.next();
            System.out.println(bindset);
            System.out.println("Concept :");
            System.out.println(bindset.getValue("Concept"));
        }

    }

}

