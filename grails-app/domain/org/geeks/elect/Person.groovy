package org.geeks.elect

class Person {
  String firstname
  String lastname

  String birthdate

  String currentTown
  String birthPlace
  String nationality

  List diplomas
  List declarations
  static hasMany = [ diplomas:Diploma, declarations:Declaration ]

  def getFullname() {
    return "${firstname} ${lastname}"
  }
}
