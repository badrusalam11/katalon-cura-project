Feature: Make Appointment

  @appointment
  Scenario Outline: Title of your scenario outline
    Given I already logged in
    When I make appointment and fill all the fields with: <DataFile>
    Then I verify the text <successText> showed

    Examples: 
      | DataFile                            | successText                                                            |
      | Data Files/MakeAppointment_DataFile | Please be informed that your appointment has been booked as following: |
