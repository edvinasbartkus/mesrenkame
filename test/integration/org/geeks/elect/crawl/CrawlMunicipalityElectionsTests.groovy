package org.geeks.elect.crawl

import grails.test.*
import org.geeks.elect.District
import org.geeks.elect.Candidate
import org.geeks.elect.Person

class CrawlMunicipalityElectionsTests extends GroovyTestCase {
  protected void setUp() {
    super.setUp()
  }

  protected void tearDown() {
    super.tearDown()
  }
  // Deprecated because it runs the whole process of crawl
/*  void testCrawl2007() {
    new CrawlMunicipalityElections().crawl2007()
    assert District.count() == 60
    assert Candidate.count() > 0
    assert Person.count() > 0
  }*/

  void testCrawlCandidate2007() {
    def url = "../../Kandidatai/Kandidatas994/Kandidato994Anketa.html"
    def person = new CrawlMunicipalityElections().crawl2007Candidate(1, "Edvinas", "Bartkus", url)

    assert person.birthPlace == "Akmenės rajonas"
    assert person.birthdate == "1953-04-30"
    assert person.currentTown == "Venta"
  }
}
