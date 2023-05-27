Feature: Almosafer

  @API
  Scenario: check the currency list
    Given Hit the baseURL "https://www.almosafer.com"
    When run the GET method for the API endpoint "/api/system/currency/list"
    Then validate the base currency "SAR" and Equivalent "11" currencies are with "SAR" at the first.

  @API
  Scenario Outline: Post method for the chalets list
    Given Hit the baseURL "https://www.almosafer.com"
    When run the POST method for the API endpoint "/api/accommodation/property/search" for checkinDate "<checkinDate>" checkoutDate "<checkoutDate>"
    Then validate a hotel name "<chaletsname>" and min and max bathroom is "<minbathroom>" "<maxbathroom>" in filter


    Examples:
    |checkinDate|checkoutDate|chaletsname|minbathroom|maxbathroom|
    |2023-06-06|2023-06-07|Bright Chalet with Pool|0|9         |
