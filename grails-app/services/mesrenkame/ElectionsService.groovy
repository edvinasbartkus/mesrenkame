package mesrenkame

import org.geeks.elect.District
import org.geeks.elect.Elections
import org.geeks.elect.Person

class ElectionsService {

  static transactional = false

  def findOrCreateDistrict(String name) {
    def district = District.findByName(name)
    if(!district) {
      district = new District(name:name)
      district.save(flush:true)
      log.debug "District not found. Saved!"
    } else {
      log.debug "District ${name} found."
    }
    return district
  }

	def findOrCreateElections(String name) {
		def elections = Elections.findByName(name)
		if(!elections) {
			elections = new Elections(name:name)
			elections.save(flush:true)
			log.debug "Elections ${name} created with id=${elections.id}."
		} else {
			log.debug "Elections ${name} found with id=${elections.id}"
		}

		return elections
	}

	def findOrCreatePerson(String firstname,String lastname,String birthdate) {
		def person = Person.findWithCriteria {
			like("firstname",firstname)
			like("lastname",lastname)
			if(birthdate.isNotEmpty())
				like("birthdate",birthdate)
		}

		if(person == null) {
			person = new Person(firstname:firstname, lastname:lastname)
			if(birthdate.isNotEmpty())
				person.birthdate = birthdate

			person.save(flush:true)
			log.debug "Person ${firstname} ${lastname} not found, therefor created one with id ${id}."
		} else {
			log.debug "Person ${firstname} ${lastname} found."
		}

		return person
	}
}
