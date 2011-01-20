package mesrenkame

import org.geeks.elect.District
import org.geeks.elect.Elections
import org.geeks.elect.Person
import org.geeks.elect.Party
import org.geeks.elect.PartyParticipation
import org.geeks.elect.Candidate

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

  def findOrCreateElections(String name, String year) {
      def elections = Elections.findByName(name)
      if(!elections) {
        elections = new Elections(name:name)
        elections.year = year
        elections.save(flush:true)
        log.debug "Elections ${name} created with id=${elections.id}."
      } else {
        log.debug "Elections ${name} found with id=${elections.id}"
      }

      return elections
  }

  def findOrCreatePerson(String firstname,String lastname,String birthdate) {
    def person = Person.withCriteria {
      like("firstname",firstname)
      like("lastname",lastname)
      like("birthdate",birthdate)
    }

    if(!person) {
      person = new Person(firstname:firstname, lastname:lastname)
      person.birthdate = birthdate

      person.save(flush:true)
      log.debug "Person ${firstname} ${lastname} not found, therefor created one with id ${person.id}."
    } else {
      log.debug "Person ${firstname} ${lastname} found."
    }

    return person
  }

  def findOrCreateParty(name) {
    def party = Party.findByName(name)
    if(!party) {
      party = new Party(name:name)
      party.save(flush:true)
      log.debug "Party ${name} saved with id ${party.id}."
    } else {
      log.debug "Party ${name} found with id ${party.id}"
    }

    return party
  }
  def findOrCreatePartyParticipation(Elections elections, Party party) {
    def participation = PartyParticipation.findByElectionsAndParty(elections, party)
    if(!participation) {
      participation = new PartyParticipation(elections: elections, party: party)
      participation.save(flush:true)
      log.debug "Participation create for ${party.name} at elections ${elections.name}"
    } else {
      log.debug "Participation found for ${party.name} at ${elections.name}"
    }

    return participation
  }
  def findOrCreateCandidate(Elections elections, Person person, Party party, District district, Integer number) {
    def candidate = Candidate.withCriteria {
      eq("person",person)
      eq("party",party)
      eq("elections",elections)
      eq("district",district)
    }

    if(!candidate) {
      candidate = new Candidate(party:party, person:person, elections:elections, district:district, number:number)
      candidate.save(flush:true)
      log.debug "Candidate ${person.fullname} was created with ${candidate.id}"

    } else {
      log.debug "Candidate ${person.fullname} created ${candidate.id}"
    }

    candidate
  }
}
