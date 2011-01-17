package org.geeks.elect

class CachedPage {
  String url
  String content

  static mapWith = "redis"
  static constraints = {
    url unique:true
    content nullable:true
  }
  static mapping = {
    url index:true
  }
}
