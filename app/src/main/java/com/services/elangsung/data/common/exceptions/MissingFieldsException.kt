package com.services.elangsung.data.common.exceptions

class MissingFieldsException(missingFields: List<String>) :
    Exception("Some fields are missing: $missingFields")
