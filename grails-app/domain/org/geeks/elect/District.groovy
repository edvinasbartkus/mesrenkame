package org.geeks.elect

class District {
  Integer numb
  String name
  String address
  String phone

  static constraints = {
  }
  static mapping = {
    name index:true
  }
}
