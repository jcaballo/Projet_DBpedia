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

        TupleQuery query= connection.prepareTupleQuery(QueryLanguage.SPARQL, "select distinct ?Concept where {?x a ?Concept} LIMIT 10");
        TupleQueryResult result=query.evaluate();
        while (result.hasNext()) {
            BindingSet bindset = result.next();
            System.out.println(bindset);
            System.out.println("Concept :");
            System.out.println(bindset.getValue("Concept"));
        }

        query = connection.prepareTupleQuery(QueryLanguage.SPARQL, "Select ?x ?foafName WHERE {?x <http://xmlns.com/foaf/0.1/name> ?foafName} LIMIT 10");
        result=query.evaluate();
        while(result.hasNext()) {
            BindingSet bindset = result.next();
            System.out.println(bindset.getValue("foafName"));
        }
        connection.close();

    }

}

