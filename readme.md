API Documentation

person (GET) = Returns a list of all people within the datasource
person/{id}/campaign (GET) = returns a list of all people within the datasource who belong to a specific campaign,  via id
person/{id} (GET) = returns a person with a specific id
person (POST) = adds a person to datasource, person is specified in body of request, name must not be blank
person/{id} (PUT) = updates a person in the datasource, person id must be valid, person is specificed in body of request, name must not be blank
person/{id} (DELETE) = removes a person from the datasource, person id must be valid