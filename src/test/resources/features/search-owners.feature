Feature: search owners

    Feature Description: Search owners by name

    Scenario: See an Owner with Multiple Pets
        Given they see the find owners section
        When they search for "Rodriquez"
        Then they see the results for a "Rodriquez Search with Two Dogs"

    Scenario: See a list of Owners with the last name Davis
        Given they see the find owners section
        When they search for "Davis"
        Then they see the results for a "Davis Search"

    Scenario: See an error message for invalid owner last name
        Given they see the
    Scenario: See a list of all owners
