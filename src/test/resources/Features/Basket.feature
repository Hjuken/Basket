Feature: Register a supporter account on basketball England

  Scenario Outline: Registration
    Given I am using browser "<browser>"
    And I am on the registration page
    And I enter date of birth "<date>"
    And I enter first name "<firstName>"
    And I enter last name "<lastName>"
    And I enter email and confirms email
    And I enter password "<PW>" and confirms password "<confirmPW>"
    And I <acceptTerms> accept Terms and conditions
    And I <confirmAge> confirm that I am over 18
    And I <acceptEthics> accept the Code of ethics and conduct
    When I click on Confirm and join
    Then I see the result "<result>"

    Examples:
      | browser | date       | firstName | lastName | PW     | confirmPW | acceptTerms | confirmAge | acceptEthics | result                                                                    |
      | chrome  | 11/04/1986 | Dumbo     | Jansson  | hejhej | hejhej    | true        | true       | true         | THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND                 |
      | chrome  | 11/04/1986 | Dumbo     |          | hejhej | hejhej    | true        | true       | true         | Last Name is required                                                     |
      | chrome  | 11/04/1986 | Dumbo     | Jansson  | hejhej | DÅDÅDÅ    | true        | true       | true         | Password did not match                                                    |
      | chrome  | 11/04/1986 | Dumbo     | Jansson  | hejhej | hejhej    | false       | true       | true         | You must confirm that you have read and accepted our Terms and Conditions |
      | edge    | 11/04/1986 | Dumbo     | Jansson  | hejhej | hejhej    | true        | true       | true         | THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND                 |
      | edge    | 11/04/1986 | Dumbo     |          | hejhej | hejhej    | true        | true       | true         | Last Name is required                                                     |
      | edge    | 11/04/1986 | Dumbo     | Jansson  | hejhej | DÅDÅDÅ    | true        | true       | true         | Password did not match                                                    |
      | firefox | 11/04/1986 | Dumbo     | Jansson  | hejhej | hejhej    | false       | true       | true         | You must confirm that you have read and accepted our Terms and Conditions |

