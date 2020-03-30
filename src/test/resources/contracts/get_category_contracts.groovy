package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method GET()
        url '/categories'
        headers {
            accept "application/hal+json"
        }
    }
    response {
        status OK()
        body([
                '$._embedded.categories[0].name': 'foo',
                '$._embedded.categories[1].name': 'bar',
                '$._embedded.categories[2].name': 'baz'
        ])
        headers {
            contentType "application/hal+json"
        }
    }
}