package org.geeks.elect

class District {
  Integer numb
  String name
  String address
  String phone

  static mapping = {
    name index:true
  }
}
