package com.luv2code.springdemo.rest;

public class API_Design {
//    1. review api requirements
//    2. Identify main resource/entity - based on most prominent noun (customers, products, etc). Convention is use plural
//    3. use HTTP methods to assign actions to the main resource/entity
//dont include action words in the endpoint - antipattern

    // GET /customers - list all customers
    // GET /customers/{id} - get a single customer
    // POST /customers - create a new customer            - post and put will take customer data as json in request msg body
    // PUT /customers/ + {id} ?  - update a customer      -""     ""     ""    ""    ""      ""
    // DELETE /customers/{id} - delete a customer

/*
    GitHub Repo API      https://developer.github.com/v3/repos/#repositories
    delete a repo    DELETE /repos/:owner/:repo    {owner} as the username, {repo} is the repo name inplace of :repo
     List your repos   GET /user/repos
    Get a specific repo   GET /repos/:owner/:repo
     */

    /* SalesForce API   - industries REST API   https://developer.salesforce.com/docs/atlas.en-us.api_rest.meta/api_rest/intro.htm
    https://sforce.co/2J40ALH
    retrieve all individuals    GET /services/apexrest/v1/individual
    retrieve a specific individual    GET /services/apexrest/v1/individual/{id}
    create a new individual    POST /services/apexrest/clinic01/v1/individual
    update an individual    PUT /services/apexrest/clinic01/v1/individual/{id}
//    they dont use plural form "individuals"
     */


}
