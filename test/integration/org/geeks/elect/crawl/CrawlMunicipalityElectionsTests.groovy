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

    void testCrawl2007() {
      new CrawlMunicipalityElections().crawl2007()
      assert District.count() == 60
      assert Candidate.count() > 0
      assert Person.count() > 0
    }
}
