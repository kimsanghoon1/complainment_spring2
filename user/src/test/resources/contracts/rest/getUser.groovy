package contracts.rest

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url ('/users/1')
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
        body(
                id: 1,
        )
        bodyMatchers {
            jsonPath('$.residentNumber', byRegex(nonEmpty()).asString())
        }
        headers {
            contentType(applicationJson())
        }
    }
}
