Feature: Manage Owners

    Feature Description: add owner, edit existing owner, add pets to an owner, add vists

    Scenario: new owner is added to system
    Given they see the add owner section
    When they submit "Bri Cat Lover" as a new owner
    Then they see the "Bri" profile

    Scenario: new pet is associated to owner
    Scenario: pet profile is updated
    Scenario: a new visit is added
    Scenario: owner profile is updated
    Scenario: Duplicate owner is added
