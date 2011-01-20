package org.geeks.elect.crawl

import mesrenkame.ElectionsService
import org.apache.commons.logging.LogFactory
import org.geeks.elect.District
import org.geeks.elect.Elections
import org.geeks.elect.Party
import org.geeks.elect.Person

class CrawlMunicipalityElections {
  private static final log = LogFactory.getLog(this)
  private static final ElectionsService electionsService = new ElectionsService()
  private Elections elections
  private District currentDistrict
  private Party currentParty

  def url = "http://www.vrk.lt/rinkimai/3/Kandidatai/"

  def crawl2007() {
    elections = electionsService.findOrCreateElections("2007 m. vasario 25 d. savivaldybių tarybų rinkimai", "2007-02-25")
    crawl2007Districts().each {
      def name = it[0]
      def link = it[1]

      District district = electionsService.findOrCreateDistrict(name)
      currentDistrict = district
      crawl2007Parties(elections, district, link)
    }

  }

  def crawl2007Districts() {
    def doc = new GetPage(url:"${this.url}index.html").parse()

    def urls = []
    doc.depthFirst().findAll {
      it.name() == "a" && it.@href =~ /DalyvaujanciosPartijos.html/
    }.each {
      log.debug "Found district ${it.text()} with link to ${it.@href.toString()}"
      urls << [ it.text(), it.@href.toString() ]
    }

    return urls
  }
  def crawl2007Parties(Elections elections, District district, String url) {
    def doc = new GetPage(url:this.url+url).parse()
    doc.depthFirst().findAll {
      it.name() == "a" && it.@href =~ /Kandidatai.html/
    }.each {
      def partyName = it.text()
      def partyUrl = it.@href.toString()

      log.debug "Found ${partyName} participating in ${elections.name}"
      Party party = electionsService.findOrCreateParty(partyName)
      this.currentParty = party
      electionsService.findOrCreatePartyParticipation(elections, party)


      crawl2007Candidates(party, partyUrl)
    }
  }

  def crawl2007Candidates(Party party, String url) {
    def doc = new GetPage(url:this.url+"Apygardos/"+url).parse()
    doc.depthFirst().findAll {
      it.name() == "a" && it.@href =~ /Anketa.html/
    }.each {
      def pattern = ~/^(\d+) (.*)/
      log.debug "We will parse firstname and lastname from '${it.parent().text().trim()}'"
      def result = pattern.matcher(it.parent().text().trim())
      log.debug "Parse result was ${result[0]}"

      def number = result[0][1]
      def (firstname,lastname) = result[0][2].replaceAll(/[^a-zA-Z]/,' ').split(' ')
      def personUrl = it.@href as String

      Person person = crawl2007Candidate(number as Integer, firstname, lastname, personUrl)
//      electionsService.findOrCreateCandidate(elections, person, this.currentParty, currentDistrict, number as Integer)
    }
  }

  Person crawl2007Candidate(Integer number, String firstname, String lastname, String url) {
    def doc = new GetPage(url:this.url + "Kandidatai/" + url).parse()
    doc.depthFirst().findAll {
      it.name() == "table" && it.@class == "partydata"
    }[1].tbody.tr.td.b.each {
      println it.text()
    }

    Person person = electionsService.findOrCreatePerson(firstname, lastname, "")
    return person
    // def doc = new GetPage(url:url).parse()
  }
}
