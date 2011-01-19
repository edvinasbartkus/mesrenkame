package org.geeks.elect

class Elections {
  String name
  String year
	ElectionsType type
	List districts
	List candidates
	static hasMany = [ candidates:Candidate, districts:District ]
}
